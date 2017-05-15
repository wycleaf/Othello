package spieler.S04;
import spieler.Farbe;
public class Brett implements Cloneable
{
	static int spielfeldgröße = 8;
	public Farbe spielfeld[][];
	private static int bewertung[][];

	Brett()
	{
		spielfeld = new Farbe [spielfeldgröße][spielfeldgröße];
		
		bewertung = new int[][] {
			{50, -1,  5, 2, 2, 5,  -1, 50},
			{-1, -10, 1, 1, 1, 1, -10, -1},
			{5,    1, 1, 1, 1, 1,   1,  5},
			{2,    1, 1, 0, 0, 1,   1,  2},
			{2,    1, 1, 0, 0, 1,   1,  2},
			{5,    1, 1, 1, 1, 1,   1,  5},
			{-1, -10, 1, 1, 1, 1, -10, -1},
			{50,  -1, 5, 2, 2, 5,  -1, 50}
			};
	
	}
	
	public void brettAufbau()
	{
		for(int zeile = 0; zeile<spielfeldgröße; zeile++)
			for(int spalte=0; spalte<spielfeldgröße; spalte++)
			{
				spielfeld[zeile][spalte] = Farbe.LEER;
			} 
	
		spielfeld[3][3] = Farbe.WEISS;
		spielfeld[3][4] = Farbe.SCHWARZ;
		spielfeld[4][3] = Farbe.SCHWARZ;
		spielfeld[4][4] = Farbe.WEISS;
	}
	
	public Brett brettKopieren(Brett original)
	{
		Brett kopie = new Brett ();
		for(int zeile = 0; zeile<spielfeldgröße; zeile++)
			for(int spalte=0; spalte<spielfeldgröße; spalte++)
				kopie.spielfeld[zeile][spalte] = original.spielfeld[zeile][spalte];
			
		return kopie;
	}
	
}
