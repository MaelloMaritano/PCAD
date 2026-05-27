package Natale;

import java.util.concurrent.Semaphore;

public class NataleMain
{
	private static final int NE=6;
	private static final int NR=9;

	public static void main(String[] args)
	{
		String[] nomiElfi={"Alabastro Palla di Neve", "Folto Sempreverde", "Pepe Minstix", "Splendente SuUnAlbero", "Mary Confetto", "Uncavallo Slittaaperta"};
		StatoElfi statoElfi=new StatoElfi(NE);
		Elfo[] elfi=new Elfo[NE];
		for (int i=0; i<NE; i++)
		{
			elfi[i]=new Elfo(nomiElfi[i], statoElfi);
			Thread t=new Thread(elfi[i]);
			t.start();
		}

		String[] nomiRenne={"Freccia", "Ballerina", "Salterello", "Donnola", "Cometa", "Cupido", "Donato", "Fulmine", "Rudolph"};
		StatoRenne statoRenne=new StatoRenne(NR);
		Renna[] renne=new Renna[NR];
		for (int i=0; i<NR; i++)
		{
			renne[i]=new Renna(nomiRenne[i], statoRenne);
			Thread t=new Thread(renne[i]);
			t.start();
		}

		Thread bn=new Thread(new BabboNatale(statoRenne));
		bn.start();
	}
}