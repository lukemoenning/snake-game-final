import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class SignIn implements ActionListener {
    private final JFrame FRAME_SIGN_IN;
    private final Launcher LAUNCHER;
    private final JPanel PANEL_SIGN_IN;
    private final JButton SIGN_IN_BUTTON;
    private final JButton BACK_BUTTON_SIGN_IN;
    private final JLabel INCORRECT_INFO;
    private final JTextField USERNAME_TEXT_FIELD;
    private final JPasswordField PASSWORD_TEXT_FIELD;


    // takes a frame as a parameter to place the panel on an existing frame
    // takes a Launch as a parameter to change the visibility of the LAUNCH_PANEL

    public SignIn(JFrame FRAME_LAUNCH, Launcher launcher) {
        FRAME_SIGN_IN = FRAME_LAUNCH;
        LAUNCHER = launcher;


        // initialize and set up the panel

        PANEL_SIGN_IN = new JPanel();
        PANEL_SIGN_IN.setBackground(Color.WHITE);
        PANEL_SIGN_IN.setBorder(BorderFactory.createEmptyBorder());
        PANEL_SIGN_IN.setSize(400, 300);
        PANEL_SIGN_IN.setLayout(null);


        // initialize and set up username text

        JLabel username = new JLabel("Username:");
        username.setForeground(new Color(125,206,235));
        username.setBounds(50, 50, 100, 25);
        PANEL_SIGN_IN.add(username);


        // initialize and set up password text

        JLabel password = new JLabel("Password:");
        password.setForeground(new Color(125,206,235));
        password.setBounds(50, 100, 100, 25);
        PANEL_SIGN_IN.add(password);


        // initialize and set up username text field

        USERNAME_TEXT_FIELD = new JTextField();
        USERNAME_TEXT_FIELD.setBounds(150, 50, 175, 25);
        PANEL_SIGN_IN.add(USERNAME_TEXT_FIELD);


        // initialize and set up password text field

        PASSWORD_TEXT_FIELD = new JPasswordField();
        PASSWORD_TEXT_FIELD.setBounds(150, 100, 175, 25);
        PANEL_SIGN_IN.add(PASSWORD_TEXT_FIELD);


        // initialize and set up sign in button

        SIGN_IN_BUTTON = new JButton("Sign In");
        SIGN_IN_BUTTON.setBounds(50, 225, 75, 25);
        SIGN_IN_BUTTON.setForeground(new Color(125,206,235));
        PANEL_SIGN_IN.add(SIGN_IN_BUTTON);
        SIGN_IN_BUTTON.addActionListener(this);


        // initialize and set up incorrect info label

        INCORRECT_INFO = new JLabel("Account does not exist, try again.");
        INCORRECT_INFO.setBounds(50,175,300,25);
        INCORRECT_INFO.setFont(new Font("italic", Font.ITALIC,16));
        INCORRECT_INFO.setForeground(new Color(125,206,235));
        INCORRECT_INFO.setVisible(false);
        PANEL_SIGN_IN.add(INCORRECT_INFO);


        // initialize and set up back button

        BACK_BUTTON_SIGN_IN = new JButton("Back");
        BACK_BUTTON_SIGN_IN.setBounds(300,225,75,25);
        BACK_BUTTON_SIGN_IN.setForeground(new Color(125,206,235));
        PANEL_SIGN_IN.add(BACK_BUTTON_SIGN_IN);
        BACK_BUTTON_SIGN_IN.addActionListener(this);


        // set up the frame
        FRAME_SIGN_IN.add(PANEL_SIGN_IN);

    }

    // sets the visibility of the PANEL_SIGN_IN
    public void signInPanelVisibility(boolean visibility){
        PANEL_SIGN_IN.setVisible(visibility);
    }

    // checks username and password and returns true if they are valid
    public boolean checkSignInCredentials(){
        try {
            ArrayList<Account> accounts = Account.getAccounts();
            for (Account account: accounts){
                if(account.getUsername().equals(USERNAME_TEXT_FIELD.getText()) &&
                account.getPassword().equals(new String(PASSWORD_TEXT_FIELD.getPassword()))){
                    return true;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        // action for SIGN_IN_BUTTON
        // if the username and password are valid initializes a new Space Invaders and closes the current frame
        // if the username and password are not valid resets the text fields and shows incorrect info statement

        if (e.getSource() == SIGN_IN_BUTTON){
            if (checkSignInCredentials()) {
                new Snake(USERNAME_TEXT_FIELD.getText());
                FRAME_SIGN_IN.dispose();
            } else {
                USERNAME_TEXT_FIELD.setText("");
                PASSWORD_TEXT_FIELD.setText("");
                INCORRECT_INFO.setVisible(true);
            }
        }

        // action for BACK_BUTTON_SIGN_IN
        // sets PANEL_SIGN_IN visibility to false and the PANEL_LAUNCH visibility to true
        if (e.getSource() == BACK_BUTTON_SIGN_IN){
            PANEL_SIGN_IN.setVisible(false);
            LAUNCHER.launcherVisibility(true);
        }
    }
}

