package org.nhathm.app.aop;

import com.alibaba.cola.catchlog.ResponseHandlerFactory;
import com.alibaba.cola.catchlog.ResponseHandlerI;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Component
public class ResponseHandlerAspect {

    @Bean
    public ResponseHandlerI colaResponseHandler() {
        return ResponseHandlerFactory.get();
    }
}
