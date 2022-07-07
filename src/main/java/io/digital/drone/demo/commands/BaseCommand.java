package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.IOException;

@ToString
@RequiredArgsConstructor
public class BaseCommand implements Command {

    private final String commandString;
    private final int pause;

    @Override
    public String execute(UdpClient udpClient) throws IOException {
        UdpClient.Message msg = udpClient.sendCommand(commandString);
        if (pause > 0) {
            udpClient.safeSleep(pause);
        }
        return msg.getMessage();
    }
}
