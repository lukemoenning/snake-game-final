import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class RegisterEmail implements ActionListener {
    private final JFrame FRAME_REGISTER_EMAIL;
    private final JPanel PANEL_REGISTER_EMAIL;
    private final JTextField EMAIL_TEXT_FIELD;
    private final JTextField CONFIRM_EMAIL_TEXT_FIELD;
    private final JButton REGISTER_EMAIL_BUTTON;
    private final JButton BACK_BUTTON_REGISTER_EMAIL;
    private final JLabel LABEL_INVALID_EMAIL;
    private final JLabel LABEL_MISMATCHED_EMAILS;
    private final JLabel LABEL_EXISTING_EMAIL;
    private boolean EXISTING_EMAIL;


    public RegisterEmail(){


        // initialize and set up PANEL_REGISTER_EMAIL
        PANEL_REGISTER_EMAIL = new JPanel();
        PANEL_REGISTER_EMAIL.setBackground(Color.WHITE);
        PANEL_REGISTER_EMAIL.setBorder(BorderFactory.createEmptyBorder());
        PANEL_REGISTER_EMAIL.setSize(750,300);
        PANEL_REGISTER_EMAIL.setLayout(null);


        // initialize and set up emailLabel
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setForeground(new Color(125,206,235));
        emailLabel.setBounds(50,75,100,25);
        PANEL_REGISTER_EMAIL.add(emailLabel);


        // initialize and set up EMAIL_TEXT_FIELD
        EMAIL_TEXT_FIELD = new JTextField();
        EMAIL_TEXT_FIELD.setBounds(175,75,200,25);
        PANEL_REGISTER_EMAIL.add(EMAIL_TEXT_FIELD);


        // initialize and set up confirmEmailLabel
        JLabel confirmEmailLabel = new JLabel("Confirm Email: ");
        confirmEmailLabel.setForeground(new Color(125,206,235));
        confirmEmailLabel.setBounds(400,75,100,25);
        PANEL_REGISTER_EMAIL.add(confirmEmailLabel);


        // initialize and set up CONFIRM_EMAIL_TEXT_FIELD
        CONFIRM_EMAIL_TEXT_FIELD = new JTextField();
        CONFIRM_EMAIL_TEXT_FIELD.setBounds(525,75,200,25);
        PANEL_REGISTER_EMAIL.add(CONFIRM_EMAIL_TEXT_FIELD);


        // initialize and set up LABEL_INVALID_EMAIL
        LABEL_INVALID_EMAIL = new JLabel("The email you have entered is invalid, try again.");
        LABEL_INVALID_EMAIL.setForeground(new Color(125,206,235));
        LABEL_INVALID_EMAIL.setBounds(75,175,500,25);
        LABEL_INVALID_EMAIL.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_EMAIL.add(LABEL_INVALID_EMAIL);
        LABEL_INVALID_EMAIL.setVisible(false);


        // initialize and set up LABEL_MISMATCHED_EMAILS
        LABEL_MISMATCHED_EMAILS = new JLabel("The emails you have entered are not the same, try again.");
        LABEL_MISMATCHED_EMAILS.setForeground(new Color(125,206,235));
        LABEL_MISMATCHED_EMAILS.setBounds(75,175,500,25);
        LABEL_MISMATCHED_EMAILS.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_EMAIL.add(LABEL_MISMATCHED_EMAILS);
        LABEL_MISMATCHED_EMAILS.setVisible(false);


        // initialize and set up LABEL_EXISTING_EMAIL
        LABEL_EXISTING_EMAIL = new JLabel("The email you have entered is already in use.");
        LABEL_EXISTING_EMAIL.setForeground(new Color(125,206,235));
        LABEL_EXISTING_EMAIL.setBounds(75,175,500,25);
        LABEL_EXISTING_EMAIL.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_EMAIL.add(LABEL_EXISTING_EMAIL);
        LABEL_EXISTING_EMAIL.setVisible(false);


        // initialize and set up REGISTER_EMAIL_BUTTON
        REGISTER_EMAIL_BUTTON = new JButton("Register Email");
        REGISTER_EMAIL_BUTTON.setBounds(75,200,125,50);
        REGISTER_EMAIL_BUTTON.setForeground(new Color(125,206,235));
        PANEL_REGISTER_EMAIL.add(REGISTER_EMAIL_BUTTON);
        REGISTER_EMAIL_BUTTON.addActionListener(this);


        // initialize and set up BACK_BUTTON_REGISTER_EMAIL
        BACK_BUTTON_REGISTER_EMAIL = new JButton("Back");
        BACK_BUTTON_REGISTER_EMAIL.setBounds(600,200,75,50);
        BACK_BUTTON_REGISTER_EMAIL.setForeground(new Color(125,206,235));
        PANEL_REGISTER_EMAIL.add(BACK_BUTTON_REGISTER_EMAIL);
        BACK_BUTTON_REGISTER_EMAIL.addActionListener(this);



        // set up the frame
        FRAME_REGISTER_EMAIL = new JFrame("Register a new email");
        FRAME_REGISTER_EMAIL.add(PANEL_REGISTER_EMAIL);
        FRAME_REGISTER_EMAIL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME_REGISTER_EMAIL.pack();
        FRAME_REGISTER_EMAIL.setSize(750,300);
        FRAME_REGISTER_EMAIL.setLocationRelativeTo(null);
        FRAME_REGISTER_EMAIL.setVisible(true);
    }




    // sets the error messages to not visible
    public void setEmailErrorMessagesFalse() {
        LABEL_INVALID_EMAIL.setVisible(false);
        LABEL_MISMATCHED_EMAILS.setVisible(false);
        LABEL_EXISTING_EMAIL.setVisible(false);
    }


    // sets the visibility of the PANEL_REGISTER_EMAIL
    public void registerEmailPanelVisibility(boolean visibility){
        PANEL_REGISTER_EMAIL.setVisible(visibility);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // action for BACK_BUTTON_REGISTER_EMAIL
        if (e.getSource() == BACK_BUTTON_REGISTER_EMAIL) {
            new Launcher();
            FRAME_REGISTER_EMAIL.dispose();
        }


        // action for REGISTER_EMAIL_BUTTON
        if (e.getSource() == REGISTER_EMAIL_BUTTON) {

            // checks to see if email is already in use
            try {
                EXISTING_EMAIL = false;
                ArrayList<Account> accounts = Account.getAccounts();
                for (Account account : accounts) {
                    if (account.getEmail().equals(EMAIL_TEXT_FIELD.getText().toLowerCase())) {
                        EXISTING_EMAIL = true;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (EXISTING_EMAIL) {
                setEmailErrorMessagesFalse();
                LABEL_EXISTING_EMAIL.setVisible(true);


            // if the email is not already in use
            } else {

                // checks if valid email
                if (!(EMAIL_TEXT_FIELD.getText().contains("@")) || !(CONFIRM_EMAIL_TEXT_FIELD.getText().contains("@"))) {
                    setEmailErrorMessagesFalse();
                    LABEL_INVALID_EMAIL.setVisible(true);
                }

                // checks if matching emails
                else if (!EMAIL_TEXT_FIELD.getText().equalsIgnoreCase(CONFIRM_EMAIL_TEXT_FIELD.getText())) {
                    setEmailErrorMessagesFalse();
                    LABEL_MISMATCHED_EMAILS.setVisible(true);
                }

                // successfully registers email
                else {
                    new RegisterAccount(FRAME_REGISTER_EMAIL, this, EMAIL_TEXT_FIELD.getText().toLowerCase());
                }
            }
        }
    }
}
