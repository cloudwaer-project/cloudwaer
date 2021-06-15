package com.cloudwaer.controller;

import com.cloudwaer.common.utils.ErrorException;
import com.cloudwaer.common.utils.ErrorMe;
import com.cloudwaer.common.utils.ResultBody;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommentError {
    private static final Logger logger = LoggerFactory.getLogger(CommentError.class);

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public ResultBody excelError(HttpServletRequest req, ErrorException e) {
        logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody nullExcel(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return ResultBody.error(ErrorMe.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultBody timeOutExcel(HttpServletRequest req, NullPointerException e) {
        logger.error("发生超时！原因是:", e);
        return ResultBody.error(ErrorMe.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return ResultBody.error(ErrorMe.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResultBody indexOutOfBoundsException(HttpServletRequest req, Exception e) {
        logger.error("索引越界！原因是:", e);
        return ResultBody.error(ErrorMe.INDEX_OFNUL);
    }

    @ExceptionHandler(ConnectionIsClosedException.class)
    @ResponseBody
    public ResultBody connectionIsClosedException(HttpServletRequest req, Exception e) {
        logger.error("数据库连接超时！原因是:", e);
        return ResultBody.error(ErrorMe.DATEBASE_TIMEOUT);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ResultBody illegalStateException(HttpServletRequest req, Exception e) {
        logger.error("数据库连接超时！原因是:", e);
        return ResultBody.error(ErrorMe.DATEBASE_TIMEOUT);
    }
}