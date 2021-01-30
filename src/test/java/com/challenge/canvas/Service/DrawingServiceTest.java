package com.challenge.canvas.Service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.canvas.Model.CanvasModel;

@ExtendWith(MockitoExtension.class)
public class DrawingServiceTest {
	
	@Autowired
	@InjectMocks
	private DrawingServiceImpl drawingService;
	
	@Mock
	private ValidatorService validatorService;
	
	CanvasModel canvasModel;
	
	@BeforeEach
	public void init() {
		canvasModel = new CanvasModel();
	}
	
	@Test
	public void drawCanvasSuccessTest() {
		Mockito.when(validatorService.validateCanvas(canvasModel))
		.thenReturn(true);
		canvasModel.setX(4);
		canvasModel.setY(4);
		final String[] expectedRow1 = {"-","-","-","-","-","-"};
		final String[] expectedRow2 = {"|"," "," "," "," ","|"};
		final String[] expectedRow3 = {"|"," "," "," "," ","|"};
		final String[] expectedRow4 = {"|"," "," "," "," ","|"};
		final String[] expectedRow5 = {"|"," "," "," "," ","|"};
		final String[] expectedRow6 = {"-","-","-","-","-","-"};

		final String [][] actual = drawingService.drawCanvas(canvasModel);
		Assert.assertArrayEquals(expectedRow1, actual[0]);
		Assert.assertArrayEquals(expectedRow2, actual[1]);
		Assert.assertArrayEquals(expectedRow3, actual[2]);
		Assert.assertArrayEquals(expectedRow4, actual[3]);
		Assert.assertArrayEquals(expectedRow5, actual[4]);
		Assert.assertArrayEquals(expectedRow6, actual[5]);
	}
	
	@Test
	public void drawCanvasNegIntTest() {
		Mockito.when(validatorService.validateCanvas(canvasModel))
		.thenReturn(false);
		canvasModel.setX(-5);
		canvasModel.setY(-3);
		final String[][] expected = canvasModel.getCanvas();
		final String[][] actual = drawingService.drawCanvas(canvasModel);
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void drawCanvasNullInputTest() {
		Mockito.when(validatorService.validateCanvas(canvasModel))
		.thenReturn(false);
		final String[][] expected = canvasModel.getCanvas();
		final String[][] actual = drawingService.drawCanvas(canvasModel);
		Assert.assertArrayEquals(expected, actual);
	}
	
//	@Test
//	public void printHelpTest() {
//		
//	}
	
}


