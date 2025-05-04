import api from "../api/apiClient";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

function SignUp() {

    const [userData, setUserData] = useState({
        email:"",
        password:"",
        name:"",
        birth:"",
        phone:"",
        address:""
    })

    const navigate = useNavigate();

    const signUpProc = async(e) => {
        e.preventDefault();

        if (!validate()) return;

        try{
            const response = await api.post(`/auth/signUp`, userData);
            if(response.status == 200){
                alert("회원가입에 성공하였습니다.");
                navigate("/");
            }else{
                alert("회원가입에 실패하였습니다.");
            }
        }catch(error){
            if(error.response && error.response.data){
                alert(error.response.data.message);
            }else{
                alert("회원가입에 실패하였습니다.");
            }
        }
    }

    const handleChange = (e) => {
        const {name, value} = e.target;

        setUserData(prev => ({
          ...prev,
          [name]: value,
        }));
    }

    const validate = () => {
        const { email, password, name, birth, phone, address } = userData;
    
        if (!email.includes("@")) {
          alert("이메일을 정확히 입력해 주세요.");
          return false;
        }
    
        if (password.trim() === "") {
          alert("비밀번호를 입력해주세요.");
          return false;
        }
    
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
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-50">
            <Link to="/" className="mb-8 text-2xl font-extrabold text-gray-800">
                ShoppingMall
            </Link>

            <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-lg">
                <h2 className="text-2xl font-bold text-center text-gray-600 mb-6 mt-6">회원가입</h2>

                <form className="space-y-4" onSubmit={signUpProc}>
                    <input
                        name="email"
                        value={userData.email}
                        placeholder="이메일"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                        onChange={handleChange}
                    />
                    <div className="mb-2"> {/* 부모요소 space-y-4 로 인해 mb-2 추가하기 위해 div 사용 */}
                        <input
                            name="password"
                            value={userData.password}
                            placeholder="비밀번호"
                            type="password"
                            className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400 mb-3"
                            onChange={handleChange}
                        />
                    </div>
                    <input
                        name="name"
                        value={userData.name}
                        placeholder="성명"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                        onChange={handleChange}
                    />
                    <input
                        name="birth"
                        value={userData.birth}
                        placeholder="생년월일 (예: 920201)"
                        maxLength={6}
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                        onChange={(e) => {
                            const onlyNums = e.target.value.replace(/[^0-9]/g, ""); // 숫자만 허용
                            setUserData(prev => ({ ...prev, birth: onlyNums }));
                        }}
                    />
                    <input
                        name="phone"
                        value={userData.phone}
                        placeholder="전화번호 (예: 01012345678)"
                        maxLength={11}
                        type="tel"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                        onChange={(e) => {
                            const onlyNums = e.target.value.replace(/[^0-9]/g, "");
                            setUserData(prev => ({ ...prev, phone: onlyNums }));
                        }}
                    />
                    <input
                        name="address"
                        value={userData.address}
                        placeholder="주소"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                        onChange={handleChange}
                    />

                    <button
                        type="submit"
                        className="mt-6 w-full bg-blue-500 text-white py-2 rounded-xl hover:bg-blue-600 transition-colors"
                    >
                        가입 완료
                    </button>
                </form>
            </div>
        </div>
    );
}

export default SignUp;
