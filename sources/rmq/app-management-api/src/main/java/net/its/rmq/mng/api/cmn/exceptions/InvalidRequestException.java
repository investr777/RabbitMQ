package net.its.rmq.mng.api.cmn.exceptions;

public abstract class InvalidRequestException extends ManagementApiException {

    public InvalidRequestException(String logMessage, String detailMessage, Throwable throwable) {

        super(logMessage, 400, detailMessage, throwable);
    }
}
