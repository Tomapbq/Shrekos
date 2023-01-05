import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class ObjetsTest {
	Objet Teleportation= new Objet(0,0);
	int longueur=12;
	int hauteur=12;
	Plateau	Plateau = new Plateau(longueur, hauteur, 1);
	@Before
	public void setUp() {
		Plateau = new Plateau(longueur, hauteur, 1);
		
		Teleportation.positionAleatoire(longueur,hauteur, Plateau.getPlateau());
	}

	
	
	@Test
	void testPuissanceAtt() {
		assertEquals(Teleportation.PuissanceAtt(1),2);
		assertEquals(Teleportation.PuissanceAtt(2),1);
		
	}

}
