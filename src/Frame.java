import javax.swing.*;

public class Frame extends JFrame {

    // initialize and set up the frame used
    public Frame(Account account){
        AccountPanel accountPanel = new AccountPanel(account);
        this.add(new GamePanel(accountPanel));
        this.add(accountPanel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(750,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}
