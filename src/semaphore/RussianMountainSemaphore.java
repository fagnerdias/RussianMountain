package semaphore;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RussianMountainSemaphore extends Thread {
	private static int id = 0;
	private Semaphore semaforo;
	private int capacidade = 10;
	private int cont =0;
	private int[] car;
	
	
	public RussianMountainSemaphore(Semaphore semaforo){
		this.semaforo = semaforo;
		this.car = new int[capacidade];
		this.id++;
	}
	//os passageiros devem embarcar no carro chamando este método
	private void board(){
		while(cont < capacidade){
			try{
				System.out.println("passageiro #"+this.id+"entrando no carro");
				car[cont] = this.id;
				cont++;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private void unboard(){
		while(cont > 0){
			try{
				System.out.println("passageiro #"+this.id+"saindo do carro");
				car[cont] = 0;
				cont--;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void load(){
		System.out.println("permitida a entrada de passageiros");
		board();
		
	}
	private void unload(){
		System.out.println("permitida a saida de passageiros");
		unboard();
	}
	
	public void run(){
		try{
			Thread.sleep((long) (Math.random() * 10000));
			semaforo.acquire();
			System.out.println("pessoas entrando no carro");
			load();
			System.out.println("carro em movimento");
			Thread.sleep((long) (Math.random() * 10000));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("pessoas saind do carro");
			unload();
		}
	}
	

}
