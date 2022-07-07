package io.digital.drone.demo;

import io.digital.drone.demo.commands.Start;
import io.digital.drone.demo.commands.Takeoff;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        commandLoop.offer(new Start(10_000));
//        commandLoop.offer(new Takeoff(5_000));
        telemetryListener.start();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting");
        telemetryListener.start();
    }
}
