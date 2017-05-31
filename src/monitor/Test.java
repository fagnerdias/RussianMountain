package monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Test extends Thread {
	Semaphore passageiro;
	Semaphore carrinho;
	Semaphore andando;
	Semaphore mutex;
	int Npass = 0;
	List<Test> trem;
	
	
	public Test(){
		this.passageiro = new Semaphore(30);
		this.carrinho = new Semaphore(0);
		this.andando = new Semaphore(0);
		this.mutex = new Semaphore(1);
		this.trem = new ArrayList<Test>();
	}
	
	public void board(){
		try{
			this.trem.add(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void unboard(){
		
	}
	public void load(){
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void unload(){
		for(int i =0; i < 30; i++){
			this.andando.release();
		}
	}
	
	public void passageiro(){
		while(true){
			try{
				this.passageiro.acquire();
				//board();
				this.mutex.acquire();
				Npass++;
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
				load();
				unload();
				this.passageiro.release();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
