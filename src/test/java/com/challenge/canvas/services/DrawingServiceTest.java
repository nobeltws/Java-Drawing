package com.challenge.canvas.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.FillInput;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.domain.ShapeInput;
import com.challenge.canvas.services.commands.Command;
import com.challenge.canvas.services.commands.CreateCanvasCommandImpl;
import com.challenge.canvas.services.commands.DrawLineCommandImpl;
import com.challenge.canvas.services.commands.DrawRectCommandImpl;
import com.challenge.canvas.services.commands.FillColorCommandImpl;
import com.challenge.canvas.util.CommandConstants.CommandType;

public class DrawingServiceTest {
	
	private DrawingService drawingService;
	private Input actualInput;
	
	private char[][] expectedZeroCanvas = {
			{'-','-'},
			{'-','-'}
		};
	private char[][] existingCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedEmptyCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedHorizontalLineCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|','X','X','X',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedVerticalLineCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ','X','|'},
			{'|',' ',' ',' ','X','|'},
			{'|',' ',' ',' ','X','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedRectCanvas = {
			{'-','-','-','-','-','-'},
			{'|','X','X','X',' ','|'},
			{'|','X',' ','X',' ','|'},
			{'|','X','X','X',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedFillCanvas = {
			{'-','-','-','-','-','-'},
			{'|','o','o','o','o','|'},
			{'|','o','o','o','o','|'},
			{'|','o','o','o','o','|'},
			{'|','o','o','o','o','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedFillLineCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|','o','o','o',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedFillRectCanvas = {
			{'-','-','-','-','-','-'},
			{'|','o','o','o',' ','|'},
			{'|','o',' ','o',' ','|'},
			{'|','o','o','o',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedFillInsideRectCanvas = {
			{'-','-','-','-','-','-'},
			{'|','X','X','X',' ','|'},
			{'|','X','o','X',' ','|'},
			{'|','X','X','X',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	private char[][] expectedFillOutsideRectCanvas = {
			{'-','-','-','-','-','-'},
			{'|','X','X','X','o','|'},
			{'|','X',' ','X','o','|'},
			{'|','X','X','X','o','|'},
			{'|','o','o','o','o','|'},
			{'-','-','-','-','-','-'}
		};

    @BeforeEach
    public void init() {
        drawingService = new DrawingService();
        actualInput = new Input();
    }

    @Test
    public void createCanvasSuccessTest() {
    	actualInput.setCommandType(CommandType.CREATE_CANVAS);
    	actualInput.setCanvasModel(new CanvasModel()
    									.setXLimit(4)
    									.setYLimit(4));
    	Command expectedCommand = new CreateCanvasCommandImpl();
    	drawingService.setCommand(actualInput.getCommandType());
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setXLimit(4)
    				.setYLimit(4);
    	actualCanvasModel = drawingService.executeCommand(actualInput);
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], actualCanvasModel.getCanvas()[i]);
    	}
    }
    
    @Test
    public void createCanvasOverwriteTest() {
    	Command expectedCommand = new CreateCanvasCommandImpl();
    	drawingService.setCommand(CommandType.CREATE_CANVAS);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	Input actualInput = new Input();
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setXLimit(4)
    				.setYLimit(4)
    				.setCanvas(existingCanvas);
    	actualInput.setCanvasModel(actualCanvasModel);
    	actualCanvasModel = drawingService.executeCommand(actualInput);
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], actualCanvasModel.getCanvas()[i]);
    	}
    }
    
    @Test
    public void createCanvasNullTest() {
    	Command expectedCommand = new CreateCanvasCommandImpl();
    	drawingService.setCommand(CommandType.CREATE_CANVAS);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(actualInput);
    	});
    }
    
    @Test
    public void createCanvasNegativeIntTest() {
    	actualInput.setCommandType(CommandType.CREATE_CANVAS);
    	actualInput.setCanvasModel(new CanvasModel()
    									.setXLimit(-1)
    									.setYLimit(-6));
    	Command expectedCommand = new CreateCanvasCommandImpl();
    	drawingService.setCommand(actualInput.getCommandType());
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	assertThrows(NegativeArraySizeException.class, () -> {
    		drawingService.executeCommand(actualInput);
    	});
    }
    
    @Test
    public void createCanvasZeroIntTest() {
    	actualInput.setCommandType(CommandType.CREATE_CANVAS);
    	actualInput.setCanvasModel(new CanvasModel()
    									.setXLimit(0)
    									.setYLimit(0));
    	Command expectedCommand = new CreateCanvasCommandImpl();
    	drawingService.setCommand(actualInput.getCommandType());
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actual = drawingService.executeCommand(actualInput);
    	for (int i = 0; i < expectedZeroCanvas.length; i++) {
    		assertArrayEquals(expectedZeroCanvas[i], actual.getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawHorizontalLineSuccessTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(1)
    				.setY1(3)
    				.setX2(3)
    				.setY2(3);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedHorizontalLineCanvas.length; i++) {
    		assertArrayEquals(expectedHorizontalLineCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawHorizontalLineNegativeIntTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(-1)
    				.setY1(-3)
    				.setX2(-3)
    				.setY2(-3);
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
        
    @Test
    public void drawVerticalLineSuccessTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(4)
    				.setY1(1)
    				.setX2(4)
    				.setY2(3);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedVerticalLineCanvas.length; i++) {
    		assertArrayEquals(expectedVerticalLineCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    
    @Test
    public void drawVerticalLineNegativeIntTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.getCanvasModel().setCanvas(expectedEmptyCanvas);
    	shapeInput.setX1(-4)
    				.setY1(-1)
    				.setX2(-4)
    				.setY2(-3);
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
    
    @Test
    public void drawLineNoCanvasTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setX1(4)
    				.setY1(1)
    				.setX2(4)
    				.setY2(3);
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
    
    @Test
    public void drawLineNullTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(null);
    	});
    }
    
    @Test
    public void drawLineZeroIntTest() {
    	Command expectedCommand = new DrawLineCommandImpl();
    	drawingService.setCommand(CommandType.LINE);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(0)
    				.setY1(0)
    				.setX2(0)
    				.setY2(0);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawRectSuccessTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(1)
    				.setY1(1)
    				.setX2(3)
    				.setY2(3);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedRectCanvas.length; i++) {
    		assertArrayEquals(expectedRectCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawRectNoCanvasTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setX1(1)
    				.setY1(1)
    				.setX2(3)
    				.setY2(3);
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
    
    @Test
    public void drawRectBadInputTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(1)
    				.setY1(1)
    				.setX2(1)
    				.setY2(3);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawRectNegativeIntTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(-1)
    				.setY1(-1)
    				.setX2(-3)
    				.setY2(-3);
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
    
    @Test
    public void drawRectZeroIntTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	ShapeInput shapeInput = new ShapeInput();
    	shapeInput.setCanvasModel(actualCanvasModel);
    	shapeInput.setX1(0)
    				.setY1(0)
    				.setX2(0)
    				.setY2(0);
    	shapeInput.setCanvasModel(drawingService.executeCommand(shapeInput));
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], shapeInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void drawRectNullTest() {
    	Command expectedCommand = new DrawRectCommandImpl();
    	drawingService.setCommand(CommandType.RECT);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	ShapeInput shapeInput = new ShapeInput();
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(shapeInput);
    	});
    }
    
    @Test
    public void fillSuccessTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(1)
    				.setY(1);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedFillCanvas.length; i++) {
    		assertArrayEquals(expectedFillCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillLineSuccessTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedHorizontalLineCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(1)
    				.setY(3);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedFillLineCanvas.length; i++) {
    		assertArrayEquals(expectedFillLineCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillRectSuccessTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedRectCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(1)
    				.setY(3);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedFillRectCanvas.length; i++) {
    		assertArrayEquals(expectedFillRectCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillRectInsideSuccessTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedRectCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(2)
    				.setY(2);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedFillInsideRectCanvas.length; i++) {
    		assertArrayEquals(expectedFillInsideRectCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillRectOutsideSuccessTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedRectCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(1)
    				.setY(4);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedFillOutsideRectCanvas.length; i++) {
    		assertArrayEquals(expectedFillOutsideRectCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillNoCanvasTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	FillInput fillInput = new FillInput();
    	fillInput.setColor('o')
    				.setX(10)
    				.setY(30);
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(fillInput);
    	});
    }
    
    @Test
    public void fillOutsideCanvasTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(10)
    				.setY(30);
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
    		drawingService.executeCommand(fillInput);
    	});
    }
    
    @Test
    public void fillNegativeIntTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(-1)
    				.setY(-3);
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
    		drawingService.executeCommand(fillInput);
    	});
    }
    
    @Test
    public void fillZeroIntTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	CanvasModel actualCanvasModel = new CanvasModel();
    	actualCanvasModel.setCanvas(expectedEmptyCanvas);
    	FillInput fillInput = new FillInput();
    	fillInput.setCanvasModel(actualCanvasModel);
    	fillInput.setColor('o')
    				.setX(0)
    				.setY(0);
    	fillInput.setCanvasModel(drawingService.executeCommand(fillInput));
    	for (int i = 0; i < expectedEmptyCanvas.length; i++) {
    		assertArrayEquals(expectedEmptyCanvas[i], fillInput.getCanvasModel().getCanvas()[i]);
    	}
    }
    
    @Test
    public void fillNullTest() {
    	Command expectedCommand = new FillColorCommandImpl();
    	drawingService.setCommand(CommandType.FILL);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    	FillInput fillInput = new FillInput();
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.executeCommand(fillInput);
    	});
    }
    
    @Test
    public void setCommandFailTest() {
    	Command expectedCommand = null;
    	drawingService.setCommand(CommandType.UNKNOWN);
    	assertTrue(new ReflectionEquals(expectedCommand).matches(drawingService.getCommand()));
    }
    
    @Test
    public void setCommandNullTest() {
    	assertThrows(NullPointerException.class, () -> {
    		drawingService.setCommand(null);
    	});
    }
    
    

}


