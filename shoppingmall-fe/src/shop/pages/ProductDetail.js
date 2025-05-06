import api from "../../api/apiClient";
import { useState, useEffect, useContext } from 'react';
import { useParams, Link, useNavigate } from "react-router-dom";
import { getDeliveryDate } from "../../utils/commUtils";
import { CheckoutContext } from "../../context/CheckoutContext";

function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { dispatch } = useContext(CheckoutContext);
  const [quantity, setQuantity] = useState(1);
  const [product, setProduct] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    uploadImage: null,
    imagePath: "",
    categoryId: "",
    categoryName:"",
  });

  useEffect(() => {
    window.scrollTo(0,0);

    api.get(`/products/${id}`)
      .then(response => {
        setProduct(response.data);
      }).catch(error => {
        console.log("상품조회에 실패하였습니다.", error);
      })
    console.log(id);
  }, [id]);

  const handleBuyNow = () => {
    dispatch({
      type: "SET_ITEMS",
      payload: [{ product_id: Number(id), quantity}]
    });
    navigate("/checkout");
  }

  return (
    <div className="max-w-5xl mx-auto p-8">
      <div className="flex flex-col lg:flex-row gap-8 items-start">
        {/* 왼쪽 - 이미지 */}
        <div className="w-full lg:w-[55%]">
          <img
            src={product.imagePath ? `http://localhost:8080${product.imagePath}` : "/default-image.png"}
            alt={product.name}
            className="w-full h-auto object-cover border rounded"
          />
        </div>

        {/* 오른쪽 - 상품 정보 */}
        <div className="w-full lg:w-[45%] flex flex-col gap-6 text-base lg:text-lg">
          <div className="text-gray-500 text-sm">{product.categoryName}</div>

          <h1 className="text-3xl font-bold text-gray-900">{product.name}</h1>

          <div className="text-2xl font-bold text-blue-600">
            {product.price.toLocaleString()}원
          </div>

          <div className="text-gray-600">
            무료배송 · <span className="font-semibold">{getDeliveryDate()}</span> 도착 예정
          </div>

          {/* 수량 선택 */}
          <div>
            <label className="block text-gray-700 mb-1 font-medium">수량</label>
            <div className="flex items-center border rounded w-36 overflow-hidden">
              <button
                type="button"
                onClick={() => setQuantity(prev => Math.max(1, prev - 1))}
                className="w-10 h-10 flex justify-center items-center text-xl border-r hover:bg-gray-100"
              >
                –
              </button>
              <input
                type="number"
                value={quantity}
                onChange={(e) => {
                  const value = Math.max(1, parseInt(e.target.value) || 1);
                  setQuantity(value);
                }}
                className="w-full text-center outline-none"
                min="1"
              />
              <button
                type="button"
                onClick={() => setQuantity(prev => prev + 1)}
                className="w-10 h-10 flex justify-center items-center text-xl border-l hover:bg-gray-100"
              >
                +
              </button>
            </div>
          </div>

          {/* 총 결제 금액 */}
          <div className="text-right font-semibold text-xl text-gray-800">
            총 결제금액: {(product.price * quantity).toLocaleString()}원
          </div>

          {/* 구매 버튼 */}
          <div className="flex justify-center">
            <button
              onClick={handleBuyNow}
              className="w-48 bg-gray-800 hover:bg-gray-900 text-white py-3 rounded text-lg text-center"
            >
              구매하기
            </button>
            {/* <Link
              to={`/checkout/${id}?quantity=${quantity}`}
              className="w-48 bg-gray-800 hover:bg-gray-900 text-white py-3 rounded text-lg text-center block"
            >
              구매하기
            </Link> */}
          </div>
        </div>
      </div>

      {/* 상품 설명 */}
      <div className="mt-12 border-t pt-8">
        <h2 className="text-xl font-semibold mb-4">상품 설명</h2>
        <p className="text-gray-700 leading-relaxed">
            {product.description ? product.description : "-"}
        </p>
      </div>

      {/* 배송 유의사항 */}
      <div className="mt-12 border-t pt-8">
        <h2 className="text-xl font-semibold mb-4">배송 및 유의사항</h2>
        <ul className="text-gray-700 list-disc list-inside space-y-2">
          <li>제품의 색상은 모니터 설정에 따라 다소 차이가 있을 수 있습니다.</li>
          <li>주문일 기준 3일 후 배송완료 예정입니다.</li>
          <li>포장은 안전하게 출고되며, 배송 중 박스에 손상이 생길 수 있습니다.</li>
          <li>개봉 후 사용한 상품은 반품이 불가합니다.</li>
        </ul>
      </div>
    </div>
  );
}

export default ProductDetail;
