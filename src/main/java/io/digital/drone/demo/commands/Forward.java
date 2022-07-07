package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Forward extends  BaseCommand {

    public Forward(int cms, int pause) {
        super("forward√ " + cms, pause);
    }
}
