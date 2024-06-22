package com.github.memoryh.nutritionfacts.api.logtrace;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그의 상태 정보를 나타내는 클래스
 */
@Getter
@AllArgsConstructor
public class TraceStatus {

    private TraceId traceId; // 내부에 트랜잭션 ID와 level을 가지고 있다.

    private Long startTimeMs; // 로그 시작 시간

    private String message; // 로그 메시지

}