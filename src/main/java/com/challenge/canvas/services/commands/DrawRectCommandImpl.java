package com.challenge.canvas.services.commands;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.domain.ShapeInput;

public class DrawRectCommandImpl implements Command {

    @Override
    public CanvasModel executeCommand(Input input) {
        ShapeInput shapeInput = (ShapeInput) input;
        CanvasModel canvasModel = shapeInput.getCanvasModel();

        int x1 = shapeInput.getX1();
        int x2 = shapeInput.getX2();
        int y1 = shapeInput.getY1();
        int y2 = shapeInput.getY2();

        char[][] updatedCanvas = canvasModel.getCanvas();
        int smallX;
        int bigX;
        int smallY;
        int bigY;

        // Refactor rectangle coordinates
        if (x1 < x2) {
            smallX = x1;
            bigX = x2;
        } else {
            smallX = x2;
            bigX = x1;
        }
        if (y1 < y2) {
            smallY = y1;
            bigY = y2;
        } else {
            smallY = y2;
            bigY = y1;
        }
        // Draw rectangle
        for (int i = smallY; i <= bigY; i++) {
            if (i == smallY || i == bigY) {
                for (int j = smallX; j <= bigX; j++) {
                    updatedCanvas[i][j] = 'X';
                }
            } else {
                updatedCanvas[i][smallX] = 'X';
                updatedCanvas[i][bigX] = 'X';
            }
        }

        canvasModel.setCanvas(updatedCanvas);
        return canvasModel;
    }
}