package semaphore;

import java.util.concurrent.Semaphore;

public class MainSemaphore {
	
	public static void main(String[] args){
			int semNumber = 10;
			int numberPeoplo = 30;
			
			/*Semaphore semaforo = new Semaphore(semNumber);
			RussianMountainSemaphore[] person = new RussianMountainSemaphore[numberPeoplo];
			for(int i =0; i < numberPeoplo; i++){
				person[i] = new RussianMountainSemaphore(i, semaforo);
				person[i].start();
			}*/
			
			Semaphore entrada = new Semaphore(1);
			Semaphore saida = new Semaphore(1);
			
			CarBuffer buffer = new CarBuffer(30,10,entrada,saida);
			buffer.start();
			Pessoa[] test = new Pessoa[30];
			for(int i =0; i< 30; i++){
				test[i] = new Pessoa();
				test[i].start();
			}
	}

}
