import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends JPanel implements ActionListener, KeyListener {

    private int ballX = 100;     // Ball's X position
    private int ballY = 100;     // Ball's Y position
    private int ballXSpeed = 2;  // Ball's X direction and speed
    private int ballYSpeed = 2;  // Ball's Y direction and speed

    private int playerY = 150;   // Player's paddle Y position
    private int compY = 150;     // Computer's paddle Y position
    private int paddleHeight = 120;
    private int paddleWidth = 10;

    private boolean upPressed = false;    // Is the 'Up' key pressed
    private boolean downPressed = false;  // Is the 'Down' key pressed

    private Timer timer;

    public PongGame() {
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        // Update ball position
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Ball collisions
        if (ballY <= 0 || ballY >= getHeight() - 20) {
            ballYSpeed = -ballYSpeed;
        }

        if (ballX <= 0) {
            ballXSpeed = 2;
            ballX = 100;
            ballY = 100;
        }

        if (ballX >= getWidth() - 20) {
            ballXSpeed = -ballXSpeed;
        }

        // Player paddle collisions
        if (ballX <= 30 && ballY >= playerY && ballY <= playerY + paddleHeight) {
            ballXSpeed = -ballXSpeed;
        }

        // Computer paddle movement
        if (ballX >= getWidth() - 30) {
            compY = ballY - paddleHeight / 2;
        }

        // Repaint the panel
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.fillRect(10, playerY, paddleWidth, paddleHeight);  // Player paddle
        g.fillRect(getWidth() - 20, compY, paddleWidth, paddleHeight);  // Computer paddle
        g.fillRect(ballX, ballY, 20, 20);  // Ball
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
            if (playerY > 0) {
                playerY -= 10;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
            if (playerY < getHeight() - paddleHeight) {
                playerY += 10;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        PongGame pongGame = new PongGame();
        frame.add(pongGame);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}