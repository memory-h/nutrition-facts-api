package capstonedesign.arlabel.exception.controller;

import capstonedesign.arlabel.exception.NoSuchDBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    // NoSuchDBException이 발생한 경우 "제품명을 다시 입력하세요." 문구와 HTTP Status Code 400 return
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerNoSuchDBException(NoSuchDBException e) {
        log.warn("[ExceptionHandler] NoSuchDBException", e);

        return "제품명을 다시 입력하세요.";
    }

    // ConnectException이 발생한 경우 "서비스를 사용할 수 없습니다. 나중에 다시 시도해 주세요." 문구와 HTTP Status Code 503 return
    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleConnectException(ConnectException e) {
        log.error("[ExceptionHandler] ConnectException", e);

        return "서비스를 사용할 수 없습니다. 나중에 다시 시도해 주세요.";
    }

}