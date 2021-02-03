package com.challenge.canvas.util;

import java.util.Map;

import static com.challenge.canvas.util.CommandConstants.CommandType.*;

public class CommandConstants {
	public final static Map<CommandType, Integer> commandConstantsMap = Map.of(
			CREATE_CANVAS, 3,
			LINE, 5,
			RECT, 5,
			FILL, 4,
			QUIT, 1);

	public enum CommandType {
		CREATE_CANVAS,
		LINE,
		RECT,
		FILL,
		QUIT,
		UNKNOWN
	}
}
