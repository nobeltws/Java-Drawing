package com.challenge.canvas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.canvas.Model.CanvasModel;

@Service
public class DrawingServiceImpl implements DrawingService{
	
	@Autowired
	ValidatorService validatorService;
	
	@Override
	public String[][] drawLine(CanvasModel canvas, int x1, int y1, int x2, int y2) {
		if (!validatorService.validateCanvas(canvas)) {
			
			if (validatorService.validateLine(canvas, x1, y1, x2, y2)) {
				String[][] updatedCanvas = canvas.getCanvas();
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
					for (;smallY <= bigY; smallY++) {
						updatedCanvas[smallY][x1] = "X";
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
					for (;smallX <= bigX; smallX++) {
						updatedCanvas[y1][smallX] = "X";
					}
				}
				return updatedCanvas;
				
			} else {
				return canvas.getCanvas();
			}
			
		} else {
			System.out.println("No existing canvas, please create canvas before drawing line.");
			return canvas.getCanvas();
		}
	}
	
	@Override
	public String[][] drawCanvas(CanvasModel newCanvas) {
		if (validatorService.validateCanvas(newCanvas)) {
			int row = newCanvas.getY() + 2;
			int col = newCanvas.getX() + 2;
			String[][] canvas = new String[row][col];
			
			for (int i = 0; i < row; i++) {
				// Draw top and bottom
				if (i == 0 || i == row-1) {
					for (int j = 0; j < col; j++) {
						canvas[i][j] = "-";
					}
				} else {
					for (int j = 0; j < col; j++) {
						// Draw sides
						if (j == 0 || j == col-1) {
							canvas[i][0] = "|";
							canvas[i][col-1] = "|";
						} else {
							// Fill the rest of the canvas
							canvas[i][j] = " ";
						}
					}
					
				}
			}
			return canvas;
		} else {
			System.out.println("Canvas already exists, cannot create new canvas.");
			return newCanvas.getCanvas();
		}
	}
	
	@Override
	public String[][] drawRectangle(CanvasModel canvas, int x1, int y1, int x2, int y2) {
		if (!validatorService.validateCanvas(canvas)) {
			if (validatorService.validateRectangle(canvas, x1, y1, x2, y2)) {
				String[][] updatedCanvas = canvas.getCanvas();
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
							updatedCanvas[i][j] = "X";
						}
					}
					else {
						updatedCanvas[i][smallX] = "X";
						updatedCanvas[i][bigX] = "X";
					}
				}
				return updatedCanvas;
				
			} else {
				return canvas.getCanvas();
			}
		} else {
			System.out.println("No existing canvas, please create canvas before drawing rectangle.");
			return canvas.getCanvas();
		}
	}
	
	@Override
	public CanvasModel fillColor(int x, int y, String color) {
		return new CanvasModel();
	}
	
	@Override
	public void printCanvas(String[][] canvasToPrint) {
		if (canvasToPrint != null) {
			for (String[] x : canvasToPrint)
			{
			   for (String y : x)
			   {
			        System.out.print(y);
			   }
			   System.out.println();
			}
		}	
	}
	
	@Override
	public void printHelp() {
		String help = "Command 			Description\n"
				+ "C w h           Create a new canvas of width w and height h.\n"
				+ "L x1 y1 x2 y2   Draw a new line from (x1,y1) to (x2,y2). Currently, only\n"
				+ "                horizontal or vertical lines are supported. Horizontal and vertical lines\n"
				+ "                will be drawn using the 'x' character.\n"
				+ "R x1 y1 x2 y2   Draw a rectangle whose upper left corner is (x1,y1) and\n"
				+ "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"
				+ "                using the 'x' character.\n"
				+ "B x y c         Fill the entire area connected to (x,y) with \"colour\" c. The\n"
				+ "                behaviour of this is the same as that of the \"bucket fill\" tool in paint\n"
				+ "                programs.\n"
				+ "Q               Quit\n";
		System.out.println(help);
		
	}
}