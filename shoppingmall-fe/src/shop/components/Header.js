import { useEffect } from 'react';
import { Link } from 'react-router-dom';

function Header({ categories }) {

  useEffect(() => {
    console.log(categories);
  }, [categories]);

  const renderedCategories = [
    ...categories,
    { id: 999, name: '관리자 페이지', isAdminLink: true }
  ];

  return (
    <div>
      {/* 상단 헤더 */}
      <header className="bg-gray-100 shadow py-6 px-6 relative">
        <div className="flex items-center justify-between">
          {/* 로고 */}
          <h1 className="text-2xl font-bold">
            <Link to="/">ShoppingMall</Link>
          </h1>

          {/* 검색창 + 버튼 */}
          <div className="absolute left-1/2 transform -translate-x-1/2 flex space-x-2">
            <input
              type="text"
              placeholder="통합검색"
              className="border rounded px-4 py-1 w-64"
            />
            <button className="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600">
              검색
            </button>
          </div>

          {/* 로그인 / 회원가입 */}
          <div className="flex space-x-2">
            <button className="border px-4 py-1 rounded">로그인</button>
            <button className="border px-4 py-1 rounded">회원가입</button>
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
