package Launcher;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputManager {
	
	public static final int NUM_PLAYERS = 2;

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
	
	private InputManager() {
		
		try {
			Scanner in = new Scanner(new File("controls.txt")).useDelimiter("(\\s*=\\s*)|(\\n)");
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
			
			in.close();
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
	}
	
	public void setKey(char key, int code, int player) {
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

	private static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    if (str.isEmpty()) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (str.length() == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < str.length(); i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
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
