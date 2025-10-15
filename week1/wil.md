25-2 백엔드 정규 스터디 1주차 Weekly I Learned
===========================================

## 1. 웹이란?
> * 인터넷: 전 세계 컴퓨터와 기기를 연결하는 거대한 글로벌 네트워크
> * 웹: 인터넷 위에서 동작하는 **서비스** 중 하나

<br>


### <웹의 동작 방식> 
#### __클라이언트 - 서버 모델__  
> - 클라이언트(Client): 요청(Request)을 보내고, 서버의 응답을 받아 사용
> - 서버(Server): 클라이언트의 요청을 받아 처리하고, 그에 대한 응답(response)을 반환

*서버 컴퓨터에서 필요한 자원을 어떻게 찾을까?*
<br><br>


### URL (Uniform Resource Locator)
----------------------------------
> 웹 상에서 특정 자원(웹페이지, 문서, 이미지 등)의 위치를 나타내는 주소  

`http://www.example.com:5883/category/food.html?topic=pizza`  
* www.example.com:5883
  + `www.example.com`
    - **Host**: 리소스가 위치한 서버의 IP 주소 혹은 도메인
  + `:5883`
    - **Port**: 서버의 특정 네트워크 포트 번호 (**일반적으로 생략**)
* /category/food.html?topic=pizza
  + `/category/food.html`
    - **Path**: 서버 내에서 원하는 리소스의 경로
  + `?topic=pizza`
    - **Query**: 서버에 추가적인 정보를 보내는 파라미터로, ? 뒤에 key-value 형식으로 나열
* `http`
  - **Scheme(Protocol)**: 컴퓨터와 같은 장치들 사이에서 데이터를 주고 받는 방식, 통신하기 위한 규칙
<br><br>


### HTTP (HyperText Transfer Protocol)
--------------------------------------
> 웹에서 데이터를 주고받는 서버-클라이언트 모델의 프로토콜  
클라이언트의 요청(request)과 서버의 응답(response)을 통해 작동
* 무상태성(Stateless): 서버는 클라이언트의 이전 요청을 저장하지 않고, 매 요청을 독립적으로 처리
* 비연결성(Connectionless): 클라이언트가 요청을 보내고 응답을 받은 후 서버와 연결을 유지하지 않음

#### HTTP 요청
```
GET /test.html HTTP/1.1     -> start line  
Host: google.com            
Accept: text/html           
Accept-Encoding: gzip, deflate  
Connection: keep-alive      ... -> headers
                            -> blank line
hl=ko&ogbl=0&page=99        -> body
```
* start line: <u>요청 메서드</u>, <u>요청할 경로</u>, <u>HTTP 버전 정보</u> 포함
  + HTTP 주요 메서드
    - GET: 리소스를 조회
    - POST: 리소스를 추가, 등록
    - PUT: 리소스를 교체, 없으면 새로 생성
    - PATCH: 리소스의 일부를 수정
    - DELETE: 리소스를 삭제
* headers: 요청에 대한 부가 정보
* body: 실제 전송할 데이터

#### HTTP 응답
```
HTTP/1.1 200 OK             -> status line
Date: Sun, 26 June ...
Server: Apache
Content-Length: 35          
Content-Type: text/html     ... -> headers
                            -> blank line
<h1>Hello World</h1>        -> body
```
* status line: <u>HTTP 버전</u>, <u>HTTP 상태 코드</u>, <u>상태 메시지</u>
  + HTTP 상태 코드
    - 200 OK: 요청이 성공적으로 처리됨
    - 201 Created: 요청이 성공적으로 처리되어 새로운 리소스가 생성됨
    - 400 Bad Request: 클라이언트의 요청이 잘못되어 서버가 이해하지 못함
    - 404 Not Found: 지정한 리소스를 찾을 수 없음
    - 500 Interval Server Error: 서버 내부 오류로 요청을 처리할 수 없음
* headers: 응답에 대한 부가 정보
* body: 실제 응답 데이터

