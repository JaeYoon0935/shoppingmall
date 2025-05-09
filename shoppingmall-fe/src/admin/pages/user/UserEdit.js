import api from "../../../api/apiClient";
import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";

function UserEdit() {

  const { id } = useParams();
  const [userData, setUserData] = useState({
    email: "",
    name: "",
    address: "",
    birth: "",
    phone: "",
  });
  const navigate = useNavigate();

  useEffect(() => {
      const fetchData = async () => {
        try{
          const resUser = await api.get(`/admin/user/${id}`);
          setUserData(resUser.data);
        } catch(error) {
          console.error('데이터 불러오기 실패', error);
        }
      };

      fetchData();
  }, [id])

  const modProc = async() => {
    try{
        if(window.confirm("회원정보를 수정하시겠습니까?")){

          console.log("ID", id);
          const response = await api.put(`/admin/user/${id}`, userData );

          if (response.status === 200) {
              alert("수정 되었습니다.");
              navigate('/admin/user-info');
          }
        }
    } catch (error) {
        alert("수정에 실패하였습니다.");
    }
  }

  const handleChange = (e) => {
    const { name, value} = e.target;
    setUserData(prev => ({
      ...prev, [name]: value,
    }));
  };

  return (
    <div className="p-6">
      <div className="text-2xl font-bold mb-6">회원정보 수정</div>
        <table className="min-w-full border-collapse border border-gray-200 rounded-md">
          <tbody>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">이메일</td>
              <td className="px-4 py-3">
                <input
                  name="email"
                  value={userData.email}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                  readOnly
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">이름</td>
              <td className="px-4 py-3">
                <input
                  name="name"
                  value={userData.name}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">주소</td>
              <td className="px-4 py-3">
                <input
                  name="address"
                  value={userData.address}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">생년월일</td>
              <td className="px-4 py-3">
                <input
                  name="birth"
                  value={userData.birth}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">전화번호</td>
              <td className="px-4 py-3">
                <input
                  name="phone"
                  value={userData.phone}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
          </tbody>
        </table>
        <div className="flex justify-end gap-2 p-4">
          <button type="button" onClick={modProc} className="bg-gray-200 px-4 py-2 rounded">수정</button>
          <Link to='/admin/user-info' className="bg-gray-200 px-4 py-2 rounded">취소</Link>
        </div>
    </div>
  );
}

export default UserEdit;