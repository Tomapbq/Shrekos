import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FantomeTest {

	Fantome f;
	Plateau p;
	ArrayList<Integer> coord;
	
	@Before
	public void setUp() {
		
		int longueur=12;
		int hauteur=12;
		int niveau=2;
		p = new Plateau(longueur, hauteur, niveau);
		f = new Fantome(1,1,1,1);
		coord = new ArrayList<Integer>(f.getCoord());

		
	}
	
	@Test
	public void testDeplacementFantome() {
		
		f.deplacementFantome(p);
		assertNotSame(coord,f.getCoord());
		
	}
}
