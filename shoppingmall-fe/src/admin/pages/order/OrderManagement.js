import { useEffect, useState } from "react";
import api from "../../../api/apiClient";
import { Link } from "react-router-dom";

function OrderManagement() {
  const [orderItems, setOrderItems] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await api.get(`/admin/order?page=${page}&size=${size}`);
        setOrderItems(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("주문 내역을 불러오지 못했습니다.", error);
      }
    };

    fetchOrders();
  }, [page, size]);

  const getOrderStatusText = (status, orderDate) => {
    const orderDateObj = new Date(orderDate);
    const today = new Date();
    const deliveryDate = new Date(orderDateObj);
    deliveryDate.setDate(deliveryDate.getDate() + 3);

    if (today >= deliveryDate) return "배송완료";
    if (status === "PAID") return "결제완료";
    return status;
  };

  // 배경색 토글용
  let prevOrderId = null;
  let toggle = false;

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <div className="text-2xl font-bold">주문내역</div>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-200 text-sm text-center table-fixed">
          <thead className="bg-gray-100">
            <tr>
              <th className="w-[10%] px-4 py-2 border">주문번호</th>
              <th className="w-[16%] px-4 py-2 border">주문일시</th>
              <th className="w-[16%] px-4 py-2 border">이메일</th>
              <th className="w-[28%] px-4 py-2 border text-left">상품정보</th>
              <th className="w-[12%] px-4 py-2 border">수량</th>
              <th className="w-[9%] px-4 py-2 border">금액</th>
              <th className="w-[9%] px-4 py-2 border">배송상태</th>
            </tr>
          </thead>
          <tbody>
            {orderItems.length === 0 ? (
              <tr>
                <td colSpan="7" className="text-center py-10 text-gray-500">
                  주문 내역이 없습니다.
                </td>
              </tr>
            ) : (
              orderItems.map((item) => {
                if (item.orderId !== prevOrderId) {
                  toggle = !toggle;
                  prevOrderId = item.orderId;
                }

                return (
                  <tr
                    key={item.orderItemId}
                    className={`border-t ${toggle ? "bg-white" : "bg-gray-100"}`}
                  >
                    <td className="px-4 py-2 border">{item.orderId}</td>
                    <td className="px-4 py-2 border">
                      {new Date(item.orderDate).toLocaleString("ko-KR", {
                        year: "numeric",
                        month: "2-digit",
                        day: "2-digit",
                        hour: "numeric",
                        minute: "2-digit",
                        hour12: true,
                      })}
                    </td>
                    <td className="px-4 py-2 border">{item.email}</td>
                    <td className="px-4 py-2 text-left border">
                      <Link
                        to={`/product-detail/${item.productId}`}
                        className="flex items-center gap-3 hover:underline min-w-0"
                      >
                        <img
                          src={
                            item.imagePath
                              ? `${window.ENV.API_IMAGE_URL}${item.imagePath}`
                              : "/default-image.png"
                          }
                          alt={item.productName}
                          className="w-16 h-16 object-cover border rounded"
                        />
                        <span className="text-sm font-medium truncate max-w-[160px]">
                          {item.productName}
                        </span>
                      </Link>
                    </td>
                    <td className="px-4 py-2 border">{item.quantity}개</td>
                    <td className="px-4 py-2 border text-blue-600 font-semibold">
                      {(item.orderPrice * item.quantity).toLocaleString()}원
                    </td>
                    <td className="px-4 py-2 border">
                      {getOrderStatusText(item.status, item.orderDate)}
                    </td>
                  </tr>
                );
              })
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination */}
      <div className="flex justify-end items-center gap-4 mt-4 text-sm text-gray-700">
        <div className="flex items-center gap-2">
          <span>Orders per page:</span>
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
              page === 0 ? "text-gray-400 cursor-not-allowed" : "text-blue-600 hover:underline"
            }`}
          >
            &lt;
          </button>

          <span className="min-w-[80px] text-center">
            Page {page + 1} of {totalPages}
          </span>

          <button
            onClick={() => setPage(page + 1)}
            disabled={page >= totalPages - 1}
            className={`px-2 py-1 rounded ${
              page >= totalPages - 1 ? "text-gray-400 cursor-not-allowed" : "text-blue-600 hover:underline"
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
