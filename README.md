# 웹(웹 기술) 프로젝트

## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :white_check_mark: Vue.js |
| :black_square_button: Mobile Web | :black_square_button: Big Data | :black_square_button: TypeScript | :black_square_button: React |
| :black_square_button: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :black_square_button: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |

<br>

# 👭FANTALK 👫(by TIMEROOM)
> 코로나 19로 인해 오프라인 팬미팅을 하지 못하는 기획사와 팬들을 위한 1:1 실시간 팬미팅 서비스
>
> [배포링크](https://i6c105.p.ssafy.io)
>
> 2022.01.10 ~ 2022.2.18 (약 6주)

<br>

## 목차

- [프로젝트 소개](#프로젝트-소개)
  - [기획 의도](#기획-의도)
  - [팀원 및 역할](#팀원-및-역할)
  - [서비스 개요](#서비스-개요)
  - [개발 그라운드 룰](#개발-그라운드-룰)
- [프로젝트 디자인](#프로젝트-디자인)
  - [Service Architecture](#service-architecture)
  - [UI Design](#ui-design)
  - [ERD Design](#erd-design)
  - [REST API](#rest-api)
- [주요 기능](#주요-기능)
  - [포팅 매뉴얼](#포팅-매뉴얼)
  - [주요 기능 목록 및 설명](#주요-기능-목록-및-설명)

<br>

## 프로젝트 소개

### 기획 의도

<br>

### 팀원 및 역할

<br>

### 서비스 개요

<br>

### 개발 그라운드 룰

<br>

## 프로젝트 디자인

### Service Architecture

>주요 기술 스택
>
>실시간 영상 서비스의 빠른 구축을 위해 WebRTC, Kurento, Stun, Turn 등이 결집된 오픈소스 플랫폼 'OpenVidu'를 이용
>
>OpenVidu의 카메라 권한이 Https에서만 허용되기 때문에 'Nginx'를 이용하여 SSL 설정
>
>개발의 생산성을 위해 'Jenkins'를 이용하여 자동 빌드 및 배포를 실행
>
>'Jira'를 이용하여 애자일 개발을 지향하려고 노력(스프린트 주기 설정, 백로그 관리 등)

![service_architecture](README.assets/service_architecture.PNG)

<br>

### UI Design

<br>

### ERD Design

<br>

### REST API

<br>

## 주요기능

### 포팅 매뉴얼

<br>

### 주요 기능 목록 및 설명

<br>

- 기획 및 설계
  - [기획안](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ea428f2f-7001-4c17-92aa-6059a6bfcae7/%EA%B4%91%EC%A3%BC_1%EB%B0%98_5%ED%8C%80.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220216%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220216T192624Z&X-Amz-Expires=86400&X-Amz-Signature=81e8eb8626b128beb1f254550198e3cdca8fc352bcae11d5d76770b224abb8e9&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EA%25B4%2591%25EC%25A3%25BC_1%25EB%25B0%2598_5%25ED%258C%2580.pdf%22&x-id=GetObject)
  - [기능 요구사항](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/1a3a5a42-5269-46ff-8b3e-5a2302e1eacb/%EA%B8%B0%EB%8A%A5%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%EC%A0%95%EC%9D%98%EC%84%9C.md?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220216%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220216T192353Z&X-Amz-Expires=86400&X-Amz-Signature=5370d1c9a4741a01da23afcb8f815cc0e02ff5b221e46c2cab47952dee7f1f2e&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EA%25B8%25B0%25EB%258A%25A5%2526%25EC%259A%2594%25EA%25B5%25AC%25EC%2582%25AC%25ED%2595%25AD%25EC%25A0%2595%25EC%259D%2598%25EC%2584%259C.md%22&x-id=GetObject)
  - [Wireframe](https://www.figma.com/file/uqk1vMFdSWYdZBB8WKEOvc/TIMEROOM)
  - [ERD](https://www.erdcloud.com/d/pwrvaA9xfHJFnyYNi)
  - [API 설계서](https://www.notion.so/3e4605c453934dfb8346cbda5edac187#f93837ee850847768bd847fe40b805e2)



---

## How to Start

- [Detail](https://lab.ssafy.com/s06-webmobile1-sub2/S06P12C105/exec)
- 설치가이드
    ```
    #git clone
    
    #backend
    cd backend-java
    sudo chmod +x ./gradlew
    sudo ./gradlew clean build
    cd ..
    sudo docker run -d --name timeroom-backend --network host timeroom/backend-spring-boot-docker


    # openvidu-server
    cd /opt/openvidu
    /sudo openvidu start
    ```
- 포트 설정
    ```
    ufw allow ssh
    ufw allow 80/tcp
    ufw allow 443/tcp
    ufw allow 3478/tcp
    ufw allow 3478/udp
    ufw allow 5044/tcp
    ufw allow 9200/tcp
    ufw allow 40000:65535/tcp
    ufw allow 40000:65535/udp
    ufw allow 9090/tcp # 젠킨스
    ufw enable
    ```



