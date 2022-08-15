import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class RegisterAccount implements ActionListener {

    private final JFrame FRAME_REGISTER_ACCOUNT;
    private final JPanel PANEL_REGISTER_ACCOUNT;
    private final JTextField ACCESS_CODE_TEXT_FIELD;
    private final JTextField USERNAME_TEXT_FIELD;
    private final JPasswordField PASSWORD_FIELD;
    private final JPasswordField CONFIRM_PASSWORD_FIELD;
    private final JButton REGISTER_ACCOUNT_BUTTON;
    private final JButton BACK_BUTTON_REGISTER_ACCOUNT;
    private final JLabel LABEL_INVALID_ACCESS_CODE;
    private final JLabel LABEL_MISMATCHED_PASSWORDS;
    private final JLabel LABEL_INCOMPLETE_FIELDS;
    private final JLabel LABEL_USABLE_ACCESS_CODE;
    private final String USABLE_CODE;
    private final String EMAIL;
    private final JLabel LABEL_EXISTING_USERNAME;
    private boolean EXISTING_USERNAME;

    public RegisterAccount(JFrame frame, RegisterEmail registerEmail, String email){

        FRAME_REGISTER_ACCOUNT = frame;
        EMAIL = email;


        // initialize and set up PANEL_REGISTER_ACCOUNT
        PANEL_REGISTER_ACCOUNT = new JPanel();
        PANEL_REGISTER_ACCOUNT.setBackground(Color.BLACK);
        PANEL_REGISTER_ACCOUNT.setBorder(BorderFactory.createEmptyBorder());
        PANEL_REGISTER_ACCOUNT.setSize(750,300);
        PANEL_REGISTER_ACCOUNT.setLayout(null);
        PANEL_REGISTER_ACCOUNT.setVisible(true);



        // initialize and set up accessCodeLabel
        JLabel accessCodeLabel = new JLabel("Access Code: ");
        accessCodeLabel.setForeground(new Color(125,206,235));
        accessCodeLabel.setBounds(50,50,100,25);
        PANEL_REGISTER_ACCOUNT.add(accessCodeLabel);


        // initialize and set up ACCESS_CODE_TEXT_FIELD
        ACCESS_CODE_TEXT_FIELD = new JTextField();
        ACCESS_CODE_TEXT_FIELD.setBounds(175,50,200,25);
        PANEL_REGISTER_ACCOUNT.add(ACCESS_CODE_TEXT_FIELD);


        // initialize and set up username
        JLabel username = new JLabel("Username: ");
        username.setForeground(new Color(125,206,235));
        username.setBounds(400,50,100,25);
        PANEL_REGISTER_ACCOUNT.add(username);


        // initialize and set up USERNAME_TEXT_FIELd
        USERNAME_TEXT_FIELD = new JTextField();
        USERNAME_TEXT_FIELD.setBounds(525,50,200,25);
        PANEL_REGISTER_ACCOUNT.add(USERNAME_TEXT_FIELD);


        // initialize and set up passwordLabel
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(new Color(125,206,235));
        passwordLabel.setBounds(50,100,100,25);
        PANEL_REGISTER_ACCOUNT.add(passwordLabel);


        // initialize and set up PASSWORD_FIELD
        PASSWORD_FIELD = new JPasswordField();
        PASSWORD_FIELD.setBounds(175,100,200,25);
        PANEL_REGISTER_ACCOUNT.add(PASSWORD_FIELD);


        // initialize and set up confirmPasswordLabel
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setForeground(new Color(125,206,235));
        confirmPasswordLabel.setBounds(400,100,100,25);
        PANEL_REGISTER_ACCOUNT.add(confirmPasswordLabel);


        // initialize and set up CONFIRM_PASSWORD_FIELD
        CONFIRM_PASSWORD_FIELD = new JPasswordField();
        CONFIRM_PASSWORD_FIELD.setBounds(525,100,200,25);
        PANEL_REGISTER_ACCOUNT.add(CONFIRM_PASSWORD_FIELD);


        // initialize and set up REGISTER_ACCOUNT_BUTTON
        REGISTER_ACCOUNT_BUTTON = new JButton("Register Account");
        REGISTER_ACCOUNT_BUTTON.setBounds(75,200,125,50);
        REGISTER_ACCOUNT_BUTTON.setForeground(new Color(125,206,235));
        PANEL_REGISTER_ACCOUNT.add(REGISTER_ACCOUNT_BUTTON);
        REGISTER_ACCOUNT_BUTTON.addActionListener(this);


        // initialize and set up BACK_BUTTON_REGISTER_ACCOUNT
        BACK_BUTTON_REGISTER_ACCOUNT = new JButton("Back");
        BACK_BUTTON_REGISTER_ACCOUNT.setBounds(600,200,75,50);
        BACK_BUTTON_REGISTER_ACCOUNT.setForeground(new Color(125,206,235));
        PANEL_REGISTER_ACCOUNT.add(BACK_BUTTON_REGISTER_ACCOUNT);
        BACK_BUTTON_REGISTER_ACCOUNT.addActionListener(this);


        // initialize and set up LABEL_USABLE_ACCESS_CODE
        USABLE_CODE = createAccessCode();
        LABEL_USABLE_ACCESS_CODE = new JLabel("The access code sent to "+ email +" is "+USABLE_CODE);
        LABEL_USABLE_ACCESS_CODE.setForeground(new Color(125,206,235));
        LABEL_USABLE_ACCESS_CODE.setBounds(75,175,750,25);
        LABEL_USABLE_ACCESS_CODE.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_ACCOUNT.add(LABEL_USABLE_ACCESS_CODE);
        LABEL_USABLE_ACCESS_CODE.setVisible(true);


        // initialize and set up LABEL_EXISTING_EMAIL
        LABEL_EXISTING_USERNAME = new JLabel("The username you have entered is already in use.");
        LABEL_EXISTING_USERNAME.setForeground(new Color(125,206,235));
        LABEL_EXISTING_USERNAME.setBounds(75,175,500,25);
        LABEL_EXISTING_USERNAME.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_ACCOUNT.add(LABEL_EXISTING_USERNAME);
        LABEL_EXISTING_USERNAME.setVisible(false);


        // initialize and set up LABEL_INVALID_ACCESS_CODE
        LABEL_INVALID_ACCESS_CODE = new JLabel("The pin you have entered is invalid, try again.");
        LABEL_INVALID_ACCESS_CODE.setForeground(new Color(125,206,235));
        LABEL_INVALID_ACCESS_CODE.setBounds(75,175,500,25);
        LABEL_INVALID_ACCESS_CODE.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_ACCOUNT.add(LABEL_INVALID_ACCESS_CODE);
        LABEL_INVALID_ACCESS_CODE.setVisible(false);


        // initialize and set up LABEL_MISMATCHED_PASSWORDS
        LABEL_MISMATCHED_PASSWORDS = new JLabel("The passwords you have entered are not the same, try again.");
        LABEL_MISMATCHED_PASSWORDS.setForeground(new Color(125,206,235));
        LABEL_MISMATCHED_PASSWORDS.setBounds(75,175,500,25);
        LABEL_MISMATCHED_PASSWORDS.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_ACCOUNT.add(LABEL_MISMATCHED_PASSWORDS);
        LABEL_MISMATCHED_PASSWORDS.setVisible(false);


        // initialize and set up LABEL_INCOMPLETE_FIELDS
        LABEL_INCOMPLETE_FIELDS = new JLabel("You have not completed all the required fields, try again.");
        LABEL_INCOMPLETE_FIELDS.setForeground(new Color(125,206,235));
        LABEL_INCOMPLETE_FIELDS.setBounds(75,175,500,25);
        LABEL_INCOMPLETE_FIELDS.setFont(new Font("italic",Font.ITALIC,16));
        PANEL_REGISTER_ACCOUNT.add(LABEL_INCOMPLETE_FIELDS);
        LABEL_INCOMPLETE_FIELDS.setVisible(false);


        // add PANEL_REGISTER_ACCOUNT to existing frame
        FRAME_REGISTER_ACCOUNT.add(PANEL_REGISTER_ACCOUNT);
        FRAME_REGISTER_ACCOUNT.setTitle("Register a new account");
        registerEmail.registerEmailPanelVisibility(false);

    }

    // sets account error messages to not visible
    public void setAccountErrorMessagesFalse() {
        LABEL_USABLE_ACCESS_CODE.setVisible(false);
        LABEL_INVALID_ACCESS_CODE.setVisible(false);
        LABEL_MISMATCHED_PASSWORDS.setVisible(false);
        LABEL_INCOMPLETE_FIELDS.setVisible(false);
        LABEL_EXISTING_USERNAME.setVisible(false);
    }

    // sets the visibility of the PANEL_REGISTER_ACCOUNT
    public void registerAccountPanelVisibility(boolean visibility){
        PANEL_REGISTER_ACCOUNT.setVisible(visibility);
    }

    // creates access code
    public String createAccessCode(){
        int temp = (int) Math.pow(10,5);
        return String.valueOf(temp + new Random().nextInt(9 * temp));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // action for BACK_BUTTON_REGISTER_ACCOUNT
        if (e.getSource() == BACK_BUTTON_REGISTER_ACCOUNT) {
            FRAME_REGISTER_ACCOUNT.dispose();
            new RegisterEmail();
        }



        // action for REGISTER_ACCOUNT_BUTTON
        if (e.getSource() == REGISTER_ACCOUNT_BUTTON) {


            // checks to see if username is already in use
            try {
                EXISTING_USERNAME = false;
                ArrayList<Account> accounts = Account.getAccounts();
                for (Account account : accounts) {
                    if (account.getUsername().equalsIgnoreCase(USERNAME_TEXT_FIELD.getText().toLowerCase())) {
                        EXISTING_USERNAME = true;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (EXISTING_USERNAME) {
                setAccountErrorMessagesFalse();
                LABEL_EXISTING_USERNAME.setVisible(true);


                // if the username is not already in use
            } else {


                // checks if all fields are filled out
                if (ACCESS_CODE_TEXT_FIELD.getText().isBlank() || USERNAME_TEXT_FIELD.getText().isBlank() ||
                        PASSWORD_FIELD.getPassword() == null || CONFIRM_PASSWORD_FIELD.getPassword() == null) {
                    setAccountErrorMessagesFalse();
                    LABEL_INCOMPLETE_FIELDS.setVisible(true);
                }

                // checks if valid access code
                else if (!ACCESS_CODE_TEXT_FIELD.getText().equals(USABLE_CODE)) {
                    setAccountErrorMessagesFalse();
                    LABEL_INVALID_ACCESS_CODE.setVisible(true);
                }

                // checks if matching passwords
                else if (!(Arrays.equals(PASSWORD_FIELD.getPassword(), CONFIRM_PASSWORD_FIELD.getPassword()))) {
                    setAccountErrorMessagesFalse();
                    LABEL_MISMATCHED_PASSWORDS.setVisible(true);
                }

                // successfully registers account
                else {
                    String password = new String(PASSWORD_FIELD.getPassword());
                    new Account(EMAIL, USERNAME_TEXT_FIELD.getText(), password);
                    new Snake(USERNAME_TEXT_FIELD.getText());
                    FRAME_REGISTER_ACCOUNT.dispose();
                }
            }
        }
    }
}
