package com.challenge.canvas.services;

import com.challenge.canvas.exceptions.*;
import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.FillInput;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.domain.ShapeInput;
import org.springframework.stereotype.Service;

import static com.challenge.canvas.util.CommandConstants.CommandType;
import static com.challenge.canvas.util.CommandConstants.commandConstantsMap;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public Input validateInput(String[] inputs, CanvasModel canvasModel) throws BusinessException {
        validateInputsNull(inputs);

        CommandType commandType = getCommandType(inputs[0]);
        Input input;
        ShapeInput shapeInput;

        switch (commandType) {
            case CREATE_CANVAS:
                validateInputsLength(commandType, inputs);
                validateCanvasDoesNotExist(canvasModel.getCanvas());
                input = createNewInput(inputs);
                input.setCommandType(commandType);
                return input;
            case LINE:
                validateInputsLength(commandType, inputs);
                validateCanvasNull(canvasModel.getCanvas());
                shapeInput = createNewShapeInput(inputs);
                shapeInput.setCommandType(commandType);
                shapeInput.setCanvasModel(canvasModel);
                validateLine(shapeInput);
                return shapeInput;
            case RECT:
                validateInputsLength(commandType, inputs);
                validateCanvasNull(canvasModel.getCanvas());
                shapeInput = createNewShapeInput(inputs);
                shapeInput.setCommandType(commandType);
                shapeInput.setCanvasModel(canvasModel);
                validateRectangle(shapeInput);
                return shapeInput;
            case FILL:
                validateInputsLength(commandType, inputs);
                validateCanvasNull(canvasModel.getCanvas());
                validateColor(inputs[3]);
                FillInput fillInput = createNewFillInput(inputs);
                fillInput.setCommandType(commandType);
                fillInput.setCanvasModel(canvasModel);
                validateFillColor(fillInput);
                return fillInput;
            case QUIT:
                validateInputsLength(commandType, inputs);
                input = new Input();
                input.setCommandType(commandType);
                input.setQuit(true);
                return input;
            default:
                throw new InvalidInputsException("Unrecognised command, please refer to above available commands and try again.");
        }
    }

    private void validateLine(ShapeInput shapeInput) {
        CanvasModel canvasModel = shapeInput.getCanvasModel();
        int x1 = shapeInput.getX1();
        int x2 = shapeInput.getX2();
        int y1 = shapeInput.getY1();
        int y2 = shapeInput.getY2();

        if (x1 > canvasModel.getXLimit() || x2 > canvasModel.getXLimit() || y1 > canvasModel.getYLimit() || y2 > canvasModel.getYLimit()) {
            throw new OutOfCanvasException("Invalid input, cannot draw line outside of canvas.");
        }

        if (x1 != x2 && y1 != y2) {
            throw new InvalidInputsException("Invalid input, cannot draw diagonal lines.");
        }
    }

    private void validateRectangle(ShapeInput shapeInput) {
        CanvasModel canvasModel = shapeInput.getCanvasModel();
        int x1 = shapeInput.getX1();
        int x2 = shapeInput.getX2();
        int y1 = shapeInput.getY1();
        int y2 = shapeInput.getY2();

        if (x1 == x2 || y1 == y2) {
            throw new InvalidInputsException("Invalid input, cannot draw rectangle.");
        }
        if (x1 > canvasModel.getXLimit() || x2 > canvasModel.getXLimit() || y1 > canvasModel.getYLimit() || y2 > canvasModel.getYLimit()) {
            throw new OutOfCanvasException("Invalid input, cannot draw rectangle outside of canvas.");
        }
    }

    private void validateFillColor(FillInput fillInput) {
        CanvasModel canvasModel = fillInput.getCanvasModel();
        int x = fillInput.getX();
        int y = fillInput.getY();

        if (x > canvasModel.getXLimit() || y > canvasModel.getYLimit()) {
            throw new OutOfCanvasException("Invalid input, fill point (" + x + "," + y + ") is outside of canvas.");
        }
    }

    private CommandType getCommandType(String input) {
        switch (input) {
            case "C":
                return CommandType.CREATE_CANVAS;
            case "L":
                return CommandType.LINE;
            case "R":
                return CommandType.RECT;
            case "B":
                return CommandType.FILL;
            case "Q":
                return CommandType.QUIT;
            default:
                return CommandType.UNKNOWN;
        }
    }

    private Input createNewInput(String[] inputs) {
        Input input = new Input();
        CanvasModel canvasModel = new CanvasModel();
        int x = parseInteger(inputs[1]);
        int y = parseInteger(inputs[2]);
        validateNegativeInteger(x);
        validateNegativeInteger(y);
        validateZeroInteger(x);
        validateZeroInteger(y);
        canvasModel.setXLimit(x).setYLimit(y);
        input.setCanvasModel(canvasModel);
        return input;
    }

    private ShapeInput createNewShapeInput(String[] inputs) {
        ShapeInput shapeInput = new ShapeInput();
        int x1 = parseInteger(inputs[1]);
        int y1 = parseInteger(inputs[2]);
        int x2 = parseInteger(inputs[3]);
        int y2 = parseInteger(inputs[4]);
        validateNegativeInteger(x1);
        validateNegativeInteger(x2);
        validateNegativeInteger(y1);
        validateNegativeInteger(y2);
        validateZeroInteger(x1);
        validateZeroInteger(x2);
        validateZeroInteger(y1);
        validateZeroInteger(y2);
        shapeInput.setX1(x1).setX2(x2).setY1(y1).setY2(y2);
        return shapeInput;
    }

    private FillInput createNewFillInput(String[] inputs) {
        FillInput fillInput = new FillInput();
        int x = parseInteger(inputs[1]);
        int y = parseInteger(inputs[2]);
        char color = inputs[3].charAt(0);
        validateNegativeInteger(x);
        validateNegativeInteger(y);
        validateZeroInteger(x);
        validateZeroInteger(y);
        fillInput.setX(x).setY(y).setColor(color);
        return fillInput;
    }

    private int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NonIntegerException("Invalid inputs, inputs contain non integer.");
        }
    }

    private void validateInputsNull(String[] inputs) {
        if (inputs == null) {
            throw new NoInputException("No inputs given. Try again.");
        }
    }

    private void validateInputsLength(CommandType commandType, String[] inputs) {
        if (commandConstantsMap.get(commandType) != inputs.length) {
            throw new InvalidInputsException("Invalid number of parameters");
        }
    }

    private void validateNegativeInteger(int number) {
        if (number < 0) {
            throw new NegativeIntegerException("Invalid inputs, inputs contain negative integer.");
        }
    }
    
    private void validateZeroInteger(int number) {
    	if (number == 0) {
    		throw new ZeroIntegerException("Invalid inputs, inputs contain integer 0.");
    	}
    }

    private void validateCanvasNull(char[][] canvas){
        if (canvas == null){
            throw new NoCanvasException("Canvas must exist before drawing or filling on Canvas.");
        }
    }

    private void validateCanvasDoesNotExist(char[][] canvas){
        if (canvas != null){
            throw new CanvasAlreadyExistException("Unable to create new Canvas. Canvas already exist.");
        }
    }

    private void validateColor(String color){
        if (color.length() > 1){
            throw new InvalidInputsException("Invalid color length");
        }
    }
}
