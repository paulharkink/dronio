package io.digital.drone.demo.telemtry;

import io.digital.drone.demo.models.TelemetryState;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class ChangedMissionPad extends ApplicationEvent {

    String ip;

    TelemetryState state;

    public ChangedMissionPad(String ip, TelemetryState state) {
        super(ip);
        this.ip = ip;
        this.state = state;
    }
}
