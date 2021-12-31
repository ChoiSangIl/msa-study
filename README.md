# msa-study

### H2 db docker setting
docker run -d -p 1521:1521 -p 8090:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2
#### http://localhost:8090 접속
![h2 setting windows](https://user-images.githubusercontent.com/19385132/147815639-6e4149ad-8eac-4c5c-a894-bb2f8f625139.png)

### 미래의 나를 위한 MSA를 이해하는데 도움이 되는 링크
[[2019] PAYCO 쇼핑 마이크로서비스 아키텍처(MSA) 전환기](https://www.youtube.com/watch?v=l195D5WT_tE)
