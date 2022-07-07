package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class RotateLeft extends  BaseCommand {

    public RotateLeft( int degrees, int pause) {
        super("ccw " + degrees, pause);
    }
}
