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
		ArrayList<Zug> zuDrehendeSteine = new ArrayList<Zug>();
		if(!(vorherigerZug==null))
		{	
			zuDrehendeSteine = dreheSteine(vorherigerZug);
//			if(zuDrehendeSteine.size()==0)
//				throw new ZugException("Der Gegner hat keins Stein umgedreht");
//			else{
					spielfeld.spielfeld[vorherigerZug.getZeile()][vorherigerZug.getSpalte()] = gegnerFarbe;
					for(int i=0; i<zuDrehendeSteine.size(); i++)
					spielfeld.spielfeld[zuDrehendeSteine.get(i).getZeile()][zuDrehendeSteine.get(i).getSpalte()] = gegnerFarbe;
//				}
			
		}
		Zug unserZug = new Zug(-1,-1);
		unserZug = findeBestenZug(woSindSteine());
		
		zuDrehendeSteine = dreheSteine(unserZug);
		spielfeld.spielfeld[unserZug.getZeile()][unserZug.getSpalte()] = unsereFarbe;
		for(int i=0; i<zuDrehendeSteine.size(); i++)
		spielfeld.spielfeld[zuDrehendeSteine.get(i).getZeile()][zuDrehendeSteine.get(i).getSpalte()] = unsereFarbe;
			
		return unserZug;
	}
	
	public ArrayList<Zug> dreheSteine(Zug zug) throws ZugException
	{
		ArrayList<Zug> gedrehteSteine = new ArrayList<Zug>();
//		if(!(spielfeld.spielfeld[zug.getZeile()][zug.getSpalte()]==Farbe.LEER))
//			throw new ZugException("Gegnerischer Zug ungültig!");
		
		spielfeld.spielfeld[zug.getZeile()][zug.getSpalte()] = gegnerFarbe;
		for(int z = -1; z<2; z++)
			for(int s = -1; s<2; s++)
			{	if(z==0 && s==0) continue;
				Zug neuerZug = new Zug(zug.getZeile()+z, zug.getSpalte()+s);
				if(imFeld(neuerZug))
				if(spielfeld.spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()]==unsereFarbe)
				{
					gedrehteSteine.add(neuerZug);
					if(z>0 && s==0)/*unten*/
					{
						for(int i =1; i<7; i++)
						{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+i, neuerZug.getSpalte());
							if(!(imFeld(gedrehterStein)))continue;
							if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
							{
								gedrehteSteine.add(gedrehterStein);
							}
							
							if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
								break;
						}
					}
						if(z<0 && s==0)/*oben*/
						{
							for(int i =-1; i>-7; i--)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+i, neuerZug.getSpalte());
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z==0 && s<0)/*links*/
						{
							for(int i =-1; i>-7; i--)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+i);
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z==0 && s>0)/*rechts*/
						{
							for(int i =1; i<7; i++)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+i);
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z>0 && s>0)/*rechts unten*/
						{
							for(int i =1; i<7; i++)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+i, neuerZug.getSpalte()+i);
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z<0 && s<0)/* oben links*/
						{
							for(int i =-1; i>-7; i--)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+i, neuerZug.getSpalte()+i);
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z<0 && s>0)/* oben rechts*/
						{
							for(int i =-1; i>-7; i--)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+i, neuerZug.getSpalte()+(i*-1));
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
						
						if(z>0 && s<0)/* unten links*/
						{
							for(int i =-1; i>-7; i--)
							{	Zug gedrehterStein = new Zug(neuerZug.getZeile()+(i*-1), neuerZug.getSpalte()+i);
								if(!(imFeld(gedrehterStein)))continue;
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==unsereFarbe)
								{
									gedrehteSteine.add(gedrehterStein);
								}
								
								if(spielfeld.spielfeld[gedrehterStein.getZeile()][gedrehterStein.getSpalte()]==gegnerFarbe)
									break;
							}	
						}
				}
			}
		
			return gedrehteSteine;
		}
	
 	public Zug findeBestenZug (ArrayList<ZugMitDrehsteinen> züge)
	{
		int bestesErgebnis=0;
		int bewertung = 0;
		Zug besterZug = new Zug(0,0);
		for(int i=0; i<züge.size(); i++)
		{	
			bewertung += Brett.getBewertung()[züge.get(i).getZeile()][züge.get(i).getSpalte()];
		
			for(int k=0; k<züge.get(i).getDrehsteine().size(); k++)
		
			{
				bewertung += Brett.getBewertung()[züge.get(i).getDrehsteine().get(k).getZeile()][züge.get(i).getDrehsteine().get(k).getSpalte()];
			}
			
			
			if(bestesErgebnis<bewertung)
			{	
				bestesErgebnis=bewertung;
				besterZug=züge.get(i);
				bewertung=0;
			}
		}
		return besterZug;
	}
	
	public ArrayList<ZugMitDrehsteinen> woSindSteine ()
	{
		ArrayList<ZugMitDrehsteinen> klasseZüge = new ArrayList<ZugMitDrehsteinen>();
		for(int zeile=0; zeile<8; zeile++)
			for(int spalte=0; spalte<8; spalte++)
			{
				if((spielfeld.spielfeld[zeile][spalte] == Farbe.LEER))
				{
					
					for(int z = -1; z<=1; z++)
						for(int s = -1; s<=1; s++)
						{						
							if(z==0 && s==0)continue;
							Zug neuerZug = new Zug (zeile+z, spalte+s);
							if(imFeld(neuerZug))
							if((spielfeld.spielfeld[neuerZug.getZeile()][neuerZug.getSpalte()] == gegnerFarbe))
							{
								ZugMitDrehsteinen züge = new ZugMitDrehsteinen(zeile, spalte);
								züge.getDrehsteine().add(neuerZug);
								
								if( z>0 && s==0)/*unten*/
								{
								
									for(int delta = 1; delta<7; delta++)
									{	
										Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte());
										if(imFeld(drehstein))
										{
											if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
											{
												//züge.getDrehsteine().clear();
												//klasseZüge.remove(züge);
												break;
											}
											
											if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
											{	
												züge.getDrehsteine().add(drehstein);
												continue;
											}
																						
											if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
											{
												klasseZüge.add(züge);
												break;
											}		
										}	
									}
								}	
								
									if( z<0 && s==0)/*oben*/
									{
										
										for(int delta = -1; delta>-7; delta--)
										{	
											Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte());
											if(imFeld(drehstein))
											{
												if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
												{
													//züge.getDrehsteine().clear();
													//klasseZüge.remove(züge);
													break;
												}
												
												if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
												{	züge.getDrehsteine().add(drehstein);
													continue;
												}
																							
												if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
												{
													klasseZüge.add(züge);
													break;
												}
											}	
										}
									}	
										
										if( z==0 && s>0)/*rechts*/
										{
											for(int delta = 1; delta<7; delta++)
											{	
												Zug drehstein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+delta);
												if(imFeld(drehstein))
												{
													if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
													{
														//züge.getDrehsteine().clear();
														//klasseZüge.remove(züge);
														break;
													}
													
													if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
													{	
														züge.getDrehsteine().add(drehstein);
														continue;
													}
																								
													if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
													{
														klasseZüge.add(züge);
														break;
													}
												}	
											}
										}	
											
											if( z==0 && s<0)/*links*/
											{
												for(int delta = -1; delta>-7; delta--)
												{	
													Zug drehstein = new Zug(neuerZug.getZeile(), neuerZug.getSpalte()+delta);
													if(imFeld(drehstein))
													{
														if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
														{
															//züge.getDrehsteine().clear();
															//klasseZüge.remove(züge);
															break;
														}
														
														if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
														{	
															züge.getDrehsteine().add(drehstein);
															continue;
														}
																									
														if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
														{
															klasseZüge.add(züge);
															break;
														}	
													}	
												}
											}	
											
												if( z>0 && s>0)/*rechts unten*/
												{
													for(int delta = 1; delta>7; delta++)
													{	
														Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+delta);
														if(imFeld(drehstein))
														{
															if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
															{
																//züge.getDrehsteine().clear();
																//klasseZüge.remove(züge);
																break;
															}
															
															if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
															{	
																züge.getDrehsteine().add(drehstein);
																continue;	
															}
																										
															if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
															{
																klasseZüge.add(züge);
																break;
															}
														}	
													}
												}	
													
													if( z<0 && s<0)/*oben links*/
													{
														for(int delta = -1; delta>-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+delta);
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
																{
																	//züge.getDrehsteine().clear();
																	//klasseZüge.remove(züge);
																	break;
																}
																
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																{	
																	züge.getDrehsteine().add(drehstein);
																	continue;
																}
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
																{
																	klasseZüge.add(züge);
																	break;
																}
															}	
														}	
													}
													
													if( z<0 && s>0)/*oben rechts*/
													{
														for(int delta = -1; delta>-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+delta, neuerZug.getSpalte()+(delta*-1));
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
																{
																	//züge.getDrehsteine().clear();
																	//klasseZüge.remove(züge);
																	break;
																}
																
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																{	
																	züge.getDrehsteine().add(drehstein);
																	continue;
																}
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
																{
																	klasseZüge.add(züge);
																	break;
																}
															}	
														}	
													}	
													
													if( z>0 && s<0)/*unten links*/
													{
														for(int delta = -1; delta>-7; delta--)
														{	
															Zug drehstein = new Zug(neuerZug.getZeile()+(delta*-1), neuerZug.getSpalte()+delta);
															if(imFeld(drehstein))
															{
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == Farbe.LEER))
																{
																	System.out.println("Hallo");
																	//züge.getDrehsteine().clear();
																	//klasseZüge.remove(züge);
																	break;
																}
																
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == gegnerFarbe))
																{	
																	züge.getDrehsteine().add(drehstein);
																	continue;
																}
																											
																if((spielfeld.spielfeld[drehstein.getZeile()][drehstein.getSpalte()] == unsereFarbe) && !züge.getDrehsteine().isEmpty())
																{	
																	klasseZüge.add(züge);
																	break;
																}	
															}	
														}	
													}		
												}
												else continue;
											}
										}
									}
					return klasseZüge;
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