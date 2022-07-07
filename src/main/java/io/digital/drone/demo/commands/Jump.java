package io.digital.drone.demo.commands;

public class Jump extends BaseCommand {
    public Jump(int x, int y, int z, int speed, int yaw, int mid1, int mid2, int pause) {
        super(String.format("jump %d %d %d %d %d m%d m%d", x, y, z, speed, yaw, mid1, mid2), pause);
    }
    // jump x y z speed yaw //mid1 mid2
}
