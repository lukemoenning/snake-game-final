import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Launcher implements ActionListener {

    // 
    // main method which opens Launcher
    //
    
    public static void main (String[] args) throws IOException {
        new Launcher();
    }




    private final JFrame FRAME_LAUNCHER;
    private final JButton SIGN_IN_BUTTON;
    private final JButton REGISTER_ACCOUNT_BUTTON;
    private final JButton GUEST_BUTTON;
    private final JPanel PANEL_LAUNCHER;


    public Launcher(){

        // initialize and set up PANEL_LAUNCHER
        PANEL_LAUNCHER = new JPanel();
        PANEL_LAUNCHER.setBorder(BorderFactory.createEmptyBorder());
        PANEL_LAUNCHER.setBackground(Color.WHITE);
        PANEL_LAUNCHER.setSize(400,300);
        PANEL_LAUNCHER.setLayout(null);


        // initialize and set up welcomeLabel
        JLabel welcomeLabel = new JLabel("Welcome to Snake Game!");
        welcomeLabel.setBounds(42,50,350,50);
        welcomeLabel.setFont(new Font("bold",Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(135, 206, 235));
        PANEL_LAUNCHER.add(welcomeLabel);


        // initialize and set up SIGN_IN_BUTTON
        SIGN_IN_BUTTON = new JButton("Sign In");
        SIGN_IN_BUTTON.setBounds(50,130,125,50);
        SIGN_IN_BUTTON.setForeground(new Color(135, 206, 235));
        PANEL_LAUNCHER.add(SIGN_IN_BUTTON);
        SIGN_IN_BUTTON.addActionListener(this);


        // initialize and set up REGISTER_ACCOUNT_BUTTON
        REGISTER_ACCOUNT_BUTTON = new JButton("Register Account");
        REGISTER_ACCOUNT_BUTTON.setBounds(225, 130,125,50);
        REGISTER_ACCOUNT_BUTTON.setForeground(new Color(135, 206, 235));
        PANEL_LAUNCHER.add(REGISTER_ACCOUNT_BUTTON);
        REGISTER_ACCOUNT_BUTTON.addActionListener(this);


        // initialize and set up GUEST_BUTTON
        GUEST_BUTTON = new JButton("Sign in as Guest");
        GUEST_BUTTON.setBounds(137, 200, 125, 50);
        GUEST_BUTTON.setForeground(new Color(135, 206, 235));
        PANEL_LAUNCHER.add(GUEST_BUTTON);
        GUEST_BUTTON.addActionListener(this);


        // initialize and set up FRAME_LAUNCHER
        FRAME_LAUNCHER = new JFrame("Snake Game Launcher");
        FRAME_LAUNCHER.add(PANEL_LAUNCHER);
        FRAME_LAUNCHER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME_LAUNCHER.pack();
        FRAME_LAUNCHER.setSize(400, 300);
        FRAME_LAUNCHER.setLocationRelativeTo(null);
        FRAME_LAUNCHER.setVisible(true);


    }


    // sets the visibility of the LAUNCHER_PANEL
    public void launcherVisibility(boolean visibility){
        PANEL_LAUNCHER.setVisible(visibility);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // action for SIGN_IN_BUTTON
        // creates new SignIn with the current frame as the input to use the same frame
        // sets the startupPanel visibility to false
        if (e.getSource() == SIGN_IN_BUTTON){
            new SignIn(FRAME_LAUNCHER, this);
            PANEL_LAUNCHER.setVisible(false);
        }

        // action for the REGISTER_ACCOUNT_BUTTON
        // creates new RegisterEmail
        // closes the current frame
        if (e.getSource() == REGISTER_ACCOUNT_BUTTON){
            new RegisterEmail();
            FRAME_LAUNCHER.dispose();
        }

        // action for GUEST_BUTTON
        // creates a new game using a guest account
        if (e.getSource() == GUEST_BUTTON){
            new Snake("Guest");
        }
    }
}
