package jlu.nan.common.exception;

import jlu.nan.common.enums.ExectionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CmException
 * @Description:
 * @author: Nan
 * @date: 2020/3/16 17:08
 * @version: V1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CmException extends RuntimeException{
    private ExectionEnum ee;

}
