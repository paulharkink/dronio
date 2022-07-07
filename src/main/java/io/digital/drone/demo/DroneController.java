package io.digital.drone.demo;

import io.digital.drone.demo.commands.Land;
import io.digital.drone.demo.commands.Start;
import io.digital.drone.demo.commands.Takeoff;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.annotation.PostConstruct;

@ToString
@RequiredArgsConstructor
public class DroneController {

    private final String identifier;

    private final CommandLoop commandLoop;

    public void executeFlightPlan() {
        commandLoop.start();
        commandLoop.offer(new Start(10_000));
        commandLoop.offer(new Takeoff(5_000));
        commandLoop.offer(new Land(5_000));
    }
}
