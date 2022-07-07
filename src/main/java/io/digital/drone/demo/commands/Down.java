package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Down extends  BaseCommand {

    public Down(int cms, int pause) {
        super( "down " + cms, pause);
    }
}
