package RWexclusive;
public class RWexclusiveTest
{
	public static void main(String[] args)
	{
		/*
			Scrivete un programma principale che crea un oggetto della classe RWbasic e crea un certo numero di thread reader e
			writer (ad esempio 50 di ciascuno). Osservate, ad esempio mettendo i thread in sleep in mezzo alla scrittura, che al
			termine dell’esecuzione il valore del data non è uguale al numero di writers (non esitate a utilizzare la funzione join
			della classe Thread per aspettare la fine di un thread). Non esitate a dare un’identità ai thread per potere capir meglio
			l’esecuzione ottenuta.
		*/
		try
		{
			Thread[] threads=new Thread[100];
			RWexclusive rw=new RWexclusive();
			Reader reader=new Reader(rw);
			Writer writer=new Writer(rw);

			for(int i=0; i<threads.length; i++)
			{
				if(i%2==0) threads[i]=new Thread(reader);
				else threads[i]=new Thread(writer);
			}
			for (Thread thread : threads) { thread.start(); }
			for (Thread thread : threads) { thread.join(); }

			System.out.println("END");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}	
}
