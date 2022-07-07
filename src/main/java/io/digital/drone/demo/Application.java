package io.digital.drone.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.digital.drone.demo.telemtry.TelemetryListener;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.SocketException;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
@NoArgsConstructor
public class Application {

    @Bean
    UdpClient commandClient(@Value("${drone.ip}") String droneIp) throws SocketException, UnknownHostException {
        return new UdpClient(droneIp, 8889);
    }

    @Bean
    UdpClient telemetryClient() throws SocketException, UnknownHostException {
        return new UdpClient("127.0.0.1", 8890);
    }
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
