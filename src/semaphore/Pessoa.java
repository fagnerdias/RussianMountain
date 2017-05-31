package semaphore;

import java.util.concurrent.Semaphore;

public class Pessoa extends Thread {
	private int id;
	private static CarBuffer car;
	
	private static Semaphore liberarSaida;
	private static Semaphore liberarEntrada;
	
	
	public Pessoa(int id,Semaphore in, Semaphore out){
		this.id = id;
		this.car = new CarBuffer();
		this.liberarEntrada = in;
		this.liberarSaida = out;
	}
	
	public CarBuffer getCar() {
		return car;
	}


	public void board(){
		System.out.println("Passageiro entrando no carro");
		car.adicionarFila(this);
	}
	
	public void unboard(){
		System.out.println("Passageiros saindo do carro");
		car.sairFila();
	}
	
	
	public void run(){
		try{
			System.out.println("Usuario passeando pelo parque.....");
			Thread.sleep((long) (Math.random() * 5000));
			System.out.println("entrando no fila");
			board();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
