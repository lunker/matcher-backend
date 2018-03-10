package org.lunker.matcher.config;

import org.lunker.matcher.util.Installation;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by dongqlee on 2018. 3. 10..
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * This method is called during Spring's startup.
     *
     * @param event Event raised when an ApplicationContext gets initialized or
     * refreshed.
     */
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        Installation.install();
        return;
    }
}
