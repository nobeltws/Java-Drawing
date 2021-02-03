package com.challenge.canvas.domain;

import lombok.Getter;

@Getter
public class FillInput extends Input{
    private int x;
    private int y;
    private char color;

    public FillInput setX(int x) {
        this.x = x;
        return this;
    }

    public FillInput setY(int y) {
        this.y = y;
        return this;
    }

    public FillInput setColor(char color) {
        this.color = color;
        return this;
    }
}
