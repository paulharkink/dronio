package io.digital.drone.demo.telemtry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import io.digital.drone.demo.UdpClient;
import io.digital.drone.demo.models.UDPMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class TelemetryListener implements Runnable {

    private final UdpClient telemetryClient;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;

    private boolean running;

    private final Map<String, String> current = new ConcurrentHashMap<>();

    public void start() {
        if (!running) {
            this.running = true;
            new Thread(this)
                    .start();
        }
    }

    public void close() {
        this.running = false;
    }



    private void take(String key, String value) {
        current.put(key, value);
    }

    public void parse(String row) throws JsonProcessingException {

        Map<String, String> messageMap = Splitter.on(";")
                .omitEmptyStrings()
                .withKeyValueSeparator(":")
                .split(row
                        .replace("\r", "")
                        .replace("\n", "")
                );

        var message = objectMapper.convertValue(messageMap, UDPMessage.class);

        String previousMissionId = current.get("mid");

        Arrays.stream(StringUtils.split(row, ";"))
                .map(pair -> StringUtils.split(pair, ":"))
                .filter(pair -> pair.length == 2)
                .forEach(pair -> take(pair[0], pair[1]));

        if (!StringUtils.equalsIgnoreCase(previousMissionId, current.get("mid"))) {
            applicationEventPublisher.publishEvent(message);
        }
    }

    public void run() {
        while (this.running) {
            String row = null;
            try {
                row = telemetryClient.listen();
                parse(row);
            } catch (IOException e) {
                log.warn("Fout", e);
            }
            log.info("Telemetry: {}", row);
        }
    }

}
