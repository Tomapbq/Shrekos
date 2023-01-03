import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PersonnageTest {
	
	Personnage p;
	ArrayList<Integer> coord;
	int x, y;

	
	@Before
	public void setUp() {
		p = new Personnage(1,1,1,1);
		coord = new ArrayList<Integer>();
	}

	@Test
	public void testPosition() {
		
		assertEquals(p.x,1);
		assertEquals(p.y,1);

		coord = p.position("z");
		x = coord.get(0);
		y = coord.get(1);
		
		assertEquals(x,1);
		assertEquals(y,2);
		
		coord = p.position("s");
		x = coord.get(0);
		y = coord.get(1);
		
		assertEquals(x,1);
		assertEquals(y,0);
		
		coord = p.position("d");
		x = coord.get(0);
		y = coord.get(1);
		assertEquals(x,2);
		assertEquals(y,1);
		
		coord = p.position("q");
		x = coord.get(0);
		y = coord.get(1);
		assertEquals(x,0);
		assertEquals(y,1);
	}
	
	@Test
	public void testGetVie() {
		assertEquals(p.getVie(),1);
	}
	
	@Test
	public void testSetVie() {
		p.setVie(2);
		assertEquals(p.getVie(),2);
	}
	
	@Test
	public void testGetAttaque() {
		assertEquals(p.getAttaque(),1);
	}
	
	@Test
	public void testSetAttaque() {
		p.setAttaque(2);
		assertEquals(p.getAttaque(),2);
	}
	
}
