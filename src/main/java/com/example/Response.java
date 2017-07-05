package com.example;

/**
 * Created by razamd on 2/15/2017.
 */
public class Response {
    private int code;
    private String status;
    private String message;
    private String devMessage;
    private String moreInfo;

    public Response() {
    }

//    public Response(int code, String status, String message) {
//        this.code = code;
//        this.status = status;
//        this.message = message;
//    }

    public Response(int code, String status, String message, String devMessage, String moreInfo) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.devMessage = devMessage;
        this.moreInfo = moreInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "{" +
               "\"code\":" + code + ","+
               "\"status\":\"" + status  + "\","+
               "\"message\":\"" + message  + "\","+
               "\"devMessage\":\"" + devMessage + "\","+
               "\"moreInfo\":\"" + moreInfo + "\""+
               '}';
    }
}
