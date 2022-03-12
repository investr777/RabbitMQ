package net.its.rmq.mng.api.cmn.exceptions;

import lombok.Getter;

@Getter
public abstract class ManagementApiException extends RuntimeException {

    private final String logMessage;
    private final int httpStatus;
    private final String errorDetails;
    private final Throwable cause;

    public ManagementApiException(
        String logMessage,
        int httpStatus,
        String errorDetails,
        Throwable cause
    ) {
        super(logMessage, cause);

        this.logMessage = logMessage;
        this.httpStatus = httpStatus;
        this.errorDetails = errorDetails;
        this.cause = cause;
    }
}
