import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 60;
    private static final int BALL_SIZE = 20;
    
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;
    private boolean vsAI;
    private Timer timer;
    private Random random;
    
    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new PaddleController());
        
        initGame();
    }
    
    private void initGame() {
        // Demander si le joueur veut jouer contre l'IA
        int choice = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous jouer contre l'IA ?", 
            "Mode de jeu", 
            JOptionPane.YES_NO_OPTION);
        vsAI = (choice == JOptionPane.YES_OPTION);
        
        paddle1 = new Paddle(0, GAME_HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, GAME_HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Ball(GAME_WIDTH/2 - BALL_SIZE/2, GAME_HEIGHT/2 - BALL_SIZE/2, BALL_SIZE);
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        
        timer = new Timer(16, this); // environ 60 FPS
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkCollision();
        repaint();
    }
    
    private void move() {
        paddle1.move();
        if (vsAI) {
            moveAI();
        } else {
            paddle2.move();
        }
        ball.move();
    }
    
    private void moveAI() {
        if (ball.y > paddle2.y + paddle2.height/2) {
            paddle2.yVelocity = 5;
        } else if (ball.y < paddle2.y + paddle2.height/2) {
            paddle2.yVelocity = -5;
        }
        paddle2.move();
    }
    
    private void checkCollision() {
        // Collision avec les bords
        if (ball.y <= 0 || ball.y >= GAME_HEIGHT - BALL_SIZE) {
            ball.yVelocity = -ball.yVelocity;
        }
        
        // Collision avec les paddles
        if (ball.intersects(paddle1) || ball.intersects(paddle2)) {
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity *= 1.1; // Augmente la vitesse
            
            // Ajout d'une direction aléatoire pour le rebond vertical
            ball.yVelocity = random.nextInt(21) - 10; // Génère une valeur entre -10 et 10
            
            // Assure une vitesse minimale verticale pour éviter les rebonds trop plats
            if (Math.abs(ball.yVelocity) < 2) {
                ball.yVelocity = (ball.yVelocity > 0) ? 2 : -2;
            }
        }
        
        // Points et vérification de la victoire
        if (ball.x <= 0) {
            score.player2Score++;
            if (score.player2Score >= 3) {
                gameOver("Joueur 2 gagne!");
            } else {
                resetBall();
            }
        }
        if (ball.x >= GAME_WIDTH - BALL_SIZE) {
            score.player1Score++;
            if (score.player1Score >= 3) {
                gameOver("Joueur 1 gagne!");
            } else {
                resetBall();
            }
        }
        
        // Limites des paddles
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
    }
    
    private void resetBall() {
        ball.x = GAME_WIDTH/2 - BALL_SIZE/2;
        ball.y = GAME_HEIGHT/2 - BALL_SIZE/2;
        ball.xVelocity = (random.nextBoolean() ? 1 : -1) * 5;
        ball.yVelocity = random.nextInt(4) - 2;
    }
    
    private void gameOver(String message) {
        timer.stop();
        JOptionPane.showMessageDialog(this, message);
        int choice = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous rejouer ?", 
            "Partie terminée", 
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }
    
    private void resetGame() {
        score.player1Score = 0;
        score.player2Score = 0;
        resetBall();
        paddle1.y = GAME_HEIGHT/2 - PADDLE_HEIGHT/2;
        paddle2.y = GAME_HEIGHT/2 - PADDLE_HEIGHT/2;
        timer.start();
    }
    
    private class PaddleController extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    paddle1.yVelocity = -5;
                    break;
                case KeyEvent.VK_S:
                    paddle1.yVelocity = 5;
                    break;
                case KeyEvent.VK_UP:
                    if (!vsAI) paddle2.yVelocity = -5;
                    break;
                case KeyEvent.VK_DOWN:
                    if (!vsAI) paddle2.yVelocity = 5;
                    break;
            }
        }
        
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                    paddle1.yVelocity = 0;
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    if (!vsAI) paddle2.yVelocity = 0;
                    break;
            }
        }
    }
} 