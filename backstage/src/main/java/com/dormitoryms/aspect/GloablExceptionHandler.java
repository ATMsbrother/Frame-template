package com.dormitoryms.aspect;

import com.dormitoryms.utils.ParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author hackyo
 * Created on 2018/3/18 12:30.
 */
@ControllerAdvice
public class GloablExceptionHandler {

    /**
     * 处理参数异常
     *
     * @param e 异常信息
     * @return 异常说明
     */
    @ResponseBody
    @ExceptionHandler(ParameterException.class)
    public String parameterException(ParameterException e) throws JsonProcessingException {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = new HashMap<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ObjectMapper().writeValueAsString(errorMap);
    }

}
