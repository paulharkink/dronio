package io.digital.drone.demo;

import io.digital.drone.demo.telemtry.ChangedMissionPad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListenerDemo implements ApplicationListener<ChangedMissionPad> {

    @Override
    public void onApplicationEvent(ChangedMissionPad event) {

        log.info("FOUND A NEW MISSION PAD: {}", event);
    }
}
