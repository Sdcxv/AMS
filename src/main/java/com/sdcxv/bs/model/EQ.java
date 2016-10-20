package com.sdcxv.bs.model;

/**
 * EQ DEMO Created by Xudong.Liu on 2016/10/10.
 */
public class EQ {
    //volume, from 0 to 32
    private int volume = 0;
    //power on(0)/off(-1)
    private int power = 0;
    // warm-dry， from 10 to -10
    private int x = 0;
    // excited-relax from 10 to -10
    private int y = 0;
    // bottom size
    private int z = 0;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
