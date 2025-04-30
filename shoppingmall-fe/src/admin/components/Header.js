import { Link } from "react-router-dom"

function Header({ toggleSidebar }) {
    return (
      <div className="bg-gray-200 px-4 py-2 flex items-center justify-between shadow">
        <div className="flex items-center space-x-2">
          <div className="text-2xl cursor-pointer" onClick={toggleSidebar}>☰</div>
          <Link to="/admin" className="bg-gray-300 text-gray-800 px-3 py-1 rounded">관리자 홈</Link>
        </div>
        <Link to="/" className="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-1 rounded shadow">쇼핑몰로 이동</Link>
      </div>
    );
  }
  
export default Header;
