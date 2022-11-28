package Source;

import java.util.ArrayList;

public class Main  {

	public static void main(String[] args) {
		
		int longueur=12;
		int hauteur=12;
		int perimetre=1;
		Plateau p = new Plateau(longueur, hauteur, 2);
		
		ArrayList<Integer> coordTresor = new ArrayList<Integer>();
		Tresor t = new Tresor(coordTresor);
		t.positionAleatoire(longueur, hauteur, p.getPlateau());
		
		ArrayList<Integer> coordHeros = new ArrayList<Integer>();
		Hero h = new Hero(coordHeros,2,1);
		h.positionAleatoire(longueur, hauteur, p.getPlateau());
		while (Perimetre(coordHeros,coordTresor,perimetre)==false){
			h.positionAleatoire(longueur, hauteur, p.getPlateau());
		}
		
		ArrayList<Integer> coordZombie = new ArrayList<Integer>();
		Zombie z = new Zombie(coordZombie,1,1);
		z.positionAleatoire(longueur, hauteur, p.getPlateau());
		while (Perimetre(coordZombie,coordHeros,perimetre)==false){
			h.positionAleatoire(longueur, hauteur, p.getPlateau());
		}
		
		ArrayList<Integer> coordFantome = new ArrayList<Integer>();
		Fantome f = new Fantome(coordFantome,1,1);
		f.positionAleatoire(longueur, hauteur, p.getPlateau());
		while (Perimetre(coordFantome,coordHeros,perimetre)==false){
			h.positionAleatoire(longueur, hauteur, p.getPlateau());
		}
		
		while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
			System.out.println("Obstacles : \t"+p.getObstacles());
			System.out.println("Heros : \t"+h.getCoord());
			System.out.println("Zombie : \t"+z.getCoord());
			System.out.println("Fantome : \t"+f.getCoord());
			System.out.println("Tresor : \t"+t.getCoord());
			f.deplacementFantome(p);
			z.deplacementZombie(p.getPlateau());
			//h.deplacementHero(p.getPlateau());
			if (z.getCoord().equals(h.getCoord())) {
				h.setVie(h.getVie()-z.getAttaque());
				z.setVie(z.getVie()-h.getAttaque());
				System.out.println("Aie ta vie : \t"+h.getVie());
			}
			if (f.getCoord().equals(h.getCoord())) {
				h.setVie(h.getVie()-f.getAttaque());
				f.setVie(f.getVie()-h.getAttaque());
				System.out.println("Aie ta vie : \t"+h.getVie());
			}
			System.out.println();

		}
		System.out.println("Heros : \t"+h.getCoord());
		System.out.println("Zombie : \t"+z.getCoord());
		System.out.println("Fantome : \t"+f.getCoord());
		System.out.println("Tresor : \t"+t.getCoord());
				
		if (h.getVie()==0) {
			System.out.println("GAME OVER");
		}
		
		if (t.getCoord().equals(h.getCoord())) {
			System.out.println("WIN");
		}
	}
}

