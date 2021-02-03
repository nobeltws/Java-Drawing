package com.challenge.canvas.services.commands;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.FillInput;
import com.challenge.canvas.domain.Input;
import org.springframework.stereotype.Service;

@Service
public class FillColorCommandImpl implements Command {

    @Override
    public CanvasModel executeCommand(Input input) {
        FillInput fillInput = (FillInput) input;
        CanvasModel canvasModel = fillInput.getCanvasModel();

        int x = fillInput.getX();
        int y = fillInput.getY();
        char color = fillInput.getColor();
        char spot = canvasModel.getCanvas()[y][x];
        fill(canvasModel, x , y, color, spot);
        return canvasModel;
    }

    private void fill(CanvasModel canvasModel, int x, int y, char ch, char spot){
        char[][] canvas = canvasModel.getCanvas();
        if(canvas[y][x] == spot){
            canvas[y][x] = ch;

            if(x > 0 || x < canvasModel.getXLimit() || y > 0 || y < canvasModel.getYLimit()){
                fill(canvasModel, x + 1, y, ch, spot);
                fill(canvasModel, x - 1, y, ch, spot);
                fill(canvasModel, x, y + 1, ch, spot);
                fill(canvasModel, x, y - 1, ch, spot);
            }
        }
    }
}
