package spieler.S04;

import java.util.ArrayList;

import spieler.Zug;

public class ZugMitDrehsteinen extends Zug
{
	private ArrayList<Zug> drehsteine;
	
	public ZugMitDrehsteinen(int zeile, int spalte) 
	{
		super(zeile, spalte);
		this.drehsteine = new ArrayList<Zug>();
	}

	public ArrayList<Zug> getDrehsteine() {
		return drehsteine;
	}

	public void setDrehsteine(ArrayList<Zug> drehsteine) {
		this.drehsteine = drehsteine;
	}

	
}
