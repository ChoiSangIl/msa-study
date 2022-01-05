# msa-study-01
기존 모놀로그 서비스에서 서비스 로직은 직접 함수를 실행하는 형태 였다면, MSA구조에 맞게 도메인을 분리하여 API를 호출하는 형태의 예제
#### 이렇게 단순하게 API를 호출하는 형태로만 개발하면 어떤 문제가 있을까? 또 이 문제를 어떻게 개선할수 있을까?
* 전체 실행시간으로 봤을 때, 모놀로그 서비스보다 + 알파의 시간이 든다(API호출)
* 각 도메인별 서비스 URL주소가 하드코딩 되어있다.(클라우드 서비스에서는 IP가 동적으로 변경될 수 있다)
* 서비스 로직에서 API호출 하는 부분이 강하게 결합되어 있다.

### H2 db docker setting
docker run -d -p 1521:1521 -p 8090:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2
#### http://localhost:8090 접속
![h2 setting windows](https://user-images.githubusercontent.com/19385132/147815639-6e4149ad-8eac-4c5c-a894-bb2f8f625139.png)

### 미래의 나를 위한 MSA를 이해하는데 도움이 되는 링크
[[2019] PAYCO 쇼핑 마이크로서비스 아키텍처(MSA) 전환기](https://www.youtube.com/watch?v=l195D5WT_tE)
