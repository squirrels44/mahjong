package com.squirrel.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.squirrel.model.Joueur;
import com.squirrel.model.MurException;
import com.squirrel.model.Tuile;

import static com.squirrel.app.MahjongPartie.EtatPartie.JOUEURAPPELLE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TROPTUILE;
import static com.squirrel.app.MahjongPartie.EtatPartie.TUILEATTEND;

public class TestMahjongPartie {

	MahjongPartie mahp;

	@Test
	public void testJetteTuile() {
		mahp = new MahjongPartie();
		mahp.mah.initialiserUnePartie();


		mahp.jetteTuile(mahp.mah.jEst, mahp.mah.jEst.getHand().get(1));
		assertEquals(13, mahp.mah.jEst.getHand().handSize());
		assertEquals(1, mahp.defausse.size());
	}

	@Test
	public void testOrdreJoueurs() {
		mahp = new MahjongPartie();
		mahp.mah.initialiserUnePartie();
		ArrayList<Joueur> liste = new ArrayList<Joueur>();

		liste = mahp.ordreJoueurs(mahp.mah.jSud);
		assertEquals(mahp.mah.jSud, liste.get(0));
		assertEquals(mahp.mah.jOuest, liste.get(1));
		assertEquals(mahp.mah.jNord, liste.get(2));
		assertEquals(mahp.mah.jEst, liste.get(3));
	}


	@Test
	public void testLancerPartie1() {

		mahp = new MahjongPartie();

		Thread t= new Thread(
				new Runnable() {

					@Override
					public void run() {
						mahp.lancerPartie();
					}
				}
				);
		t.start();
		mahp.repRecue = true;
		try {
			Thread.sleep(mahp.sleepTime_ms*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(TUILEATTEND,mahp.etat);	
		assertEquals(false,mahp.repRecue);

		//Ligne pour arreter le thread...
		mahp.mahjong=true;
	}



	@Test
	public void testLancerPartie2a() {

		mahp = new MahjongPartie();

		Thread t= new Thread(
				new Runnable() {

					@Override
					public void run() {
						mahp.etat = TUILEATTEND;
						mahp.lancerPartie();
					}
				}
				);
		t.start();
		mahp.repAppel(mahp.mah.jSud,MahjongPartie.Combinaison.CHOW);
		mahp.repAppelJoueur.appel();

		try {
			Thread.sleep(mahp.sleepTime_ms*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(JOUEURAPPELLE,mahp.etat);

	}

	@Test

	public void testLancerPartie2b() {

		mahp = new MahjongPartie();

		Thread t= new Thread(
				new Runnable() {

					@Override
					public void run() {
						mahp.etat = TUILEATTEND;
						mahp.lancerPartie();
					}
				}
				);
		t.start();
		//	mahp.repRecue=false;
		try {
			Thread.sleep(mahp.sleepTime_ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(TUILEATTEND,mahp.etat);
		try {
			Thread.sleep(mahp.sleepTime_ms*(mahp.ENTRETOURS+1));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(TROPTUILE,mahp.etat);
	}

	@Test
	public void testLancerPartie2c() {

		mahp = new MahjongPartie();

		Thread t= new Thread(
				new Runnable() {

					@Override
					public void run() {

						mahp.lancerPartie();
					}
				}
				);
		t.start();
		
			mahp.etat = JOUEURAPPELLE;
			try {
				Thread.sleep(mahp.sleepTime_ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			assertEquals(JOUEURAPPELLE,mahp.etat);
			mahp.repAppelJoueur=mahp.mah.jOuest;
			try{
				Tuile tuile =mahp.murPioche.piocherTuile();
				mahp.tuile=tuile;
			}
			catch(MurException e){
				e.printStackTrace();
			}


			try {
				Thread.sleep(mahp.sleepTime_ms*3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			assertTrue(mahp.etat.equals(TUILEATTEND)|| mahp.etat.equals(TROPTUILE));

		

	}
	
	@Test
	public void TestPartieComplete(){
		mahp = new MahjongPartie();

		Thread t= new Thread(
				new Runnable() {

					@Override
					public void run() {

						mahp.lancerPartie();
					}
				}
				);
		t.start();
		for (int i = 0; i < 100; i++) {
			if(mahp.etat==TROPTUILE){
				mahp.repSelectionTuile=mahp.jCourant.getHand().get(0);
				try {
					Thread.sleep(mahp.sleepTime_ms);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{i--;}
			System.out.println(mahp.defausse.size());
			
		
		}
		
	}
}

