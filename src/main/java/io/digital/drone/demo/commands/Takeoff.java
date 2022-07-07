package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Takeoff extends  BaseCommand {

    public Takeoff(int pause) {
        super("takeoff", pause);
    }
}
