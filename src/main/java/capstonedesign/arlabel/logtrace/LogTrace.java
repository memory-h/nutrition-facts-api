package capstonedesign.arlabel.logtrace;

public interface LogTrace {

    // 클라이언트의 요청 정보(URL, IP, User-Agent)를 포함하는 로그를 출력하는 메서드
    void requestInfo(String requestUrl, String ipAddress, String userAgent);

    // 시작 로그를 출력하는 메서드
    TraceStatus begin(String message);

    // 종료 로그를 출력하는 메서드
    void end(TraceStatus status);

    // 스레드 로컬 값 제거
    void remove();

    // 예외 발생 시 로그를 출력하는 메서드
    void exception(TraceStatus status, Exception e);

}