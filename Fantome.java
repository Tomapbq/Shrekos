package Source;

import java.util.ArrayList;

public class Fantome extends Monstre {

	public Fantome(ArrayList<Integer> coord, int vie, int attaque) {
		super(coord, vie, attaque);
	}
	
	public void deplacementFantome(Plateau p) {
		ArrayList<Integer> testCoord;
		do {
			testCoord=positionMonstre();
		}
		while (!(0<testCoord.get(0) && testCoord.get(0)<p.getLongeur()-1 && 0<testCoord.get(1) && testCoord.get(1)<p.getHauteur()));
		setCoord(testCoord);
	}

}
