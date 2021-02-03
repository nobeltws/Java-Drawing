package com.challenge.canvas.services;

import com.challenge.canvas.exceptions.BusinessException;
import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.util.Helper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CanvasService {

    private final DrawingService drawingService;

    private final ValidatorService validatorService;

    private final Helper helper;

    public CanvasService(DrawingService drawingService, ValidatorService validatorService, Helper helper) {
        this.drawingService = drawingService;
        this.validatorService = validatorService;
        this.helper = helper;
    }

    public void runCanvasApplication() throws IOException {
        CanvasModel canvasModel = new CanvasModel();

        while (true) {
            System.out.print("Enter command: ");
            
            try {
            	String[] inputCommand = readInput();
                Input input = validatorService.validateInput(inputCommand, canvasModel);
                if (input.isQuit()) {
                    break;
                }
                drawingService.setCommand(input.getCommandType());
                canvasModel = drawingService.executeCommand(input);
                canvasModel.printCanvas();
            } catch (BusinessException e) {
                helper.printHelp();
                System.out.println(e.getMessage());
            }
        }
    }

    private String[] readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputCommand = new String[0];
        try {
            inputCommand = reader.readLine().split(" ");
        } catch (IOException e) {
        	e.printStackTrace();
        	throw e;
        }
        return inputCommand;
    }

}
