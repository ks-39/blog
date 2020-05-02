package com.ks39.myblog.customizeException;

//自定义异常code和message的枚举，实现errorCode接口
public enum CustomizeExceptionCodeEunm implements ICustomizeErrorCode{

    //blog异常码
    ARTICLE_NOT_FOUND(2001,"该文章不存在"),
    CATEGORY_NOT_FOUND(2002,"该分类不存在"),
    TAG_NOT_FOUND(2003,"该标签不存在") ,
    SEARCH_NOT_FOUND(2004,"搜索结果不存在") ,


    //admin异常码
    SYS_ERROR(2005, "服务冒烟了，要不然你稍后再试试"),
    PARSE_TAG_ERROR(2006,"解析Tag出错"),
    ILLEGAL_ERROR(2007,"参数不合法")
    ;

    private Integer code;
    private String messag;

    CustomizeExceptionCodeEunm(Integer code, String message){
        this.code = code ;
        this.messag = message ;
    }

    @Override
    public String getMessage() {
        return messag;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
