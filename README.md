# linkplusmall

이커머스 쇼핑몰 및 관리자 페이지(Back Office)를 개발한 사이드 프로젝트입니다.  
프론트엔드와 백엔드를 분리하여 구성했으며, 백엔드는 API 서버로 동작합니다.  
Docker 기반으로 배포 환경을 구성하였고, AWS EC2에서 서비스되고 있습니다.

---

# 서비스 주소

- URL: [https://linkplusmall.com](https://linkplusmall.com)

---

# 기술 스택

- Frontend: React, Tailwind CSS
- Backend: Kotlin, Spring Boot (Spring Data JPA, Spring Security - JWT)
- Database: MySQL
- Version Control: GitHub (Git CLI)
- Deployment: Docker, AWS EC2 (Ubuntu, t3.small)

---

# 개발 도구

- JDK 17
- IntelliJ IDEA
- Visual Studio Code
- Docker (WSL2)
- Gradle

---

# 주요 기능

사용자 기능 (회원/비회원)
- 회원가입 및 로그인 (JWT 인증)
- 상품 목록 및 상세 조회
- 장바구니 담기 및 조회
- 주문 및 결제
- 주문 내역 조회
- 비회원은 로그인 후 구매 가능

관리자 기능 (Back Office)
- 회원 정보 조회 및 수정
- 상품 등록, 수정 및 조회
- 주문 내역 조회
- 반영 내역 확인

---

# 배포

- Docker Compose로 프론트엔드, 백엔드, DB(MySQL) 분리 구성
- AWS EC2 인스턴스(Ubuntu)에서 운영 중

