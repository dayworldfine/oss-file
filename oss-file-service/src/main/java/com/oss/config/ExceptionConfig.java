package com.oss.config;

import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName：ExceptionConfig
 * @Description: 全局异常捕获类
 * @Author：13738700108
 * @Data 2020/11/21 0:08
 * @Version: v1.0
 **/
@RestControllerAdvice
public class ExceptionConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionConfig.class);


    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public ResponseModel BindExceptionHandler(BindException e) {
        LOGGER.error("捕获到BindException异常",e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
//        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseModel.errorWithMsg(ErrorCodes.SERVICE_SNEAK_OFF,message);
    }

    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseModel handleValidationBodyException(MethodArgumentNotValidException e) {
        LOGGER.error("捕获到MethodArgumentNotValidException异常", e);
        for (ObjectError s : e.getBindingResult().getAllErrors()) {
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR, s.getDefaultMessage());
        }
        return ResponseModel.error(ErrorCodes.SERVICE_SNEAK_OFF);
    }

    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseModel handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error("捕获到ConstraintViolationException异常", e);
        //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException

        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());

        return ResponseModel.errorWithMsg(ErrorCodes.SERVICE_SNEAK_OFF,message);
    }

    /**
     * 处理统一异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseModel javaExceptionHandler(Exception ex) {//APIResponse是项目中对外统一的出口封装，可以根据自身项目的需求做相应更改
        LOGGER.error("捕获到Exception异常", ex);
        return ResponseModel.errorWithMsg(ErrorCodes.SERVICE_SNEAK_OFF, ex.getMessage());
    }

//    /**
//     * 主动throw的异常
//     *
//     * @param e
//     * @param response
//     * @return
//     */
//    @ExceptionHandler(ServiceException.class)
//    public ErrorMessage handleUnProccessableServiceException(ServiceException e, HttpServletResponse response) {
//        response.setStatus(e.getStatusCode().value());
//        return new ErrorMessage(e.getErrorCode(), e.getMessage());
//    }
}
