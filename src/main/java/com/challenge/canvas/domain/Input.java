package com.challenge.canvas.domain;


import lombok.Getter;
import lombok.Setter;

import static com.challenge.canvas.util.CommandConstants.CommandType;

@Getter
@Setter
public class Input {
    private CanvasModel canvasModel;
    private CommandType commandType;
    private boolean quit = false;
}
