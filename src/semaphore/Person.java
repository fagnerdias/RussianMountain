package semaphore;

import java.util.concurrent.Semaphore;

public class Person extends Thread {
	private int id;
	private Semaphore semaforo;
	private CarBuffer buffer;
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public CarBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(CarBuffer buffer) {
		this.buffer = buffer;
	}

	public Person(int id, Semaphore semaforo, CarBuffer buffer){
		this.id = id;
		this.semaforo = semaforo;
		this.buffer = buffer;
	}
	
	private void entrar(){
		try{
			System.out.println("The person number: "+ this.id +" is get in the car");
			buffer.getIn(this);
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			semaforo.acquire();
			entrar();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			semaforo.release();
		}
	}
}
