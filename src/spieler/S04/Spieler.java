package spieler.S04;

import java.util.Vector;

import spieler.Farbe;
import spieler.OthelloSpieler;
import spieler.Zug;
import spieler.ZugException;

public class Spieler implements OthelloSpieler {
	
	public static final int PASSEN = -1;
	private static final int SCHWARZ = -1;
	private static final int WEISS = 1;
	private static final int LEER = 0;
	private static final int ANZ_ZEILEN = 8;
	private static final int ANZ_SPALTEN = 8;
	public Farbe meineFarbe;
	private int gegner;
	public int bedenkZeitInSekunden;
	public static int [][] bewertung = new int[7][7];
	public static Farbe [][] spielfeld = new Farbe[7][7];
	public static int positionRating;
	public static Vector <Zug> züge = new Vector();

	private static void fuelleBewertungsmatrix() {
		bewertung[0][0] = 50;
		bewertung[0][1] = -1;
		bewertung[0][2] = 5;
		bewertung[0][3] = 2;
		bewertung[1][0] = -1;
		bewertung[1][1] = -10;
		bewertung[1][2] = 1;
		bewertung[1][3] = 1;
		bewertung[2][0] = 5;
		bewertung[2][1] = 1;
		bewertung[2][2] = 1;
		bewertung[2][3] = 1;
		bewertung[3][0] = 2;
		bewertung[3][1] = 1;
		bewertung[3][2] = 1;
		bewertung[3][3] = 0;
		for (int zeile = 0; zeile < 4; zeile++) {
			for (int spalte = 4; spalte < 8; spalte++)
				bewertung[zeile][spalte] = bewertung[zeile][7 - spalte];

		}

		for (int zeile = 4; zeile < 8; zeile++) {
			for (int spalte = 0; spalte < 8; spalte++)
				bewertung[zeile][spalte] = bewertung[7 - zeile][spalte];

		}

	}
		
	public Spieler ()
	{
		
	}
	
	Spieler (int suchtiefe)
	{
		
	}
	
	@Override
	public Zug berechneZug(Zug vorherigerZug, long zeitWeiss, long ZeitSchwarz) throws ZugException 
	{
		Farbe gegner;
		if(meineFarbe == Farbe.WEISS)
			gegner = Farbe.SCHWARZ;
		else gegner = Farbe.WEISS;
		
		if(!(vorherigerZug==null))
		spielfeld[vorherigerZug.getZeile()][vorherigerZug.getSpalte()] = gegner;
		
		züge = möglicheZüge(meineFarbe);
		rateIt(meineFarbe);
		Zug aktuellerZug = züge.get(positionRating);
		spielfeld[aktuellerZug.getZeile()][aktuellerZug.getSpalte()] = meineFarbe;
		return aktuellerZug;
		
		// TODO Eigenen Zug berechnen
	}

	@Override
	public String meinName() {
		// TODO Auto-generated method stub
		return "S04";
	}
	
	

	@Override
	public void neuesSpiel(Farbe meineFarbe, int bedenkZeitInSekunden) 
	{
		gegner = -1;
		if(meineFarbe == Farbe.WEISS)
			gegner = 1;
		
		for (int zeile = 0; zeile < spielfeld.length; zeile++)
		{
			for (int spalte = 0; spalte < spielfeld[0].length; spalte++)
				spielfeld[zeile][spalte] = Farbe.LEER;
		}
		
		spielfeld[3][3] = Farbe.WEISS;
		spielfeld [4][4] = Farbe.WEISS;
		spielfeld [3][4] = Farbe.SCHWARZ;
		spielfeld [4][3] = Farbe.SCHWARZ;
		
		// TODO Neues Spiel beginnen; beachten Sie die
		// Anfangsbelegung des Spielbretts!
	}

	public Vector möglicheZüge(Farbe farbe)
	{
		Vector möglicheZüge = new Vector();
		for(int zeile = 0; zeile<7; zeile++)
			for(int spalte=0; spalte<7; spalte++)
			{
				if(spielfeld[zeile][spalte] == Farbe.LEER)
				{
					Zug zug = new Zug(zeile, spalte);
					if(!turnsStones(farbe, zug).isEmpty())
						möglicheZüge.add(zug);
				}
			}
		if(möglicheZüge.isEmpty())
			möglicheZüge.add(new Zug(2, 3));
		return möglicheZüge;
	}
	
	public int rateIt(Farbe meineFarbe)
	{
		
		int rating = 0;
		Zug unserZug;
		for(int i = 0; i<züge.size(); i++)
		{
			//System.out.println(züge.get(i));
			unserZug = (Zug) züge.get(i);
				
			System.out.println("Unser Zug Zeile: " + unserZug.getZeile() + " Unser Zug Spalte: " + unserZug.getSpalte());
				int zwischenrating = 0;
				Farbe feld = spielfeld[unserZug.getZeile()][unserZug.getSpalte()];
				if(feld != Farbe.LEER)
					if(feld == meineFarbe)
						zwischenrating += bewertung[unserZug.getZeile()][unserZug.getSpalte()];
					else 
						zwischenrating -= bewertung[unserZug.getZeile()][unserZug.getSpalte()];
			if(rating < zwischenrating)
			{
				rating = zwischenrating;
				positionRating = i;
			}
		}	
		return rating;
	}
	
	private boolean inBounds (Zug zug)
	{
		boolean inBounds = false;
		if(zug.getSpalte()>=0 && zug.getSpalte()<7 && zug.getZeile()>=0 && zug.getZeile()<7)
				inBounds = true;
		
		return inBounds;
	}
	
	public Vector turnsStones (Farbe farbe, Zug zug)
	{
		Farbe gegner;
		Vector turnedStones = new Vector();
		if(farbe == Farbe.WEISS)
			gegner = Farbe.SCHWARZ;
			else
			{
			gegner = Farbe.WEISS;
			}	
		
		for(int neueZeile = -1; neueZeile<=2; neueZeile++)
			{	
				for(int neueSpalte=-1; neueSpalte<=2; neueSpalte++)
						if(neueSpalte != 0 || neueZeile !=0)
					{
						Zug neuerZug = new Zug(zug.getZeile() + neueZeile, zug.getSpalte() + neueSpalte);
							if(inBounds(neuerZug))
							{
								Vector possibleTurnedStones = new Vector();
								//System.out.println("Alte Zeile: " + zug.getZeile() + " Alte Spalte: "+ zug.getSpalte());
								//System.out.println("Zeile: " + neueZeile + " Spalte: " + neueSpalte );
								if(spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == gegner)
								{
									possibleTurnedStones.add(neuerZug);
									neuerZug = new Zug(neuerZug.getZeile()+neueZeile, neuerZug.getSpalte()+neueSpalte);
									while(inBounds(neuerZug))
									{
										if(spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()]== gegner)
										{
											possibleTurnedStones.addElement(neuerZug);
											neuerZug = new Zug(neuerZug.getZeile()+neueZeile, neuerZug.getSpalte()+neueSpalte);
											continue;
										}
										if(inBounds(neuerZug))
										if (spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == farbe)
										{
											turnedStones.addAll(possibleTurnedStones);
											System.out.println(turnedStones);
										}
										break;
									}
								}
							}
					}
			}
		
		return turnedStones;
			
		}
}
