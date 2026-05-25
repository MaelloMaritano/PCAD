package Piscina;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable
{
	private Semaphore armadietto;
	private Semaphore spogliatoio;
	private int id;

	public Cliente(Semaphore _armadietti, Semaphore _spogliatoi, int _id)
	{
		armadietto=_armadietti;
		spogliatoio=_spogliatoi;
		id=_id;
	}

	public void run()
	{
	/*	(a) Prende la chiave di uno spogliatoio;
		(b) Prende la chiave di un armadietto;
		(c) Si cambia nello spogliatoio;
		(d) Libera lo spogliatoio;
		(e) Mette i suoi vestiti nell’armadietto;
		(f) Ridà la chiave dello spogliatoio;
		(g) Nuota (tenendosi la chiave dell’armadietto);
		(h) Prende la chiave di uno spogliatoio;
		(i) Recupera i suoi vestiti nel armadietto;
		(j) Si riveste nello spogliatoio;
		(k) Libera lo spogliatoio;
		(l) Rida le chiave dello spogliatoio e dell’armadietto. */
		try
		{
			/* ORDINE CAUSA DEADLOCK
			spogliatoio.acquire();
			System.out.println(id+" prende la chiave di uno spogliatoio;");
			armadietto.acquire();
			System.out.println(id+" prende la chiave di un armadietto;");*/

			armadietto.acquire();
			System.out.println(id+" prende la chiave di un armadietto;");
			spogliatoio.acquire();
			System.out.println(id+" prende la chiave di uno spogliatoio;");

			System.out.println(id+" si cambia nello spogliatoio;");
			System.out.println(id+" libera lo spogliatoio;");
			System.out.println(id+" mette i suoi vestiti nell'armadietto;");

			spogliatoio.release();
			System.out.println(id+" ridà la chiave dello spogliatoio;");

			System.out.println(id+" nuota (tenendosi la chiave dell'armadietto);");

			spogliatoio.acquire();
			System.out.println(id+" prende la chiave di uno spogliatoio;");

			System.out.println(id+" recupera i suoi vestiti nel armadietto;");
			System.out.println(id+" si riveste nello spogliatoio;");
			System.out.println(id+" libera lo spogliatoio;");

			spogliatoio.release();
			armadietto.release();
			System.out.println(id+" ridà le chiave dello spogliatoio e dell'armadietto.");
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
