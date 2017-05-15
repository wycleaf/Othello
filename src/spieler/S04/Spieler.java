package spieler.S04;

import java.util.ArrayList;

import spieler.*;

public class Spieler implements OthelloSpieler
{
	private static final int SCHWARZ = -1;
	private static final int WEISS = 1;
	private static final int LEER = 0;
	private static Farbe unsereFarbe;
	private static Farbe gegnerFarbe;
	private static int restlicheZeit;
	private Brett spielfeld;
	
	
	
	Spieler ()
	{
		
	}
	
	Spieler (int suchtiefe)
	{
		
	}
	
	
	public Zug berechneZug(Zug vorherigerZug, long zeitWeiss, long zeitSchwarz) throws ZugException
	{
		
		
		return null;
	}

	public ArrayList<Zug> woSindSteine ()
	{
		ArrayList<Zug> hierSindSteine = new ArrayList();
		for(int zeile=0; zeile<8; zeile++)
			for(int spalte=0; spalte<8; spalte++)
			{
				if(!(spielfeld.spielfeld[zeile][spalte] == Farbe.LEER))
				{
					Zug neuerZug = new Zug(zeile, spalte);
					hierSindSteine.add(neuerZug);
				}	
			}
		return hierSindSteine;
	}
	
	public ArrayList<Zug> gegnerischeSteineUndNebenanLeer (ArrayList<Zug> steine) 
	{
		Zug einZug = new Zug(0,0);
		boolean gefunden = false;
		boolean nachbar = true;
		ArrayList<Zug> möglicheZüge = new ArrayList();
		for(int i =0; i<steine.size(); i++)
		{
			//if(spielfeld.spielfeld[steine.get(i).getZeile()][steine.get(i).getSpalte()] == gegnerFarbe)
				for(int k = -1; k<2; k++)
					for(int j = -1; j<2; j++)
					{
						Zug neuerZug = new Zug(steine.get(i).getZeile()+k,steine.get(i).getSpalte()+j);
						if(imFeld(neuerZug))
						nachbar = spielfeld.spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == Farbe.LEER;
						//oberhalb des Felds kucken 
						
						if(nachbar && k>0 && j == 0)
						{	for(int p = 1; p<=7; p++)
								
						{	einZug = new Zug (neuerZug.getZeile()+p, neuerZug.getSpalte());
							if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
							{
								gefunden=true;
								continue;
							}
							
								if(spielfeld.spielfeld[einZug.getZeile()+p][einZug.getSpalte()] == unsereFarbe)
									{
										Zug möglicherZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());	
												möglicheZüge.add(möglicherZug);
									}
						
						}	
						/*unterhalb des Felds kucken*/ if(nachbar && k<0 && j == 0)
							
									if(spielfeld.spielfeld[steine.get(0).getZeile()][steine.get(0).getSpalte()-p] == gegnerFarbe && 
										spielfeld.spielfeld[steine.get(0).getZeile()][steine.get(0).getSpalte()-t] == unsereFarbe){
								
									}
								
						/*rechts des Felds kucken*/ if(nachbar && k==0 && j > 0)
							
									if(spielfeld.spielfeld[steine.get(0).getZeile()+p][steine.get(0).getSpalte()] == gegnerFarbe && 
										spielfeld.spielfeld[steine.get(0).getZeile()+t][steine.get(0).getSpalte()] == unsereFarbe){
								
									}
									
						/*links des Felds kucken*/ if(nachbar && k==0 && j > 0)
							
									if(spielfeld.spielfeld[steine.get(0).getZeile()-p][steine.get(0).getSpalte()] == gegnerFarbe && 
										spielfeld.spielfeld[steine.get(0).getZeile()-t][steine.get(0).getSpalte()] == unsereFarbe){
								
								}
						/*links oben des Felds kucken*/ if(nachbar && k0 && j > 0)
							
							if(spielfeld.spielfeld[steine.get(0).getZeile()-p][steine.get(0).getSpalte()] == gegnerFarbe && 
								spielfeld.spielfeld[steine.get(0).getZeile()-t][steine.get(0).getSpalte()] == unsereFarbe){
						
						}
						}	
				}
		}	
		return null;
	}

	public ArrayList<Zug> möglicheZüge(ArrayList<Zug> nachbarnGegnerischerSteine)
	{
		for(int i=0; i<nachbarnGegnerischerSteine.size(); i++)
		{
			Zug neuerZug = new Zug(nachbarnGegnerischerSteine.get(i).getZeile(), nachbarnGegnerischerSteine.get(i).getSpalte());
			
		}
		
		return null;
	}
	
	
	public boolean imFeld(Zug zug)
	{
		if(zug.getZeile()>7 || zug.getZeile()<0 || zug.getSpalte()>7 || zug.getSpalte()<0)
			return false;
		else return true;
		
	}
	
	public String meinName() 
	{
		return "S04";
	}

	public void neuesSpiel(Farbe meineFarbe, int bedenkZeitInSekunden) 
	{
		spielfeld = new Brett();
		spielfeld.brettAufbau();
		unsereFarbe = meineFarbe;
		if(meineFarbe == Farbe.WEISS)
			gegnerFarbe = Farbe.SCHWARZ;
		else gegnerFarbe = Farbe.WEISS;
		
		restlicheZeit = bedenkZeitInSekunden;
		
	}
	
}