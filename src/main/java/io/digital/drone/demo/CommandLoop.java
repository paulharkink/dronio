package io.digital.drone.demo;


import io.digital.drone.demo.commands.Command;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
@Slf4j
public class CommandLoop implements Runnable {

    private final UdpClient commandClient;
    private boolean running = false;

    private final BlockingQueue<Command> commandQueue;

    public CommandLoop(UdpClient commandClient) {
        this.commandClient = commandClient;
        this.commandQueue = new ArrayBlockingQueue<>(10);
    }

    public void start() {
        if (!running) {
            this.running = true;
            new Thread(this)
                    .start();
        }
    }

    public void offer(Command command) {
        commandQueue.offer(command);
    }

    public void close() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Command command = commandQueue.take();
                String response = command.execute(commandClient);
                if (!StringUtils.equalsIgnoreCase(response, "ok")) {
                    log.warn("Got NON-OK on {}: {}", command, response);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
