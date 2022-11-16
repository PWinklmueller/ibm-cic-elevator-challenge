package at.ibm.elevator.entities;

import java.util.ArrayList;


/**
 * Represents the elevator manager.
 * @author philippw
 *
 */
public class ElevatorManager {
	
	private ArrayList<Elevator> elevators = new ArrayList<>();
	private int numberOfElevators;
	private int numberOfFloors;
	
	/**
	 * Default Constructor.
	 */
	public ElevatorManager() {
		this(55,7); // Default Values from Challenge; 55 Floors, 7 Elevators
	}
	
	/**
	 * Constuctor. Also initializes the elevator list.
	 * @param numberOfFloors    - The number of floors in the building.
	 * @param numberOfElevators - The number of elevators in the building.
	 */
	public ElevatorManager(int numberOfFloors, int numberOfElevators) {
		this.setNumberOfElevators(numberOfElevators);
		this.setNumberOfFloors(numberOfFloors);
		for(int i = 0; i < numberOfElevators; i++) {
			Elevator newElevator = new Elevator(i);
			elevators.add(newElevator);
		}
	}
	
	
	/**
	 * Adds the elevatorEvent to the closest elevator.
	 * @param elevatorEvent - Carries the start, end and direction of the ride.
	 */
	public void addRequest(ElevatorEvent elevatorEvent) {
		int bestScore = Integer.MAX_VALUE;
		Elevator bestElevator = elevators.get(0);
		for(Elevator elevator : elevators) {
				int score = elevator.calcAvailability(elevatorEvent.getStartFloor());
				if(score < bestScore) {
					bestScore = score;
					bestElevator = elevator;
				}				
		}
		bestElevator.addToListOfEvents(elevatorEvent);
		if(!bestElevator.isAlive()) {
			bestElevator.start();
		}	
	}
	
	
	public int getNumberOfElevators() {
		return numberOfElevators;
	}


	public void setNumberOfElevators(int numberOfElevators) {
		this.numberOfElevators = numberOfElevators;
	}


	public int getNumberOfFloors() {
		return numberOfFloors;
	}


	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	
	
	
	
}
