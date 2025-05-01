import api from "../../../api/apiClient";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function UserInfo() {
  const [users, setUsers] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    api.get(`/admin/user?page=${page}&size=${size}`)
      .then(response => {
        setUsers(response.data.content);
        setTotalPages(response.data.totalPages);
      })
      .catch(error => {
        console.error("회원정보를 불러오지 못하였습니다.", error);
      });
  }, [page, size]);

  const delUser = async (id) => {
    try{
      if(window.confirm("회원정보를 삭제하시겠습니까?")){
        const response = await api.put(
          `/admin/user/${id}/delete`
        );
        if(response.status = 200){
          alert("회원정보가 삭제되었습니다.");
          setUsers((prev) => prev.filter(user => user.id != id));
        }
      }
    } catch(error){
      alert("회원정보 삭제에 실패하였습니다.")
    }
  }

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <div className="text-2xl font-bold">회원정보</div>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-200">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 border">이메일</th>
              <th className="px-4 py-2 border">이름</th>
              <th className="px-4 py-2 border">주소</th>
              <th className="px-4 py-2 border">전화번호</th>
              <th className="px-4 py-2 border">관리</th>
            </tr>
          </thead>
          <tbody>
            {users.length > 0 ? (
              users.map((user) => (
                <tr key={user.id}>
                  <td className="px-4 py-2 border">{user.email}</td>
                  <td className="px-4 py-2 border">{user.name}</td>
                  <td className="px-4 py-2 border">{user.address}</td>
                  <td className="px-4 py-2 border">{user.phone}</td>
                  <td className="px-4 py-2 border text-center">
                    <Link to={`/admin/user-edit/${user.id}`} className="inline-flex items-center justify-center bg-blue-500 text-white mr-2 px-4 py-2 rounded no-underline">수정</Link>
                    <button onClick={() => delUser(user.id)} className="inline-flex items-center justify-center bg-blue-500 text-white px-4 py-2 rounded">삭제</button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="7" className="text-center py-8 text-gray-500">
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

export default UserInfo;
