import api from "../../api/apiClient";
import { useState, useEffect, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { getDeliveryDate } from "../../utils/commUtils";
import { AuthContext } from "../../context/AuthContext";
import { CheckoutContext } from "../../context/CheckoutContext";

function Checkout() {
  const navigate = useNavigate();
  const { userInfo } = useContext(AuthContext);
  const { checkoutState } = useContext(CheckoutContext);
  const items = checkoutState.items;

  const [products, setProducts] = useState([]);
  const [shippingInfo, setShippingInfo] = useState({
    name: "",
    phone: "",
    address: ""
  });

  useEffect(() => {
    if (!userInfo?.token || checkoutState.items.length === 0) {
      navigate("/");
    }
  }, [userInfo?.token, checkoutState.items]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const resShippingInfo = await api.get(`/user/${userInfo.id}`);
        setShippingInfo(resShippingInfo.data);

        const productPromises = items.map(item =>
          api.get(`/products/${item.product_id}`)
          .then(response => ({
            ...response.data,
            quantity: item.quantity
          }))
        );

        const productResults = await Promise.all(productPromises);
        setProducts(productResults);
      } catch (error) {
        console.error("데이터 불러오기 실패", error);
      }
    };

    if (items.length > 0) {
      fetchData();
    } else {
      navigate("/");
    }
  }, [items, userInfo.id]);

  const totalPrice = products.reduce(
    (sum, p) => sum + p.price * p.quantity, 0
  );

  const handlePayment = async () => {
    const orderData = {
      userId: userInfo.id,
      orderItems: products.map(product => ({
        productId: product.id,
        quantity: product.quantity,
        orderPrice: product.price
      })),
      totalPrice: totalPrice
    };

    try{
      if(window.confirm("주문 하시겠습니까?")){
        const response = await api.post(`/order`, orderData);
        if(response.status === 200){
          const id = response.data.id;
          navigate(`/order-complete/${id}`);      
        }else{
          alert("주문에 실패하였습니다.");  
        }
      }
    } catch (error) {
        alert("주문에 실패하였습니다.");
    }

    
  };

  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-2xl font-bold mb-6">주문 / 결제</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {/* 좌측: 배송지 + 상품정보 */}
        <div className="md:col-span-2 flex flex-col gap-6">
          {/* 배송 정보 */}
          <div className="border rounded-lg p-4">
            <h2 className="font-semibold mb-2">배송지 정보</h2>
            <p className="mb-1">수령인: {shippingInfo.name}</p>
            <p className="mb-1">주소: {shippingInfo.address}</p>
            <p className="mb-1">연락처: {shippingInfo.phone}</p>
          </div>

          {/* 상품 정보 */}
          <div className="border rounded-lg p-4">
            <h2 className="font-semibold mb-4">주문 상품</h2>
            {products.map(product => (
              <div key={product.id} className="flex gap-6 items-center mb-6">
                <div className="flex-shrink-0 w-32 h-32">
                  <Link to={`/product-detail/${product.id}`}>
                    <img
                      src={
                        product.imagePath
                          ? `http://localhost:8080${product.imagePath}`
                          : "/default-image.png"
                      }
                      alt={product.name}
                      className="w-full h-full object-cover border rounded"
                    />
                  </Link>
                </div>

                <div className="flex flex-col justify-center flex-grow">
                  <Link to={`/product-detail/${product.id}`}>
                    <p className="font-medium text-lg mb-2">{product.name}</p>
                  </Link>
                  <p className="text-blue-600 font-bold text-lg mb-2">
                    {product.price.toLocaleString()}원
                  </p>
                  <p className="mb-1">수량: {product.quantity}</p>
                  <span className="text-green-600 text-sm font-medium mb-2">무료배송</span>
                  <span className="text-gray-500 text-xs">
                    <span className="font-semibold">{getDeliveryDate()}</span> 도착 예정
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* 우측: 결제 정보 */}
        <div className="border rounded-lg p-4 h-fit flex flex-col justify-between">
          <div>
            <h2 className="font-semibold mb-2">결제 금액</h2>
            <div className="flex justify-between py-1">
              <span>상품 금액</span>
              <span>{totalPrice.toLocaleString()}원</span>
            </div>
            <div className="flex justify-between py-1 text-gray-700">
              <span>배송비</span>
              <span className="text-blue-600 font-bold">0원</span>
            </div>
            <div className="flex justify-between py-1 font-bold text-lg">
              <span>총 결제 금액</span>
              <span>{totalPrice.toLocaleString()}원</span>
            </div>
          </div>

          <button
            onClick={handlePayment}
            className="mt-6 w-full bg-black text-white py-3 text-lg rounded hover:bg-gray-900"
          >
            {totalPrice.toLocaleString()}원 결제하기
          </button>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
