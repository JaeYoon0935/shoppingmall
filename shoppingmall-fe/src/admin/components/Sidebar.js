import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div className="w-48 bg-white shadow h-screen pt-4 space-y-2 relative z-10">
      <h2 className="text-gray-600 font-semibold">목록</h2>
      <Link to="/admin/user-info" className="block w-full text-center py-2 bg-gray-100 hover:bg-gray-200">회원정보</Link>
      <Link to="/admin/product-management" className="block w-full text-center py-2 bg-gray-100 hover:bg-gray-200">상품관리</Link>
      <Link to="/admin/order-management" className="block w-full text-center py-2 bg-gray-100 hover:bg-gray-200">주문내역</Link>
    </div>
  );
}

export default Sidebar;