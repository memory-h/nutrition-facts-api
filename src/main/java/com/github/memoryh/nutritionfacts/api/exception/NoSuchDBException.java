package com.github.memoryh.nutritionfacts.api.exception;

public class NoSuchDBException extends RuntimeException{

    // 데이터베이스 조회에서 특정 조건에 맞는 데이터가 없을 때 발생하는 예외를 정의
    public NoSuchDBException(String message, Throwable cause) {
        super(message, cause);
    }

}