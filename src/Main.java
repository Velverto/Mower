/**
* @author Quang Hieu TRUONG
* @version 1.01
* Date: 24-04-2018
*
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args){
	
	    BufferedReader in, in_file;
	    	    
	    boolean exit = false;					//Terminate the loops when set to true.
	    String input_string = new String();		//Stocked strings for readline() method.
	    
	    while(!exit) {
		    // Sleep for 1 second for each loop (better visual experience).
	    	try {
	    		Thread.sleep(1000);
	    	} catch(InterruptedException ie) {
	    		ie.printStackTrace();
	    	}
	    	/** **Main menu***/
	    	System.out.println("*********MOWER SIMULATOR********************");
	    	System.out.println("*[1]: Use the default test case   				");
	    	System.out.println("*	5 5 										");
	    	System.out.println("*	1 2 N 										");
	    	System.out.println("*	LFLFLFLFF 									");
	    	System.out.println("*	3 3 E										");
	    	System.out.println("*	FFRFFRFRRF 									");
	    	System.out.println("*[2]: Use the test case from a file   			");
	    	System.out.println("*[3]: Exit   									");
	    	
	    	in = new BufferedReader(new InputStreamReader(System.in));
	    	try {
	    		input_string = in.readLine();
	    		try {
	    			int input_number = Integer.parseInt(input_string);
	    			switch(input_number) {
	    			case 1:
	    				//Run the default test
	    				default_test();
	    				break;
	    			case 2:
	    				//Run the simulation from a file
	    				System.out.println("Please include the full path of the file:");
	    				System.out.println("If successful, a file output.txt is generated at the application directory. It contain the final position of the mowers");
	    				input_string = in.readLine();
	    				try {
	    					in_file = new BufferedReader(new FileReader(new File(input_string)));
	    					test_from_file(in_file);
	    					in_file.close();
	    				}
	    				catch (FileNotFoundException e) {
	    			        System.out.println("File not Found!");
	    			    }
	    				break;
	    			case 3:
	    				//Exit the program
	    				System.out.println("Exiting!");
	    				exit = true;
	    				in.close();
	    			default:
	    				System.out.println("Not a valid number/chatacter!");
	    				break;
	    			}
	    		}
	    		catch(NumberFormatException e) {
    				System.out.println("Not a valid number/chatacter!");
    			}
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }	    
	    return;
	}

	
	public static void default_test() {
		Field main_field;
	    Mower mower;
	    String input_string;
	    
		main_field = new Field(5, 5);
		System.out.println("Field initialized!");
		
		mower = new Mower(1, 2, 'N', main_field);
		System.out.println("Mower 1 initialized at: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
		
		input_string = "LFLFLFLFF";
		for(char i : input_string.toCharArray()) {
    		mower.move(main_field, i);
    	}
    	System.out.println("Moving sequences completed!");
    	System.out.println("Mower 1  final position: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
    	
    	mower = new Mower(3, 3, 'E', main_field);
		System.out.println("Mower 2 initialized at: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
		
    	input_string = "FFRFFRFRRF";
		for(char i : input_string.toCharArray()) {
    		mower.move(main_field, i);
    	}
    	System.out.println("Moving sequences completed!");
    	System.out.println("Mower 2  final position: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
		
		return;
	}
	
	
	public static void test_from_file(BufferedReader input_file) {
		int x, y;
    	char direction;
    	int space_index1, space_index2;
    	Field main_field;
	    Mower mower;
	    String input_string;
	    BufferedWriter output_file;
	    
	    try {
	    	output_file = new BufferedWriter(new FileWriter(new File("output.txt")));
	    
		    /******	First line is the coordinates of the upper right corner of the lawn
	    	 * 		The program will create a field using these coordinates
	    	 */
		    try {
		    	input_string = input_file.readLine();
		    	if(input_string == null) {
		    		System.out.println("Data Error: Data not found at line 1 !");
		    		output_file.close();
		    		return;
		    	}
		    		//Locate the space between 2 numbers, 
		    		//if there is no space then the test case is invalid
		    		space_index1 = input_string.indexOf(' ', 0);
		    		if(space_index1 == -1) {
		    			System.out.println("Data Error: Invalid format at line 1 !");
		    			output_file.close();
		    			return;
		    		}
		    		else {
		    			try {
			    			//Initiate the main field
			    		    x = Integer.parseInt(input_string.substring(0, space_index1));
			    			y = Integer.parseInt(input_string.substring(space_index1+1, input_string.length()));
			    			main_field = new Field(x, y);
			    			System.out.println("Field initialized!");
		    			}
		    			catch(NumberFormatException e) {
		    				System.out.println("Data Error: Invalid value at line 1 !");
		    				output_file.close();
		    				return;
		    			}
		    		}
		    			
		    	/*****Mower commands lines************/
		    	int mower_counter = 1;
		    	while((input_string = input_file.readLine()) != null) {  
		    		/***Line 1: initiate the mower
		    		 * the format is x y Direction
		    		 * Exemple: 1 2 N
		    		 */
		    		space_index1 = input_string.indexOf(' ', 0);
		    		space_index2 = input_string.indexOf(' ', space_index1+1);
		    		if(space_index1 == -1 || space_index2 == -1) {
		    			System.out.println("Data Error: Invalid format at line " + 2*mower_counter + " !");
		    			output_file.close();
		    			return;
		    		}
		    		else {
		    			try {
			    		    x = Integer.parseInt(input_string.substring(0, space_index1));
			    			y = Integer.parseInt(input_string.substring(space_index1+1, space_index2));
		    			}
		    			catch(NumberFormatException e) {
		    				System.out.println("Data Error: Invalid value at line " + 2*mower_counter + " !");
		    				output_file.close();
		    				return;
		    			}
		    			String tmp_str = input_string.substring(space_index2+1, input_string.length());
		    			direction = tmp_str.charAt(0);
		    			mower = new Mower(x, y, direction, main_field);
		    			System.out.println("Mower " + mower_counter + " initialized at: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
		    		}
		    		
			    	/***Line 2: Mower commands*****/
			    	input_string = input_file.readLine();
				    if(input_string == null) {
				    	System.out.println("Data Error: Invalid format at line " + ((2*mower_counter)+1) + " !");
				    	output_file.close();
				    	return;
				    }
			    	for(char i : input_string.toCharArray()) {
			    		mower.move(main_field, i);
			    	}
			    	System.out.println("Moving sequences completed!");
			    	System.out.println("Mower " + mower_counter + " final position: " + mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection());
			    	System.out.println("");
			    	//save the results to output.txt
			    	output_file.write(mower.getPos_x() + " " + mower.getPox_y() + " " + mower.getDirection() + "\n");
			    	mower_counter++;
		    	}
		    	output_file.close();
		    }
		    catch(IOException e_input) {
		    	System.out.println("Data Error: IOException!");
		    	e_input.printStackTrace();
		    }
	    }
	    catch(IOException e_output) {
	    	System.out.println("Error: Cannot create file output.txt, make sure the name output.txt and/or the permission is avaiable");
	    	e_output.printStackTrace();
	    }
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
