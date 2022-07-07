package io.digital.drone.demo.models;


import lombok.Data;

@Data
public class TelemetryState {

    private Integer mid;
    private Integer x;
    private Integer y;
    private Integer z;
    private String mpry;
    private Integer pitch;
    private Integer roll;
    private Integer yaw;
    private Integer vgx;
    private Integer vgy;
    private Integer vgz;
    private Integer templ;
    private Integer temph;
    private Integer tof;
    private Integer h;
    private Integer bat;
    private Float baro;
    private Integer time;
    private Float agx;
    private Float agy;
    private Float agz;

}
