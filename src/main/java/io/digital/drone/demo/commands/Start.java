package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;
import lombok.ToString;

public class Start extends BaseCommand{

    public Start(int pause) {
        super( "command", pause);
    }
}
