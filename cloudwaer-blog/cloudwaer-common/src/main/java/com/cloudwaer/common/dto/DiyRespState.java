package com.cloudwaer.common.dto;

/**
 * @ClassName DiyRespState
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 12:22
 * @Version 1.0
 **/
public class DiyRespState {
    private Integer diyCode;

    private String errMsg;

    public Integer getDiyCode() {
        return diyCode;
    }

    public void setDiyCode(Integer diyCode) {
        this.diyCode = diyCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DiyRespState(String msg){
        this.errMsg = msg;
    }

    public DiyRespState(Integer code, String msg){

        this.diyCode = code;
        this.errMsg = msg;
    }
}
