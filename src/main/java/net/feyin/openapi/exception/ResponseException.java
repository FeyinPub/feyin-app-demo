package net.feyin.openapi.exception;

public class ResponseException extends RuntimeException {

    public ResponseException(){
        super();
    }

    public ResponseException(String msg) {
        super(msg);
    }
}
