package barrier;

import java.util.concurrent.CyclicBarrier;

import barrier.RussianMountainBarrier;

public class MainBarrier {
	
	public static void main(String args[]) {
		
		RussianMountainBarrier[] person = new RussianMountainBarrier[30];
		Runnable acaoBarreira = new Runnable() {
			public void run() {
				try{
					System.out.println("carro em movimento");
					System.out.println("liberrando o carro");

				}catch(Exception e){
					e.printStackTrace();;
				}
			}
		};
		CyclicBarrier barreira = new CyclicBarrier(10, acaoBarreira);
			for(int i =0; i < 30 ; i++){
				person[i] = new RussianMountainBarrier(i, barreira);
				person[i].start();
			}
	}
		
	
		
}	

