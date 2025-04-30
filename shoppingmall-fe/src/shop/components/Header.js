function Header(){
  return(
    <header className="bg-gray-100 shadow py-12 px-4 flex items-center justify-between">
      <div className="flex items-center space-x-2">
          <button className="border px-4 py-1 rounded">로그인</button>
          <button className="border px-4 py-1 rounded">회원가입</button>
      </div>
      <h1 className="text-3xl font-bold">ShoppingMall</h1>
      <div className="w-24" />
    </header>
  );
}

export default Header;