package Piscina;

import java.util.concurrent.Semaphore;

public class Piscina
{
	private static final int N=10;	//numero clienti
	private static final int NS=5;	//numero spogliatoi
	private static final int NC=7;	//numero armadietti
	
	public static void main(String[] args)
	{
		Semaphore armadietti=new Semaphore(NC);
		Semaphore spogliatoi=new Semaphore(NS);

		Cliente[] clienti=new Cliente[N];
		for (int i=0; i<N; i++)
		{
			clienti[i]=new Cliente(armadietti, spogliatoi, i);
			Thread t=new Thread(clienti[i]);
			t.start();
		}
	}
}