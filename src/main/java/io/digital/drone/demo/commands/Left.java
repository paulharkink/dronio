package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Left extends  BaseCommand {

    public Left(int cms, int pause) {
        super("left " + cms, pause);
    }
}
