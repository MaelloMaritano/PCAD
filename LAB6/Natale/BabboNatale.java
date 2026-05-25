package Natale;
public class BabboNatale implements Runnable
{
	PoloNord status;

    public BabboNatale(PoloNord _status)
	{
		status=_status;
    }
	public void run()
	{
		while(true)
		{
			while(!status.abbastanzaElfiInDifficolta() || status.tutteRenneArrivate())
			{
				try
				{
					status.wait();
					System.out.println("Babbo Natale dorme");
				}
				catch(Exception e)
				{
					System.out.println(e);
					e.printStackTrace();
				}
			}
		}
	}
}
