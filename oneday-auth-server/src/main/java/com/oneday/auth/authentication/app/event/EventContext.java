package com.oneday.auth.authentication.app.event;

import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Component;

/**
 * @author zhuangzf
 * @date 2019/3/29 11:13
 */
@Component
public class EventContext {
    private static final EventBus eventBus = new EventBus();

    public static void register(Object o) {
        eventBus.register(o);
    }

    public static void unregister(Object o) {
        eventBus.unregister(o);
    }

    public static void post(Object event) {
        eventBus.post(event);
    }
}
