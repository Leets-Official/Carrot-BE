# Carrot-BE : 4조 당근마켓 클론코딩

<div align="center">
  <table>
    <tr>
      <td align="center" style="padding: 20px;">
        <a href="https://github.com/huncozyboy" target="_blank">
          <img src="https://avatars.githubusercontent.com/u/163561527?v=4" width="120px" style="border-radius: 50%;" alt="이지훈"/><br />
          <h3 style="margin: 10px 0 5px; color: #007acc;">이지훈</h3>
          <p style="margin: 0; font-weight: bold; color: #555;">✨ 3기</p>
          <p style="margin: 5px 0; font-size: 14px; color: #888;">백엔드 엔지니어</p>
        </a>
      </td>
      <td align="center" style="padding: 20px;">
        <a href="https://github.com/codingmy" target="_blank">
          <img src="https://avatars.githubusercontent.com/u/97686638?v=4" width="120px" style="border-radius: 50%;" alt="최민영"/><br />
          <h3 style="margin: 10px 0 5px; color: #007acc;">최민영</h3>
          <p style="margin: 0; font-weight: bold; color: #555;">✨ 4기</p>
          <p style="margin: 5px 0; font-size: 14px; color: #888;">백엔드 엔지니어</p>
        </a>
      </td>
    </tr>
  </table>
</div>

---

## 📌 구현 기능
### 이지훈
### 🚀 CICD 구축 및 서버 배포
- Docker, GitHub Actions, RDS, EC2 를 조합하여 자동화된 배포 파이프라인 구성.
- 서버는 안정성과 확장성을 고려하여 클라우드 인프라에 배포.
### 🔐 JWT 인증 기능
- JWT 기반 인증: 로그인 시 JWT 토큰을 발급하고, 이를 통해 사용자의 인증 상태 유지.
- 구직자 및 고용자 로그인: 사용자 유형별로 분리된 로그인, 회원가입 API 제공.
- 커런트 유저 기능: 현재 로그인한 사용자 정보 조회 기능 구현.
### 👤 유저 관리 (회원가입 및 유저 정보 관리)
- 회원가입 API: 구직자와 고용자 유형별 회원가입 기능 구현.
- 유저 프로필 이미지 관리: S3 파일 업로드 API를 활용하여 프로필 이미지 생성, 수정, 삭제 기능 제공.
### 🛠 마이페이지 API
- 사용자 프로필 정보를 조회 및 수정 가능.
- 고용자와 구직자별 맞춤형 마이페이지 데이터 제공.
### 🛡 공통 기능 및 예외 처리
- 모든 API 응답은 통일된 커스텀 형식으로 제공하여 일관성 유지.
- 다양한 예외 상황에 대한 일관된 메시지와 상태 코드 제공.
- 사용자 친화적인 오류 메시지로 디버깅 및 사용자 경험 강화.
---
### 최민영
### ✏️ 게시글 CRUD
-	생성(Create): 사용자가 텍스트 및 이미지를 포함한 게시글 작성 가능.
-	조회(Read): 모든 게시글 조회 및 단일 게시글 상세 조회 API 제공.
-	수정(Update): 사용자가 작성한 게시글 수정 기능 제공.
-	삭제(Delete): 게시글 삭제 기능 구현.
### 🔍 게시글 검색 및 상태 수정
- 키워드 검색을 통해 게시글 필터링 가능.
- 게시글 상태(공개/비공개)를 변경하는 기능 제공.
### 📸 이미지 업로드
- 단일 및 다중 이미지 업로드 기능 제공.
### 💼 채용 및 지원 관리
- 구직자는 특정 게시글에 지원할 수 있는 기능 제공.
- 고용자는 지원 상태를 조회하고 채용 여부를 변경 가능.
---
