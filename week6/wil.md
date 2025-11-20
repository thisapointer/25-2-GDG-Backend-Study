25-2 백엔드 정규 스터디 6주차 Weekly I Learned
===========================================

## 1. 유효성 검사
의도하지 않은 상황이 발생할 때 대처할 방안이 필요하다.  
잘못된 형식의 입력 데이터가 들어왔을 때 500 Internal Server Error를 출력함  
-> 클라이언트의 잘못, 4xx 상태 코드가 떠야 정상

    요청으로 들어오는 데이터가 '올바른 형식'인지 검사하는 것

* 입력 데이터 형식을 검사함  
* Spring: **DTO**에서 유효성을 검사  
<br>

#### 이를 위해 build.gradle에 유효성 검사 의존성 추가  
```
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

### Annotation
> **DTO의 Request들의 각 속성들에 추가**  
> (제약 사항)  
> `@NotNull(message = )`  
> \- Null값이 될 수 없음을 명시, 에러 메시지 출력  
> `@Size(min =, max =, message = )`  
> \- 크기의 최솟값과 최댓값 설정, 에러 메시지 출력  
> `@Pattern(regexp =, message = )`  
> \- 정해진 형식(정규식) 지정, 에러 메시지 출력  
> 
> **Controller 메소드의 매개변수 Request body에 추가**  
> `@Valid`  
> \- 유효성 검사 실행  

<br>

## 2. 예외 처리
## 2.1. Global Exception Handler
    도메인에 상관없이 공통적으로 예외 처리를 하는 핸들러
    - 에러 정보 반환용 DTO
    - AOP(관점 지향 프로그래밍)
* Spring 제공
* 예외 종류에 따라 response를 설정 가능
* **본 애플리케이션 전역**의 모든 에러 처리 방법을 결정

<br>

### *패키지 구조 추가
> - com.example.shop
>   * <u>common</u>
>       + dto
>           - ErrorResponse.java
>       + exception
>           - BadRequestException.java
>           - NotFoundException.java
>       + message
>           - ErrorMessage.java
>       + <u>GlobalExceptionHandler.java</u>

### Annotation
> **GlobalExceptionHandler에 추가**  
> `@ControllerAdvice`  
> \- 모든 컨트롤러의 공통 관심사를 중앙에서 처리  
>
> **본 클래스의 메소드에 추가**  
> `@ExceptionHandler(value = 객체 클래스)`  
> \- 해당 객체 타입의 에러가 발생하면,  
     해당 에러 타입을 다루는 핸들러가 Controller 메소드 대신 Response Body를 생성 & 응답  

> \-- Exception.class  
> 모든 에러 클래스의 공통 부모,  
> 만든 특정 핸들러에서 처리 못한 예외는 이 부분에서 처리 (500 Internal Server Error)  
> \-- MethodArgumentNotValidException.class  
> 유효성 검사 했을 때 나온 에러를 처리함  
> \-- (커스텀 클래스)

<br>

### ResponseEntity 클래스
| 메서드 | 상태 코드 | Body | 사용 시점 |  
| ----- | -------- | ---- | ------- |  
| `internalServerError().body(에러 메시지)` | 500 Internal Server Error | 에러 메시지 | 예외 처리 |  
| `badRequest().body(에러 메시지)` | 400 Bad Request | 에러 메시지 | 잘못된 요청 |  
| `notFound().build()` | 404 Not Found | 없음 | 데이터 찾지 못할 때 |  
| `status(HTTP 상태 번호).body(data)` | (매개 변수에 따른 상태 코드) | data | (상태에 맞는 사용 시점) |  

<br>

### *AOP(Aspect-Oriented Programming)
    관점 지향 프로그래밍
    - 객체 지향 프로그래밍 보완 개념

| OOP(객체 지향) | AOP(관점 지향) |  
| ----- | ----- |
| **핵심 기능을 모듈화** | **부가 기능을 모듈화** |  
| 비즈니스 로직을 클래스와 메소드로 구조화 | 여러 클래스에 걸쳐 반복되는 공통 기능을 분리 |  
| 예) 회원 관리, 주문 처리, 상품 관리 등 | 예) 로깅, 트랜잭션, 보안, 예외 처리 등 |  

<br>

### 2.2. 커스텀 예외 처리
    RuntimeException을 상속한 커스텀 예외 클래스  
    Global Exception Handler에 등록 -> 에러 원인 명확히 알 수 있음
-> `exception` 디렉터리 안의 예외 클래스들

<br>

### 2.3. 에러 메시지 클래스
-> `message` 디렉터리 안의 `ErrorMessage.java` 클래스  

예외 메시지 문자열이 중복 사용되므로 추가/수정하기 힘들 수 있다.  
따라서 상수로 정의한다.  
ex) ErrorMessage.LOGIN_ID_NOT_NULL  

<br>

## 3. API 문서화
* API 사용 설명서 공유  
* 백엔드 API 명세를 문서로 공유
* 클라이언트(프론트엔드)와 소통/협업 시 API 문서를 공유  

*OpenAPI : API 표준 명세

### Swagger -> API 문서화 도구  

1. spring doc 사용 -> OpenAPI 규격의 API 문서 생성  
2. Swagger UI 사용 -> API 문서에 Swagger 디자인 적용  


#### 사용하기 위해 spring doc 의존성 추가
```
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14'
```
<br>

접속
```
http://localhost:8080/swagger-ui/index.html
```

### Annotation
> **Controller에 작성**  
> `@Tag(name =, description = )`  
> \- API 그룹화, 본 도메인의 이름과 설명 표기  
> 
> **Controller 메소드에 작성**  
> `@Operation(summary =, description = )`  
> \- API의 요약과 설명  
> `@ApiResponse(responseCode =, description = )`  
> \- 단일 코드 응답 설명, 내보낼 때 어떤 응답을 내보낼지 명세(응답 코드, 설명) 표기

<br>

# 과제: 4xx 에러 메시지 응답 postman 스크린샷
![Exception](/week6/exception.jpg "400 Bad Request")

# 과제: Swagger UI 확인 스크린샷
![Swagger](/week6/swagger.jpg "Swagger UI")