package spieler.S04;

import java.util.ArrayList;

import spieler.Farbe;
import spieler.Zug;

public abstract class Test {

	public static void main(String[] args) 
	{
		Spieler s04 = new Spieler();
		s04.neuesSpiel(Farbe.SCHWARZ, 150);
		ArrayList<Zug> züge = s04.woSindSteine();
		for(int i = 0; i<züge.size(); i++)
		{
		System.out.println("Zeile:" + züge.get(i).getZeile() + " Spalte:" + züge.get(i).getSpalte());
		}

	}
}