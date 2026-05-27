package Natale;
public class BabboNatale implements Runnable
{
	private StatoRenne statoRenne;
	//private StatoElfi statoElfi;

    public BabboNatale(StatoRenne _statoRenne)
	{
		statoRenne=_statoRenne;
    }

	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Babbo Natale dorme");
				statoRenne.aspettaRenne();
				statoRenne.distribuisciRegali();
			}
			catch(Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}
}
