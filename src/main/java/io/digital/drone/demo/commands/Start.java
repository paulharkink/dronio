package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Start extends BaseCommand{

    public Start(int pause) {
        super( "command", pause);
    }
}
