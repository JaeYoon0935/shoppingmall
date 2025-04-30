import api from "../../../api/apiClient";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function ProductManagement() {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    api.get(`/admin/products?page=${page}&size=${size}`)
      .then(response => {
        setProducts(response.data.content);
        setTotalPages(response.data.totalPages);
      })
      .catch(error => {
        console.error("상품목록을 불러오지 못하였습니다.", error);
      });
  }, [page, size]);

  const delProduct = async( id ) => {
    try{
      if(window.confirm("상품을 삭제하시겠습니까?")){
        const response = await api.put(
          `/admin/products/${id}/delete`
        );
        if(response.status == 200){
          alert("상품이 삭제되었습니다.");
          setProducts((prev) => prev.filter(product => product.id !== id));
        }
      }
    } catch (error) {
        alert("상품삭제에 실패하였습니다.");
    }
  }
 
  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <div className="text-2xl font-bold">상품관리</div>
        <Link to='/admin/product-registration' className="bg-blue-500 text-white px-4 py-2 rounded">상품등록</Link>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-200">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 border w-1/5">상품코드</th>
              <th className="px-4 py-2 border w-1/5">상품정보</th>
              <th className="px-4 py-2 border w-1/5">분류</th>
              <th className="px-4 py-2 border w-1/5">재고</th>
              <th className="px-4 py-2 border w-1/5">관리</th>
            </tr>
          </thead>
          <tbody>
            {products.length > 0 ? (
              products.map((product) => (
                <tr key={product.id}>
                  <td className="px-4 py-2 border">{product.id}</td>
                  <td className="px-4 py-2 border">
                    <div className="flex flex-col items-center space-y-2">
                      <span className="text-center">{product.name}</span>
                      <img
                        src={product.imagePath ? `http://localhost:8080${product.imagePath}` : '/default-image.png'}
                        alt={product.name}
                        className="w-16 h-16 object-cover rounded"
                      />
                    </div>
                  </td>
                  <td className="px-4 py-2 border">{product.categoryName}</td>
                  <td className="px-4 py-2 border">{product.stock}</td>
                  <td className="px-4 py-2 border text-center">
                    <Link to={`/admin/product-edit/${product.id}`} className="inline-flex items-center justify-center bg-blue-500 text-white mr-2 px-4 py-2 rounded no-underline">수정</Link>
                    <button onClick={() => delProduct(product.id)} className="inline-flex items-center justify-center bg-blue-500 text-white px-4 py-2 rounded">삭제</button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" className="text-center py-8 text-gray-500">
                  No data available
                </td>
              </tr>
            )}
          </tbody>
        </table>

        <div className="flex justify-end items-center gap-4 mt-4 text-sm text-gray-700">
          <div className="flex items-center gap-2">
            <span>Rows per page:</span>
            <select
              value={size}
              onChange={(e) => setSize(Number(e.target.value))}
              className="border rounded px-2 py-1 bg-white"
            >
              <option value={10}>10</option>
              <option value={20}>20</option>
              <option value={30}>30</option>
            </select>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={() => setPage(page - 1)}
              disabled={page === 0}
              className={`px-2 py-1 rounded ${
                page === 0 ? 'text-gray-400 cursor-not-allowed' : 'text-blue-600 hover:underline'
              }`}
            >
              &lt;
            </button>

            <span className="min-w-[80px] text-center">Page {page + 1} of {totalPages}</span>

            <button
              onClick={() => setPage(page + 1)}
              disabled={page >= totalPages - 1}
              className={`px-2 py-1 rounded ${
                page >= totalPages - 1 ? 'text-gray-400 cursor-not-allowed' : 'text-blue-600 hover:underline'
              }`}
            >
              &gt;
            </button>
          </div>
        </div>


      </div>
    </div>
  );
}

export default ProductManagement;