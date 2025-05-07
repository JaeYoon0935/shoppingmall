import api from "../../api/apiClient";
import { useState, useEffect } from 'react';
import { useOutletContext, Link } from 'react-router-dom';

function ShopMain() {
  const { categories } = useOutletContext();
  const [productList, setProductList] = useState([]);

  useEffect(() => {
    api.get('/products/top-by-category?limit=4')
    .then(response => {
        setProductList(response.data);
    }).catch(error => {
        console.error("상품목록을 불러오지 못하였습니다.", error);
      });
  }, []);

  return (
    <div className="px-4 md:px-8">
      <main className="max-w-screen-xl mx-auto space-y-10 mt-8">
        {categories.map((category, index) => (
          <div key={category.id}>
            <h2 className="text-xl font-bold">
              Category{index + 1}{' '}
              <span className="text-sm font-normal text-gray-500">
                {category.name}
              </span>
            </h2>
            <hr className="mt-2" />

            {/* 상품 목록 */}
            <div className="grid grid-cols-2 sm:grid-cols-4 gap-6 mt-4 relative">
              {productList
                .filter(product => product.categoryId === category.id)
                .map((product) => (
                <Link
                  key={product.id}
                  to={`/product-detail/${product.id}`}
                  className="border rounded overflow-hidden flex justify-center items-center aspect-square"
                >
                  <img
                    src={product.imagePath ? `http://localhost:8080${product.imagePath}` : '/default-image.png'}
                    alt={`Product ${product.id}`}
                    className="w-full h-full object-contain p-4"
                  />
                </Link>
              ))}
            </div>
            
            {/* 더보기 버튼 */}
            <div className="mt-2 flex justify-end">
              <Link
                to={`/category/${category.id}?index=${index}`}
                className="px-3 py-1 text-sm text-gray-700 border border-gray-300 rounded hover:bg-gray-100 transition"
              >
                더보기
              </Link>
            </div>
          </div>
        ))}
      </main>
    </div>
  );
}

export default ShopMain;
