package semaphore;

import java.util.concurrent.Semaphore;

public class MainSemaphore {
	
	public static void main(String[] args){
			int semNumber = 10;
			int numberPeoplo = 31;
			
			Semaphore semaforo = new Semaphore(semNumber);
			RussianMountainSemaphore[] person = new RussianMountainSemaphore[numberPeoplo];
			for(int i =1; i < numberPeoplo + 1; i++){
				person[i] = new RussianMountainSemaphore(i, semaforo);
				person[i].start();
			}
	}

}
