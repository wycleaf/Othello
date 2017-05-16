package spieler.S04;

import java.util.ArrayList;

import spieler.Farbe;
import spieler.Zug;
import spieler.ZugException;

public abstract class Test {

	public static void main(String[] args) 
	{
		Spieler s04 = new Spieler();
		s04.neuesSpiel(Farbe.SCHWARZ, 150);
		ArrayList<ZugMitDrehsteinen> z�ge = s04.woSindSteine();
		for(int i = 0; i<z�ge.size(); i++)
		{
			System.out.println("Zug " + (i+1)+":");
			System.out.print("Zeile: "+ z�ge.get(i).getZeile() + " Spalte:" + z�ge.get(i).getSpalte());
			System.out.print(" Gedrehte Steine: Zeile:");
			for(int k=0; k<z�ge.get(i).getDrehsteine().size(); k++)
			System.out.println(z�ge.get(i).getDrehsteine().get(k).getZeile() + " Spalte: " + z�ge.get(i).getDrehsteine().get(k).getSpalte());
		}
		ArrayList<Zug> steine = new ArrayList<Zug>();
		Zug testzug = new Zug (2,2);
		try {
			steine=s04.dreheSteine(testzug);
		} catch (ZugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Durch Zug umgedrechte Steine:");
		for(int i=0; i<steine.size(); i++)
		{
			System.out.print("Zeile:" + steine.get(i).getZeile()+ " Spalte:" + steine.get(i).getSpalte());
		}

	}
}