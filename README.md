# 출퇴근 관리 애플리케이션
### 3단계
- 연차 신청
    - ~~연차는 하루 단위~~
    - ~~올해 입사한 직원은 11개의 연차, 그 외 직원은 15개의 연차~~
    - 연차를 사용할 때, 연차 사용일을 기준으로 며칠전 연차 등록
      - ~~며칠 전인지는 팀마다 다름~~
- 연차 조회
  - 각 직원은 `id`를 이용해 올해 남은 연차를 조회할 수 있다.
- 특정 직원의 날짜별 근무 시간을 조회하는 기능 V2
  - 연차를 사용한 날짜는 `usingDayOff : true`