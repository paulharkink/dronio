package io.digital.drone.demo.commands;

public class Go extends BaseCommand {
// go x y z speed mid
    public Go(int x, int y, int z, int speed, int mpid, int pause) {
        super(String.format("go %d %d %d %d m%d", x, y, z, speed, mpid), pause);
    }
    public Go(int x, int y, int z, int mpid, int pause) {
        super(String.format("go %d %d %d 1 m%d", x, y, z, mpid), pause);
    }
}
