# linkplusmall

이커머스 쇼핑몰 및 관리자 페이지(Back Office)를 개발한 사이드 프로젝트입니다.<br/>
프론트엔드와 백엔드를 분리하여 개발하였으며, 백엔드는 API 전용 서버로 구성했습니다.<br/>
Docker 기반으로 배포 환경을 구성하고 AWS EC2에 배포하였습니다.<br/>
- URL: [https://linkplusmall.com](https://linkplusmall.com)
---

# 개발 목적

기존 실무 경험(Spring Boot, JSP, MyBatis)을 바탕으로 React, Kotlin, JPA, Docker 등 추가 기술을 학습하고 적용하여,<br/>
실무에 바로 투입 가능한 풀스택 역량을 확장하는 것을 목표로 개발한 프로젝트입니다.

---

# 기술 스택

- Frontend: React, Tailwind CSS
- Backend: Kotlin, Spring Boot (Spring Data JPA, Spring Security - JWT)
- Database: MySQL
- Deployment: Docker, AWS EC2 (Ubuntu, t3.small)
- Version Control: GitHub (Git CLI)

---

# 개발 도구

- JDK 17
- IntelliJ IDEA
- Visual Studio Code
- Docker (WSL2)
- Gradle

---

# 주요 기능

- 회원가입, 로그인 및 인증 (JWT)
- 상품 조회 및 장바구니 기능
- 주문/결제 및 주문 내역 확인 (회원 전용)
- 관리자 페이지를 통한 회원 및 주문 내역 관리
- 상품 등록, 수정 및 조회 (관리자)
  
---

# 배포

- Docker Compose로 프론트엔드, 백엔드, DB(MySQL) 분리 구성
- AWS EC2 인스턴스(Ubuntu)에 배포
