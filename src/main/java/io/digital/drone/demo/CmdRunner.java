package io.digital.drone.demo;

import io.digital.drone.demo.commands.*;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CmdRunner implements CommandLineRunner {

    private final TelemetryListener telemetryListener;

    private final CommandLoop commandLoop;

    @Autowired
    public CmdRunner(TelemetryListener telemetryListener, CommandLoop commandLoop) {
        this.telemetryListener = telemetryListener;
        this.commandLoop = commandLoop;
        commandLoop.start();
        for (Command c : flightplan) {
            System.out.println("Executing command: " + c);
            commandLoop.offer(c);
        }
//        flightplan.forEach(commandLoop::offer);
        telemetryListener.start();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting");
        telemetryListener.start();
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
