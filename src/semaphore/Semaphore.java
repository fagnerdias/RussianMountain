package semaphore;

import java.util.ArrayList;
import java.util.List;

public class Semaphore {
	private int contador;
	private List process;

	public Semaphore(){
		this.contador = 0;
		this.process = new ArrayList();
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public List getProcess() {
		return process;
	}

	public void setProcess(List process) {
		this.process = process;
	}
	
	public void down(Semaphore sem){
		contador = contador - 1;
		if(contador < 0){
			sem.getProcess().add(null);
			//wait();
		}
	}
	public void up(Semaphore sem){
		contador = contador + 1;
		if(contador > 0){
			sem.getProcess().add(null);
			//Run();
		}
	}
}
