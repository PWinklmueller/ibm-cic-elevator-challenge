package at.ibm.elevator.entities;

import java.util.ArrayList;

import at.ibm.elevator.enums.Direction;

/**
 * Represents an elevator.
 * @author philippw
 *
 */
public class Elevator extends Thread {

	private int elevatorId;
	private int currentFloor;
	private ArrayList<ElevatorEvent> listOfEvents;
	
	/**
	 * Constructor. Default Floor for elevator is 0. Also intializes list of Events.
	 * @param elevatorId
	 */
	Elevator(int elevatorId) {
		this.setElevatorId(elevatorId);
		this.setCurrentFloor(0);
		this.setListOfEvents(new ArrayList<>());
	}

	/**
	 * Calculates how close the elevator is to a new event.
	 * This works by adding together all the distances the elevator
	 * has to travel. If you have more questions about this algorithm
	 * just ask me in the interview, as I figured this algorithm out with pen & paper. 
	 * @param eventFloor       - Floor of the new Event.
	 * @param destinationFloor
	 * @return
	 */
	public int calcAvailability(int eventFloor) {
		int score = 0;
		int floor = currentFloor;
		for(ElevatorEvent event : listOfEvents){
			score += Math.abs(floor - event.getStartFloor()) + Math.abs(event.getStartFloor() - event.getDestinationFloor());
			floor = event.getDestinationFloor();
		}
		score += Math.abs(floor - eventFloor);
		return score;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		String spacer = "";
		for(int i = 0; i < this.getElevatorId(); i++) {
			spacer += "\t\t\t";
		}
		System.out.printf("Elevator Nr. %d is now running, Destination = %s \n",this.getElevatorId(), this.getListOfEvents().get(0).toString());
		while(!Thread.interrupted()){
			if (!listOfEvents.isEmpty()) {
				ElevatorEvent nextEvent = listOfEvents.get(0); 
				System.out.printf("%s #: %d Next Event: %d-%d \n", spacer, this.getElevatorId(), nextEvent.getStartFloor(), nextEvent.getDestinationFloor());
				
				// Move Elevator to start Floor
				while(nextEvent.getStartFloor() - currentFloor != 0) {
					if(nextEvent.getStartFloor() > currentFloor) {
						currentFloor = moveElevator(currentFloor, Direction.UP);
					} else {
						currentFloor = moveElevator(currentFloor, Direction.DOWN);
					}
					System.out.printf("%s #: %d| %d/%d \n", spacer, this.getElevatorId(), currentFloor, nextEvent.getStartFloor());
				}
				System.out.printf("%s #: %d Arrived @ Event Floor \n", spacer, this.getElevatorId());
				
				// Move elevator to destination Floor
				while(nextEvent.getDestinationFloor() - currentFloor != 0) {
					System.out.printf("%s #: "+this.getElevatorId()+"| %d/%d \n", spacer, currentFloor, nextEvent.getDestinationFloor());
					
					if(nextEvent.getDirection() == Direction.UP) {						
						currentFloor = moveElevator(currentFloor, Direction.UP);
					} else {
						currentFloor = moveElevator(currentFloor, Direction.DOWN);
					}
				}
				System.out.printf("%s #: %d Arrived @ Dest. Floor \n", spacer, this.getElevatorId());
				
				// After Elevator arrived, remove event
				if(listOfEvents.size() != 0) {					
					listOfEvents.remove(0);	
				}
			}
		}
	}
	
	private int moveElevator(int currentFloor, Direction direction) {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {}
		if(direction == Direction.UP) {
			return (currentFloor + 1);
		} else {
			return (currentFloor - 1);
		}
	}
	
	public int getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(int elevatorId) {
		this.elevatorId = elevatorId;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public ArrayList<ElevatorEvent> getListOfEvents() {
		return listOfEvents;
	}

	public void setListOfEvents(ArrayList<ElevatorEvent> listOfEvents) {
		this.listOfEvents = listOfEvents;
	}

	public void addToListOfEvents(ElevatorEvent event) {
		listOfEvents.add(event);
	}
	public void removeFromListOfEvents(ElevatorEvent event) {
		listOfEvents.remove(event);
	}


}
