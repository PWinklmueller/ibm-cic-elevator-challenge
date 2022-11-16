package at.ibm.elevator.entities;

import at.ibm.elevator.enums.Direction;
/**
 * Represents the information that is given when pressing the button.
 * @author philippw
 *
 */
public class ElevatorEvent {
	private int startFloor;
	private int destinationFloor;
	private Direction direction;
	
	/**
	 * Constructor with all parameters.
	 * @param currentFloor     - Floor where person wants to be picked up from.
	 * @param destinationFloor - Floor where person wants to go.
	 * @param direction        - Direction in which the elevator will travel,
	 */
	public ElevatorEvent(int currentFloor, int destinationFloor, Direction direction) {
		this.setStartFloor(currentFloor);
		this.setDestinationFloor(destinationFloor);
		this.setDirection(direction);
	}
	
	/**
	 * Constructor without direction, as direction can be derived from the other two parameters.
	 * @param startFloor       - Floor where person wants to be picked up from.
	 * @param destinationFloor - Floor where person wants to go.
	 */
	public ElevatorEvent(int startFloor, int destinationFloor) {
		this(startFloor, destinationFloor,startFloor < destinationFloor ? Direction.UP : Direction.DOWN);
	}
	

	public int getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(int startFloor) {
		this.startFloor = startFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public String toString() {
		return "Start Floor: "+ this.getStartFloor() + ", destination Floor: "+ this.getDestinationFloor() + ", Direction:"+ this.getDirection();
	}

	
}
