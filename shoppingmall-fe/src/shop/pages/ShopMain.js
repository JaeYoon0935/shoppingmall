import { useState } from 'react';
import { Link } from 'react-router-dom';

function ShopMain() {
   const categories = [
    '분류1', '분류2', '분류3', '분류4', '분류5', '관리자 페이지'
   ];
    
   const products = [
    { title: 'Category1', label: '분류1' },
    { title: 'Category2', label: '분류2' },
    { title: 'Category3', label: '분류3' },
    { title: 'Category4', label: '분류4' },
    { title: 'Category5', label: '분류5' },
   ];

  const [activeTab, setActiveTab] = useState('분류1');

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-white border-b mt-2">
        <ul className="flex justify-center space-x-24 px-6 overflow-x-auto">
          {categories.map((category) =>
            category === '관리자 페이지' ? (
              <li key={category}>
                <Link
                  to="/admin"
                  className="block py-3 px-4 text-sm font-medium text-gray-800 bg-gray-200 hover:bg-gray-300"
                >
                  {category}
                </Link>
              </li>
            ) : (
              <li
                key={category}
                className={`cursor-pointer py-3 px-2 text-sm font-medium border-b-2 transition-colors ${
                  activeTab === category
                    ? 'border-blue-500 text-blue-600'
                    : 'border-transparent text-gray-600 hover:text-blue-500'
                }`}
                onClick={() => setActiveTab(category)}
              >
                {category}
              </li>
            )
          )}
        </ul>
      </nav>

      <main className="p-6 space-y-10">
        {products.map((product, index) => (
          <div key={index}>
            <h2 className="text-xl font-bold">
              {product.title}{' '}
              <span className="text-sm font-normal text-gray-500">
                {product.label}
              </span>
            </h2>
            <hr className="mt-2" />
          </div>
        ))}
      </main>
    </div>
  );
}

export default ShopMain;