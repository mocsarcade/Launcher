package Launcher;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputManager {
	
	public static final int NUM_PLAYERS = 2;
	
	/*
	 * This class is a singleton. In order to reach the manager, you have to call the getManager method
	 */
	private static InputManager instance;
	public static InputManager getManager() {
		if(instance != null) {
			return instance;
		}
		else {
			instance = new InputManager();
			return instance;
		}
	}

	private int[] up = new int[NUM_PLAYERS];
	private int[] left = new int[NUM_PLAYERS];
	private int[] down = new int[NUM_PLAYERS];
	private int[] right = new int[NUM_PLAYERS];
	private int[] A = new int[NUM_PLAYERS];
	private int[] B = new int[NUM_PLAYERS];
	private int[] X = new int[NUM_PLAYERS];
	private int[] Y = new int[NUM_PLAYERS];
	private int[] Z = new int[NUM_PLAYERS];
	private boolean activeKeyboard = true;
	
	/*
	 * Manager constructor: Reads the controls file and sets up player controls accordingly
	 */
	private InputManager() {
		Scanner in = null;
		try {
			in = new Scanner(new File("controls.txt")).useDelimiter("(\\s*=\\s*)|(\\n)");
			int player = -1;
			while(in.hasNext()) {
				String next = in.next();
				if(!Pattern.matches("\\[Player\\d*\\]", next)) {
					//if(isInteger)
					switch(next) {
						case "up":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								up[player] = Integer.parseInt(newIn);
							}
						break;
						case "left":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								left[player] = Integer.parseInt(newIn);
							}
						break;
						case "right":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								right[player] = Integer.parseInt(newIn);
							}
						break;
						case "down":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								down[player] = Integer.parseInt(newIn);
							}
						break;
						case "A":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								A[player] = Integer.parseInt(newIn);
							}
						break;
						case "B":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								B[player] = Integer.parseInt(newIn);
							}
						break;
						case "X":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								X[player] = Integer.parseInt(newIn);
							}
						break;
						case "Y":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								Y[player] = Integer.parseInt(newIn);
							}
						break;
						case "Z":
							if(in.hasNext()) {
								String newIn = (in.next()).trim();
								Z[player] = Integer.parseInt(newIn);
							}
						break;
					}
				} else {
					player++;
				}
			}
		}
		catch(FileNotFoundException e) {
			//Create new controls.ini file
				//Set default values if no controls file
				 up[0] = 38;
				 left[0] = 37;
				 right[0] = 39;
				 down[0] = 40;
				 A[0] = 74;
				 B[0] = 73;
				 X[0] = 72;
				 Y[0] = 75;
				 Z[0] = 76;
				 up[1] = 87;
				 left[1] = 65;
				 right[1] = 68;
				 down[1] = 83;
				 A[1] = 68;
				 B[1] = 69;
				 X[1] = 70;
				 Y[1] = 71;
				 Z[1] = 72;
			UpdateFile();
		}
		finally {
			in.close();
		}
	}
	
	public void setKey(char key, int code, int player) {
		if(isUnused(code)) {
			char keyLetter = Character.toUpperCase(key);
			switch(keyLetter) {
				case 'L':
					left[player] = code;
				break;
				case 'R':
					right[player] = code;
				break;
				case 'U':
					up[player] = code;
				break;
				case 'D':
					down[player] = code;
				break;
				case 'A':
					A[player] = code;
				break;
				case 'B':
					B[player] = code;
				break;
				case 'X':
					X[player] = code;
				break;
				case 'Y':
					Y[player] = code;
				break;
				case 'Z':
					Z[player] = code;
				break;
			}
		}
	}
	
	public String getKey(char key, int player) {
		char keyLetter = Character.toUpperCase(key);
		switch(keyLetter) {
			case 'L':
				return KeyEvent.getKeyText(left[player]);
			case 'R':
				return KeyEvent.getKeyText(right[player]);
			case 'U':
				return KeyEvent.getKeyText(up[player]);
			case 'D':
				return KeyEvent.getKeyText(down[player]);
			case 'A':
				return KeyEvent.getKeyText(A[player]);
			case 'B':
				return KeyEvent.getKeyText(B[player]);
			case 'X':
				return KeyEvent.getKeyText(X[player]);
			case 'Y':
				return KeyEvent.getKeyText(Y[player]);
			case 'Z':
				return KeyEvent.getKeyText(Z[player]);
			default:
				return "NULL";
		}
	}

	public int getKeyNum(char key, int player) {
		char keyLetter = Character.toUpperCase(key);
		switch(keyLetter) {
			case 'L':
				return (left[player]);
			case 'R':
				return (right[player]);
			case 'U':
				return (up[player]);
			case 'D':
				return (down[player]);
			case 'A':
				return (A[player]);
			case 'B':
				return (B[player]);
			case 'X':
				return (X[player]);
			case 'Y':
				return (Y[player]);
			case 'Z':
				return (Z[player]);
			default:
				return -1;
		}
	}
	
	public void allowInput(boolean flag) {
		activeKeyboard = flag;
	}
	
	public boolean isActive() {
		return activeKeyboard;
	}
	
	/*
	 * Checks if a key is unused by arrow keys and the A and buttons
	 * This method is private because it is only called by the setKey method
	 * @param keyCode is the code of the newest keystroke being checked if it is already being used
	 */
	private boolean isUnused(int keyCode) {
		if(keyCode == up[0] || keyCode == up[1]) {
			return false;
		}
		if(keyCode == left[0] || keyCode == left[1]) {
			return false;
		}
		if(keyCode == right[0] || keyCode == right[1]) {
			return false;
		}
		if(keyCode == down[0] || keyCode == down[1]) {
			return false;
		}
		if(keyCode == A[0] || keyCode == A[1]) {
			return false;
		}
		return true;
	}
	
	public void UpdateFile() {
		//Create number writer output file
		try {
	        PrintWriter out = new PrintWriter("controls.txt");
			for(int i=0; i<NUM_PLAYERS; i++) {
				out.println(" = [Player" + (i+1) + "] = ");
				out.println("up = " + up[i]);
				out.println("left = " + left[i]);
				out.println("right = " + right[i]);
				out.println("down = " + down[i]);
				out.println("A = " + A[i]);
				out.println("B = " + B[i]);
				out.println("X = " + X[i]);
				out.println("Y = " + Y[i]);
				out.println("Z = " + Z[i]);
				out.println();
			}
			//Close the file
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
