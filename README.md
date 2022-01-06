# msa-study
MSA를 공부하며 이론적인 이해도 중요하지만 실제로 구현하며 고민해보고 싶어서 시작한 스터디입니다.  
#### 공부하고자 하는 것
* MSA(Spring Cloud)
* JPA 
* TDD

# 프로젝트 환경 셋팅
## H2 DB
### docker setting
docker run -d -p 1521:1521 -p 8090:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2
#### http://localhost:8090 접속
![h2 setting windows](https://user-images.githubusercontent.com/19385132/147815639-6e4149ad-8eac-4c5c-a894-bb2f8f625139.png)

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

# MSA를 공부하며 참고했던 자료
[[2019] PAYCO 쇼핑 마이크로서비스 아키텍처(MSA) 전환기](https://www.youtube.com/watch?v=l195D5WT_tE)
