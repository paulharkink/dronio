package io.digital.drone.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "drones")
@Data
public class DroneIOProperties {

    DroneIp one;

    DroneIp two;

    DroneIp three;

    @Data
    public static class DroneIp {
        String ip;
    }

}
