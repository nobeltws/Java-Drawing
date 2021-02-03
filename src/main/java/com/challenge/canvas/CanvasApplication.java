package com.challenge.canvas;

import com.challenge.canvas.services.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CanvasApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CanvasApplication.class, args);
	}

	@Autowired
	private CanvasService canvasService;

	@Override
	public void run(String...args) throws Exception{
		canvasService.runCanvasApplication();
	}
	
}
