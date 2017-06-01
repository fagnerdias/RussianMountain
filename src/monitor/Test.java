package monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Test extends Thread {
	static Semaphore passageiro;
	static Semaphore carrinho;
	static Semaphore andando;
	static Semaphore mutex;
	static int Npass = 0;
	static List<Test> trem;
	
	
	public Test(){
		this.passageiro = new Semaphore(30);
		this.carrinho = new Semaphore(0);
		this.andando = new Semaphore(0);
		this.mutex = new Semaphore(1);
		this.trem = new ArrayList<Test>();
	}
	
	public void board(){
		try{
			System.out.println("Passageiro adicionado no carro");
			this.trem.add(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void unboard(){
		System.out.println("esvasiando o carro");
		this.trem.clear();
	}
	public void load(){
		try{
			System.out.println("carro em movimento");
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void unload(){
		unboard();
		for(int i =0; i < 30; i++){
			this.andando.release();
		}
	}
	
	public void passageiro(){
		while(true){
			try{
				this.passageiro.acquire();
				board();
				this.mutex.acquire();
				Npass++;
				System.out.println(Npass);
				if(Npass == 30){
					this.carrinho.release();
					this.andando.acquire();
					this.mutex.release();
				}
				else{
					this.mutex.release();
					this.andando.release();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void carrinho(){
		while(true){
			try{
				this.carrinho.acquire();
				System.out.println("test");
				load();
				System.out.println("test");
				unload();
				this.passageiro.release();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
			passageiro();
			carrinho();
		
	}
}
