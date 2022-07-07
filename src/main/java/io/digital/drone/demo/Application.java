package io.digital.drone.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@NoArgsConstructor
@EnableConfigurationProperties(DroneIOProperties.class)
public class Application {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int DRONE_PORT = 8889;
    private static final int LISTEN_PORT_BASE = 8890;

    @Bean
    DatagramSocket sendSocket() throws SocketException {
        return new DatagramSocket(DRONE_PORT);
    }

    @Bean
    DatagramSocket listenSocket() throws SocketException {
        return new DatagramSocket(LISTEN_PORT_BASE);
    }

    @Bean
    DroneController controllerOne(DroneIOProperties droneIOProperties) throws SocketException, UnknownHostException {
        return create(droneIOProperties.getOne().getIp());
    }

    @Bean
    DroneController controllerTwo(DroneIOProperties droneIOProperties) throws SocketException, UnknownHostException {
        return create(droneIOProperties.getTwo().getIp());
    }

    @Bean
    DroneController controllerThree(DroneIOProperties droneIOProperties) throws SocketException, UnknownHostException {
        return create(droneIOProperties.getThree().getIp());
    }

    @Bean
    TelemetryListener telemetryListener(ApplicationEventPublisher applicationEventPublisher) throws SocketException, UnknownHostException {
        UdpClient listener = new UdpClient(SERVER_IP, listenSocket());
        return new TelemetryListener(listener, applicationEventPublisher, objectMapper());
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    private DroneController create(String ip) throws SocketException, UnknownHostException {
        UdpClient sender = new UdpClient(ip, sendSocket());
        CommandLoop commandLoop = new CommandLoop(sender);

        return new DroneController(ip, commandLoop);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
