package com.challenge.canvas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.canvas.Model.CanvasModel;
import com.challenge.canvas.Util.CommandConstants;

@Service
public class ValidatorServiceImpl implements ValidatorService{
	@Autowired
	DrawingService drawingService;
	
	@Override
	public boolean validateLine(CanvasModel canvas, int x1, int y1, int x2, int y2) {
		if (x1 <= 0 || x2 <= 0 || y1 <= 0 || y2 <= 0) {
			System.out.println("Invalid input, cannot draw line outside of canvas.");
			return false;
		}
		else if (x1 > canvas.getX() || x2 > canvas.getX() || y1 > canvas.getY() || y2 > canvas.getY()) {
			System.out.println("Invalid input, cannot draw line outside of canvas.");
			return false;
		}
		else if (x1 != x2 && y1 != y2){
			System.out.println("Invalid input, cannot draw diagonal lines.");
			return false;
		} else {
			return true;
		}
	}
	@Override
	public boolean validateCanvas(CanvasModel canvas) {
		if (canvas == null) {
			return false;
		}
		if (canvas.getCanvas() == null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean validateNewCanvas(CanvasModel newCanvas) {
		if (newCanvas == null) {
			return false;
		}
		if (newCanvas.getCanvas() == null) {
			return true;
		} else {
			System.out.println("Cannot create canvas, canvas already exists.");
			return false;
		}
	}
	@Override
	public boolean validateRectangle(CanvasModel canvas, int x1, int y1, int x2, int y2) {
		if (x1 == x2 || y1 == y2) {
			System.out.println("Invalid input, cannot draw rectangle.");
			return false;
		}
		else if (x1 > canvas.getX() || x2 > canvas.getX() || y1 > canvas.getY() || y2 > canvas.getY()) {
			System.out.println("Invalid input, cannot draw rectangle outside of canvas.");
			return false;
		}
		return true;
	}
	@Override
	public boolean validateFillColor(CanvasModel canvas, int x, int y) {
		return true;
	}
	@Override
	public boolean validateInput(String[] inputs) {
		if (inputs == null) {
			System.out.println("No inputs given. Try again.");
			drawingService.printHelp();
			return false;
		}
		
		if (CommandConstants.CREATE_CANVAS.equals(inputs[0])) {
			if (CommandConstants.NEW_CANVAS_PARAM == inputs.length) {
				int x;
				int y;
				try {
					x = Integer.parseInt(inputs[1]);
					y = Integer.parseInt(inputs[2]);
				} catch (NumberFormatException e) {
					System.out.println("Invalid inputs, inputs contain non integer. Width and height must be positive integers.");
					return false;
				}
				
				if (x > 0 && y > 0) {
					return true;
				} else {
					System.out.println("Invalid inputs, inputs contain negative integer. Width and height must be positve integers.");
					return false;
				}
			} else {
				System.out.println("Invalid number of parameters");
				return false;
			}
		}
		
		else if (CommandConstants.LINE.equals(inputs[0])) {
			if (CommandConstants.LINE_PARAM == inputs.length) {
				int x1;
				int x2;
				int y1;
				int y2;
				try {
					x1 = Integer.parseInt(inputs[1]);
					x2 = Integer.parseInt(inputs[2]);
					y1 = Integer.parseInt(inputs[3]);
					y2 = Integer.parseInt(inputs[4]);
				} catch (NumberFormatException e) {
					System.out.println("Invalid inputs, inputs contain non integer.");
					return false;
				}
				
				if (x1 > 0 && x2 > 0 && y1 > 0 && y2 > 0) {
					return true;
				} else {
					System.out.println("Invalid inputs, inputs contain negative integer.");
					return false;
				}
			} else {
				System.out.println("Invalid number of parameters");
				return false;
			}
		}
		
		else if (CommandConstants.RECT.equals(inputs[0])) {
			if (CommandConstants.RECT_PARAM == inputs.length) {
				int x1;
				int x2;
				int y1;
				int y2;
				try {
					x1 = Integer.parseInt(inputs[1]);
					x2 = Integer.parseInt(inputs[2]);
					y1 = Integer.parseInt(inputs[3]);
					y2 = Integer.parseInt(inputs[4]);
				} catch (NumberFormatException e) {
					System.out.println("Invalid inputs, inputs contain non integer.");
					return false;
				}
				
				if (x1 > 0 && x2 > 0 && y1 > 0 && y2 > 0) {
					return true;
				}
				else {
					System.out.println("Invalid inputs, inputs contain negative integer.");
					return false;
				}
			} else {
				System.out.println("Invalid number of parameters");
				return false;
			}
		}
		
		else if (CommandConstants.QUIT.equals(inputs[0])) {
			if (CommandConstants.QUIT_PARAM == inputs.length) {
				return true;
			} else {
				System.out.println("Invalid input, too many parameters for Quit command.");
				return false;
			}
		}
		
		else {
			drawingService.printHelp();
			System.out.println("Unrecognised command, please refer to above available commands and try again.");
			return false;
		}
	}
}
