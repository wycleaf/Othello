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
		ArrayList<Zug> gedrehteSteine = new ArrayList<Zug>();
		for(int zeile=0; zeile<8; zeile++)
			for(int spalte=0; spalte<8; spalte++)
			{
				if((spielfeld.spielfeld[zeile][spalte] == Farbe.LEER))
				{
					for(int z = -1; z<=1; z++)
						for(int s = -1; s<=1; s++)
						{	ArrayList<Zug> hierSindSteine = new ArrayList<Zug>();
						
							if(z==0 && s==0)continue;
							Zug neuerZug = new Zug (zeile+z, spalte+s);
							if(imFeld(neuerZug))
							if((spielfeld.spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == gegnerFarbe))
							{
								hierSindSteine.add(neuerZug);
								
								if( z>0 && s==0)/*unten*/
								{
									for(int delta = 2; delta<=7; delta++)
									{	
										Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte());
										if(imFeld(drehstein))
										{
											if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
												hierSindSteine.add(drehstein);
																						
											if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
												gedrehteSteine.addAll(hierSindSteine);	
										}	
									}
								}	
								
									if( z<0 && s==0)/*oben*/
									{
										for(int delta = -2; delta>=-7; delta--)
										{	
											Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte());
											if(imFeld(drehstein))
											{
												if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
													hierSindSteine.add(drehstein);
																							
												if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
													gedrehteSteine.addAll(hierSindSteine);	
											}	
										}
									}	
										
										if( z==0 && s>0)/*rechts*/
										{
											for(int delta = 2; delta<=7; delta++)
											{	
												Zug drehstein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+delta);
												if(imFeld(drehstein))
												{
													if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
														hierSindSteine.add(drehstein);
																								
													if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
														gedrehteSteine.addAll(hierSindSteine);	
												}	
											}
										}	
											
											if( z==0 && s<0)/*links*/
											{
												for(int delta = -2; delta>=-7; delta--)
												{	
													Zug drehstein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+delta);
													if(imFeld(drehstein))
													{
														if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
															hierSindSteine.add(drehstein);
																									
														if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
															gedrehteSteine.addAll(hierSindSteine);	
													}	
												}
											}	
											
												if( z>0 && s>0)/*rechts unten*/
												{
													for(int delta = 2; delta<=7; delta++)
													{	
														Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+delta);
														if(imFeld(drehstein))
														{
															if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																hierSindSteine.add(drehstein);
																										
															if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
																gedrehteSteine.addAll(hierSindSteine);	
														}	
													}
												}	
													
													if( z<0 && s<0)/*oben links*/
													{
														for(int delta = -2; delta>=-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+delta);
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																	hierSindSteine.add(drehstein);
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
																	gedrehteSteine.addAll(hierSindSteine);	
															}	
														}	
													}
													
													if( z<0 && s>0)/*oben rechts*/
													{
														for(int delta = -2; delta>=-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+(delta*-1));
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																	hierSindSteine.add(drehstein);
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
																	gedrehteSteine.addAll(hierSindSteine);	
															}	
														}	
													}	
													
													if( z>0 && s<0)/*unten links*/
													{
														for(int delta = -2; delta>=-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+(delta*-1), neuerZug.getSpalte()+delta);
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																	hierSindSteine.add(drehstein);
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe))
																	gedrehteSteine.addAll(hierSindSteine);	
															}	
														}	
													}		
												}
					
											}
										}
									}
					return gedrehteSteine;
								}
							
				
			
	
	public ArrayList<Zug> möglicheZüge (ArrayList<Zug> steine) 
	{
		Zug einZug = new Zug(0,0);
		boolean gefunden = false;
		boolean nachbar = true;
		ArrayList<Zug> möglicheZüge = new ArrayList<Zug>();
		//möglicheZüge.add(einZug);
		for(int i =0; i<steine.size(); i++)
		{
					for(int k = -1; k<2; k++)
					for(int j = -1; j<2; j++)
					{
						if(k == 0 && j == 0) continue;
						Zug neuerZug = new Zug(steine.get(i).getZeile()+k,steine.get(i).getSpalte()+j);
						if(imFeld(neuerZug))
						nachbar = spielfeld.spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == Farbe.LEER;
								
						/*oberhalb des Felds kucken*/	if(nachbar && k<0 && j == 0)
							
						for(int p = 1; p<=7; p++)		
							{	
							einZug = new Zug (neuerZug.getZeile()+p, neuerZug.getSpalte());
							if(imFeld(einZug))
							if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
							{
								gefunden=true;
								continue;
							}
								Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
								if(imFeld(zweiZug))
								if(spielfeld.spielfeld[einZug.getZeile()+p][einZug.getSpalte()] == unsereFarbe)
									{
										
										Zug möglicherZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());	
										möglicheZüge.add(möglicherZug);
										break;
									}
								
							}	
						gefunden=false;
						/*unterhalb des Felds kucken*/ if(nachbar && k>0 && j == 0)
							
							for(int p = -1; p>=-7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile()+p, neuerZug.getSpalte());
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
									Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
									if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()+p][einZug.getSpalte()] == unsereFarbe)
										{
											
											Zug möglicherZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}	
						gefunden=false;	
						/*rechts des Felds kucken*/ if(nachbar && k==0 && j > 0)
							
							for(int p = 1; p>=7; p++)		
							{	
								einZug = new Zug (neuerZug.getZeile(), neuerZug.getSpalte()+p);
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
										Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
										if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()+p] == unsereFarbe)
										{
											Zug möglicherZug = new Zug(einZug.getZeile(), einZug.getSpalte()+p);	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;		
						/*links des Felds kucken*/ if(nachbar && k==0 && j < 0)
							
							for(int p = -1; p>=7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile(), neuerZug.getSpalte()+p);
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
									
									Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
									if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()+p] == unsereFarbe && imFeld(einZug))
										{
											Zug möglicherZug = new Zug(einZug.getZeile(), einZug.getSpalte()+p);	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;
						/*links oben des Felds kucken*/ if(nachbar && k<0 && j<0)
							
							for(int p = -1; p>=7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile()+p, neuerZug.getSpalte()+p);
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
										Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
										if(imFeld(zweiZug))
								
									if(spielfeld.spielfeld[einZug.getZeile()+p][einZug.getSpalte()+p] == unsereFarbe && imFeld(einZug))
										{
											Zug möglicherZug = new Zug(einZug.getZeile()+p, einZug.getSpalte()+p);	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;
						/*links unten des Felds kucken*/ if(nachbar && k>0 && j<0)
							for(int p = -1; p>=7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile()+(p*-1), neuerZug.getSpalte()+p);
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
									Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
									if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()+(p*-1)][einZug.getSpalte()+p] == unsereFarbe && imFeld(einZug))
										{
											Zug möglicherZug = new Zug(einZug.getZeile()+(p*-1), einZug.getSpalte()+p);	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;
						/*rechts oben des Felds kucken*/ if(nachbar && k<0 && j>0)
							for(int p = -1; p>=7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile()+p, neuerZug.getSpalte()+(p*-1));
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
									Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
									if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()+p][einZug.getSpalte()+(p*-1)] == unsereFarbe && imFeld(einZug))
										{
											Zug möglicherZug = new Zug(einZug.getZeile()+p, einZug.getSpalte()+(p*-1));	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;
						/*rechts unten des Felds kucken*/ if(nachbar && k>0 && j>0)
							for(int p = -1; p>=7; p--)		
							{	
								einZug = new Zug (neuerZug.getZeile()+(p*-1), neuerZug.getSpalte()+(p*-1));
								if(imFeld(einZug))
								if(spielfeld.spielfeld[einZug.getZeile()][einZug.getSpalte()] == gegnerFarbe && !gefunden) 
								{
									gefunden=true;
									continue;
								}
									Zug zweiZug = new Zug(einZug.getZeile()+p, einZug.getSpalte());
									if(imFeld(zweiZug))
									if(spielfeld.spielfeld[einZug.getZeile()+(p*-1)][einZug.getSpalte()+(p*-1)] == unsereFarbe && imFeld(einZug))
										{
										
											Zug möglicherZug = new Zug(einZug.getZeile()+(p*-1), einZug.getSpalte()+(p*-1));	
											möglicheZüge.add(möglicherZug);
											break;
										}
							}
						gefunden=false;
						
					}	
			}
		return möglicheZüge;
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