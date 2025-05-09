import api from "../../api/apiClient";
import { useState, useEffect } from 'react';
import { Link, useLocation } from "react-router-dom";

function SearchResult() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const query = searchParams.get("query")?.toLowerCase() || '';

  const [productList, setProductList] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    setPage(0);
  }, [query]);

  useEffect(() => {
    api.get(`/products?query=${encodeURIComponent(query)}&page=${page}&size=${size}`)
      .then(response => {
        setProductList(response.data.content);
        setTotalPages(response.data.totalPages);
      })
      .catch(error => {
        console.error("검색 결과를 불러오지 못했습니다.", error);
      });
  }, [query, page, size]);

  return (
    <div className="px-4 md:px-8">
      <main className="max-w-screen-xl mx-auto space-y-4 mt-8 pb-10">
        <div>
          <h2 className="text-xl font-bold">
            “{query}” 검색 결과
          </h2>
          <hr className="mt-2" />
        </div>

        {/* 상품 목록 */}
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-6 mt-4 relative">
          {productList.length === 0 ? (
            <p className="col-span-full text-center text-gray-500">검색 결과가 없습니다.</p>
          ) : (
            productList.map((product) => (
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
                    {product.name}
                  </div>
                  <div className="text-xs text-gray-600">
                    {`${product.price.toLocaleString()}원`}
                  </div>
                </div>
              </Link>
            ))
          )}
        </div>

        {totalPages > 1 && (
          <div className="flex justify-center items-center gap-2 mt-12 text-sm text-gray-700">
            <button
              onClick={() => setPage(page - 1)}
              disabled={page === 0}
              className={`px-3 py-1 rounded border border-gray-300 ${page === 0 ? 'opacity-0 pointer-events-none' : 'text-600 hover:bg-gray-100'}`}
            >
              이전
            </button>

            {Array.from({ length: totalPages }, (_, i) => (
              <button
                key={i}
                onClick={() => setPage(i)}
                className={`px-3 py-1 border rounded ${page === i ? 'bg-gray-800 text-white' : 'border-gray-300 hover:bg-gray-100'}`}
              >
                {i + 1}
              </button>
            ))}

            <button
              onClick={() => setPage(page + 1)}
              disabled={page >= totalPages - 1}
              className={`px-3 py-1 rounded border border-gray-300 ${page >= totalPages - 1 ? 'opacity-0 pointer-events-none' : 'text-600 hover:bg-gray-100'}`}
            >
              다음
            </button>
          </div>
        )}
      </main>
    </div>
  );
}

export default SearchResult;
