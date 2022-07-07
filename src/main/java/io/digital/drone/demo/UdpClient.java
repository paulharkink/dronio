package io.digital.drone.demo;

import lombok.Getter;
import lombok.Value;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@Getter
public class UdpClient implements Closeable {

    private final DatagramSocket socket;
    private InetAddress address;

    private String ipString;

    public UdpClient(String targetIp, DatagramSocket socket) throws SocketException, UnknownHostException {
        address = InetAddress.getByName(targetIp);
        this.socket = socket;
        this.ipString = targetIp;
    }

    public Message sendCommand(String msg) throws IOException {
        byte[] buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 8889);
        socket.send(packet);
        return listen();
    }

    public Message listen() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String ip = extractIp(packet);
        return new Message(ip, new String(
                packet.getData(), 0, packet.getLength()));
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

    private String extractIp(DatagramPacket packet) {
        SocketAddress socketAddress = packet.getSocketAddress();
        if (socketAddress instanceof InetSocketAddress) {
            InetAddress inetAddress = ((InetSocketAddress) socketAddress).getAddress();
            if (inetAddress instanceof Inet4Address) {
                System.out.println("IPv4: " + inetAddress);
                return inetAddress.getHostAddress();
            }
        }
        throw new IllegalStateException("Not an ipV4 address: " + socketAddress);
    }

    @Value
    public static class Message {
        String ipAddress;
        String message;
    }
}
