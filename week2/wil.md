25-2 백엔드 정규 스터디 2주차 Weekly I Learned
===========================================

## 1. Spring Layered Architecture
* ### Browser -> (프론트엔드)
* ### Controller
* ### Service
* ### DAO(Data Access Object)
* ### DB
비유: `손님` <-> `웨이터` <-> `주방장` <-> `창고 관리자` <-> `창고`

### DTO(Data Transfer Object)
    데이터 전송 객체
    - 소통 목적에 맞는, 필요한 정보만 전달
    - Browser에서 DAO까지 사이에서 서로 전달됨
### Entity
    DB 테이블과 매핑되는 핵심 객체
    - 외부 직접 노출 금지
    - DAO와 DB 사이에서만 전달됨 

<br>

### * 패키지 구조: 계층형 / 도메인형
#### 계층형: 애플리케이션을 기능별로 나눔
> - com.example.shop
>   * controller
>     + MemberController.java
>     + ProductController.java
>     + OrderController.java
>   * service  
>   * repository
>   * domain
>   * dto
>   * config
>   * exception
#### 도메인형: 도메인 관련 모든 클래스를 포함
> - com.example.shop
>   * member
>     + controller
>     + service  
>     + repository
>     + domain
>     + dto
>     + exception
>   * product  
>     + ...
>   * Order  
>     + ...
>   * config

<br>

## 2. Controller Layer
### Controller
> * HTTP 요청 / 응답  
> * 특정 endpoint(URL)로 요청을 가장 먼저 처리
> * DTO를 사용하여 Service 계층과 데이터 주고받음  

### Annotation
---
> `@Controller`  
\- 컨트롤러 명시  
`@ResponseBody`  
\- 클래스 안의 메소드들의 반환값들을 HTTP 응답 본문에 직접 작성  
`@RequestBody`  
\- JSON인 HTTP body을 객체로 변환  
`@RestController`  
\- @Controller와 @ResponseBody를 동시에 적용  

> `@PostMapping("요청할 경로")`  
\- HTTP POST request  
`@GetMapping("요청할 경로")`  
\- HTTP GET request  
`@PatchMapping("요청할 경로")`  
\- HTTP PATCH request  
`@DeleteMapping("요청할 경로")`  
\- HTTP DELETE request  
`@RequestMapping("요청할 경로")`  
\- 위 annotation들의 default, 중복된 경로를 묶을 수 있음  
`@PathVariable`  
\- REST API의 path variable  

<br>

### * Lombok Annotation

> `@RequiredArgsConstructor`  
\- 모든 필드값을 파라미터로 받는 생성자 생성  
`@Getter`  
\- getter(ex.getName() 등)를 자동으로 삽입  
`@Setter`  
\- setter(ex.setName(String..) 등)를 자동으로 삽입  

<br>

### ResponseEntity 클래스
    HttpEntity를 상속하는 클래스로, 
    Http Response와 관련된 설정들을 응답에 추가할 수 있도록 해줌

| 메서드 | 상태 코드 | Body | 사용 시점 |  
| ----- | -------- | ---- | ------- |  
| `ok().build()` | 200 OK | 없음 | 성공, 데이터 불필요 |
| `ok(data)` 혹은 `ok().body(data)` | 200 OK | data | 성공, 데이터 반환 |
| `created(uri).build()` | 201 created | 없음 | 생성 성공 |
| `noContent().build()` | 204 No Content | 없음 | 삭제 성공 |

<br>

## 3. Service Layer
### Service
    애플리케이션의 비지니스 로직이 담기는 계층  
    - 레포지토리 계층과 소통 with 엔티티 또는 DTO
    - 컨트롤러와 레포지토리 사이의 중간 다리

### Annotation
---
> `@Service`  
\- 서비스 명시  
`@Transactional`  
\- 하나의 오류로 모든 request 처리가 막히는 현상 해결  
\- 트랜잭션 단위로 처리하여 원자성 보장  
`@Transactional(readOnly = true)`  
\- 읽기 전용  

<br>

### * JPA(Java Persistence API)
    자바의 ORM기술을 쉽게 구현하도록 도와주는 API  
    (DB를 쉽게 이용하기 위한 API)  

JPA 사용 시 `@Transactional` 사용

<br>

## 4. 스프링 빈 & 의존성 주입

### Spring 애플리케이션 구조
#### Tomcat
> <u>내장서버</u>, 먼저 HTTP Requst받음, 여길 거쳐서 Spring Container로 감
#### Spring Container
> <u>스프링 빈 저장소</u>, = Application Context

<br>

### Spring Bean
    애플리케이션 전역에서 사용할 공용 객체  
    - Spring Container에 Bean을 저장 및 사용
    - 필요한 Bean을 Spring 프레임워크가 자동으로 가져다 줌
    - Bean을 요구하는 객체 또한 Bean
#### Bean 등록 방법
* 설정 파일 작성 (수동 등록)  
* 컴포넌트 스캔 (자동 등록)  
    + Annotation
        ---
        > `@ComponentScan`  
        \- 어떤 클래스가 Spring Bean인지 찾아서 등록  
        `@Component`  
        \- Spring Bean으로 표시  
    
    @ComponentScan 포함: `@SpringBootApplication`  
    @Component 포함: `@Controller`, `@Service`, `@Repository`, `@Entity` 등

<br>

### 의존성 주입(Dependency Injection, DI)
    내가 의존하는 객체를 직접 생성하지 않고 밖에서 주입받는 것
Bean 사이에서 Bean 요구함  
사용하는 이유: **생성한 객체를 계속 사용**해서 <u>더 효율적</u>

#### 의존성 주입 방법
* 생성자 주입
    1. final 선언
    2. 생성자에 `@Autowired` 사용
        + 필요한 의존 객체의 타입에 해당되는 Bean을 찾아 주입함  

        (생성자가 하나라면 생략 가능)  
    
    <br>

    이 과정을 단축한 것이 `@RequiredArgsConstructor`
* 필드 주입
* 수정자 주입 (세터 주입)