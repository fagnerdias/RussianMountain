package semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CarBuffer extends Thread {
	private int capacidade;
	private int qntUsuario;
	private static List<Pessoa> passageiros;
	private static Semaphore liberarEntrada;
	private static Semaphore liberarSaida;
	
	public CarBuffer(){
		this.passageiros = new ArrayList<Pessoa>();
				
	}
	
	public CarBuffer(int usuario,int capacidade, Semaphore entrada, Semaphore saida){
		this.passageiros = new ArrayList<Pessoa>();
		this.capacidade = capacidade;
		this.liberarEntrada = entrada;
		this.liberarSaida = saida;
		this.qntUsuario = usuario;
		
	}
	
	public List<Pessoa> adicionarFila(Pessoa aux){
		System.out.println("Pessoa #" + aux.getId() + " entrou na fila" );
		this.passageiros.add(aux);
		return this.passageiros; 
	}
	
	public List<Pessoa> sairFila(){
		System.out.println("Pessoa #" + this.passageiros.remove(this.passageiros.size()).getId() + " saiu do carro");
		return this.passageiros;		
	}
	
	
	public void load() {
		while(this.passageiros.size() < capacidade){
			try{
				Thread.sleep(100);
				System.out.println("esperando passageiros");
			}catch(Exception e){
				e.printStackTrace();
			}
			if(qntUsuario < capacidade){
				System.out.println("Quantidade de usuarios < capacidade do carro");
				break;
			}
		}
	
	}
	
	public void unload(){
		while(this.passageiros.size() > 0){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public void run(){
		try{
			liberarEntrada.acquire();
			load();
			System.out.println("carro em movimento");
			Thread.sleep(5000);
			liberarEntrada.release();
			liberarSaida.acquire();
			unload();
			liberarSaida.release();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
