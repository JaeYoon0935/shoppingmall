import { Link } from "react-router-dom";
import { getDeliveryDate } from "../../utils/commUtils";
import { useContext } from "react";
import { CartContext } from "../../context/CartContext";
import { AuthContext } from "../../context/AuthContext";
import api from "../../api/apiClient";

function CartItem({ product, isSelected, onToggle }) {
  const { userInfo } = useContext(AuthContext);
  const { dispatch } = useContext(CartContext);
  const isLoggedIn = !!userInfo.token;

  const handleCartAction = async (actionType, payload) => {
    
    switch (actionType) {
      case "update":
        dispatch({ type: "UPDATE_ITEM", payload });
        break;
      case "delete":
        dispatch({ type: "REMOVE_ITEM", payload });
        break;
    }

    if (!isLoggedIn) return;

    try {
      if (actionType === "update") {
        await api.put("/cart", {
          productId: payload.id,
          quantity: payload.quantity
        });
      } else if (actionType === "delete") {
        await api.delete(`/cart/${payload.id}`);
      }

    } catch (error) {
      console.error("장바구니 서버 처리 실패:", error);
      alert("장바구니 작업에 실패했습니다.");
    }
  };

  const handleQuantityChange = (newQuantity) => {
    if (newQuantity < 1) return;
    handleCartAction("update", { id: product.id, quantity: newQuantity });
  };

  const handleProductDelete = () => {
    if (window.confirm("상품을 삭제하시겠습니까?")) {
      handleCartAction("delete", { id: product.id });
    }
  };

  return (
    <div className="flex items-start gap-4 mb-6 border rounded-md p-4 bg-gray-50 relative">
      <input
        type="checkbox"
        checked={isSelected}
        onChange={() => onToggle(product.id)}
        className="absolute top-4 left-4 scale-125 accent-black"
      />

      <div className="w-28 h-28 ml-6">
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

        <p className="text-blue-600 font-bold text-lg mb-1">
          {(product.price * product.quantity).toLocaleString()}원
        </p>

        {/* 수량 조절 UI */}
        <div className="flex items-center gap-2 mb-1">
          <button
            onClick={() => handleQuantityChange(product.quantity - 1)}
            className="w-7 h-7 border rounded text-lg flex items-center justify-center hover:bg-gray-100"
          >
            −
          </button>
          <span className="min-w-[24px] text-center">{product.quantity}</span>
          <button
            onClick={() => handleQuantityChange(product.quantity + 1)}
            className="w-7 h-7 border rounded text-lg flex items-center justify-center hover:bg-gray-100"
          >
            +
          </button>
        </div>

        <span className="text-green-600 text-sm font-medium mb-1">무료배송</span>
        <span className="text-gray-500 text-xs">
          <span className="font-semibold">{getDeliveryDate()}</span> 도착 예정
        </span>
      </div>

      <button
        onClick={handleProductDelete}
        className="absolute top-4 right-4 text-gray-400 hover:text-red-500 text-xl font-bold"
      >
        ×
      </button>
    </div>
  );
}

export default CartItem;
