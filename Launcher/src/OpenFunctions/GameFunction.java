package OpenFunctions;

import java.io.IOException;
import java.util.Scanner;

import Launcher.Selector;

public class GameFunction extends Function {
	
	protected String runCommand;
	
	public GameFunction(Scanner in) {
		runCommand = in.nextLine();
	}
	
	@Override
	public void activate() {
		try {
			Process proc = Runtime.getRuntime().exec(runCommand);
			try {
				proc.waitFor();
			} catch(Exception e) {
				e.printStackTrace();
			}
			/*
			int exitValue = proc.waitFor();
			if (exitValue != 0) {
			    System.out.println("Abnormal process termination");
			}*/
		} catch(IOException e) {
			//TODO: If this error happens, it means game couldn't be run and so remove this game from the library
			e.printStackTrace();
		}
	}
}
