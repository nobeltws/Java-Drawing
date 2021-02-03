package com.challenge.canvas.services;

import com.challenge.canvas.exceptions.BusinessException;
import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;

public interface ValidatorService {
	Input validateInput(String[] inputs, CanvasModel canvasModel) throws BusinessException;
}