<br><br>

#### *매번 HTML 페이지 전체를 받는다면 어떻게 될까?*  
데이터 낭비가 일어나고, 사용자 입장에서도 느리고 화면이 깜빡거리는 일을 겪게 됨
=> **<u>화면의 뼈대</u>와 그 뒤에서 일어나는 <u>데이터 작업</u>을 분리하자**



## 2. 프론트엔드와 백엔드
> * 프론트엔드(Front-end): 사용자가 직접 보고 상호작용하는 화면, 사용자 인터페이스(UI)를 개발
> * 백엔드(Back-end): 사용자의 요청을 받아 실제 동작을 처리하고 데이터를 저장, 관리


### 데이터베이스 (Database, DB)
> 서버가 다루는 방대한 양의 데이터를 체계적으로 모아둔 저장소  
데이터베이스 관리 시스템(DBMS)으로 데이터베이스를 관리, 조작

<br><br>

클라이언트 입장에서 요청 메시지를  
*어떤 경로로, 어떤 메소드를 사용해서, 어떤 데이터를, 어떤 형식으로 보내야 하는가?*



## 3. REST API
### API (Application Programming Interface)
> 한 프로그램이 다른 프로그램의 기능이나 데이터를 사용할 수 있도록 미리 정해놓은 약속(규칙)이자 소통 창구  
**(어떻게 요청을 보내고, 응답할지)**

<br>

### REST (REpresentational State Transfer)
> **네트워크 아키텍처 스타일**로, HTTP의 장점을 최대한 활용 가능  
#### REST 구성 요소
* 자원 (Resource) - URI  
    모든 자원은 고유한 ID를 가지며, 이 ID는 **/student/1** 같은 HTTP URI이다
* 행위 (Verb) - Method  
    자원을 조작하기 위해 HTTP Method를 사용
* 표현 (Representation)  
    서버와 클라이언트가 데이터를 주고 받는 형식으로, JSON(JavaScript Object Notation) 형식이 일반적이다

<br>

### REST API
* API 설계 - API 명세서 작성
  + ex) 회원 상세 조회  
         HTTP Method: GET  
         URI: /members/{memberId}  

    **path variable**  
    :URI 일부를 변수처럼 사용해서 특정 자원을 식별하는 방식  
    /members/1  
    /members/2



## 4. Spring Boot
### 프레임워크
> 애플리케이션 개발을 쉽고 효율적으로 할 수 있도록 필요한 기본 구조와 공통 구조를 제공하는 뼈대

### Spring
> Java 엔터프라이즈 애플리케이션 개발에 사용되는 오픈소스 경량급 애플리케이션 프레임워크  
Java의 가장 큰 특징인 **객체 지향**을 잘 살려냄

### Spring Boot
> 복잡한 초기 설정 없이도 스프링 프레임워크를 아주 빠르고 쉽고 사용할 수 있게 해주는 도구

<br>

# 과제: localhost:8080 스크린샷
![localhost:8080](/week1/localhost8080.jpg "localhost:8080")

# 과제: 온라인 쇼핑몰 프로젝트 API 명세서
* 상품 기능
  + 상품 정보 등록  
  HTTP Method: POST  
  URI: /products  
  + 상품 목록 조회  
  HTTP Method: GET  
  URI: /products  
  + 개별 상품 정보 상세 조회  
  HTTP Method: GET  
  URI: /products/{productId}  
  + 상품 정보 수정  
  HTTP Method: PATCH  
  URI: /products/{productId}  
  + 상품 삭제  
  HTTP Method: DELETE  
  URI: /products/{productId}  
* 주문 기능
  + 주문 정보 생성  
  HTTP Method: POST  
  URI: /orders  
  + 주문 목록 조회  
  HTTP Method: GET  
  URI: /orders  
  + 개별 주문 정보 상세 조회  
  HTTP Method: GET  
  URI: /orders/{orderId}   
  + 주문 취소  
  HTTP Method: DELETE  
  URI: /orders/{orderId}
