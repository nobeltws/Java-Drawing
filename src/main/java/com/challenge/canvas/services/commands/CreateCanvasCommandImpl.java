package com.challenge.canvas.services.commands;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import org.springframework.stereotype.Service;

@Service
public class CreateCanvasCommandImpl implements Command {

    @Override
    public CanvasModel executeCommand(Input input) {
        input.getCanvasModel().drawCanvas();
        return  input.getCanvasModel();
    }
}
