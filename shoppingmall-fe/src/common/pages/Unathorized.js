import React from "react";
import { Link } from "react-router-dom";

const Unauthorized = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 px-4">
      <div className="bg-white shadow-md rounded-2xl p-8 max-w-md text-center">
        <h1 className="text-3xl font-bold text-gray-600 mb-4">접근 불가</h1>
        <p className="text-gray-700 mb-6">
          이 페이지에 접근할 수 있는 권한이 없습니다.
        </p>
        <Link
          to="/"
          className="inline-block bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg transition"
        >
          홈으로 돌아가기
        </Link>
      </div>
    </div>
  );
};

export default Unauthorized;
