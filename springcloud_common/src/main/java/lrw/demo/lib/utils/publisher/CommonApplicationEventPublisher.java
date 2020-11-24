package lrw.demo.lib.utils.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author fun
 * @date 2019/11/21
 */
@Component
public class CommonApplicationEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(ApplicationEvent event) {
        if (event == null) {
            return;
        }
        applicationEventPublisher.publishEvent(event);
    }

}
