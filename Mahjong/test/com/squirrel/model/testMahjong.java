package com.squirrel.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.squirrel.app.MahjongInitialisation;

public class testMahjong {

	@Test
	public void testDistributionMain() {
		MahjongInitialisation mahjong=new MahjongInitialisation();
		mahjong.initialiserUnePartie();
		
		assertEquals(14, mahjong.mainEst.handSize());
		assertEquals(13, mahjong.mainOuest.handSize());
		assertEquals(13, mahjong.mainNord.handSize());
		assertEquals(13, mahjong.mainSud.handSize());
	
	}
	
	

}
