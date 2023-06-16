# CRUD_BLOG

## ✅ 서비스 완성 요구사항

### 1. 아래의 요구사항을 기반으로 Use Case 그려보기
   - 그림판으로 그리려고했는데.. ![유스케이스](/src/main/resources/static/images/UseCase.PNG)
   - 팀원분이 😳 정말 감사하게도 [draw.io](https://app.diagrams.net/) 라는 꿀앱을 알려주셔서 쉽게 그릴 수 있었다!
   
### 2. 전체 게시글 목록 조회 API
   - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
   - 작성 날짜 기준 내림차순으로 정렬하기
   - PostMan을 활용하면 결과를 쉽게 확인할 수 있다. 솔직히 사용법을 잘 모르겠어서 시간을 허비했다. ![get](/src/main/resources/static/images/get.PNG)
   
### 3. 게시글 작성 API
  - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
  - 저장된 게시글을 Client 로 반환하기
  - ![post](/src/main/resources/static/images/post2.PNG)
   
### 4. 선택한 게시글 조회 API
  - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
  - 글번호로 조회![post](/src/main/resources/static/images/선택조회.PNG)

### 5. 선택한 게시글 수정 API
  - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
  - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
  - 뭔가 잘못된것 같다.. 후후..팀원들에게 물어봐야겠다😔![post](/src/main/resources/static/images/put.PNG)

### 6. 선택한 게시글 삭제 API
  - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
  - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기
  - 역시 뭔가 잘못된것 같다! 확인했을때 삭제는 되었지만 잘 모르겠어서 팀원들에게 물어봐야겠다..😭 ![post](/src/main/resources/static/images/deletee.PNG)

### PostMan 사용해보기
강의를 다시 보면서 사용해봐야겠다.

### API 명세서
![API 명세](/src/main/resources/static/images/API.PNG)
![API 명세](/src/main/resources/static/images/꼬롬한입력화면.PNG)

부족한 부분
- PostMan을 잘 활용했다면 이런 꼬롬한 입력창도 안만들었을텐데 안타깝다.
- 선택 조회, 비밀번호 입력 후 삭제 부분 강의 복습후 구현
- 테스트부분 복습


