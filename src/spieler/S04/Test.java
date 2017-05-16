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
		ArrayList<ZugMitDrehsteinen> züge = s04.woSindSteine();
		for(int i = 0; i<züge.size(); i++)
		{
			System.out.println("Zug " + (i+1)+":");
			System.out.print("Zeile: "+ züge.get(i).getZeile() + " Spalte:" + züge.get(i).getSpalte());
			System.out.print(" Gedrehte Steine: Zeile:");
			for(int k=0; k<züge.get(i).getDrehsteine().size(); k++)
			System.out.println(züge.get(i).getDrehsteine().get(k).getZeile() + " Spalte: " + züge.get(i).getDrehsteine().get(k).getSpalte());
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