package com.qaprosoft.zafira.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Exception that is meant to be thrown when error, related to processing application own resources (e.g. application
 * configuration and/or properties are invalid) occurs.
 * Reserved range 2100 - 2119
 */
public class ProcessingException extends ApplicationException {

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public enum ProcessingErrorDetail implements ErrorDetail {

        WIDGET_QUERY_EXECUTION_ERROR(2100),
        UNPROCESSABLE_XML_ENTITY(2101),
        UNPROCESSABLE_DOCUMENT(2102),
        MALFORMED_FREEMARKER_TEMPLATE(2103);

        private final Integer code;
        private String messageKey;

    }

    public ProcessingException(ProcessingErrorDetail errorDetail, String message) {
        super(errorDetail, message);
    }

    public ProcessingException(ProcessingErrorDetail errorDetail, String message, Throwable cause) {
        super(errorDetail, message, cause);
    }

}
