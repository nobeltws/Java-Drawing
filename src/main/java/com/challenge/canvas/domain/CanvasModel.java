package com.challenge.canvas.domain;

import lombok.Getter;

@Getter
public class CanvasModel {
	private int xLimit;
	private int yLimit;
	private char[][] canvas;

	public CanvasModel setXLimit(int xLimit){
		this.xLimit = xLimit;
		return this;
	}

	public CanvasModel setYLimit(int yLimit){
		this.yLimit = yLimit;
		return this;
	}

	public CanvasModel setCanvas(char[][] canvas) {
		this.canvas = canvas;
		return this;
	}

	public void drawCanvas() {
		int row = yLimit + 2;
		int col = xLimit + 2;
		canvas = new char[row][col];

		for (int i = 0; i < row; i++) {
			// Draw top and bottom
			if (i == 0 || i == row - 1) {
				for (int j = 0; j < col; j++) {
					canvas[i][j] = '-';
				}
			} else {
				for (int j = 0; j < col; j++) {
					// Draw sides
					if (j == 0 || j == col - 1) {
						canvas[i][0] = '|';
						canvas[i][col - 1] = '|';
					} else {
						// Fill the rest of the canvas
						canvas[i][j] = ' ';
					}
				}

			}
		}
	}

	public void printCanvas() {
		if (canvas != null) {
			for (char[] x : canvas)
			{
				for (char y : x)
				{
					System.out.print(y);
				}
				System.out.println();
			}
		}
	}
}
