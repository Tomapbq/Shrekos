import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PlateauTest {
	int longueur=12;
	int hauteur=12;
	Plateau	p = new Plateau(longueur, hauteur, 1);
	@Before
	public void setUp() {
		p = new Plateau(longueur, hauteur, 1);
		
	}
	@Test
	void testTaille() {
		assertEquals(p.getLongueur(),12);
		assertEquals(p.getHauteur(),12);
	}

}
