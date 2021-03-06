package io.digital.drone.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class FlightCoordinator {

    private final DroneController controllerOne;
    private final DroneController controllerTwo;
    private final DroneController controllerThree;

    @PostConstruct
    public void startFlight() {
        controllerOne.executeFlightPlan();

//        controllerTwo.executeFlightPlan();

//        controllerThree.executeFlightPlan();
    }

}
