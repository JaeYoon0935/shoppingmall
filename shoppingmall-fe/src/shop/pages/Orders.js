import { useEffect, useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import api from "../../api/apiClient";
import { Link } from "react-router-dom";

function Orders() {
  const { userInfo } = useContext(AuthContext);
  const [ordersByOrderId, setOrdersByOrderId] = useState({});

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await api.get(`/user/${userInfo.id}/orders`);

        const ordersMap = {};
        response.data.forEach((item) => {
          const orderId = item.orderId;

          if (!ordersMap[orderId]) {
            ordersMap[orderId] = {
              orderId: item.orderId,
              orderDate: item.orderDate,
              totalPrice: item.totalPrice,
              status: item.status,
              items: [],
            };
          }

          ordersMap[orderId].items.push(item);
        });

        setOrdersByOrderId(ordersMap);
      } catch (error) {
        console.error("주문 내역 불러오기 실패", error);
      }
    };

    if (userInfo?.id) {
      fetchOrders();
    }
  }, [userInfo]);

  const orderList = Object.values(ordersByOrderId).sort(
    (a, b) => b.orderId - a.orderId
  );

  const getOrderStatusText = (status, orderDate) => {
    const orderDateObj = new Date(orderDate);
    const today = new Date();
    const deliveryDate = new Date(orderDateObj);
    deliveryDate.setDate(deliveryDate.getDate() + 3);

    if (today >= deliveryDate) {
      return "배송완료";
    }
    if (status === "PAID") {
      return "결제완료";
    }
    return status;
  };

  return (
    <div className="max-w-5xl mx-auto px-4 py-6">
      <h1 className="text-2xl font-bold mt-6 mb-6">주문 내역</h1>

      {orderList.length === 0 ? (
        <p className="text-gray-500">주문 내역이 없습니다.</p>
      ) : (
        orderList.map((order) => (
          <div key={order.orderId} className="mb-10 border-b pb-4">
            <p className="text-sm text-gray-500 mb-2">
              주문일자: {new Date(order.orderDate).toLocaleDateString()}
            </p>

            <div className="overflow-x-auto">
              <table className="w-full text-sm border border-gray-200 table-fixed">
                <thead className="bg-gray-100 text-gray-700 text-center">
                  <tr>
                    <th className="w-[20%] px-2 py-1">주문번호</th>
                    <th className="w-[20%] px-4 py-1">상품정보</th>
                    <th className="w-[20%] px-4 py-1">수량</th>
                    <th className="w-[20%] px-4 py-1">금액</th>
                    <th className="w-[20%] px-4 py-1">배송상태</th>
                  </tr>
                </thead>
                <tbody>
                  {order.items.map((item, idx) => (
                    <tr key={item.orderItemId} className={`border-t text-center ${idx % 2 === 1 ? 'bg-gray-50' : ''}`}>
                      <td className="px-4 py-2 font-semibold text-gray-700">{order.orderId}</td>
                      <td className="px-4 py-2 text-left">
                        <div className="flex items-center gap-3">
                          <div className="w-16 h-16 flex-shrink-0">
                            <Link to={`/product-detail/${item.productId}`}>
                              <img
                                src={item.imagePath ? `${window.ENV.API_IMAGE_URL}${item.imagePath}` : "/default-image.png"}
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
                      <td className="px-4 py-2">{item.quantity}개</td>
                      <td className="px-4 py-2 text-blue-600 font-semibold">
                        {(item.orderPrice * item.quantity).toLocaleString()}원
                      </td>
                      <td className="px-4 py-2">{getOrderStatusText(order.status, order.orderDate)}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default Orders;
