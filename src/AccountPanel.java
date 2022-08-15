import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    private final Account ACCOUNT;
    private JLabel USERNAME_LABEL;
    private JLabel SCORE_LABEL;
    //private JLabel HIGH_SCORE_LABEL;


    // creates a panel where account information can be displayed
    public AccountPanel(Account account){
        ACCOUNT = account;

        this.setSize(750,50);
        this.setBackground(new Color(135,206,235));
        this.setBounds(0,0,750,50);
        this.setLayout(null);
        this.setVisible(true);
        this.displayUsername();
        this.displayScore();
        //this.displayHighScore();
    }


    // initialize and set up USERNAME_LABEL
    public void displayUsername(){
        USERNAME_LABEL = new JLabel(ACCOUNT.getUsername());
        USERNAME_LABEL.setBounds(20,10,300,30);
        USERNAME_LABEL.setFont(new Font("italic", Font.ITALIC,20));
        USERNAME_LABEL.setForeground(Color.WHITE);
        USERNAME_LABEL.setVisible(true);
        this.add(USERNAME_LABEL);
    }

/*
    // initialize and set up HIGH_SCORE_LABEL
    public void displayHighScore(){
        int highScore = Integer.parseInt(ACCOUNT.getHighScore());
        HIGH_SCORE_LABEL = new JLabel("High Score: " + String.format("%0" + 6 + "d", highScore));
        HIGH_SCORE_LABEL.setBounds(525,10,200,30);
        HIGH_SCORE_LABEL.setFont(new Font("italic", Font.ITALIC,20));
        HIGH_SCORE_LABEL.setForeground(Color.WHITE);
        HIGH_SCORE_LABEL.setVisible(true);
        this.add(HIGH_SCORE_LABEL);
    }

    // update HIGH_SCORE_LABEL with a new high score
    public void updateHighScore(int currentHighScore){
        HIGH_SCORE_LABEL.setText("High Score: " + String.format("%0" + 6 + "d", currentHighScore));
    }


 */
    // initialize and set up SCORE_LABEL
    public void displayScore(){
        SCORE_LABEL = new JLabel("Score: " + String.format("%0" + 6 + "d", 0));
        SCORE_LABEL.setBounds(302,10,200,30);
        SCORE_LABEL.setFont(new Font("italic", Font.ITALIC,20));
        SCORE_LABEL.setForeground(Color.WHITE);
        SCORE_LABEL.setVisible(true);
        this.add(SCORE_LABEL);
    }

    // update SCORE_LABEL with a new score
    public void updateScore(int currentScore){
        SCORE_LABEL.setText("Score: " + String.format("%0" + 6 + "d", currentScore));


/*
        int highScore = Integer.parseInt(ACCOUNT.getHighScore());

        if(currentScore > highScore){
            updateHighScore(currentScore);


 */
        }
    }


