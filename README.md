# 🚀Intro.
해당 내용은 인프런 강의를 들으며 Spring Boot - React 기반의 프로젝트를 실습하며 부족한 내용을 보완하기 위해 작성함.  
아직 작성중 ~~~~ 

<br>

# 📚 Index

1. [Spring](#spring)
2. [JPA](#JPA)
3. [Docker](#Docker)
4. 
<br>

# 1. 🌱 Spring

<br>

## 1.1 🧠 스프링 핵심 개념

### ● Bean과 ApplicationContext

<br>

### ● Dependency Injection (DI : 의존성주입)
- 객체 간의 의존 관계를 외부에서 주입해주는 것  
- 클래스 내부에서 직접 new로 생성하지 않고 필요한 객체를 스프링이 대신 만들어서 넣어줌  

<br>

❌예) 직접결합 (강한결합)

    public class Store {
        private Pencil pencil = new Pencil();
    }

- Store 클래스가 Pencil에 직접 의존
- 나중에 Pen으로 바꾸려면 코드를 수정해야 함
- 테스트, 유지보수, 확장성에 불리함

<br>

✅예) 느슨한결합 (DI)

```java
    public class Store {

        private final Pencil pencil;

        public Store(Pencil pencil) {
            this.pencil = pencil;
        }
    }
```

- 외부에서 Pencil을 주입받음
- 클래스는 구현이 아닌 인터페이스/추상화에 의존
- 다양한 객체를 주입 가능 → 확장성 ⬆️

<br>

### ● Aspect-Oriented Programming (AOP : 관점 지향 프로그래밍)
- 관심사 분리를 위한 프로그래밍 패러다임, 공통적으로 반복되는 기능을 따로 분리해서 관리
- 핵심 로직과 관련 없는 관심사(Logging, Security, Transaction 등)를 모듈화하여 코드에 끼워 넣을 수 있음

<br>

🧩 핵심 개념

| 용어 | 설명 |
|------|------|
| **Aspect** | 공통 기능을 모듈화한 클래스 (`@Aspect`) |
| **JoinPoint** | 공통 기능이 삽입될 위치 (ex. 메서드 실행 시점) |
| **Advice** | 공통 기능 자체 (로깅, 트랜잭션 등) |
| **Pointcut** | 어떤 JoinPoint에 Advice를 적용할지 조건 지정 |
| **Weaving** | Advice를 실제 코드에 삽입하는 과정 |

<br>

🛠 Advice 종류

| 어노테이션 | 설명 |
|------------|------|
| `@Before` | 메서드 실행 전에 수행 |
| `@After` | 메서드 실행 후에 수행 (성공/실패 관계없이) |
| `@AfterReturning` | 메서드가 정상 종료된 후 수행 |
| `@AfterThrowing` | 예외 발생 시 수행 |
| `@Around` | 메서드 실행 전후 전체 감싸서 수행 (가장 많이 사용됨) |

<br>

사용 예시 - 실행 시간 측정

```java
    @Aspect
    @Component
    public class LogAspect {  

        @Around("execution(* com.example.demo.service.*.*(..))")
        public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed(); // 실제 메서드 실행
            long end = System.currentTimeMillis();  

            System.out.println("실행 메서드: " + joinPoint.getSignature());
            System.out.println("실행 시간: " + (end - start) + "ms");  

            return result;
        }
    }
```

<br>

- AOP 주요 사용처
1. 로깅 (Logging)
2. 트랜잭션 처리 (@Transactional)
3. 인증 및 권한 처리 (ex. 관리자만 접근)
4. 예외 처리 (Exception Handling)
5. 캐싱 처리 (Caching)
6. 성능 모니터링 (메서드 실행 시간 측정 등)

<br>

### ● 라이프사이클과 스코프

<br>

## 1.3 🧩 스프링 MVC

### ● Controller / Service / Repository 구조

<br>

### ● 요청 흐름과 DispatcherServlet

<br>

### ● Model, View, Response 구조

<br>

## 1.4 🚀 스프링 부트

### ● Spring Boot 개념 및 장점

<br>

### ● Starter, AutoConfiguration

<br>

### ● 의존성 설정 및 실행 구조

<br>

## 1.5 🗃 JPA와 DB 연동

### ● Spring Data JPA

<br>

### ● Entity, Repository 설계

<br>

### ● DTO와 Entity 변환

<br>

## 1.6 🧪 스프링 테스트와 트랜잭션

### ● 단위 테스트 & 통합 테스트

<br>

### ● @Transactional 사용법

<br>

## 1.7 🧰 기타 개념

### ● Validation 처리

<br>

### ● Exception Handling

<br>

### ● 포맷터와 데이터 바인딩

<br>


## 2. 🏗 프로젝트 구조 & 레이어드 아키텍처
### ● Domain, DTO, Entity, Repository, Service, Controller의 역할

<br>

### ● DTO ↔ Entity 변환 로직 (toEntity, toDTO)

<br>

### ●Builder 패턴, 기본 생성자, 불변 객체 개념

<br>
<br>

## 3. 💾 JPA와 데이터 처리
### ● JPA 기본 개념과 어노테이션 (@Entity, @Id, @Column, @GeneratedValue, etc.)

<br>

### ● Repository 인터페이스와 CRUD

<br>

### ● 트랜잭션 처리 (@Transactional)

<br>
<br>

## 4. 📦 REST API 설계 & 예외 처리
### ● 컨트롤러에서의 요청 처리 (@RestController, @PostMapping, @RequestBody)

<br>

### ● 예외 처리 (@RestControllerAdvice, @ExceptionHandler)

<br>

### ● ResponseEntity로 응답하기

<br>
<br>


