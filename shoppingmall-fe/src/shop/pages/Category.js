import api from "../../api/apiClient";
import { useState, useEffect } from 'react';
import { Link, useParams, useLocation } from "react-router-dom";

function Category() {
  const { id } = useParams();
  const location = useLocation();

  const searchParams = new URLSearchParams(location.search);
  const index = Number(searchParams.get("index") ?? 0)
  const categoryOrd = Number(index) + 1;

  const [productList, setProductList] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    setPage(0)
  }, [id]);

  useEffect(() => {
    api.get(`/products/category/${id}?page=${page}&size=${size}`)
    .then(response => {
        setProductList(response.data.content);
        setTotalPages(response.data.totalPages);
    }).catch(error => {
        console.error("상품목록을 불러오지 못하였습니다.", error);
      });
  }, [id, page, size]);

  return (
    <div className="px-4 md:px-8">
      <main className="max-w-screen-xl mx-auto space-y-4 mt-12 pb-6">
        <div>
          <h2 className="text-xl font-bold">
            Category{categoryOrd}{' '}
            <span className="text-sm font-normal text-gray-500">
              분류{categoryOrd}
            </span>
          </h2>
          <hr className="mt-2" />
        </div>

        {/* 상품 목록 */}
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-6 mt-4 relative">
          {productList.map((product) => (
            <Link
              key={product.id}
              to={`/product-detail/${product.id}`}
              className="border rounded overflow-hidden flex flex-col aspect-square p-2"
            >
              <div className="w-full flex-1 flex justify-center items-center">
                <img
                  src={product.imagePath ? `http://localhost:8080${product.imagePath}` : '/default-image.png'}
                  alt={`Product ${product.id}`}
                  className="max-h-40 object-contain"
                />
              </div>
              <div className="w-full text-left mt-2">
                <div className="text-sm font-medium truncate">
                  {product.name || '상품명 없음'}
                </div>
                <div className="text-xs text-gray-600">
                  {product.price != null ? `${product.price.toLocaleString()}원` : '가격 미정'}
                </div>
              </div>
            </Link>
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