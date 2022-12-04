import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main  {

	public static void main(String[] args) {

		int longueur=12;
		int hauteur=12;
		int perimetre;

		Tresor t = new Tresor(hauteur-2,longueur-2);
		Hero h = new Hero(1,1,2,1);

		System.out.print("Choisir son niveau : ");
		Scanner sca = new Scanner(System.in);
		String cmde = sca.nextLine();
		int niveau=Integer.valueOf(cmde);

		if (niveau==1) {

			perimetre=5;

			Plateau p = new Plateau(longueur, hauteur, niveau);

			Zombie z = new Zombie(0,0,1,1);
			z.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);

			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t);
				System.out.println("Zombie : \t"+z.getCoord());
				z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());
				
				if (z.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-z.getAttaque());
					// si le zombie n'a plus de vie on le fait reaparaitre sur la case du tresor avec toute sa vie
					if (z.getVie()-h.getAttaque()<=0){
						z.setX(t.getX());
						z.setY(t.getY());
						z.setVie(1);
					// si il lui reste de la vie, on lui en enleve
					} else {
						z.setVie(z.getVie()-h.getAttaque());
					}
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				System.out.println();
			}
			
			afficherCoord(p,h,t);
			System.out.println("Zombie : \t"+z.getCoord());

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
			f.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);


			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t);
				System.out.println("Fantome : \t"+f.getCoord());
				f.deplacementFantome(p);
				h.deplacementHero(p.getPlateau());
				// test si coord hero et fantome =
				if (f.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-f.getAttaque());
					// si le fantome n'a plus de vie on le fait reaparaitre sur la case du tresor avec toute sa vie
					if (f.getVie()-h.getAttaque()<=0){
						f.setX(t.getX());
						f.setY(t.getY());
						f.setVie(1);
					// si il lui reste de la vie, on lui en enleve
					} else {
						f.setVie(f.getVie()-h.getAttaque());
					}
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				System.out.println();
			}
			afficherCoord(p,h,t);
			System.out.println("Fantome : \t"+f.getCoord());

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
			z.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);


			Fantome f = new Fantome(0,0,1,1);
			f.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);


			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t);
				System.out.println("Zombie : \t"+z.getCoord());
				System.out.println("Fantome : \t"+f.getCoord());
				
				//f.deplacementFantome(p);
				//z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());
				
				if (z.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-z.getAttaque());
					// si le zombie n'a plus de vie on le fait reaparaitre sur la case du tresor avec toute sa vie
					if (z.getVie()-h.getAttaque()<=0){
						z.setX(t.getX());
						z.setY(t.getY());
						z.setVie(1);
					// si il lui reste de la vie, on lui en enleve
					} else {
						z.setVie(z.getVie()-h.getAttaque());
					}
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				
				if (f.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()-f.getAttaque());
					// si le fantome n'a plus de vie on le fait reaparaitre sur la case du tresor avec toute sa vie
					if (f.getVie()-h.getAttaque()<=0){
						f.setX(t.getX());
						f.setY(t.getY());
						f.setVie(1);
					// si il lui reste de la vie, on lui en enleve
					} else {
						f.setVie(f.getVie()-h.getAttaque());
					}
					System.out.println("Aie ta vie : \t"+h.getVie());
				}
				System.out.println();
			}
			
			afficherCoord(p,h,t);
			System.out.println("Zombie : \t"+z.getCoord());
			System.out.println("Fantome : \t"+f.getCoord());

			if (h.getVie()==0) {
				System.out.println("GAME OVER");
			}

			if (t.getCoord().equals(h.getCoord())) {
				System.out.println("WIN");
			}
		}

		else System.out.println("Le niveau n'existe pas");
	}
	
	static void afficherCoord(Plateau p, Hero h, Tresor t) {
		System.out.println("Obstacles : \t"+p.getObstacles());
		System.out.println("Tresor : \t"+t.getCoord());
		System.out.println("Heros : \t"+h.getCoord());
	}
}

