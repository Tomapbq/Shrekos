import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ZombieTest {
	
	Zombie z;
	Plateau p;
	ArrayList<Integer> coord;
	
	@Before
	public void setUp() {
		
		int longueur=12;
		int hauteur=12;
		int niveau=2;
		p = new Plateau(longueur, hauteur, niveau);
		z = new Zombie(1,1,1,1);
		coord = new ArrayList<Integer>(z.getCoord());
	}
	
	@Test
	public void testDeplacementZombie() {
		
		z.deplacementZombie(p.getPlateau());
		assertNotSame(coord,z.getCoord());
		
	}

}
