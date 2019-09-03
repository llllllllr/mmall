package com.mmall.common;

import net.sf.jsqlparser.schema.Server;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }

    private ServerResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    @JsonIgnore
    public boolean isSucess() {
       return this.status== ResponseCode.SUCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ServerResponse<T> createBySucess(){
        return new ServerResponse<T>(ResponseCode.SUCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySucessMsg(String message){
        return new ServerResponse<T>(ResponseCode.SUCESS.getCode(),message);
    }

    public static <T> ServerResponse<T> createBySucess(T data){
        return new ServerResponse<T>(ResponseCode.SUCESS.getCode(),data);
    }
    public static <T> ServerResponse<T> createBySucess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMsg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }
    public static <T> ServerResponse<T> createByErrorCode(int errorCode,String errorMsg){
        return new ServerResponse<T>(errorCode,errorMsg);
    }
}
