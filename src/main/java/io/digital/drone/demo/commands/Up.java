package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

public class Up extends  BaseCommand {

    public Up( int cms, int pause) {
        super( "up " + cms, pause);
    }
}
