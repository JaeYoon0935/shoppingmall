import { useState, useEffect } from 'react';
import { useParams, useLocation } from "react-router-dom";

function Category() {
  const { id } = useParams();
  const { state } = useLocation();
  const categoryName = state?.name;
  const categoryOrd = Number(state?.index) + 1;

  // 가짜 이미지 인덱스 리스트 (나중에 실제 상품 데이터로 교체 가능)
  const [imageList, setImageList] = useState([1, 2, 3, 4, 5, 6, 7, 8]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(3);


  useEffect(() => {
    // 예: 비동기로 상품 받아서 setImageList([...]) 가능
  }, []);

  return (
    <div className="px-4 md:px-8">
      <main className="max-w-screen-xl mx-auto space-y-4 mt-8 pb-10">
        <div>
          <h2 className="text-xl font-bold">
            Category{categoryOrd}{' '}
            <span className="text-sm font-normal text-gray-500">
              {categoryName}
            </span>
          </h2>
          <hr className="mt-2" />
        </div>

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

        <div className="flex justify-center items-center gap-2 mt-12 text-sm text-gray-700">
          {/* 이전 버튼 */}
          <button
            onClick={() => setPage(page - 1)}
            disabled={page === 0}
            className={`px-3 py-1 rounded border border-gray-300 ${
              page === 0
                ? 'opacity-0 pointer-events-none'
                : 'text-600 hover:bg-gray-100'
            }`}
          >
            이전
          </button>

          {/* 페이지 번호 */}
          {Array.from({ length: totalPages }, (_, i) => (
            <button
              key={i}
              onClick={() => setPage(i)}
              className={`px-3 py-1 border rounded ${
                page === i
                  ? 'bg-gray-800 text-white'
                  : 'border-gray-300 hover:bg-gray-100'
              }`}
            >
              {i + 1}
            </button>
          ))}

          {/* 다음 버튼 */}
          <button
            onClick={() => setPage(page + 1)}
            disabled={page >= totalPages - 1}
            className={`px-3 py-1 rounded border border-gray-300 ${
              page >= totalPages - 1
                ? 'opacity-0 pointer-events-none'
                : 'text-600 hover:bg-gray-100'
            }`}
          >
            다음
          </button>
        </div>
      </main>
    </div>
  );
}

export default Category;
