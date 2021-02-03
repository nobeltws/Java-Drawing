package com.challenge.canvas.services;

import com.challenge.canvas.domain.CanvasModel;
import com.challenge.canvas.domain.Input;
import com.challenge.canvas.services.commands.*;
import com.challenge.canvas.util.CommandConstants.CommandType;
import org.springframework.stereotype.Service;

@Service
public class DrawingService {

    private Command command;

    public void setCommand(CommandType commandType){
    	try {
	        switch (commandType){
	            case CREATE_CANVAS:
	                this.command = new CreateCanvasCommandImpl();
	                break;
	            case LINE:
	                this.command = new DrawLineCommandImpl();
	                break;
	            case RECT:
	                this.command = new DrawRectCommandImpl();
	                break;
	            case FILL:
	                this.command = new FillColorCommandImpl();
	                break;
	            default:
	        }
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    public Command getCommand() {
    	return this.command;
    }

    public CanvasModel executeCommand(Input input){
        return command.executeCommand(input);
    }

}
