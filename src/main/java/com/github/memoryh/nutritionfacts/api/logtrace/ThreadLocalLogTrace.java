package com.github.memoryh.nutritionfacts.api.logtrace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    // 로그 메시지에 사용될 Prefix들을 정의한다.
    private static final String START_PREFIX = "--> ";
    private static final String COMPLETE_PREFIX = "<-- ";
    private static final String EX_PREFIX = "<X-- ";

    // ThreadLocal을 사용하여 각 스레드에 고유한 TraceId를 저장한다. (동시성 문제 해결)
    private final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    // 클라이언트의 요청 정보(URL, IP, User-Agent)를 포함하는 로그를 출력하는 메서드
    @Override
    public void requestInfo(String requestMethod, String requestUrl, String ipAddress, String userAgent) {
        syncTraceId(); // 현재 스레드에 대한 TraceId를 업데이트하거나 초기화한다.

        TraceId traceId = traceIdHolder.get();

        log.info("[{}] [Method]: {}, [URL]: {}, [User IP]: {}, [User-Agent]: {}", traceId.getId(), requestMethod, requestUrl, ipAddress, userAgent);
    }

    // 시작 로그를 출력하는 메서드
    @Override
    public TraceStatus begin(String message) {
        syncTraceId(); // 현재 스레드에 대한 TraceId를 업데이트하거나 초기화한다.

        TraceId traceId = traceIdHolder.get();

        long startTime = System.currentTimeMillis(); // 작업의 시작 시간을 저장한다.

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        // TraceStatus 객체를 생성하여, 현재 TraceId, 작업 시작 시간, 메시지를 포함하여 반환
        return new TraceStatus(traceId, startTime, message);
    }

    // 종료 로그를 출력하는 메서드
    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    // 예외 발생 시 로그를 출력하는 메서드
    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    // 스레드 로컬 값 제거
    public void remove() {
        traceIdHolder.remove();
    }

    private void complete(TraceStatus status, Exception e) {
        long endTime = System.currentTimeMillis(); // 작업의 종료 시간을 저장한다.
        long resultTime = endTime - status.getStartTimeMs(); // 작업의 총 수행 시간을 계산한다.

        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time = {}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTime);
        } else { // 예외 발생 시
            log.warn("[{}] {}{} time = {}ms ex = {}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTime, e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();

        traceIdHolder.set(traceId.createPreviousId());
    }

    // TraceId 동기화
    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();

        if (traceId == null) { // TraceId가 없으면 새로 생성
            traceIdHolder.set(new TraceId());
        } else { // 있으면 createNextId() 메서드 호출
            traceIdHolder.set(traceId.createNextId());
        }
    }

    // 로그 레벨에 따라 공백과 Prefix를 추가한다.
    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }

        return sb.toString();
    }

}