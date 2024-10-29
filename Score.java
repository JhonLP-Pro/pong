import java.awt.*;

public class Score {
    int player1Score;
    int player2Score;
    int gameWidth;
    int gameHeight;
    
    public Score(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.player1Score = 0;
        this.player2Score = 0;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        
        g.drawString(String.valueOf(player1Score), gameWidth/4, 50);
        g.drawString(String.valueOf(player2Score), 3*gameWidth/4, 50);
        
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString("Premier à 3 points", gameWidth/2 - 80, 30);
    }
} 