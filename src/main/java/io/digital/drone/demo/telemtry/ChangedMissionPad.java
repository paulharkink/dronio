package io.digital.drone.demo.telemtry;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class ChangedMissionPad extends ApplicationEvent {

    String mid;

    int x;

    int y;

    int z;

    public ChangedMissionPad(String mid, String x, String y, String z) {
        super(mid);
        this.mid = mid;
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.z = Integer.parseInt(z);
    }
}
