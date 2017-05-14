package com.mmg.common;

/**
 * Created by yj on 2017/5/13.
 */
public class MyException extends Exception {
    private static final long serialVersionUID = 7147976717398138894L;
    private String msg;
    private String code;
//    private String detailMessage = "";

    public MyException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
/*    public MyException(String code,String message,String detailMessage){
        this(code,message);
        this.detailMessage = detailMessage;
    }*/

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

/*
    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
*/

}
