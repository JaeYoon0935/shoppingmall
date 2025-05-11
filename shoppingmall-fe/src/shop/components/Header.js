import { useEffect, useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import { CheckoutContext } from '../../context/CheckoutContext';
import { CartContext } from '../../context/CartContext';

function Header({ categories }) {
  const { userInfo, dispatch: authDispatch } = useContext(AuthContext);
  const { dispatch: checkoutDispatch } =  useContext(CheckoutContext);
  const { dispatch: cartDispatch} = useContext(CartContext);
  const isAdmin = userInfo?.roles?.includes("ROLE_ADMIN");
  const [ keyword, setKeyword] = useState('');

  useEffect(() => {
    console.log(categories);
  }, [categories]);

  

  const renderedCategories = isAdmin ? [...categories, { id: 999, name: '관리자 페이지', isAdminLink: true }] : [...categories];

  const navigate = useNavigate();
  const handelLogout = () => {
      authDispatch({ type: "LOGOUT"});
      checkoutDispatch({ type: "CLEAR_ITEMS"})
      cartDispatch({ type: "CLEAR_CART" });
  
      localStorage.removeItem("checkoutItems");
      localStorage.removeItem("cartItems");
      localStorage.removeItem("cartMerged");
      alert("로그아웃 되었습니다.");
      navigate('/');
  }

  const handleSearch = () => {
    if(keyword.trim()){
      navigate(`/search?query=${encodeURIComponent(keyword)}`)
    }else{
      alert("검색어를 입력해주세요.")
    }
  }

  return (
    <div>
      {/* 상단 헤더 */}
      <header className="bg-gray-100 shadow py-6 px-6 relative">
        <div className="flex items-center justify-between">
          {/* 로고 */}
          <h1 className="text-2xl font-bold text-gray-800">
            <Link to="/">ShoppingMall</Link>
          </h1>

          {/* 검색창 + 버튼 */}
          <div className="absolute left-1/2 transform -translate-x-1/2 flex space-x-2">
            <input
              type="text"
              placeholder="통합검색"
              className="border rounded px-4 py-1 w-64"
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
              onKeyDown={(e) => {
                if(e.key == 'Enter') handleSearch();
              }}
            />
            <button className="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600">
              검색
            </button>
          </div>

          {/* 로그인 / 회원가입 */}
          <div className="flex space-x-2">
            {userInfo.token ? (
              <>
              <Link to="/cart" className="border px-4 py-1 rounded">장바구니</Link>
              <div className="relative group">
                <button className="border px-4 py-1 rounded text-center">
                  내 정보
                </button>
                <div className="absolute top-full left-1/2 transform -translate-x-1/2 hidden group-hover:flex flex-col bg-white border rounded shadow-md z-10 whitespace-nowrap text-center">
                  <Link to="/profile-edit" className="px-4 py-2 hover:bg-gray-100">회원정보 수정</Link>
                  <Link to="/orders" className="px-4 py-2 hover:bg-gray-100">주문내역 조회</Link>
                </div>
              </div>
              <button
                onClick={ handelLogout }
                className="border px-4 py-1 rounded"
                >
                  로그아웃
              </button>
              </>
            ) : (
              <>
              <Link to={"/cart"} className="border px-4 py-1 rounded">장바구니</Link>
              <Link to={"/login"} className="border px-4 py-1 rounded">로그인</Link>
              <Link to={"/signUp"} className="border px-4 py-1 rounded">회원가입</Link>  
              </>
            )}
          </div>
        </div>
      </header>

      {/* 카테고리 네비게이션 */}
      <nav className="bg-white border-b w-full shadow-sm">
        <ul className="flex justify-center space-x-24 px-6 overflow-x-auto">
          {renderedCategories.map((category, index) =>
            category.isAdminLink ? (
              <li key={category.id}>
                <Link
                  to="/admin"
                  className="block py-3 px-4 text-sm font-medium text-gray-800 bg-gray-200 hover:bg-gray-300"
                >
                  {category.name}
                </Link>
              </li>
            ) : (
              <li
                key={category.id}
                className="py-3 px-2 text-sm font-medium"
              >
                <Link
                  to={`/category/${category.id}?index=${index}`}
                  className="block cursor-pointer transition-colors border-b-2 border-transparent text-gray-600 hover:text-blue-500"
                >
                  {category.name}
                </Link>
              </li>
            )
          )}
        </ul>
      </nav>
    </div>
  );
}

export default Header;
