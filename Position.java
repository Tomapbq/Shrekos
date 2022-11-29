import java.util.ArrayList;
import java.util.List;

public class Position {

	int x;
	int y;

	public Position(int x,int y) {
		this.x=x;
		this.y=y;
	}

	public boolean mouvPossible(ArrayList<List<Integer>> plateau, ArrayList<Integer> coord) {
		boolean boule=false;
		for (List<Integer> element : plateau) {
			if (element.equals(coord))
				boule=true;
		}
		return boule;
	}

	public void positionAleatoire(int longueur, int hauteur, ArrayList<List<Integer>> plateau){
		// le mur se positionne sur [0,1:hauteur]; [0:longueur,0], [longueur,0:hauteur], [0:longueur,hauteur]
		ArrayList<Integer> coordAlea;
		do {
			coordAlea = new ArrayList<Integer>();
			coordAlea.add((int)(Math.random()*(longueur-1))+1);			 
			coordAlea.add((int)(Math.random()*(hauteur-1))+1);
		}
		while(mouvPossible(plateau, coordAlea)==false);
		setCoord(coordAlea);
	}

	public boolean perimetre(ArrayList<Integer> coordHero,int perimetre) {
		boolean boule=false;
		for (int i =-perimetre;i<=perimetre;i++) {
			if (x==coordHero.get(0)+i && y==coordHero.get(1)) boule=false;
			if (x==coordHero.get(0) && y==coordHero.get(1)+i) boule=false;
			if (x==coordHero.get(0)+i && y==coordHero.get(1)+i) boule=false;
			if (x==coordHero.get(0)+i && y==coordHero.get(1)-i) boule=false;
			else boule=true;
		}
		return boule;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public ArrayList<Integer> getCoord(){
		ArrayList<Integer> coord = new ArrayList<Integer>();
		coord.add(x);
		coord.add(y);
		return coord;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}

	public void setCoord(ArrayList<Integer> coord){
		setX(coord.get(0));
		setY(coord.get(1));
	}
	
	
}



