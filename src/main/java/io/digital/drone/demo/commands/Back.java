package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Back extends  BaseCommand {

    public Back(int cms, int pause) {
        super("back " + cms, pause);
    }
}
