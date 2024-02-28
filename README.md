### Spring-boot + APM + Damo 
- docker 환경에 elk apm, damo 구성  
- 해당 레포지토리에 damo 키정보로 인하여 폴더가 포함되어있지 않습니다.
- spring build 후에 도커라이징 진행해야합니다.

### Docker build 명령어
```sh
$ ./gradlew clean bootJar --parallel  
$ docker build -t spring-test:1.0.0 .
```

### Damo log 확인
- docker 실행 후 컨테이너 터미널 접근하여 damo폴더에 로그 확인하실수 있습니다.
```sh
$ docker exec -it <컨테이너id> /bin/bash
```

### 암복호화 API 확인방법
- swagger에 접속, 암복호 api 호출
- `localhost/swagger-ui/index.html`

### APM 설정
- docker file에 spans_enable 활성 확인
```
// 활성화시 damo 미동작
ELASTIC_APM_PROFILING_INFERRED_SPANS_ENABLED=true
```