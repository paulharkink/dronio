package io.digital.drone.demo;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient implements Closeable  {

    private DatagramSocket socket;
    private InetAddress address;

    public UdpClient(String targetIp, int port) throws SocketException, UnknownHostException {
        socket = new DatagramSocket(port);
        address = InetAddress.getByName(targetIp);
    }

    public String sendCommand(String msg) throws IOException {
        byte[] buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 8889);
        socket.send(packet);
        return listen();
//        packet = new DatagramPacket(buf, buf.length);
//        socket.receive(packet);
//        String received = new String(
//                packet.getData(), 0, packet.getLength());
//        return received;
    }

    public String listen() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(
                packet.getData(), 0, packet.getLength());
    }

    public void safeSleep(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        socket.close();
    }
}
