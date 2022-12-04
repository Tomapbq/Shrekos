import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
	int perimetre;
	int nb_obstacles;
	int niveau;

	Plateau p;
	ArrayList<List<Integer>> plateau;
	ArrayList<List<Integer>> obstacles;

	Tresor t = new Tresor(hauteur-2,hauteur-2);

	Hero h = new Hero(1,1,2,1);

	Zombie z = new Zombie(0,0,3,1);

	Fantome f = new Fantome(0,0,3,1);

	static final int UNIT_SIZE = 50;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int DELAY = 50;
	static final int temps_depl_z = 1000;
	static final int temps_depl_f = 1000;
	static final int longueur = SCREEN_WIDTH/UNIT_SIZE;
	static final int hauteur = SCREEN_HEIGHT/UNIT_SIZE;
	int time = -DELAY;
	boolean running = false;
	int win = 0;
	Timer timer;

	GamePanel(int niveau) {
		this.niveau = niveau;
		if (niveau == 1) {
			perimetre = 5;
			nb_obstacles = 7;
		}
		else if (niveau == 2) {
			perimetre = 4;
			nb_obstacles = 15;
		}
		else if (niveau == 3) {
			perimetre = 3;
			nb_obstacles = 20;
		}
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());

		p = new Plateau(longueur, hauteur, niveau);
		plateau = p.getPlateau();
		obstacles = p.getObstacles();

		z.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);
		f.testPositionPerimetre(h.getCoord(), p.getPlateau(), perimetre, longueur, hauteur);
		startGame();
	}

	public void startGame() {
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}

	//@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			checkTresor();
			checkMort();
			if (time % temps_depl_f == 0) {
				f.deplacementFantome(p);
				checkTouche_f();
			}
			if (time % temps_depl_f == 0) {
				z.deplacementZombie(p.getPlateau());
				checkTouche_z();
			}
			time += DELAY;
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if(running) {
			draw_lines(g);
			draw_obst(g);
			draw_vie(g);
			draw_h(g);
			draw_t(g);
			draw_f(g);
			draw_z(g);
		}
		else {
			gameOver(g);
		}
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			String cmd = "";
			switch(e.getKeyCode()) {
			case KeyEvent.VK_Q:
				cmd = "q";
				break;
			case KeyEvent.VK_D:
				cmd = "d";
				break;
			case KeyEvent.VK_Z:
				cmd = "s";
				break;
			case KeyEvent.VK_S:
				cmd = "z";
				break;
			}
			deplacementHero(plateau, cmd);
		}
	}
	public void gameOver(Graphics g) {
		//Le joueur gagne
		if(win==1) {
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD, 75));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("C'est gagné", (SCREEN_WIDTH - metrics1.stringWidth("C'est gagné"))/2, SCREEN_HEIGHT/2);
		}
		//Le joueur a perdu
		else {
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD, 75));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("C'est perdu", (SCREEN_WIDTH - metrics2.stringWidth("C'est perdu"))/2,SCREEN_HEIGHT/2);
		}
	}

	//dessine les lignes
	public void draw_lines(Graphics g) {
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE);
		}
	}

	//dessine les coeurs du héros
	public void draw_vie(Graphics g) {
		g.setColor(Color.red);
		for(int i = 0;i<h.getVie();i++) {
			g.fillOval(UNIT_SIZE*(longueur-1-i),0,UNIT_SIZE,UNIT_SIZE);
		}
	}

	//dessine les obstacles (et bords)
	public void draw_obst(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<nb_obstacles;i++) {
			g.fillRect(UNIT_SIZE*obstacles.get(i).get(0),UNIT_SIZE*obstacles.get(i).get(1),UNIT_SIZE,UNIT_SIZE);
		}
		for(int i=0;i<longueur;i++) {
			g.fillRect(UNIT_SIZE*i,0,UNIT_SIZE,UNIT_SIZE);
			g.fillRect(UNIT_SIZE*i,UNIT_SIZE*(hauteur-1),UNIT_SIZE,UNIT_SIZE);
		}
		for(int i=0;i<hauteur;i++) {
			g.fillRect(0,UNIT_SIZE*i,UNIT_SIZE,UNIT_SIZE);
			g.fillRect(UNIT_SIZE*(longueur-1),UNIT_SIZE*i,UNIT_SIZE,UNIT_SIZE);
		}
	}

	//dessine le zombie
	public void draw_z(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(UNIT_SIZE*z.getX(),UNIT_SIZE*z.getY(),UNIT_SIZE,UNIT_SIZE);
	}

	//dessine le fantome
	public void draw_f(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(UNIT_SIZE*f.getX(),UNIT_SIZE*f.getY(),UNIT_SIZE,UNIT_SIZE);
	}

	//dessine le trésor
	public void draw_t(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(UNIT_SIZE*t.getX(),UNIT_SIZE*t.getY(),UNIT_SIZE,UNIT_SIZE);
	}

	//dessine le héros
	public void draw_h(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(UNIT_SIZE*h.getX(),UNIT_SIZE*h.getY(),UNIT_SIZE,UNIT_SIZE);
	}

	public void deplacementHero(ArrayList<List<Integer>> plateau, String cmd) {
		ArrayList<Integer> tesCoord;
		tesCoord = h.position(cmd);
		if (h.mouvPossible(plateau, tesCoord) == true) {
			h.setCoord(tesCoord);
		}
	}

	//verifie si le héros est sur le trésor
	public void checkTresor() {
		if (t.getCoord().equals(h.getCoord())) {
			running = false;
			win = 1;
		}
	}

	//verifie si le heros n'as plus de vies
	public void checkMort() {
		if (h.getVie()<=0) {
			running = false;
		}
	}

	//verifie si le zombie touche le héros
	public void checkTouche_z() {
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
		}
	}

	//verifie si le fantome touche le héros
	public void checkTouche_f() {
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
		}
	}
}
