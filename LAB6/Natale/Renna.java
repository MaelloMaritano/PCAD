package Natale;
public class Renna implements Runnable
{
	private String nome;
	private StatoRenne stato;

	public Renna(String _nome, StatoRenne _stato)
	{
		nome=_nome;
		stato=_stato;
    }
	public void run()
	{
		while(true)
		{
			System.out.println(nome+" è in vacanza");
			try { Thread.sleep(100); } catch(Exception e) {}
			stato.tornaDaBabboNatale(nome);
			stato.trainaSlitta(nome);
		}
	}
}
