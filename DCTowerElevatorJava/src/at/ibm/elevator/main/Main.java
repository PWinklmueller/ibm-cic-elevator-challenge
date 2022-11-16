package at.ibm.elevator.main;

import at.ibm.elevator.entities.ElevatorEvent;
import at.ibm.elevator.entities.ElevatorManager;
import at.ibm.elevator.enums.Direction;

/**
 * 
 * @author philippw
 *
 */
public class Main {

	/**
	 * Used for starting the application and testing the functionality of the elevatorManager.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int floors = 55;
		int elevators = 7;
		ElevatorManager elevatorManager = new ElevatorManager(floors,elevators);
		
//		ElevatorEvent newEvent = new ElevatorEvent(1,35,Direction.UP);
//		elevatorManager.addRequest(newEvent);
//		ElevatorEvent newEvent2 = new ElevatorEvent(15,5,Direction.DOWN);
//		elevatorManager.addRequest(newEvent2);
//		ElevatorEvent newEvent3 = new ElevatorEvent(20,30,Direction.UP);
//		elevatorManager.addRequest(newEvent3);
//		ElevatorEvent newEvent4 = new ElevatorEvent(55,35,Direction.DOWN);
//		elevatorManager.addRequest(newEvent4);
//		ElevatorEvent newEvent5 = new ElevatorEvent(44,45,Direction.UP);
//		elevatorManager.addRequest(newEvent5);
		
		for(int i = 0; i < 20; i++) {
			elevatorManager.addRequest(
					new ElevatorEvent((int) (Math.random()*floors),(int) (Math.random()*floors))
			);
			try {
				Thread.sleep(1000);
			}catch( InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
