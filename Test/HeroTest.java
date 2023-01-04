import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HeroTest {

	Hero h;
	Plateau p;
	ArrayList<Integer> coord;
	ArrayList<List<Integer>> obstacles;
	int longueur=12;
	int hauteur=12;
	int niveau=2;
	int x, y;

	
	@Before
	public void setUp() {
		
		p = new Plateau(longueur, hauteur, niveau);
		obstacles = p.getObstacles();


		h = new Hero(1,1,1,1);
		coord = new ArrayList<Integer>();
		
	}

	
	  @Test
	  public void testPositionHeros() {
	  
	  //"z"
	  coord=h.positionHeros();
	  x = coord.get(0);
	  y = coord.get(1);
	  assertEquals(x,1);
	  assertEquals(y,2);
	  
	  //"s"
	  coord=h.positionHeros();
	  x = coord.get(0);
	  y = coord.get(1);
	  assertEquals(x,1);
	  assertEquals(y,0);
	  
	  //"d" 
	  coord=h.positionHeros();
	  x = coord.get(0);
	  y = coord.get(1);
	  assertEquals(x,2);
	  assertEquals(y,1);
	  
	  // "q" 
	  coord=h.positionHeros();
	  x = coord.get(0);
	  y = coord.get(1);
	  assertEquals(x,0);
	  assertEquals(y,1);
	  
	  }
	 
	
	@Test
	public void testDeplacementHero() {
		
		//"z"
		h.deplacementHero(p.getPlateau());
		assertEquals(h.x,1);
		assertEquals(h.y,2);
		
		//"s"
		h.deplacementHero(p.getPlateau());
		assertEquals(h.x,1);
		assertEquals(h.y,1);
		
		//"d"
		h.deplacementHero(p.getPlateau());
		assertEquals(h.x,2);
		assertEquals(h.y,1);
		
		// "q"
		h.deplacementHero(p.getPlateau());
		assertEquals(h.x,1);
		assertEquals(h.y,1);

	}
	
	
	
	


}
