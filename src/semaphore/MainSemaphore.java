package semaphore;

import java.util.concurrent.Semaphore;

public class MainSemaphore {
	
	public static void main(String[] args){
			int semNumber = 10;
			int numberPeoplo = 30;
			
			Semaphore semaforo = new Semaphore(semNumber);
			RussianMountainSemaphore[] person = new RussianMountainSemaphore[numberPeoplo];
			for(int i =0; i < numberPeoplo; i++){
				person[i] = new RussianMountainSemaphore(semaforo);
				person[i].start();
			}
	}

}
