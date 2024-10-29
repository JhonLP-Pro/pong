import java.awt.*;

public class Ball extends Rectangle {
    int xVelocity;
    int yVelocity;
    
    public Ball(int x, int y, int size) {
        super(x, y, size, size);
        xVelocity = 5;
        yVelocity = 2;
    }
    
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
} 