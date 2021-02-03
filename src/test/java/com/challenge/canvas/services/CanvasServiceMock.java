package com.challenge.canvas.services;

import com.challenge.canvas.exceptions.BusinessException;
import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.util.Helper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

@Service
public class CanvasServiceMock {

    private final DrawingService drawingService;

    private final ValidatorService validatorService;

    private final Helper helper;

    public CanvasServiceMock(DrawingService drawingService, ValidatorService validatorService, Helper helper) {
        this.drawingService = drawingService;
        this.validatorService = validatorService;
        this.helper = helper;
    }

    public void runCanvasApplication(String strInput) throws IOException {
        CanvasModel canvasModel = new CanvasModel();
        System.out.print("Enter command: ");
        
        try {
        	String[] inputCommand = readInput(strInput);
            Input input = validatorService.validateInput(inputCommand, canvasModel);
            drawingService.setCommand(input.getCommandType());
            canvasModel = drawingService.executeCommand(input);
            canvasModel.printCanvas();
        } catch (BusinessException e) {
            helper.printHelp();
            System.out.println(e.getMessage());
        }
    }

    public String[] readInput(String input) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(input));
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
