package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class BaseCommand implements Command {

    private final String commandString;
    private final int pause;

    @Override
    public String execute(UdpClient udpClient) throws IOException {
        String res = udpClient.sendCommand(commandString);
        if (pause > 0) {
            udpClient.safeSleep(pause);
        }
        return res;
    }
}
