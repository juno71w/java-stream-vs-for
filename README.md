# Java Stream vs For-Loop 실험 프로젝트
> 이 저장소는 Java의 `Stream`과 `for-loop` 성능 차이를 벤치마크하며 학습하기 위한 레포지토리입니다.

## 프로젝트 목적
다양한 경우에 따른 Stream 과 for-loop 의 실행 시간 차이 분석

- 데이터 크기 (예: 100, 10,000, 100,000)
- 데이터 타입 (primitive vs boxed)
- 병렬 처리 여부 (sequential vs parallel)

## 실험 환경
- 벤치마크 도구: JMH
- 실험 하드웨어: Mac mini M4, 32GB RAM

## 실행 방법

1. git clone

```bash
git clone https://github.com/juno71w/java-stream-vs-for.git
```

2. gradle 명령 실행

```bash
./gradlew jmh
```