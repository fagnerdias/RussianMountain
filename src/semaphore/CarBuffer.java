package semaphore;

public class CarBuffer {
	Person[] buffer;
	int cont;
	int limity = 10;
	
	public CarBuffer(){
		this.buffer = new Person[limity];
		this.cont = 0;
	}
	
	public synchronized void getIn(Person person){
		while(cont < limity){
			try{
				System.out.println("the person #"+person.getId()+"get in the car");
				
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.buffer[cont] = person;
		cont++;
		System.out.println("the person is in the car");
		notifyAll();
	}
	
	public synchronized Person getOff(){
		while(cont > 0){
			try{
				System.out.println("the car is stopping :'(");
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("the person #" + buffer[cont].getId()+ "get off");
		Person aux = buffer[cont];
		buffer[cont] = null;
		cont--;
		notifyAll();
		return aux;
	}

}
