package com.sdcxv.bs.model;

/**
 * EQ DEMO Created by Xudong.Liu on 2016/10/10.
 */
public class EQ implements Cloneable {
    //BT connection, 0 -> on , 1 -> off.
    private int connection = 1;
    //ToneScape switch, 0 -> on , 1 -> off.
    private int tonescape = 0;
    //volume, from 0 to 32
    private int volume = 0;
    // warm-dry， from 10 to -10
    private int x = 0;
    // excited-relax from 10 to -10
    private int y = 0;
    // bottom size
    private int z = 0;

    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public int getTonescape() {
        return tonescape;
    }

    public void setTonescape(int tonescape) {
        this.tonescape = tonescape;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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

    public Object clone() {

        EQ eq = null;
        try {
            eq = (EQ) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return eq;
    }
}
