/*package spieler.S04;
import java.util.Vector;

import spieler.Farbe;
import spieler.OthelloSpieler;
import spieler.Zug;
public class Referenzspieler implements Cloneable, OthelloSpieler
{

	public static final int PASSEN = -1;
	//private static final int WEISS = 1;
	//private static final int LEER = 0;
	private static final int ANZ_ZEILEN = 8;
	private static final int ANZ_SPALTEN = 8;
	//private static int bm[][] = new int[8][8];
	private static int ALPHA = -1000;
	private static int BETA = 1000;
	//private int brett[][];
	private int computerspieler;
	private int anzahlPassen;
	private int suchtiefe;

	

	public Referenzspieler() {
		anzahlPassen = 0;
		suchtiefe = 6;
		fuelleBewertungsmatrix();
		brett = new int[8][8];
	}

	public Referenzspieler(int suchtiefe) {
		this();
		if (suchtiefe > 0 && suchtiefe < 60)
			this.suchtiefe = suchtiefe;
	}

	public Referenzspieler clone() {
		Referenzspieler kopie = new Referenzspieler(computerspieler, suchtiefe);
		for (int zeile = 0; zeile < brett.length; zeile++) {
			for (int spalte = 0; spalte < brett[0].length; spalte++)
				kopie.brett[zeile][spalte] = brett[zeile][spalte];

		}

		return kopie;
	}

	private Vector berechneMoeglicheZuege(int spielerAmZug) {
		Vector moeglicheZuege = new Vector();
		if (brett[0][0] == 0 && (legalerZug(0, 0, 0, 1, spielerAmZug) || legalerZug(0, 0, 1, 0, spielerAmZug)
				|| legalerZug(0, 0, 1, 1, spielerAmZug)))
			moeglicheZuege.add(new ZugPriemer(0, 0));
		if (brett[0][7] == 0 && (legalerZug(0, 7, 0, -1, spielerAmZug) || legalerZug(0, 7, 1, 0, spielerAmZug)
				|| legalerZug(0, 7, 1, -1, spielerAmZug)))
			moeglicheZuege.add(new ZugPriemer(0, 7));
		if (brett[7][0] == 0 && (legalerZug(7, 0, 0, 1, spielerAmZug) || legalerZug(7, 0, -1, 0, spielerAmZug)
				|| legalerZug(7, 0, -1, 1, spielerAmZug)))
			moeglicheZuege.add(new ZugPriemer(7, 0));
		if (brett[7][7] == 0 && (legalerZug(7, 7, 0, -1, spielerAmZug) || legalerZug(7, 7, -1, 0, spielerAmZug)
				|| legalerZug(7, 7, -1, -1, spielerAmZug)))
			moeglicheZuege.add(new ZugPriemer(7, 7));
		for (int spalte = 1; spalte < 7; spalte++) {
			if (brett[0][spalte] == 0 && (legalerZug(0, spalte, 0, 1, spielerAmZug)
					|| legalerZug(0, spalte, 1, 1, spielerAmZug) || legalerZug(0, spalte, 1, 0, spielerAmZug)
					|| legalerZug(0, spalte, 1, -1, spielerAmZug) || legalerZug(0, spalte, 0, -1, spielerAmZug)))
				moeglicheZuege.add(new ZugPriemer(0, spalte));
			if (brett[7][spalte] == 0 && (legalerZug(7, spalte, 0, 1, spielerAmZug)
					|| legalerZug(7, spalte, 0, -1, spielerAmZug) || legalerZug(7, spalte, -1, -1, spielerAmZug)
					|| legalerZug(7, spalte, -1, 0, spielerAmZug) || legalerZug(7, spalte, -1, 1, spielerAmZug)))
				moeglicheZuege.add(new ZugPriemer(7, spalte));
		}

		for (int zeile = 1; zeile < 7; zeile++) {
			if (brett[zeile][0] == 0 && (legalerZug(zeile, 0, -1, 0, spielerAmZug)
					|| legalerZug(zeile, 0, -1, 1, spielerAmZug) || legalerZug(zeile, 0, 0, 1, spielerAmZug)
					|| legalerZug(zeile, 0, 1, 1, spielerAmZug) || legalerZug(zeile, 0, 1, 0, spielerAmZug)))
				moeglicheZuege.add(new ZugPriemer(zeile, 0));
			if (brett[zeile][7] == 0 && (legalerZug(zeile, 7, -1, 0, spielerAmZug)
					|| legalerZug(zeile, 7, 1, 0, spielerAmZug) || legalerZug(zeile, 7, 1, -1, spielerAmZug)
					|| legalerZug(zeile, 7, 0, -1, spielerAmZug) || legalerZug(zeile, 7, -1, -1, spielerAmZug)))
				moeglicheZuege.add(new ZugPriemer(zeile, 7));
		}

		for (int zeile = 1; zeile < 7; zeile++) {
			for (int spalte = 1; spalte < 7; spalte++)
				if (brett[zeile][spalte] == 0 && (legalerZug(zeile, spalte, -1, -1, spielerAmZug)
						|| legalerZug(zeile, spalte, -1, 0, spielerAmZug)
						|| legalerZug(zeile, spalte, -1, 1, spielerAmZug)
						|| legalerZug(zeile, spalte, 0, -1, spielerAmZug)
						|| legalerZug(zeile, spalte, 0, 1, spielerAmZug)
						|| legalerZug(zeile, spalte, 1, -1, spielerAmZug)
						|| legalerZug(zeile, spalte, 1, 0, spielerAmZug)
						|| legalerZug(zeile, spalte, 1, 1, spielerAmZug)))
					moeglicheZuege.add(new ZugPriemer(zeile, spalte));

		}

		return moeglicheZuege;
	}

	private int bewertePosition() {
		int bewertung = 0;
		for (int zeile = 0; zeile < 8; zeile++) {
			for (int spalte = 0; spalte < 8; spalte++)
				bewertung += brett[zeile][spalte] * computerspieler * bm[zeile][spalte];

		}

		return bewertung;
	}

	private Vector ziehe(int spieler, ZugPriemer zug) {
		Vector drehsteine = new Vector();
		for (int dz = -1; dz < 2; dz++) {
			for (int ds = -1; ds < 2; ds++)
				if (dz != 0 || ds != 0) {
					Vector neueSteine = berechneDrehsteine(zug.zeile, zug.spalte, dz, ds, spieler);
					if (neueSteine != null)
						drehsteine.addAll(neueSteine);
				}

		}

		if (!drehsteine.isEmpty()) {
			brett[zug.zeile][zug.spalte] = spieler;
			for (int i = 0; i < drehsteine.size(); i++) {
				int drehpos = ((Integer) drehsteine.get(i)).intValue();
				brett[drehpos / 8][drehpos % 8] = spieler;
			}

		}
		return drehsteine;
	}

	private ZugPriemer findeBestenZug(int tiefe, int spieler) {
		Vector moeglicheZuege = berechneMoeglicheZuege(spieler);
		int anzahlMoeglicheZuege = moeglicheZuege.size();
		if (anzahlMoeglicheZuege > 0) {
			int maxpos = -1;
			int minpos = -1;
			for (int i = 0; i < anzahlMoeglicheZuege; i++) {
				Referenzspieler brettKopie = clone();
				brettKopie.ziehe(spieler, (ZugPriemer) moeglicheZuege.get(i));
				if (tiefe == 1) {
					((ZugPriemer) moeglicheZuege.get(i)).bewertung = Integer.valueOf(brettKopie.bewertePosition());
				} else {
					ZugPriemer besterZug = brettKopie.findeBestenZug(tiefe - 1, spieler * -1);
					((ZugPriemer) moeglicheZuege.get(i)).bewertung = besterZug.bewertung;
				}
				if (i == 0) {
					maxpos = 0;
					minpos = 0;
				} else {
					int bewertung = ((ZugPriemer) moeglicheZuege.get(i)).bewertung.intValue();
					if (bewertung > ((ZugPriemer) moeglicheZuege.get(maxpos)).bewertung.intValue())
						maxpos = i;
					else if (bewertung < ((ZugPriemer) moeglicheZuege.get(minpos)).bewertung.intValue())
						minpos = i;
				}
			}

			if (spieler == computerspieler)
				return (ZugPriemer) moeglicheZuege.get(maxpos);
			else
				return (ZugPriemer) moeglicheZuege.get(minpos);
		}
		ZugPriemer ergebnis = new ZugPriemer(-1, -1);
		if (tiefe == 1)
			ergebnis.bewertung = Integer.valueOf(bewertePosition());
		else
			ergebnis.bewertung = findeBestenZug(tiefe - 1, spieler * -1).bewertung;
		return ergebnis;
	}

	private ZugPriemer findeBestenZug(int tiefe, int spieler, int alpha, int beta) {
		Vector moeglicheZuege = berechneMoeglicheZuege(spieler);
		int anzahlMoeglicheZuege = moeglicheZuege.size();
		if (anzahlMoeglicheZuege > 0) {
			int maxpos = -1;
			int minpos = -1;
			for (int i = 0; i < anzahlMoeglicheZuege; i++) {
				Referenzspieler brettKopie = clone();
				brettKopie.ziehe(spieler, (ZugPriemer) moeglicheZuege.get(i));
				if (tiefe == 1) {
					((ZugPriemer) moeglicheZuege.get(i)).bewertung = Integer.valueOf(brettKopie.bewertePosition());
				} else {
					ZugPriemer besterZug = brettKopie.findeBestenZug(tiefe - 1, spieler * -1, alpha, beta);
					((ZugPriemer) moeglicheZuege.get(i)).bewertung = besterZug.bewertung;
					if (spieler == computerspieler && besterZug.bewertung.intValue() > alpha)
						alpha = besterZug.bewertung.intValue();
					if (spieler != computerspieler && besterZug.bewertung.intValue() < beta)
						beta = besterZug.bewertung.intValue();
				}
				if (i == 0) {
					maxpos = 0;
					minpos = 0;
				} else {
					int bewertung = ((ZugPriemer) moeglicheZuege.get(i)).bewertung.intValue();
					if (bewertung > ((ZugPriemer) moeglicheZuege.get(maxpos)).bewertung.intValue())
						maxpos = i;
					else if (bewertung < ((ZugPriemer) moeglicheZuege.get(minpos)).bewertung.intValue())
						minpos = i;
				}
				if (alpha >= beta)
					break;
			}

			if (spieler == computerspieler)
				return (ZugPriemer) moeglicheZuege.get(maxpos);
			else
				return (ZugPriemer) moeglicheZuege.get(minpos);
		}
		ZugPriemer ergebnis = new ZugPriemer(-1, -1);
		if (tiefe == 1)
			ergebnis.bewertung = Integer.valueOf(bewertePosition());
		else
			ergebnis.bewertung = findeBestenZug(tiefe - 1, spieler * -1, alpha, beta).bewertung;
		return ergebnis;
	}

	private boolean legalerZug(int zeile, int spalte, int dz, int ds, int spieler) {
		return berechneDrehsteine(zeile, spalte, dz, ds, spieler) != null;
	}

	private Vector berechneDrehsteine(int zeile, int spalte, int dz, int ds, int spieler) {
		Vector drehsteine = null;
		int gegenspieler = spieler * -1;
		int neueZeile = zeile + dz;
		int neueSpalte = spalte + ds;
		if (neueZeile >= 0 && neueZeile < 8 && neueSpalte >= 0 && neueSpalte < 8) {
			if (brett[neueZeile][neueSpalte] != gegenspieler)
				return null;
			drehsteine = new Vector();
			drehsteine.add(new Integer(neueZeile * 8 + neueSpalte));
			neueZeile += dz;
			for (neueSpalte += ds; neueZeile < 8 && neueZeile >= 0 && neueSpalte < 8
					&& neueSpalte >= 0; neueSpalte += ds) {
				if (brett[neueZeile][neueSpalte] == spieler)
					return drehsteine;
				if (brett[neueZeile][neueSpalte] == 0)
					return null;
				drehsteine.add(new Integer(neueZeile * 8 + neueSpalte));
				neueZeile += dz;
			}

		}
		return null;
	}

	private ZugPriemer computerzug(int suchtiefe) {
		ZugPriemer computerzug = findeBestenZug(suchtiefe, computerspieler, ALPHA, BETA);
		if (computerzug == null)
			return null;
		if (computerzug.zeile == -1) {
			anzahlPassen++;
			return computerzug;
		} else {
			anzahlPassen = 0;
			ziehe(computerspieler, computerzug);
			return computerzug;
		}
	}

	private boolean spielerzug(int zeile, int spalte) {
		Vector moeglicheZuege = berechneMoeglicheZuege(-1 * computerspieler);
		ZugPriemer spielerzug = new ZugPriemer(zeile, spalte);
		if (moeglicheZuege.contains(spielerzug)) {
			if (spielerzug.zeile == -1)
				anzahlPassen++;
			else
				anzahlPassen = 0;
			ziehe(-computerspieler, spielerzug);
			return true;
		} else {
			return false;
		}
	}

	public Zug berechneZug(Zug vorherigerZug, long zeitWeiss, long zeitSchwarz) throws ZugException {
		if (vorherigerZug != null)
			spielerzug(vorherigerZug.getZeile(), vorherigerZug.getSpalte());
		ZugPriemer meinZug = computerzug(suchtiefe);
		Zug ergebnis = new Zug(meinZug.zeile, meinZug.spalte);
		return ergebnis;
	}

	public void neuesSpiel(Farbe meineFarbe, int bedenkzeitInSekunden) {
		computerspieler = -1;
		if (meineFarbe == Farbe.WEISS)
			computerspieler = 1;
		initialisiereBrett();
	}

	public String meinName() {
		return (new StringBuilder("Referenzspieler(")).append(suchtiefe).append(")").toString();
	}

	public Object cloneB() throws CloneNotSupportedException {
		return clone();
	}




}
*/