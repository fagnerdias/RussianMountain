package semaphore;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RussianMountainSemaphore extends Thread {
	private int id;
	private Semaphore semaforo;
	private int capacidade = 10;
	private static int cont; 
	private static int[] car;
	
	
	public RussianMountainSemaphore(int id, Semaphore semaforo){
		this.semaforo = semaforo;
		this.car = new int[capacidade];
		this.cont = 0;
		this.id = id;
	}
	
	private void print(){
		System.out.println("########################################");
		System.out.println("##### Capacidade do carro: " + capacidade);
		System.out.println("##### Contador de passageiros: " + cont);
		System.out.println("##### Carro ############################");
		System.out.print("### [ ");
		for(int i = 0; i < car.length; i++){
			System.out.print(car[i]+ " - ");
		}
		System.out.println(" ] ####");
		System.out.println("########################################");
	}
	//os passageiros devem embarcar no carro chamando este método
	private void board(){
		
			try{
				System.out.println("passageiro #"+this.id+" entrando no carro");
				car[this.cont] = this.id;
				this.cont++;
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	private void unboard() {
		try {
			for(int i =0 ; i < car.length; i++){
				car[i] = 0;
			}
			this.cont = 0;
			System.out.println("carro esvasiado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void load(){
		System.out.println("permitida a entrada de passageiros");
		board();
		print();
		
	}
	private void unload(){
		System.out.println("permitida a saida de passageiros");
		unboard();
	}
	
	public void run(){
		try{
			semaforo.acquire();
			System.out.println("pessoas entrando no carro");
			load();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("carro em movimento");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			unload();
			semaforo.release();
			
		}

	}
	

}
