import { useEffect, useState } from "react";
import api from "../../../api/apiClient";
import { Link } from "react-router-dom";

function OrderManagement() {
  const [ordersByOrderId, setOrdersByOrderId] = useState({});
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        //const response = await api.get(`/admin/orders?page=${page}&size=${size}`); (아래 사용자 주문내역으로 테스트 URL 사용으로 잠시 주석처리..)
        const response = await api.get(`/user/2/orders`);
        const ordersMap = {};

        response.data.forEach((item) => {
        //response.data.content.forEach((item) => { (아래 사용자 주문내역으로 테스트 URL 사용으로 잠시 주석처리..)
          const orderId = item.orderId;

          if (!ordersMap[orderId]) {
            ordersMap[orderId] = {
              orderId: item.orderId,
              orderDate: item.orderDate,
              totalPrice: item.totalPrice,
              status: item.status,
              userEmail: item.userEmail,
              items: [],
            };
          }

          ordersMap[orderId].items.push(item);
        });

        setOrdersByOrderId(ordersMap);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("주문 내역을 불러오지 못했습니다.", error);
      }
    };

    fetchOrders();
  }, [page, size]);

  const orderList = Object.values(ordersByOrderId).sort(
    (a, b) => b.orderId - a.orderId
  );

  const getOrderStatusText = (status, orderDate) => {
    const orderDateObj = new Date(orderDate);
    const today = new Date();
    const deliveryDate = new Date(orderDateObj);
    deliveryDate.setDate(deliveryDate.getDate() + 3);

    if (today >= deliveryDate) return "배송완료";
    if (status === "PAID") return "결제완료";
    return status;
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <div className="text-2xl font-bold">주문내역</div>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-200 text-sm text-center">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 border">주문번호</th>
              <th className="px-4 py-2 border">주문일자</th>
              <th className="px-4 py-2 border">주문자 이메일</th>
              <th className="px-4 py-2 border text-left">상품정보</th>
              <th className="px-4 py-2 border">수량</th>
              <th className="px-4 py-2 border">금액</th>
              <th className="px-4 py-2 border">배송상태</th>
            </tr>
          </thead>
          <tbody>
            {orderList.length === 0 ? (
              <tr>
                <td colSpan="7" className="text-center py-10 text-gray-500">
                  주문 내역이 없습니다.
                </td>
              </tr>
            ) : (
              orderList.flatMap((order) =>
                order.items.map((item, idx) => (
                  <tr key={item.orderItemId} className={`border-t ${idx % 2 === 1 ? 'bg-gray-50' : ''}`}>
                    <td className="px-4 py-2 border">{order.orderId}</td>
                    <td className="px-4 py-2 border">
                      {new Date(order.orderDate).toLocaleDateString()}
                    </td>
                    <td className="px-4 py-2 border">{order.userEmail}</td>
                    <td className="px-4 py-2 text-left border">
                      <div className="flex items-center gap-3">
                        <div className="w-16 h-16 flex-shrink-0">
                          <Link to={`/product-detail/${item.productId}`}>
                            <img
                              src={item.imagePath ? `http://localhost:8080${item.imagePath}` : "/default-image.png"}
                              alt={item.productName}
                              className="w-full h-full object-cover border rounded"
                            />
                          </Link>
                        </div>
                        <Link
                          to={`/product-detail/${item.productId}`}
                          className="text-sm font-medium hover:underline truncate max-w-[160px]"
                        >
                          {item.productName}
                        </Link>
                      </div>
                    </td>
                    <td className="px-4 py-2 border">{item.quantity}개</td>
                    <td className="px-4 py-2 border text-blue-600 font-semibold">
                      {(item.orderPrice * item.quantity).toLocaleString()}원
                    </td>
                    <td className="px-4 py-2 border">{getOrderStatusText(order.status, order.orderDate)}</td>
                  </tr>
                ))
              )
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination */}
      <div className="flex justify-end items-center gap-4 mt-4 text-sm text-gray-700">
        <div className="flex items-center gap-2">
          <span>Rows per page:</span>
          <select
            value={size}
            onChange={(e) => setSize(Number(e.target.value))}
            className="border rounded px-2 py-1 bg-white"
          >
            <option value={10}>10</option>
            <option value={20}>20</option>
            <option value={30}>30</option>
          </select>
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={() => setPage(page - 1)}
            disabled={page === 0}
            className={`px-2 py-1 rounded ${
              page === 0 ? 'text-gray-400 cursor-not-allowed' : 'text-blue-600 hover:underline'
            }`}
          >
            &lt;
          </button>

          <span className="min-w-[80px] text-center">Page {page + 1} of {totalPages}</span>

          <button
            onClick={() => setPage(page + 1)}
            disabled={page >= totalPages - 1}
            className={`px-2 py-1 rounded ${
              page >= totalPages - 1 ? 'text-gray-400 cursor-not-allowed' : 'text-blue-600 hover:underline'
            }`}
          >
            &gt;
          </button>
        </div>
      </div>
    </div>
  );
}

export default OrderManagement;
