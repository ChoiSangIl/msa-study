# msa-study
MSA를 공부하며 이론적인 이해도 중요하지만 실제로 구현하며 고민해보고 싶어서 시작한 스터디입니다.  
#### 공부하고자 하는 것
* MSA(Spring Cloud) - Api Gateway, Eureka, Feign 
* JPA 
* TDD

# 프로젝트 환경
## H2 DB
### docker setting
~~docker run -d -p 1521:1521 -p 8090:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2~~ -> h2 버전 고정(jpa 기본키 전략 identity insert pk null error)
```
docker run -d -p 1521:1521 -p 8090:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=msa-study-db oscarfonts/h2:1.4.199
```

#### http://localhost:8090 접속
![h2 setting windows](https://user-images.githubusercontent.com/19385132/147815639-6e4149ad-8eac-4c5c-a894-bb2f8f625139.png)

## API 문서
#### APIGATE WAY SWAGGER URL
[http://localhost:8080/webjars/swagger-ui/index.html](http://localhost:8080/webjars/swagger-ui/index.html)

## KAFKA DOCKER IMAGE
[DOCKER COMPOSE 파일 참고](https://github.com/ChoiSangIl/msa-study/tree/master/msa-study-kafka-docker)

## FRONT (REACT)
[MSA-STUDY-FRONT-REACT 참고](https://github.com/ChoiSangIl/msa-study/tree/master/msa-study-front-react)
```
npm install
npm start
http://localhost:3000
```

#### front example
![web_example](https://user-images.githubusercontent.com/19385132/158193748-6cf2fad8-80fe-4ed6-92e0-3bb0e254a071.png)


# branch 설명
MSA 구조를 공부하며 단순한 API 호출부터 점차 개선해나가는 과정을 브런치별로 저장해보고자 한다.  
아래에는 브런치별 설명과 개선해야될 부분들을 정리한다.
## msa-study-01
기존 모놀로그 서비스에서 서비스 로직은 직접 함수를 실행하는 형태 였다면, MSA구조에 맞게 도메인을 분리하여 API를 호출하는 형태로 개발 했다.  
#### 주문 api 호출 url
http://localhost:8080/order
#### 주문 비지니스 로직
1. 재고체크 API 호출
2. 주문서생성
3. 결제 API 호출
#### 이렇게 단순하게 API를 호출하는 형태로만 개발하면 어떤 문제가 있을까? 또 이 문제를 어떻게 개선할수 있을까?
* 전체 실행시간으로 봤을 때, 모놀로그 서비스보다 + 알파의 시간이 든다(API호출)
* API 호출이 실패한다면 !?
* 공통 Util은 MSA 환경에서 어떻게 처리할 수 있을까?
* 각 도메인별 서비스 URL주소가 하드코딩 되어있다.(클라우드 서비스에서는 IP가 동적으로 변경될 수 있다)
* 서비스 로직에서 API호출 하는 부분이 강하게 결합되어 있다.
* 이렇게 단순하게 나누기만 해서는 MSA에 의미가 있을까?

## msa-study-02
msa-study-01에서 소스마다 종속되어 있는 restful api 호출 주소를 eureka를 이용하여 제거하였다. 올라오는 서비스들은 eureka에 자신의 정보를 등록하며, 다른api를 호출 할때에는 eureka에서 서비스url을 가져온다. 이로 인해서 클라우드상에 동적으로올라오는 서비스들의 호출이 가능해졌다.

## msa-study-03
spring cloude feign을 적용하여 api 호출하는 부분을 인터페이스를 이용하여 분리하였다.

# MSA를 공부하며 참고했던 자료
[[2019] PAYCO 쇼핑 마이크로서비스 아키텍처(MSA) 전환기](https://www.youtube.com/watch?v=l195D5WT_tE)

# 이런저런 이슈들
[[Docker에서 외부에 할당된 Port정보로 Eureka에 서비스 정보를 등록 할 수 없을까?]](https://sang12.co.kr/283/Docker-%EC%9E%AC%EB%AF%B8%EC%9E%88%EB%8A%94-%EB%85%BC%EC%9F%81%28Docker-Eureka-Client-detect-exposed-ports%29)
