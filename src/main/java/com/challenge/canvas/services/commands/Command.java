package com.challenge.canvas.services.commands;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;

public interface Command {
    CanvasModel executeCommand(Input input);
}
