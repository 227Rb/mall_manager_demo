package jlu.nan.common.vo;

import jlu.nan.common.enums.ExectionEnum;
import jlu.nan.common.exception.CmException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ExceptionResult
 * @Description:
 * @author: Nan
 * @date: 2020/3/16 17:37
 * @version: V1.0
 */

@Data
public class ExceptionResult {
    private Integer status;
    private String errMsg;
    private Boolean isResult;
    private Long timestamp;

    public ExceptionResult(){}

    public ExceptionResult(ExectionEnum exectionEnum) {
        this.status = exectionEnum.getCode();
        this.errMsg = exectionEnum.getMsg();
        timestamp=System.currentTimeMillis();
    }

    public ExceptionResult(CmException ce){
        this(ce.getEe());
    }
}
