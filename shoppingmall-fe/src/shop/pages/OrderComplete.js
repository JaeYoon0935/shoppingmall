import api from "../../api/apiClient";
import { Link, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import { getDeliveryDate } from "../../utils/commUtils";

function OrderComplete() {

   const {id} = useParams();
   const [orderResult, setOrderResult] = useState({
      id: "",
      orderDate: "",
      totalPrice: "",
   });

   useEffect(() => {
      api.get(`/order/${id}`)
      .then(response => {
          setOrderResult(response.data);
      })
      .catch(error => {
        console.log("주문결과 조회에 실패하였습니다.", error)
      })
   },[id]);
    
  return (
    <div className="max-w-2xl mx-auto p-6 text-center">
      <h1 className="text-3xl font-bold mt-14 mb-8">주문이 완료되었습니다</h1>

      <div className="border rounded-lg p-6 shadow">
        <p className="text-lg mb-2">주문번호: <span className="font-semibold">{orderResult.id}</span></p>
        <p className="text-lg mb-2">
            결제일시: <span className="font-semibold">
                {new Date(orderResult.orderDate).toLocaleString("ko-KR", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
                hour: "numeric",
                minute: "2-digit",
                hour12: true
                })}
            </span>
        </p>
        <p className="text-lg mb-2">결제금액: <span className="font-semibold">{orderResult.totalPrice.toLocaleString()}원</span></p>
        <p className="text-lg mb-2">예상 도착일: <span className="font-semibold">{getDeliveryDate()} 도착 예정</span></p>
      </div>

      <div className="mt-8 flex flex-col sm:flex-row justify-center gap-4">
        <Link
          to="/"
          className="bg-black text-white py-3 px-6 rounded hover:bg-gray-900"
        >
          홈으로 돌아가기
        </Link>
        <Link
          to="/orders"
          className="border border-gray-300 py-3 px-6 rounded hover:bg-gray-100"
        >
          주문 내역 보기
        </Link>
      </div>
    </div>
  );
}

export default OrderComplete;
