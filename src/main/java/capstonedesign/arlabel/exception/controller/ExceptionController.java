package capstonedesign.arlabel.exception.controller;

import capstonedesign.arlabel.exception.NoSuchDBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    // NoSuchDBException이 발생한 경우 "등록되지 않은 제품명입니다." 문구와 HTTP Status Code 200 return
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> handleNoSuchDBException(NoSuchDBException e) {
        log.warn("[ExceptionHandler] NoSuchDBException", e);

        Map<String, String> response = new HashMap<>();
        response.put("message", "등록되지 않은 제품명입니다.");

        return response;
    }

    // ConnectException이 발생한 경우 "서비스를 사용할 수 없습니다. 나중에 다시 시도해 주세요." 문구와 HTTP Status Code 503 return
    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, String> handleConnectException(ConnectException e) {
        log.error("[ExceptionHandler] ConnectException", e);

        Map<String, String> response = new HashMap<>();
        response.put("message", "서비스를 사용할 수 없습니다. 나중에 다시 시도해 주세요.");

        return response;
    }

}