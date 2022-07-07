package io.digital.drone.demo;

import io.digital.drone.demo.commands.*;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

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
    private List<Command> flightplan = Arrays.asList(
            new Start(10_000),
            new Takeoff(5_000),
//            new Go(0, 0, 0, 5, 3, 5_000),
//            new Up(25, 5_000),
            new Jump(100, 0, 25, 10, 0, 7, 2, 5_000),
            new Land(5_000)
    );
}
