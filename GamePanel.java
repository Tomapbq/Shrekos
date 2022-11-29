import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
	
	int longueur=12;
	int hauteur=12;
	
	Plateau p = new Plateau(longueur, hauteur, 2);
	ArrayList<List<Integer>> plateau = p.getPlateau();
	ArrayList<List<Integer>> obstacles = p.getObstacles();
	
	ArrayList<Integer> coordTresor = new ArrayList<Integer>();
	Tresor t = new Tresor(coordTresor);

	ArrayList<Integer> coordHeros = new ArrayList<Integer>();
	Hero h = new Hero(coordHeros,2,1);
	
	ArrayList<Integer> coordZombie = new ArrayList<Integer>();
	Zombie z = new Zombie(coordZombie,1,1);
	
	ArrayList<Integer> coordFantome = new ArrayList<Integer>();
	Fantome f = new Fantome(coordFantome,1,1);
	
	static final int UNIT_SIZE = 50;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int DELAY = 1000;
	boolean running = false;
	int win = 0;
	Timer timer;
	
	GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		t.positionAleatoire(longueur, hauteur, plateau);
		h.positionAleatoire(longueur, hauteur, plateau);
		z.positionAleatoire(longueur, hauteur, plateau);
		f.positionAleatoire(longueur, hauteur, plateau);
		startGame();
	}
	
	public void startGame() {
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE);
			}
			for(int i=0;i<15;i++) {
				g.setColor(Color.black);
				g.fillRect(UNIT_SIZE*obstacles.get(i).get(0),UNIT_SIZE*obstacles.get(i).get(1),UNIT_SIZE,UNIT_SIZE);
			}
			g.setColor(Color.green);
			g.fillOval(UNIT_SIZE*h.getCoord().get(0),UNIT_SIZE*h.getCoord().get(1),UNIT_SIZE,UNIT_SIZE);
			g.setColor(Color.yellow);
			g.fillOval(UNIT_SIZE*t.getCoord().get(0),UNIT_SIZE*t.getCoord().get(1),UNIT_SIZE,UNIT_SIZE);
			g.setColor(Color.gray);
			g.fillOval(UNIT_SIZE*f.getCoord().get(0),UNIT_SIZE*f.getCoord().get(1),UNIT_SIZE,UNIT_SIZE);
			g.setColor(Color.red);
			g.fillOval(UNIT_SIZE*z.getCoord().get(0),UNIT_SIZE*z.getCoord().get(1),UNIT_SIZE,UNIT_SIZE);
		}
		else {
			gameOver(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			checkTresor();
			checkMort();
			f.deplacementFantome(p);
			z.deplacementZombie(p.getPlateau());
			checkTouche();
		}
		repaint();
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
		//Win
		if(win==1) {
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD, 75));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString("C'est gagné", (SCREEN_WIDTH - metrics1.stringWidth("C'est gagné"))/2, SCREEN_HEIGHT/2);
		}
		//Lose
		else {
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD, 75));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("C'est perdu", (SCREEN_WIDTH - metrics2.stringWidth("C'est perdu"))/2,SCREEN_HEIGHT/2);
		}
	}
	
	public void deplacementHero(ArrayList<List<Integer>> plateau, String cmd) {
		ArrayList<Integer> tesCoord;
		tesCoord = h.position(cmd);
		if (h.mouvPossible(plateau, tesCoord) == true) {
			h.setCoord(tesCoord);
		}
	}
	
	public void checkTresor() {
		if (t.getCoord().equals(h.getCoord())) {
			running = false;
			win = 1;
		}
	}
	
	public void checkMort() {
		if (h.getVie()==0) {
			running = false;
		}
	}
	
	public void checkTouche() {
		if (z.getCoord().equals(h.getCoord())) {
			h.setVie(h.getVie()-z.getAttaque());
			z.setVie(z.getVie()-h.getAttaque());
		}
		if (f.getCoord().equals(h.getCoord())) {
			h.setVie(h.getVie()-f.getAttaque());
			f.setVie(f.getVie()-h.getAttaque());
		}
	}
}
