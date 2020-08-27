package jlu.nan.common.advice;

import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import jlu.nan.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName: ExceptionHandle
 * @Description:
 * @author: Nan
 * @date: 2020/3/16 17:21
 * @version: V1.0
 */

@ControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(CmException.class)
    public ResponseEntity<ExceptionResult> exceptionHandle(CmException e){
        ExectionEnum ee = e.getEe();
        return ResponseEntity.status(ee.getCode()).body(new ExceptionResult(ee));
    }


}
