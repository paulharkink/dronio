package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Right extends  BaseCommand {

    public Right(int cms, int pause) {
        super("right " + cms, pause);
    }
}
