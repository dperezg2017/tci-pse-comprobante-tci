package com.tci.pse.comprobante.core.exception;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "error")
public class ErrorResponse {
    public ErrorResponse(String message, Map<String, Object> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;

    private Map<String, Object> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
