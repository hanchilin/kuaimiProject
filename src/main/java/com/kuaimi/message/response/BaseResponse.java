package com.kuaimi.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : chenwei
 * @create : 2017-10-04 15:44
 */
@ApiModel("返回信息基本对象")
public class BaseResponse implements Serializable {
    @ApiModelProperty(name = "success", notes = "业务处理是否成功")
    private boolean success;
    @ApiModelProperty(name = "message", notes = "请求返回的附带信息")
    private String message;

    public static BaseResponse OK = new BaseResponse(null);

    public static BaseResponse FAIL = new BaseResponse(false, null);

    public BaseResponse () { }

    public BaseResponse (String message) {
        this.success = true;
        this.message = message;
    }

    public BaseResponse (boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static BaseResponse response (boolean result){
        return result ? OK : FAIL;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.success = false;
    }

}
