package com.challenge.canvas.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import com.challenge.canvas.util.Helper;

public class CanvasServiceTest {
	
	private CanvasServiceMock canvasService;
	private DrawingService drawingService;
	private Helper helper;
	private ValidatorService validatorService;
	
	private char[][] expectedEmptyCanvas = {
			{'-','-','-','-','-','-'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'|',' ',' ',' ',' ','|'},
			{'-','-','-','-','-','-'}
		};
	
	@BeforeEach
    public void init() {
		drawingService = new DrawingService();
		helper = new Helper();
		canvasService = new CanvasServiceMock(drawingService, validatorService, helper);
    }

    @Test
    public void readInputSuccessTest() throws IOException{
    	String actualInput = "C 4 4";
    	String[] actualReturnInput = canvasService.readInput(actualInput);
    	String[] expected = {"C","4","4"};
    	assertArrayEquals(expected, actualReturnInput);
    }
    
    @Test
    public void readCreateCanvasInputNullTest() throws IOException{
    	String actualInput = "";
    	assertThrows(NullPointerException.class, () -> {
    		canvasService.readInput(actualInput);
    	});
    }
    
    @Test
    public void runApplicationSuccessTest() throws IOException{
    	String actualInput = "C 4 4";
    	canvasService.runCanvasApplication(actualInput);
    	//TODO
    }
}