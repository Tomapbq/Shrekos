
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

			Potion Potion = new Potion(0,0);
			Potion.positionAleatoire(longueur,hauteur, p.getPlateau());

			Objet Teleportation= new Objet(0,0);
			Teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());

			Objet Attaque= new Objet(0,0);
			Attaque.positionAleatoire(longueur,hauteur, p.getPlateau());			

			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t,Potion,Teleportation);
				System.out.println("Attaque: \t"+Attaque.getCoord());
				System.out.println("Zombie : \t"+z.getCoord());
				z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());

				if (Potion.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()+Potion.AugmenterVie(niveau));
					System.out.println("Tu gagnes un bonus de vie ! Ton nombre de vie : \t"+h.getVie());
					Potion.positionAleatoire(longueur,hauteur, p.getPlateau());
				}

				if (Teleportation.getCoord().equals(h.getCoord())) {
					h.positionAleatoire(longueur,hauteur, p.getPlateau());
					System.out.println("Tu as été téléporté ! Ta nouvelle case : \t"+h.getCoord());
					Teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());
				}

				if (Attaque.getCoord().equals(h.getCoord())) {
					if (z.getVie()-Attaque.PuissanceAtt(niveau)<=0){
						z.setX(t.getX());
						z.setY(t.getY());
						z.setVie(1);
						System.out.println("Tu as tué le zombie !");
						// si il lui reste de la vie, on lui en enleve
					} else {
						z.setVie(z.getVie()-Attaque.PuissanceAtt(niveau));
						System.out.println("Le zombie perd une vie !");
					}
					Attaque.positionAleatoire(longueur,hauteur, p.getPlateau());
				}
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

			afficherCoord(p,h,t,Potion,Teleportation);
			System.out.println("Attaque: \t"+Attaque.getCoord());
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

			Potion Potion = new Potion(0,0);
			Potion.positionAleatoire(longueur,hauteur, p.getPlateau());

			Objet Teleportation = new Objet(0,0);
			Teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());

			Objet Attaque = new Objet(0,0);
			Attaque.positionAleatoire(longueur,hauteur, p.getPlateau());
			System.out.println("Attaque : \t"+Attaque.getCoord());

			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t,Potion,Teleportation);
				System.out.println("Attaque: \t"+Attaque.getCoord());
				System.out.println("Fantome : \t"+f.getCoord());
				f.deplacementFantome(p);
				h.deplacementHero(p.getPlateau());

				if (Potion.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()+Potion.AugmenterVie(niveau));
					System.out.println("Tu gagnes un bonus de vie ! Ton nombre de vie : \t"+h.getVie());
				}

				if (Teleportation.getCoord().equals(h.getCoord())) {
					h.positionAleatoire(longueur,hauteur, p.getPlateau());
					System.out.println("Tu as été téléporté ! Ta nouvelle case : \t"+h.getCoord());
					Teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());
				}
				if (Attaque.getCoord().equals(h.getCoord())) {
					if (f.getVie()-Attaque.PuissanceAtt(niveau)<=0){
						f.setX(t.getX());
						f.setY(t.getY());
						f.setVie(1);
						System.out.println("Tu as tué le zombie !");
						// si il lui reste de la vie, on lui en enleve
					} else {
						f.setVie(f.getVie()-Attaque.PuissanceAtt(niveau));
						System.out.println("Le fantome perd une vie !");
					}
					Attaque.positionAleatoire(longueur,hauteur, p.getPlateau());
				}

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
			afficherCoord(p,h,t,Potion,Teleportation);
			System.out.println("Attaque: \t"+Attaque.getCoord());
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

			Potion Potion = new Potion(0,0);
			Potion.positionAleatoire(longueur,hauteur, p.getPlateau());

			Objet teleportation= new Objet(0,0);
			teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());
			System.out.println("case teleportation : \t"+teleportation.getCoord());

			while (h.getVie()!=0 && !(t.getCoord().equals(h.getCoord()))) {
				afficherCoord(p,h,t,Potion,teleportation);
				System.out.println("Zombie : \t"+z.getCoord());
				System.out.println("Fantome : \t"+f.getCoord());
				f.deplacementFantome(p);
				z.deplacementZombie(p.getPlateau());
				h.deplacementHero(p.getPlateau());

				if (Potion.getCoord().equals(h.getCoord())) {
					h.setVie(h.getVie()+Potion.AugmenterVie(niveau));
					System.out.println("Tu gagnes un bonus de vie ! Ton nombre de vie : \t"+h.getVie());
				}

				if (teleportation.getCoord().equals(h.getCoord())) {
					h.positionAleatoire(longueur,hauteur, p.getPlateau());
					System.out.println("Tu as été téléporté ! Ta nouvelle case : \t"+h.getCoord());
					teleportation.positionAleatoire(longueur,hauteur, p.getPlateau());
				}

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

			afficherCoord(p,h,t,Potion,teleportation);
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

	static void afficherCoord(Plateau p, Hero h, Tresor t,Potion Potion,Objet teleportation) {
		System.out.println("Obstacles : \t"+p.getObstacles());
		System.out.println("Tresor : \t"+t.getCoord());
		System.out.println("Heros : \t"+h.getCoord());
		System.out.println("Potion : \t"+Potion.getCoord());
		System.out.println("Teleportation :\t"+teleportation.getCoord());
	}
}
