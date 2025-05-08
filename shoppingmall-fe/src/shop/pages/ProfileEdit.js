import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import api from "../../api/apiClient";

function ProfileEdit() {
    const { userInfo } = useContext(AuthContext);

    const [userData, setUserData] = useState({
        email: "",
        name: "",
        birth: "",
        phone: "",
        address: "",
    });

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await api.get(`/user/${userInfo.id}`);
                if(response.status === 200){
                    setUserData(response.data);
                } 
            } catch {
                alert("회원 정보를 불러오는 중 오류가 발생했습니다.");
            }
        };
        fetchProfile();
    }, [userInfo.id]);

    const handleChange = (e) => {
        const { name, value } = e.target;

        setUserData(prev => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validate()) return;

        try {
            if(window.confirm("회원정보를 수정하시겠습니까?")){
                const response = await api.put(`/user/${userInfo.id}`, userData );
                if(response.status === 200) {
                    document.activeElement.blur();
                    alert("수정 되었습니다.");
                }
            }
        } catch {
            alert("회원 정보 수정에 실패했습니다.");
        }
    };

    const validate = () => {
        const { name, birth, phone, address } = userData;
    
        if (name.trim() === "") {
          alert("성명을 입력해주세요.");
          return false;
        }
    
        if (!/^\d{6}$/.test(birth)) {
          alert("생년월일은 6자리 숫자로 입력해주세요. (예: 920201)");
          return false;
        }
    
        if (phone.trim() === "") {
          alert("전화번호를 입력해주세요.");
          return false;
        }
    
        if (address.trim() === "") {
          alert("주소를 입력해주세요.");
          return false;
        }
    
        return true;
    };

    return (
        <div className="w-full max-w-xl mx-auto px-6 py-16">
            <h2 className="text-2xl font-bold text-black mb-10 border-b pb-4">
                회원 정보 수정
            </h2>

            <form onSubmit={handleSubmit} className="space-y-10">
                <div>
                    <label className="block text-base font-semibold text-gray-800 mb-1">이메일</label>
                    <input
                        name="email"
                        value={userData.email}
                        readOnly
                        className="w-full border-b border-gray-300 text-gray-600 font-semibold cursor-not-allowed"
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label className="block text-base font-semibold text-gray-800 mb-1">이름</label>
                    <input
                        name="name"
                        value={userData.name}
                        className="w-full border-b border-gray-300 focus:outline-none focus:border-black text-black"
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label className="block text-base font-semibold text-gray-800 mb-1">생년월일</label>
                    <input
                        name="birth"
                        value={userData.birth}
                        maxLength={6}
                        className="w-full border-b border-gray-300 focus:outline-none focus:border-black text-black"
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label className="block text-base font-semibold text-gray-800 mb-1">전화번호</label>
                    <input
                        name="phone"
                        value={userData.phone}
                        maxLength={11}
                        className="w-full border-b border-gray-300 focus:outline-none focus:border-black text-black"
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label className="block text-base font-semibold text-gray-800 mb-1">주소</label>
                    <input
                        name="address"
                        value={userData.address}
                        className="w-full border-b border-gray-300 focus:outline-none focus:border-black text-black"
                        onChange={handleChange}
                    />
                </div>

                <div className="flex justify-center mt-12">
                    <button
                        type="submit"
                        className="px-6 py-3 bg-black text-white text-sm font-semibold rounded-md hover:bg-gray-900 transition"
                    >
                        수정하기
                    </button>
                </div>
            </form>
        </div>
    );
}

export default ProfileEdit;
