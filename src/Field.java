/**
* @author Quang Hieu TRUONG
* @version 1.01
* Date: 24-04-2018
*
*/
public class Field {
	/**
	 * The field is divided by grids:
	 * x_value represent the number of columns.
	 * y_value represent the number of lines.
	 */
	private int x_value, y_value;
	
	//Contructor
	/**
	 * 
	 * @param x: x_value of the upper right grid
	 * @param y: y_value of the upper right grid
	 */
	public Field(int x, int y) {
		this.x_value = x;
		this.y_value = y;
	}
	
	//Getter
	public int getX( ) {
		return this.x_value;
	}
	public int getY() {
		return this.y_value;
	}
}
