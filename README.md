# SHOP_REST API(JPA)

# 프로젝트 소개
  ***쇼핑몰 프로젝트 REST API 구현입니다***
  
# 기능
  - 회원가입 / 로그인
  - 회원가입 / 로그인 유효성 검사 ( 나이 제한, 이메일 확인, 비밀번호 확인 )
  - 로그인 시 토큰 생성
  - 상품 목록 보여주기 ( 더보기 버튼 구현 )
  - 장바구니 버튼 구현
  - 회원 상품 장바구니 추가 / 삭제 / 출력

# 사용 기술
  - Spring Boot
  - Rest Api
  - JPA
  - SQL (MySQL)
  
# 필수 조건 
 - 자바 설치 (https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
 - STS 설치 (Spring boot는 4버전 권장) (https://spring.io/tools)
 - MySQL(5.7), MySQL Workbench(8) 설치 (https://dev.mysql.com/downloads/)
 - 프로젝트 폴더에 shop.sql 파일 실행 --> 데이터베이스, 테이블 생성
 - resource폴더에 aplication.properties생성 --> 로컬의 MySQL 관련 정보, MyBatis 설정 작성
 ```
#jdbc
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/shopping?characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=계정이름
spring.datasource.password=계정비밀번호

#JPA Setting
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=false
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.format_sql=true

#Logging Setting
logging.level.org.hibernate=info

 ```
 
# 실행 방법
 - Eclipse : Boot Dashboard --> local --> shop 프로젝트 우클릭 -- start
 - cmd : 
   - 프로젝트 다운로드 
   - cmd 창에서 해당 프로젝트 경로로 이동
   - 'mvn package' 명령어 입력 (jar 파일 생성) 
   - target 폴더로 이동 
   - 'java -jar shop-0.0.1-SNAPSHOT.jar' 명령어 입력(jar 파일 실행) 
   - 브라우저 주소창에 'localhost:8080' 입력

# 블로그 / 이메일
 - https://thankstory.tistory.com
 - yongdevel@gmail.com
 
---

#### ````이 프로젝트는 CODEPRESSO의 DEV-OPS 과정 중 실습한 프로젝트입니다.(https://blog.naver.com/code-presso)````
