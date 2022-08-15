import java.io.IOException;
import java.util.ArrayList;

public class Snake {
    private Account ACCOUNT = null;

    public Snake(String username){


        // sets ACCOUNT equal to the account which has been signed in with
        try {
            ArrayList<Account> accounts = Account.getAccounts();
            for (Account account: accounts){
                if (account.getUsername().equals(username)){
                    ACCOUNT = account;
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }


        // launches the game signed in with the signed in account
        new Frame(ACCOUNT);




    }

    

}
