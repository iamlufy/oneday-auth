package com.oneday.core.exception;

import com.alibaba.fastjson.JSON;
import com.oneday.core.global.GlobalEnum;
import com.oneday.core.global.IBaseCode;
import com.oneday.core.web.OdResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Optional;

/**
 * @author zhuangzf
 * @date 2019/3/11 15:40
 */
@ControllerAdvice
@Slf4j
public class CommonHandlerExceptionResolver {

    @ExceptionHandler(value = RuntimeException.class)
    public void doResolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        IBaseCode code = Optional.of(ex).filter(e -> e instanceof BaseException).map(e -> ((BaseException) e).getIBaseCode()).orElse(null);
        code = Optional.ofNullable(code).orElseGet(() -> Optional.of(ex).filter(e -> e instanceof MethodArgumentNotValidException).map(e -> GlobalEnum.PARAMS_ERROR).orElse(null));
        code = Optional.ofNullable(code).orElse(GlobalEnum.UNEXPECTED_ERROR);

        OdResponse odResponse = OdResponse.ofFail(code).getBody();
        writeOutput(response, odResponse);

        log.error("CommonHandlerExceptionResolver.doResolveException:url{} exception", request.getRequestURI(), ex);
        log.error("CommonHandlerExceptionResolver.doResolveException:code{}", code);
    }


    /**
     * 输出到流
     *
     * @param response
     * @param o
     * @throws IOException
     */
    private void writeOutput(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSON.toJSONString(o));
            writer.flush();
        } catch (IllegalStateException ise) {
            // 兼容一下业务方用getWriter()的方式
            String iseMsg = "getWriter() has already been called for this response";
            if (iseMsg.equals(ise.getMessage())) {
                Writer writer = response.getWriter();
                writer.write(JSON.toJSONString(o));
                writer.flush();
                writer.close();
            }
        }
    }

}