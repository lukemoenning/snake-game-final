import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 750;
    static final int SCREEN_HEIGHT = 500;
    static final int UNIT_SIZE = 25;

    LinkedList<Coordinate> snakeCoordinates;
    int snakeSize;
    char direction;
    Coordinate tempTailCoordinate;

    JLabel blueberryImage;
    Coordinate blueberryCoordinate;
    int blueberriesGathered;

    Random random;
    boolean running = false;
    Timer timer;
    static final int DELAY = 75;

    private final AccountPanel accountPanel;

    private JLabel gameOverLabel;
    private JButton playAgainButton;

    // initialize and set up the panel which the game is played on
    public GamePanel(AccountPanel accountPanel){
        this.accountPanel = accountPanel;
        random = new Random();
        this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setBounds(0,50,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        setupEndGame();
        start();


    }


    // starts the game
    public void start(){
        running = true;

        // generates the head of the snake
        snakeCoordinates = new LinkedList<>();
        snakeCoordinates.add(new Coordinate(0,0));
        snakeSize = 1;
        direction = 'R';

        blueberriesGathered = 0;
        generateBlueberry();
        timer = new Timer(DELAY,this);
        timer.start();
    }

    // draw objects on the frame
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    // draw objects on the frame
    public void draw(Graphics g){

        // creates a checkerboard patten on the frame using blue lines
        g.setColor(new Color(135,206,235));

        // draws the horizontal lines of the checkerboard
        for(int i = 0; i <= SCREEN_HEIGHT; i+= UNIT_SIZE){
            g.drawLine(0, i, SCREEN_WIDTH, i);
        }

        //draws the vertical lines of the checkerboard
        for(int i = 0; i <= SCREEN_WIDTH; i+= UNIT_SIZE){
            g.drawLine(i,0, i, SCREEN_HEIGHT);
        }


        //draws the snake on GamePanel
        for (Coordinate c: snakeCoordinates){
            g.setColor(Color.CYAN);
            g.fillRect(c.getX(),c.getY(),UNIT_SIZE,UNIT_SIZE);
            System.out.println(c.getX() +", " + c.getY());
        }

    }

    // checks the input and modifies snakeCoordinates in the corresponding way
    public void move(){

        // duplicates the head of the snake
        // the first and second node are the same and are equal to the prior head
        Coordinate c = new Coordinate(snakeCoordinates.get(0).getX(), snakeCoordinates.get(0).getY());
        snakeCoordinates.add(0,c);

        // temporarily stores the tail of the snake then removes it
        // will be added back if a blueberry is gathered
        tempTailCoordinate = snakeCoordinates.getLast();
        snakeCoordinates.removeLast();

        switch (direction){

            // case for up
            case 'U':
                c.setY(c.getY() - UNIT_SIZE);
                break;

            // case for down
            case 'D':
                c.setY(c.getY() + UNIT_SIZE);
                break;

            // case for right
            case 'R':
                c.setX(c.getX() + UNIT_SIZE);
                break;

            // case for left
            case 'L':
                c.setX(c.getX() - UNIT_SIZE);
                break;
        }

    }

    // generates a blueberry at a random coordinate in GamePanel
    public void generateBlueberry(){

        // generates a random coordinate for the blueberry
        int x = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        int y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        blueberryCoordinate = new Coordinate(x,y);


        // creates a JLabel of the blueberry image scaled to UNIT_SIZE
        Image image = requestImage("images/blueberry.png", UNIT_SIZE,UNIT_SIZE);
        blueberryImage = new JLabel(new ImageIcon(image));

        // adds the JLabel to the panel at the random coordinate generated
        blueberryImage.setBounds(blueberryCoordinate.getX(),blueberryCoordinate.getY(),UNIT_SIZE,UNIT_SIZE);
        blueberryImage.setVisible(true);
        this.add(blueberryImage);
        System.out.println("blueberry: "+blueberryCoordinate.getX()+", "+blueberryCoordinate.getY());
    }

    // checks to see if the snake's head collided with the blueberry
    public void checkBlueberry(){
        if(snakeCoordinates.get(0).getX() == blueberryCoordinate.getX() &&
                snakeCoordinates.get(0).getY() == blueberryCoordinate.getY()){
            gatherBlueberry();
        }
    }

    // removes the previous blueberry
    // generates a new blueberry
    // increments the score
    // increases the length of the snake by one
    public void gatherBlueberry(){
        removeBlueberry();
        blueberriesGathered++;
        accountPanel.updateScore(blueberriesGathered*100);
        generateBlueberry();

        snakeSize++;
        snakeCoordinates.add(tempTailCoordinate);
    }

    public void removeBlueberry(){
        this.remove(blueberryImage);
    }


    // checks all collisions which would result in endGame
    public void checkCollisions(){

        // checks if the snakes head is colliding with its body
        // allows one collision for the head colliding with itself
        AtomicInteger collisions = new AtomicInteger(0);
        for (Coordinate c: snakeCoordinates){
            if((snakeCoordinates.get(0).getX() == c.getX()) && (snakeCoordinates.get(0).getY() == c.getY())){
                collisions.getAndIncrement();
            }
            if(collisions.get() == 2){
                running = false;
            }
        }

        // checks if the snake has collided with the border
        if(snakeCoordinates.get(0).getX()<0 ||
                snakeCoordinates.get(0).getX()>SCREEN_WIDTH ||
                snakeCoordinates.get(0).getY()<0 ||
                snakeCoordinates.get(0).getY()>SCREEN_HEIGHT){
            running = false;
        }

        // stops the timer if there is a collision
        if(!running) {
            timer.stop();
        }
    }

    // creates the UI for when the game has ended
    public void setupEndGame(){
        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("italic",Font.ITALIC,50));
        gameOverLabel.setForeground(new Color(125,206,235));
        gameOverLabel.setBounds(237,200,350,50);
        this.add(gameOverLabel);
        gameOverLabel.setVisible(false);


        playAgainButton = new JButton("Play Again");
        playAgainButton.setForeground(new Color(125,206,235));
        playAgainButton.setBounds((int) (0.65 * SCREEN_WIDTH), (int) (0.75 * SCREEN_HEIGHT),200,50);
        this.add(playAgainButton);
        playAgainButton.addActionListener(this);
        playAgainButton.setVisible(false);
    }


    public void endGame(){
        gameOverLabel.setVisible(true);
        playAgainButton.setVisible(true);
    }

    // requests an image to be returned from the given file name
    // throws and IOException of the file is not located
    // scales the image to the inputted width and height
    public Image requestImage(String fileName, int width, int height) {

        Image image = null;
        Image scaledImage = null;

        try {
            image = ImageIO.read(new File(fileName));
            scaledImage = image.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // action if the playAgainButton is pressed
        // sets the endGame Labels to visible
        // resets the score
        if (e.getSource() == playAgainButton){
            gameOverLabel.setVisible(false);
            playAgainButton.setVisible(false);
            accountPanel.updateScore(0);
            start();
        }

        if(running){
            move();
            checkBlueberry();
            checkCollisions();
        }

        if(!running){
            removeBlueberry();
            endGame();
        }

        repaint();

    }


    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent key){

            // reads input from both arrow keys and WASD
            // changes direction based on the input
            // four cases for each direction: up, down, left, right
            // prevents 180 degree turns which would result in collisions
            switch (key.getKeyCode()){

                // case for up and W
                case 38:
                case 87:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;

                // case for down and S
                case 40:
                case 83:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;

                // case for left and A
                case 37:
                case 65:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;

                // case for right and D
                case 39:
                case 68:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
            }

        }
    }



}
