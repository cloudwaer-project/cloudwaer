package com.cloudwaer.common.utils;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.BusinessException;
import com.google.gson.GsonBuilder;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName ResponseUtils
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 12:08
 * @Version 1.0
 **/
public class ResponseUtils {
    /**
     * 依据code构造返回结构
     *
     * @param code
     * @return
     */
    public static ResponseDto buildVoByResponseCode(ResponseCode code) {
        ResponseDto vo = new ResponseDto<>();
        vo.setSuccess(code.isSuccess());
        vo.setCode(code.getCode());
        vo.setMsg(code.getMsg());
        return vo;
    }

    /**
     * 依据code构造返回结构
     * @param <T>
     *
     * @param code
     * @return
     */
    public static <T> ResponseDto<T> buildVoByResponseCode(ResponseCode code, Class<?> t) {
        ResponseDto<T> vo = new ResponseDto<>();
        vo.setSuccess(code.isSuccess());
        vo.setCode(code.getCode());
        vo.setMsg(code.getMsg());
        return vo;
    }

    /**
     * 依据code和data构造返回结构
     *
     * @param <T>
     *
     * @param code
     * @param data
     * @return
     */
    public static <T> ResponseDto<T> buildVoByResponseCode(ResponseCode code, T data) {
        return buildVo(code.isSuccess(), code.getCode(), code.getMsg(), data);
    }

    /**
     * 自定义参数返回结构
     *
     * @param <T>
     *
     * @param <T>
     * @param result
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static <T> ResponseDto<T> buildVo(boolean result, String code, String msg, T data) {
        ResponseDto<T> vo = new ResponseDto<>();
        vo.setSuccess(result);
        vo.setCode(code);
        vo.setMsg(msg);
        vo.setData(data);
        return vo;
    }

    /**
     * 依据ResponseCode 及自定义code和msg构造返回参数 可用于调第三方异常的场景，将第三方的code和msg追加到我们的返回response里便于定位问题
     * @param code
     * @param
     * @return
     */
    public static ResponseDto buildVoByResponseCode(ResponseCode code, String diyMsg, Integer diyCode) {
        StringBuffer buffer = new StringBuffer();
        ResponseDto vo = new ResponseDto();
        vo.setSuccess(code.isSuccess());
        vo.setCode(code.getCode());
        buffer.append(code.getMsg()).append(diyCode).append(diyMsg);
        vo.setMsg(buffer.toString());
        return vo;
    }

    /**
     * 通过响应码和参数 构造返回的json字符串
     *
     * @param code
     * @param data
     * @return
     */
    public static String getReturnString(ResponseCode code, Object data) {
        return new GsonBuilder().serializeNulls().setDateFormat(DateTimeUtil.TIME_FORMAT_PUBLIC).create()
                .toJson(buildVoByResponseCode(code, data));
    }

    /**
     * 通过响应结构 构造返回的json字符串
     *
     * @param dto
     * @return
     */
    public static String getReturnString(ResponseDto<Object> dto) {
        return new GsonBuilder().serializeNulls().setDateFormat(DateTimeUtil.TIME_FORMAT_PUBLIC).create().toJson(dto);
    }

    public static ResponseDto buildVoByBusinessException(BusinessException be) {
        ResponseDto vo = new ResponseDto();
        vo.setSuccess(be.getErrorCode().isSuccess());
        vo.setCode(be.getErrorCode().getCode());
        StringBuffer buffer = new StringBuffer();
        buffer.append(be.getMessage());
        if(be.isState()){
            buffer.append(be.getRespState().getErrMsg());
        }
        vo.setMsg(buffer.toString());
        return vo;
    }


    /**
     * 手动设置返回内容 构造返回的json字符串
     *
     * @param result
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static String getReturnString(boolean result, String code, String msg, Object data) {
        return new GsonBuilder().serializeNulls().setDateFormat(DateTimeUtil.TIME_FORMAT_PUBLIC).create()
                .toJson(buildVo(result, code, msg, data));
    }

    /**
     * 判断调用服务是否成功
     *
     * @param responseDto
     * @return
     */
    public static <T> boolean isSuccess(ResponseDto<T> responseDto) {
        if (responseDto == null) {
            return false;
        }
        return responseDto.getCode() == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 判断调用服务返回数据是否为空
     *
     * @param fppsResponseDto
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> boolean isDataEmpty(ResponseDto<T> fppsResponseDto) {
        if (fppsResponseDto == null) {
            return true;
        }
        if (ObjectUtils.isEmpty(fppsResponseDto.getData())) {
            return true;
        }
        if (fppsResponseDto.getData() instanceof PageModel) {
            PageModel page = (PageModel) fppsResponseDto.getData();
            return ObjectUtils.isEmpty(page.getRows());
        }
        return false;
    }
}
