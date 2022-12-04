import java.util.ArrayList;
import java.util.List;

public class Zombie extends Monstre {

	public Zombie(int x,int y, int vie, int attaque) {
		super(x, y, vie, attaque);
		// on initialisera toujours la vie à 1
		setVie(1);
	}

	public void deplacementZombie(ArrayList<List<Integer>> plateau) {
		ArrayList<Integer> testCoord;
		do {
			testCoord=positionMonstre();
		}
		while (mouvPossible(plateau, testCoord)==false);
		setCoord(testCoord);
	}

}
