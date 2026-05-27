package Natale;

public class Elfo implements Runnable
{
	private String nome;
	private StatoElfi stato;

	public Elfo(String _nome, StatoElfi _stato)
	{
		nome=_nome;
		stato=_stato;
    }
	public void run()
	{
		while(true)
		{
			System.out.println(nome+" sta costruendo un giocattolo");
			try { Thread.sleep(100); } catch(Exception e) {}
			System.out.println(nome+" ha bisogno di aiuto");
			stato.chiediAiuto(nome);
		}
	}
}
