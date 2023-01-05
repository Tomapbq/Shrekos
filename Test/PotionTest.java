import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PotionTest {
	Potion p= new Potion(0,0);
	int longueur=12;
	int hauteur=12;
	Plateau	Plateau = new Plateau(longueur, hauteur, 1);
	
	@Before
	public void setUp() {
		Plateau = new Plateau(longueur, hauteur, 1);
		p.positionAleatoire(longueur,hauteur, Plateau.getPlateau());
	}

	
	
	@Test
	void testAugmenterVie() {
		assertEquals(p.AugmenterVie(1),3);
		assertEquals(p.AugmenterVie(2),2);
		assertEquals(p.AugmenterVie(3),1);
		
	}

}
