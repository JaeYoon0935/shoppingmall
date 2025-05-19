function AdminHome() {
  return (
    <div className="p-8 text-gray-700">
      <h1 className="text-3xl font-bold mb-3">관리자 페이지</h1>
      <p className="mb-8 text-lg">
        좌측 메뉴를 통해 관리 기능을 이용할 수 있습니다.
      </p>

      <div className="space-y-6 text-lg leading-relaxed">
        <div>
          <h2 className="font-semibold">- 회원정보</h2>
          <p className="pl-1">회원 목록을 조회하고, 회원의 정보를 수정하거나 삭제할 수 있습니다.</p>
        </div>
        <div>
          <h2 className="font-semibold">- 상품관리</h2>
          <p className="pl-1">상품을 등록하고, 수정 및 삭제할 수 있습니다.</p>
        </div>
        <div>
          <h2 className="font-semibold">- 주문내역</h2>
          <p className="pl-1">모든 사용자의 주문내역을 조회할 수 있습니다.</p>
        </div>
      </div>
    </div>
  );
}

export default AdminHome;
