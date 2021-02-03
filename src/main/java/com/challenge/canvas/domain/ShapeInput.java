package com.challenge.canvas.domain;

import lombok.Getter;

@Getter
public class ShapeInput extends Input {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public ShapeInput setX1(int x1) {
        this.x1 = x1;
        return this;
    }

    public ShapeInput setY1(int y1) {
        this.y1 = y1;
        return this;
    }

    public ShapeInput setX2(int x2) {
        this.x2 = x2;
        return this;
    }

    public ShapeInput setY2(int y2) {
        this.y2 = y2;
        return this;
    }
}
