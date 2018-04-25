/**
* @author Quang Hieu TRUONG
* @version 1.01
* Date: 24-04-2018
*
*/

public class Mower {
	private int Pos_x, Pos_y;
	//This int value is the direction pointer on Direction_list (for better maneuver)
	private int Direction;
	/**When the Mower turn left or right, the pointer just need to move left or right in the list respectively
	 * N: Nord
	 * E: East
	 * S: South
	 * W: West
	 */
	private char[] Direction_list = new char[] {'N','E','S','W'};
	
	/**
	 * 
	 * @param pos_x: Initial position x of the mower
	 * @param pos_y: Initial position y of the mower
	 * @param direction: Initial direction of the mower
	 */
	public Mower(int pos_x, int pos_y, char direction, Field field) {
		if(direction != 'N' && direction != 'E' && direction != 'S' && direction != 'W') {
			System.out.println("Error - Mower Direction not valid");
			return;
		}
		else if(pos_x < 0 || pos_x > field.getX()) {
			System.out.println("Error - Mower X coordination input not valid");
			return;
		}
		else if(pos_y < 0 || pos_y > field.getY()) {
			System.out.println("Error - Mower Y coordination input not valid");
			return;
		}
			
		else {
			this.Pos_x = pos_x;
			this.Pos_y = pos_y;
			switch(direction) {
			case 'N':
				this.Direction = 0;
				break;
			case 'E':
				this.Direction = 1;
				break;
			case 'S':
				this.Direction = 2;
				break;
			case 'W':
				this.Direction = 3;
				break;
			}
		}
	}
	
	//Getter
	public char getDirection() {
		return this.Direction_list[Direction];
	}
	public int getPos_x() {
		return this.Pos_x;
	}
	public int getPox_y() {
		return this.Pos_y;
	}
	
	//Setter
	/**
	 * 
	 * @param field : The field where the mower initialize on
	 * @param command: R: Right, L: Left, F: Forward
	 */
	public void move(Field field, char command) {
		switch(command) {
		case 'R':
			if(Direction == 3) Direction = 0;
			else Direction = Direction + 1;
			break;
		case 'L':
			if(Direction == 0) Direction = 3;
			else Direction = Direction - 1;
			break;
		case 'F':
			//The mower cannot go outside of the field
			switch(Direction_list[Direction]) {
			case 'N':
				if(Pos_y < field.getY()) Pos_y = Pos_y + 1;
				break;
			case 'E':
				if(Pos_x < field.getX()) Pos_x = Pos_x + 1;
				break;
			case 'S':
				if(Pos_y > 0) Pos_y = Pos_y - 1;
				break;
			case 'W':
				if(Pos_x > 0) Pos_x = Pos_x - 1;
				break;
			}
			break;
		default:
			System.out.println(command + " is not a valid command!");
			break;
		}
	}
}
