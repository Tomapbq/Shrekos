import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int longueur=12;
		int hauteur=12;
		int perimetre;
		
		Tresor t = new Tresor(hauteur-2,hauteur-2);
		Hero h = new Hero(1,1,2,1);
		
		System.out.print("Choisir son niveau : ");
		Scanner sca = new Scanner(System.in);
		String cmde = sca.nextLine();
		int niveau=Integer.valueOf(cmde);
		
		if (niveau==1) {
			
			perimetre=5;
			
			Plateau p = new Plateau(longueur, hauteur, niveau);
			
			Zombie z = new Zombie(0,0,1,1);
			do {
				z.positionAleatoire(longueur, hauteur, p.getPlateau());
			} while (!(z.perimetre(h.getCoord(),perimetre)));
			
			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				System.out.println("Obstacles : \t"+p.getObstacles());
				System.out.println("Heros : \t"+h.getCoord());
				System.out.println("Zombie : \t"+z.getCoord());
				System.out.println("Tresor : \t"+t.getCoord());
				z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());
				if (z.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-z.getAttaque());
					z.setVie(z.getVie()-h.getAttaque());
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				System.out.println();

			}
			System.out.println("Heros : \t"+h.getCoord());
			System.out.println("Zombie : \t"+z.getCoord());
			System.out.println("Tresor : \t"+t.getCoord());

			if (h.getVie()==0) {
				System.out.println("GAME OVER");
			}

			if (t.getCoord().equals(h.getCoord())) {
				System.out.println("WIN");
			}
		}
		
		else if (niveau==2) {
			
			perimetre=4;
			
			Plateau p = new Plateau(longueur, hauteur, niveau);
			
			Fantome f = new Fantome(0,0,1,1);
			do {
				f.positionAleatoire(longueur, hauteur, p.getPlateau());
			} while (!(f.perimetre(h.getCoord(),perimetre)));
			
			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				System.out.println("Obstacles : \t"+p.getObstacles());
				System.out.println("Heros : \t"+h.getCoord());
				System.out.println("Fantome : \t"+f.getCoord());
				System.out.println("Tresor : \t"+t.getCoord());
				f.deplacementFantome(p);
				h.deplacementHero(p.getPlateau());
				if (f.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-f.getAttaque());
					f.setVie(f.getVie()-h.getAttaque());
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				System.out.println();
			}
			System.out.println("Heros : \t"+h.getCoord());
			System.out.println("Fantome : \t"+f.getCoord());
			System.out.println("Tresor : \t"+t.getCoord());

			if (h.getVie()==0) {
				System.out.println("GAME OVER");
			}

			if (t.getCoord().equals(h.getCoord())) {
				System.out.println("WIN");
			}
			
		}
		
		else if (niveau==3) {
			
			perimetre=3;
			
			Plateau p = new Plateau(longueur, hauteur, niveau);

			Zombie z = new Zombie(0,0,1,1);
			do {
				z.positionAleatoire(longueur, hauteur, p.getPlateau());
			} while (!(z.perimetre(h.getCoord(),perimetre)));

			Fantome f = new Fantome(0,0,1,1);
			do {
				f.positionAleatoire(longueur, hauteur, p.getPlateau());
			} while (!(f.perimetre(h.getCoord(),perimetre)));

			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				System.out.println("Obstacles : \t"+p.getObstacles());
				System.out.println("Heros : \t"+h.getCoord());
				System.out.println("Zombie : \t"+z.getCoord());
				System.out.println("Fantome : \t"+f.getCoord());
				System.out.println("Tresor : \t"+t.getCoord());
				f.deplacementFantome(p);
				z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());
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
		else System.out.println("Le niveau n'existe pas");

	}
}

