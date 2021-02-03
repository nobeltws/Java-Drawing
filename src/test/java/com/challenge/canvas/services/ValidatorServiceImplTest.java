package com.challenge.canvas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.exceptions.CanvasAlreadyExistException;
import com.challenge.canvas.exceptions.InvalidInputsException;
import com.challenge.canvas.exceptions.NegativeIntegerException;
import com.challenge.canvas.exceptions.NoCanvasException;
import com.challenge.canvas.exceptions.NoInputException;
import com.challenge.canvas.exceptions.NonIntegerException;
import com.challenge.canvas.exceptions.OutOfCanvasException;
import com.challenge.canvas.exceptions.ZeroIntegerException;
import com.challenge.canvas.util.CommandConstants.CommandType;
import com.challenge.canvas.utils.TestHelper;

public class ValidatorServiceImplTest {

	private ValidatorServiceImpl validatorService;
	
	private TestHelper testHelper = new TestHelper();
	
	@BeforeEach
    public void init() {
		validatorService = new ValidatorServiceImpl();
	}
	
	@Test
	public void validateInputCreateCanvasSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4);
		Input expected = new Input()
						.setCommandType(CommandType.CREATE_CANVAS)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"C","4","4"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel();
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputCreateCanvasNonIntTest() {
		String[] actualInput = {"C","B",";"};
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(NonIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputCreateCanvasNegativeIntTest() {
		String[] actualInput = {"C","-1","-4"};
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(NegativeIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputCreateCanvasZeroIntTest() {
		String[] actualInput = {"C","0","0"};
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(ZeroIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputCreateCanvasAlreadyExistTest() {
		String[] actualInput = {"C","5","4"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas);
		assertThrows(CanvasAlreadyExistException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInvalidInputCreateCanvasTest() {
		String[] actualInput = {"C","5","4","W","434"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas);
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputHorizontalLineSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.LINE)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"L","1","3","3","3"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputReversedHorizontalLineSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.LINE)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"L","3","3","1","3"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputVerticalLineSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.LINE)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"L","4","1","4","3"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputReversedVerticalLineSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.LINE)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"L","4","3","4","1"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputDiagonalLineTest() {
		String[] actualInput = {"L","1","2","2","3"};
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputLineNonIntTest() {
		String[] actualInput = {"L","B",";","H","g"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NonIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputLineNegativeIntTest() {
		String[] actualInput = {"L","-8","-2","-4","-6"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NegativeIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputLineZeroIntTest() {
		String[] actualInput = {"L","0","0","0","0"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(ZeroIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputLineOutOfCanvasTest() {
		String[] actualInput = {"L","10","14","20","14"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(OutOfCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	

	@Test
	public void validateInputLineNoCanvasTest() {
		String[] actualInput = {"L","1","3","3","3"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NoCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInvalidInputLineTest() {
		String[] actualInput = {"L","5","4","87","434","345","WE"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}

	@Test
	public void validateInputRectSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.RECT)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"R","1","1","3","3"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputRectNonIntTest() {
		String[] actualInput = {"R","B",";","H","g"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NonIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputRectNegativeIntTest() {
		String[] actualInput = {"R","-8","-2","-4","-6"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NegativeIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputRectZeroIntTest() {
		String[] actualInput = {"R","0","0","0","0"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(ZeroIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputRectOutOfCanvasTest() {
		String[] actualInput = {"R","10","14","20","18"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(OutOfCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}	

	@Test
	public void validateInputRectNoCanvasTest() {
		String[] actualInput = {"R","1","3","3","4"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NoCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInvalidInputRectTest() {
		String[] actualInput = {"R"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputFillSuccessTest() {
		CanvasModel expectedCanvasModel = new CanvasModel()
												.setXLimit(4)
												.setYLimit(4)
												.setCanvas(testHelper.expectedEmptyCanvas);
		Input expected = new Input()
						.setCommandType(CommandType.FILL)
						.setCanvasModel(expectedCanvasModel);
		String[] actualInput = {"B","1","1","o"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel()
						.setCanvas(testHelper.expectedEmptyCanvas)
						.setXLimit(4)
						.setYLimit(4);
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertTrue(new ReflectionEquals(actualReturn.getCanvasModel()).matches(expected.getCanvasModel()));
		assertTrue(new ReflectionEquals(actualReturn.getCommandType()).matches(expected.getCommandType()));
		assertTrue(new ReflectionEquals(actualReturn.isQuit()).matches(expected.isQuit()));
	}
	
	@Test
	public void validateInputFillNonIntTest() {
		String[] actualInput = {"B","g",":","o"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NonIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputFillNegativeIntTest() {
		String[] actualInput = {"B","-1","-1","o"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NegativeIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputFillZeroIntTest() {
		String[] actualInput = {"B","0","0","o"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(ZeroIntegerException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputFillOutOfCanvasTest() {
		String[] actualInput = {"B","100","100","o"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(OutOfCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}

	@Test
	public void validateInputFillNoCanvasTest() {
		String[] actualInput = {"B","1","1","o"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(NoCanvasException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInvalidInputFillTest() {
		String[] actualInput = {"B","1","1","o","m","r"};
		CanvasModel actualCanvasModel = new CanvasModel()
				.setCanvas(testHelper.expectedEmptyCanvas)
				.setXLimit(4)
				.setYLimit(4);
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputQuitSuccessTest() {
		String[] actualInput = {"Q"};
		Input actualReturn = new Input();
		CanvasModel actualCanvasModel = new CanvasModel();
		actualReturn = validatorService.validateInput(actualInput, actualCanvasModel);
		assertEquals(actualReturn.isQuit(),true);
	}
	
	@Test
	public void validateInvalidInputQuitTest() {
		String[] actualInput = {"Q","1","2","3"};
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputUnknownTest() {
		String[] actualInput = {"K"};
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(InvalidInputsException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
	
	@Test
	public void validateInputNullTest() {
		String[] actualInput = null;
		CanvasModel actualCanvasModel = new CanvasModel();
		assertThrows(NoInputException.class, () -> {
			validatorService.validateInput(actualInput, actualCanvasModel);
    	});
	}
}
