package io.digital.drone.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CmdRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting");
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
