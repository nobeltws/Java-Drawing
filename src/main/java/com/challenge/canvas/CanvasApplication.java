package com.challenge.canvas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.canvas.Model.CanvasModel;
import com.challenge.canvas.Service.DrawingService;
import com.challenge.canvas.Service.ValidatorService;
import com.challenge.canvas.Util.CommandConstants;

@SpringBootApplication
public class CanvasApplication implements CommandLineRunner {

	@Autowired
	DrawingService drawingService;
	
	@Autowired
	ValidatorService validatorService;
	
	public static void main(String[] args) {
		SpringApplication.run(CanvasApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		CanvasModel canvasModel = new CanvasModel();
		
		while (true) {
			System.out.print("Enter command: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String[] inputCommand = reader.readLine().split(" ");
			
			if (validatorService.validateInput(inputCommand)) {
				if (CommandConstants.CREATE_CANVAS.equals(inputCommand[0]) && validatorService.validateNewCanvas(canvasModel)) {
					canvasModel.setX(Integer.parseInt(inputCommand[1]));
					canvasModel.setY(Integer.parseInt(inputCommand[2]));
					String[][] newCanvas = drawingService.drawCanvas(canvasModel);
					canvasModel.setCanvas(newCanvas);
				}
					if (canvasModel.getCanvas() != null) {
						drawingService.printCanvas(canvasModel.getCanvas());
					}
								
				else if (CommandConstants.LINE.equals(inputCommand[0])) {
					canvasModel.setCanvas(drawingService.drawLine(canvasModel,
							Integer.parseInt(inputCommand[1]),
							Integer.parseInt(inputCommand[2]),
							Integer.parseInt(inputCommand[3]),
							Integer.parseInt(inputCommand[4])));
					if (canvasModel.getCanvas() != null) {
						drawingService.printCanvas(canvasModel.getCanvas());
					}
				}
				
				else if (CommandConstants.RECT.equals(inputCommand[0])) {
					canvasModel.setCanvas(drawingService.drawRectangle(canvasModel,
							Integer.parseInt(inputCommand[1]),
							Integer.parseInt(inputCommand[2]),
							Integer.parseInt(inputCommand[3]),
							Integer.parseInt(inputCommand[4])));
					if (canvasModel.getCanvas() != null) {
						drawingService.printCanvas(canvasModel.getCanvas());
					}
				}
				
				else if (CommandConstants.QUIT.equals(inputCommand[0])) {
					break;
				}
				
			} else {
				if (canvasModel.getCanvas() != null) {
					drawingService.printCanvas(canvasModel.getCanvas());
				}
			}
		}
	}
	
}
