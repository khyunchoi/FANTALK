# JWT

> RFC 7519 웹 표준으로 지정이 되어있고 JSON 객체를 
사용해서 토큰 자체에 정보들을 저장하고 있는 Web Token

<br>

## 구성

- Header
    - Signature를 해싱하기 위한 알고리즘 정보
- Payload
    - 시스템에서 실제로 사용될 정보에 대한 내용(client - server )
- Signature
    - 토큰의 유효성 검증을 위한 문자열

<br>

## JWT의 장단점

- 중앙의 인증서버, 데이터 스토어에 대한 의존성 X, 시스템 수평 확장 유리
- Base64 사용 > URL, Cookie, Header 모두 사용 사능

<br>

- Payload의 정보가 많아지면 네트워크 사용량 증가
- 토큰이 클라이언트에 저장되므로 서버에서 클라이언트의 토큰을 조작할 수 없음

<br>

---

# JPA

<br>

![jpa](README.assets/jpa.jpg)


## JPA 사용 이유

<br>

Application은 JAVA, 객체지향 언어로 개발하는데 데이터는 RDB에 저장한다면 패러다임의 불일치가 발생
<br>

~~***패러다임 불일치 : OOP와 RDB의 데이터 표현방식과 다루는 방법이 달라 일어나는 현상***~~

객체를 RDB에 저장하기 위해 객체를 SQL로 변환해야한다.<br>
이 변환과정을 개발자가 해야하는데 이 과정이 굉장히 불편함 ~~MyBatis~~

<br>

JPA는 이 과정을 대신해준다.

<Br>

## 영속성 컨텍스트

> Entity를 영구 저장하는 환경(논리적 개념)

<br>

애플리케이션과 DB 사이에서 객체를 보관하는 가상의 DB 같은 역할을 해줌.<Br>

<br>
영속성 컨텍스트의 기능
- 1차 캐시
- 동일성 보장
- 쓰기 지연(flush?)
- 변경 감지(snap shot)
- 지연 로딩과 즉시 로딩


<Br>

. . . 
JPA는 __트랜잭션 격리 수준__ 을 2단계(0~3) 아래로 낮춰도 JPA 애플리케이션에서 2단계까지 보장해준다고 한다.<br>
*이게 동일성 보장?

---

<h2 id = "Isolation Level">트랜잭션 격리 수준</h2>

<br>
직렬성은 프로그래머가 트랜잭션의 코드를 작성할 때에 __동시성을 고려하지 않을 수 있도록 해주는__ 유용한 개념이다.<br>
모든 트랜잭션이 각자 혼자 수행되었을 때에 데이터베이스의 일관성을 깨지않는다면,<Br>
직렬성은 이들을 동시에 수행하여도 일관성을 유지할 수 있다는 것을 보장한다.<br>
<br>

SQL 표준에 지정된 고립성 수준

1. Read Uncommited (Level 0)
> - 트랜잭션 수행 중이거나 __아직 commit 되지 않은 데이터를 다른 트랜잭션에서 Read 하는 것을 허용한다.__<br>
> - Dirty Read, Non Repeatable Read, Phantom Read 발생 가능

<br>

2. Read Commited (Level 1)
> - 트랜잭션 수행이 완료되고 __commit 된 데이터만 다른 트랜잭션에서 Read__ 하도록 허용<br>
> - Non Repeatable Read, Phantom Read 발생가능<br>
> - 일반적으로 Default로 설정하는 레벨(DBMS)

<br>

3. Repeatable Read (Level 2)
> - 특정 트랜잭션에서 __읽고 있는 데이터는 다른 트랜잭션에서 수정/삭제가 불가능__ 하다.<br>
> - __데이터 삽입은 가능하다.__<br>
> - Phantom Read 발생가능<br>



4. Serializable (Level 3)
> - 특정 트랜잭션에서 __읽고 있는 데이터는 다른 트랜잭션에서 수정/삭제/삽입 모두 불가능__ 하다.
> - 모든 이상 현상 방지 가능<br>
> - 성능은 하락함<br>


<br>

위의 모든 고립성 수준은 추가적으로 Dirty Write를 허용하지 않는다.<br>
= commit 또는 중단되지 않은 다른 트랜잭션이 기록한 데이터 항목에 대한 기록을 허용하지 않는다.

<br>

## 이상현상

<BR>

이상 현상 | 설명 |
---| ---|
|Dirty Read|어떤 트랜잭션에서 아직 __실행이 끝나지 않은 트랜잭션에 의한 변경사항__ 을 보게 되는 경우|
|Non Repeatable Read |어떤 트랜잭션이 같은 쿼리를 2번 실행하는데 __그 사이에 다른 트랜잭션이 수정/삭제를 하여__ 다른 값이 나오는 경우|
|Phantom Read|어떤 트랜잭션이 같은 쿼리를 2번 실행하는데 __그사이에 없던 레코드가 추가된 경우__|

<br>

- Dirty Read<br>

![dirty-read](README.assets/dirty-read.jpg)

<br>

- Non Repeatable Read<br>

![non-repeatable-read](README.assets/non-repeatable-read.jpg)

<br>

- Phantom Read<br>

![phantom-read](README.assets/phantom-read.jpg)


<br>

...

*지연 로딩과 즉시 로딩

---

## 실습 내용

간단한 모든 회원 조회 API 구현

스켈레톤 코드에 추가

<br>
컨트롤러

![api](README.assets/api.PNG)

<br>
서비스

![service](README.assets/service.PNG)

<br>
결과(swagger)

![res](README.assets/res.PNG)
