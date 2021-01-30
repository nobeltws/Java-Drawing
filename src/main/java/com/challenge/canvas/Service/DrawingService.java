package com.challenge.canvas.Service;

import com.challenge.canvas.Model.CanvasModel;

public interface DrawingService {
	public String[][] drawLine(CanvasModel canvas, int x1, int y1, int x2, int y2);
	
	public String[][] drawCanvas(CanvasModel newCanvas);
	
	public String[][] drawRectangle(CanvasModel canvas, int x1, int y1, int x2, int y2);
	
	public CanvasModel fillColor(int x, int y, String color);
	
	public void printCanvas(String[][] canvasToPrint);
	
	public void printHelp();
}
