package Source;
import java.util.ArrayList;
import java.util.List;

public class Position {

	private ArrayList<Integer> coord = new ArrayList<Integer>();

	public Position(ArrayList<Integer> coord) {
		this.coord= coord;
	}
	
	public boolean mouvPossible(ArrayList<List<Integer>> plateau, ArrayList<Integer> coord) {
		boolean boule=false;
		for (List<Integer> element : plateau) {
			if (element.equals(coord))
			boule=true;
		}
		return boule;
	}
	public boolean Perimetre(ArrayList<Integer> coord1, ArrayList<Integer> coord2,int perimetre) {
		boolean boule=false;

		for(int i =-perimetre;i<=perimetre;i++) {
		if (coord1.get(0)==coord2.get(0)+i && coord1.get(1)==coord2.get(1))
		boule=false;
		if  (coord1.get(0)==coord2.get(0) && coord1.get(1)==coord2.get(1)+i)
		boule=false;
		if  (coord1.get(0)==coord2.get(0)+i && coord1.get(1)==coord2.get(1)+i)
		boule=false;
		if  (coord1.get(0)==coord2.get(0)+i && coord1.get(1)==coord2.get(1)-i)
		boule=false;
		else boule=true;
		}
		return boule;
	}
	public void positionAleatoire(int longueur, int hauteur, ArrayList<List<Integer>> plateau){
		// le mur se positionne sur [0,1:hauteur]; [0:longueur,0], [longueur,0:hauteur], [0:longueur,hauteur]
		ArrayList<Integer> coordAlea;
		do {
			coordAlea = new ArrayList<Integer>(getCoord());
			coordAlea.add((int)(Math.random()*(longueur-1))+1);			 
			coordAlea.add((int)(Math.random()*(hauteur-1))+1);
		}
		while(mouvPossible(plateau, coordAlea)==false);
		setCoord(coordAlea);
	}

	public ArrayList<Integer> getCoord(){
		return coord;
	}

	public void setCoord(ArrayList<Integer> coord){
		this.coord=coord;
	}
}



