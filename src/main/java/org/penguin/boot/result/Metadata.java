package org.penguin.boot.result;

public class Metadata implements java.io.Serializable {

    private static final long serialVersionUID = -6214457635468904106L;

    private String message;

    public Metadata(ReturnCode code) {
        this.code = code;
        this.message = code.getReasonPhrase();
    }

    public Metadata(ReturnCode code, String message) {
        this.code = code;
        this.message = message;
    }

    private ReturnCode code = ReturnCode.OK;

    public int getCode() {
        return code.getValue();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
