import api from "../../api/apiClient";
import { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { CartContext } from "../../context/CartContext";
import { AuthContext } from "../../context/AuthContext";
import { CheckoutContext } from "../../context/CheckoutContext";
import CartItem from "./CartItem";

function Cart() {
  const navigate = useNavigate();
  const { cartState } = useContext(CartContext);
  const { userInfo } = useContext(AuthContext);
  const { dispatch: checkoutDispatch } = useContext(CheckoutContext);
  const cartItems = cartState.cartItems;
  const [products, setProducts] = useState([]);
  const [selectedItems, setSelectedItems] = useState([]);
  const isLoggedIn = !!userInfo.token;

  // 장바구니 상품 정보 불러오기
  useEffect(() => {
    const fetchData = async () => {
      try {
        const productPromises = cartItems.map(item =>
          api.get(`/products/${item.id}`).then(response => ({
            ...response.data,
            quantity: item.quantity
          }))
        );

        const productResults = await Promise.all(productPromises);
        setProducts(productResults);
      } catch (error) {
        console.error("상품 정보 불러오기 실패", error);
      }
    };

    if (cartItems.length > 0) {
      fetchData();
    } else {
      setProducts([]);
    }
  }, [cartItems]);

  // 상품 선택 체크박스, CartItem 컴포넌트로 전달.
  const toggleSelectItem = (id) => {
    setSelectedItems(prev =>
      prev.includes(id)
        ? prev.filter(itemId => itemId !== id)
        : [...prev, id]
    );
  };

  const isAllSelected = products.length > 0 && selectedItems.length === products.length;

  const toggleSelectAll = () => {
    if (isAllSelected) {
      setSelectedItems([]);
    } else {
      setSelectedItems(products.map(product => product.id));
    }
  };

  const selectedProducts = products.filter(product => selectedItems.includes(product.id));
  const totalPrice = selectedProducts.reduce(
    (sum, product) => sum + product.price * product.quantity, 0
  );

  // 구매하기
  const handleCheckout = async () => {
    if (selectedProducts.length === 0) {
      alert("선택된 상품이 없습니다.");
      return;
    }

    // 비회원 일 경우
    if (!isLoggedIn) {
      alert("로그인 후 결제를 진행할 수 있습니다.");
      navigate("/login",{
        state: { 
          redirectTo: "/cart"
        }
      });
      return;
    }

    try {
      const response = await api.get("/cart");
      const items = response.data
        .filter(item => selectedItems.includes(item.productId))  // 선택된 것만
        .map(item => ({
          id: item.productId,
          quantity: item.quantity
        }));

      checkoutDispatch({ type: "SET_ITEMS", payload: items });
      navigate("/checkout");

    } catch (error) {
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-2xl font-bold mt-6 mb-6">장바구니</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="md:col-span-2 flex flex-col gap-6">
          <div className="border rounded-lg p-4 shadow-sm bg-white">
            {/* 전체 선택 */}
            <div className="flex items-center mb-4">
              <input
                type="checkbox"
                checked={isAllSelected}
                onChange={toggleSelectAll}
                className="mr-2 scale-125 accent-black"
              />
              <span className="font-semibold text-lg">전체 선택</span>
            </div>

            <hr className="mb-4 border-gray-300" />
            
            {/* 상품 목록 */}
            {products.length === 0 ? (
              <div className="text-center text-gray-500 py-10">
                장바구니에 담긴 상품이 없습니다.
              </div> 
              ) : (
                products.map(product => (
                <CartItem
                  key={product.id}
                  product={product}
                  isSelected={selectedItems.includes(product.id)}
                  onToggle={toggleSelectItem}
                />
              ))
            )}
          </div>
        </div>

        {/* 결제 영역 */}
        <div className="border rounded-lg p-4 h-fit flex flex-col justify-between bg-white shadow-sm">
          <div>
            <h2 className="font-semibold text-lg mb-2">구매 금액</h2>
            <div className="flex justify-between py-1">
              <span>상품 금액</span>
              <span>{totalPrice.toLocaleString()}원</span>
            </div>
            <div className="flex justify-between py-1 text-gray-700">
              <span>배송비</span>
              <span className="text-blue-600 font-bold">0원</span>
            </div>
            <div className="flex justify-between py-1 font-bold text-lg border-t mt-2 pt-2">
              <span>총 결제 금액</span>
              <span>{totalPrice.toLocaleString()}원</span>
            </div>
          </div>

          <button
            onClick={handleCheckout}
            disabled={selectedProducts.length === 0}
            className="mt-6 w-full bg-black text-white py-3 text-lg rounded hover:bg-gray-900 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {totalPrice.toLocaleString()}원 구매하기 ({selectedProducts.length}개)
          </button>
        </div>
      </div>
    </div>
  );
}

export default Cart;
