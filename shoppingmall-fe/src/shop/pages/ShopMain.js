import { useState, useEffect } from 'react';
import { useOutletContext, Link } from 'react-router-dom';

function ShopMain() {
  const { categories } = useOutletContext();

  // 가짜 이미지 인덱스 리스트 (나중에 실제 상품 데이터로 교체 가능)
  const [imageList, setImageList] = useState([1, 2, 3, 4]);

  useEffect(() => {
    console.log(categories);
    // 예: 비동기로 상품 받아서 setImageList([...]) 가능
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
              {imageList.map((imgIndex) => (
                <div
                  key={imgIndex}
                  className="border rounded overflow-hidden flex justify-center items-center aspect-square"
                >
                  <img
                    src={'/default-image.png'}
                    alt={`Product ${imgIndex}`}
                    className="w-full h-full object-contain p-4"
                  />
                </div>
              ))}
            </div>
            
            {/* 더보기 버튼 */}
            <div className="mt-2 flex justify-end">
              <Link
                to={`/category/${category.id}`}
                state={{index, name: category.name}}
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
