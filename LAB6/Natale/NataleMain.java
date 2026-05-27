package Natale;

public class NataleMain
{
	private static final int NE=6;
	private static final int NR=9;

	public static void main(String[] args)
	{
		Stato stato=new Stato(NR, NE);

		String[] nomiRenne={"Freccia", "Ballerina", "Salterello", "Donnola", "Cometa", "Cupido", "Donato", "Fulmine", "Rudolph"};
		
		Renna[] renne=new Renna[NR];
		for (int i=0; i<NR; i++)
		{
			renne[i]=new Renna(nomiRenne[i], stato);
			Thread t=new Thread(renne[i]);
			t.start();
		}

		String[] nomiElfi={"Alabastro Palla di Neve", "Folto Sempreverde", "Pepe Minstix", "Splendente SuUnAlbero", "Mary Confetto", "Uncavallo Slittaaperta"};
		Elfo[] elfi=new Elfo[NE];
		for (int i=0; i<NE; i++)
		{
			elfi[i]=new Elfo(nomiElfi[i], stato);
			Thread t=new Thread(elfi[i]);
			t.start();
		}

		Thread bn=new Thread(new BabboNatale(stato));
		bn.start();
	}
}