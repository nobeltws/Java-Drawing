package com.challenge.canvas.services.commands;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.domain.ShapeInput;
import org.springframework.stereotype.Service;

@Service
public class DrawLineCommandImpl implements Command {

    @Override
    public CanvasModel executeCommand(Input input) {
        ShapeInput shapeInput = (ShapeInput) input;
        CanvasModel canvasModel = shapeInput.getCanvasModel();

        int x1 = shapeInput.getX1();
        int x2 = shapeInput.getX2();
        int y1 = shapeInput.getY1();
        int y2 = shapeInput.getY2();

        char[][] updatedCanvas = canvasModel.getCanvas();
        int smallY;
        int bigY;
        int smallX;
        int bigX;

        // Draw vertical line
        if (x1 == x2) {
            if (y1 < y2) {
                smallY = y1;
                bigY = y2;
            } else {
                smallY = y2;
                bigY = y1;
            }
            for (; smallY <= bigY; smallY++) {
                updatedCanvas[smallY][x1] = 'X';
            }
        }

        // Draw horizontal line
        else if (y1 == y2) {
            if (x1 < x2) {
                smallX = x1;
                bigX = x2;
            } else {
                smallX = x2;
                bigX = x1;
            }
            for (; smallX <= bigX; smallX++) {
                updatedCanvas[y1][smallX] = 'X';
            }
        }
        canvasModel.setCanvas(updatedCanvas);

        return canvasModel;
    }
}