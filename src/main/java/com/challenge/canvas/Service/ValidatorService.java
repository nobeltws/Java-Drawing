package com.challenge.canvas.Service;

import com.challenge.canvas.Model.CanvasModel;

public interface ValidatorService {
	public boolean validateInput(String[] inputs);
	public boolean validateLine(CanvasModel canvas, int x1, int y1, int x2, int y2);
	public boolean validateCanvas(CanvasModel canvas);
	public boolean validateNewCanvas(CanvasModel newCanvas);
	public boolean validateRectangle(CanvasModel canvas, int x1, int y1, int x2, int y2);
	public boolean validateFillColor(CanvasModel canvas, int x, int y);
}
