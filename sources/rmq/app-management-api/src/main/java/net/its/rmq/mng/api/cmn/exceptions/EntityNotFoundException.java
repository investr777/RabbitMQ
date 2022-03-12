package net.its.rmq.mng.api.cmn.exceptions;

public class EntityNotFoundException extends ManagementApiException {

    public EntityNotFoundException(String logMessage, String errorDetails) {

        super(logMessage, 404, errorDetails, null);
    }
}
