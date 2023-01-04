import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {
	
	Plateau p;
	Position po;
	ArrayList<Integer> coord1;
	ArrayList<Integer> coord2;
	ArrayList<Integer> coord3;
	int longueur=12;
	int hauteur=12;
	int niveau=2;
	int x, y;
	boolean boul;

	
	@Before
	public void setUp() {
		p = new Plateau(longueur, hauteur, niveau);
		po = new Position(1,1);
		coord1 = new ArrayList<Integer>();
		coord2 = new ArrayList<Integer>();
		coord3 = new ArrayList<Integer>(po.getCoord());
	}

	@Test
	public void testMouvPossible() {
		boul=po.mouvPossible(p.getPlateau(), po.getCoord());
		assertTrue(boul);
		
		coord1.add(0);
		coord1.add(0);
		po.setCoord(coord1);
		boul=po.mouvPossible(p.getPlateau(), po.getCoord());
		assertFalse(boul);
	}

	
	@Test
	public void testPositionAleatoire() {
		po.positionAleatoire(longueur, hauteur, p.getPlateau());
		assertNotSame(po.getCoord(),coord3);
	}

	
	@Test
	public void testPerimetre() {
		coord1.add(0);
		coord1.add(0);
		coord2.add(3);
		coord2.add(3);
		
		boul=po.perimetre(coord1,1);
		assertFalse(boul);

		boul=po.perimetre(coord2,1);
		assertTrue(boul);
	}
	
	@Test
	public void testTestPositionPerimetre() {
		po.testPositionPerimetre(po.getCoord(), p.getPlateau(), 5, longueur, hauteur);
		assertNotSame(po.getCoord(),coord3);

	}
	
	@Test
	public void testGetCoord() {
		coord1=po.getCoord();
		assertSame(coord1.get(0),po.x);
		assertSame(coord1.get(1),po.y);
	}
	
	@Test
	public void testSetCoord() {
		coord1.add(0);
		coord1.add(0);
		po.setCoord(coord1);
		assertSame(coord1.get(0),po.x);
		assertSame(coord1.get(1),po.y);
	}
	

	
	
	
	
	
}
