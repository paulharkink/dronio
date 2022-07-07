package io.digital.drone.demo.commands;

import io.digital.drone.demo.UdpClient;

import java.io.IOException;

public interface Command {

    String execute(UdpClient udpClient) throws IOException;

}
