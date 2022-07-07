package io.digital.drone.demo.telemtry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import io.digital.drone.demo.UdpClient;
import io.digital.drone.demo.models.TelemetryState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class TelemetryListener implements Runnable {

    private final UdpClient telemetryClient;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;

    private boolean running;

    private Map<String, TelemetryState> current = new ConcurrentHashMap<>();

    @PostConstruct
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

    public void parse(UdpClient.Message row) throws JsonProcessingException {

        Map<String, String> messageMap = Splitter.on(";")
                .omitEmptyStrings()
                .withKeyValueSeparator(":")
                .split(row.getMessage()
                        .replace("\r", "")
                        .replace("\n", "")
                );

        var nwState = objectMapper.convertValue(messageMap, TelemetryState.class);
        @Nullable
        var oldState = current.get(row.getIpAddress());
        current.put(row.getIpAddress(), nwState);

        int oldMid = Optional.ofNullable(oldState)
                .map(TelemetryState::getMid)
                .orElse(0);

        if (!Objects.equals(oldMid, nwState.getMid())) {
            applicationEventPublisher.publishEvent(new ChangedMissionPad(row.getIpAddress(), nwState));
        }
    }

    public void run() {
        while (this.running) {
            UdpClient.Message row = null;
            try {
                row = telemetryClient.listen();
                parse(row);
            } catch (IOException e) {
                log.warn("Fout", e);
            }
        }
    }

}
