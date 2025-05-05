import api from "../api/apiClient";
import { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from '../context/AuthContext';

function Login() {
    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    });

    const navigate = useNavigate();
    const { dispatch } = useContext(AuthContext);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLoginData((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const loginProc = async (e) => {
        e.preventDefault();

        if (!loginData.email.includes("@")) {
            alert("올바른 이메일을 입력해주세요.");
            return;
        }

        if (loginData.password.trim() === "") {
            alert("비밀번호를 입력해주세요.");
            return;
        }

        try {
            const response = await api.post("/auth/login", loginData);
            if (response.status === 200) {
                dispatch({ type:"LOGIN", payload: response.data })
                navigate("/");
            } else {
                alert("로그인 실패");
            }
        } catch (error) {
            if (error.response && error.response.data) {
                debugger;
                alert(error.response.data.message);
            } else {
                alert("로그인 중 오류가 발생했습니다.");
            }
        }
    };

    return (
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-50">
            <Link to="/" className="mb-8 text-2xl font-extrabold text-gray-800">
                ShoppingMall
            </Link>

            <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-lg">
                <h2 className="text-2xl font-bold text-center text-gray-600 mb-6 mt-6">로그인</h2>

                <form className="space-y-4" onSubmit={loginProc}>
                    <input
                        name="email"
                        value={loginData.email}
                        onChange={handleChange}
                        placeholder="이메일"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                    />
                    <input
                        name="password"
                        value={loginData.password}
                        onChange={handleChange}
                        placeholder="비밀번호"
                        type="password"
                        className="w-full px-4 py-2 border rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-400"
                    />

                    <button
                        type="submit"
                        className="mt-6 w-full bg-blue-500 text-white py-2 rounded-xl hover:bg-blue-600 transition-colors"
                    >
                        로그인
                    </button>
                </form>

                <p className="text-center text-sm text-gray-500 mt-4">
                    아직 회원이 아니신가요?{" "}
                    <Link to="/signup" className="text-blue-500 hover:underline">
                        회원가입
                    </Link>
                </p>
            </div>
        </div>
    );
}

export default Login;
