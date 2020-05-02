package com.ks39.myblog.customizeException;

//自定义的异常类
public class CustomizeException extends RuntimeException{

    private String message;
    private Integer code;

    //当出现异常时，根据传入的参数获取枚举中相应的errorCode：
    //如：抛出：
    // if(blogById == null || blogById.isEmpty()){
    //    throw new CustomizeException(CustomizeExceptionCodeEunm.ARTICLE_NOT_FOUND) ;
    // }
    //      获取ARTICLE_NOT_FOUND的code和message（code：2001，message：该文章不存在）
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
