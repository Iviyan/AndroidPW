package com.iv.androidpw;

public class Award {
    public int image;
    public boolean received = false;

    public Award(int image) {
        this.image = image;
    }

    public Award(int image, boolean received) {
        this.image = image;
        this.received = received;
    }
}
