import java.awt.*;

public class Paddle extends Rectangle {
    int yVelocity;
    
    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        yVelocity = 0;
    }
    
    public void move() {
        y += yVelocity;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
} 