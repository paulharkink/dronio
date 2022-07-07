package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class RotateRight extends  BaseCommand {

    public RotateRight( int degrees, int pause) {
        super("cw " + degrees, pause);
    }
}
