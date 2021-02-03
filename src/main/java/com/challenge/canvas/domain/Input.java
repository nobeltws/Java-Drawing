package com.challenge.canvas.domain;


import lombok.Getter;
import lombok.Setter;

import static com.challenge.canvas.util.CommandConstants.CommandType;

@Getter
public class Input {
    private CanvasModel canvasModel;
    private CommandType commandType;
    private boolean quit = false;
    
	public Input setCanvasModel(CanvasModel canvasModel) {
		this.canvasModel = canvasModel;
		return this;
	}
	public Input setCommandType(CommandType commandType) {
		this.commandType = commandType;
		return this;
	}
	public Input setQuit(boolean quit) {
		this.quit = quit;
		return this;
	}
    
}
