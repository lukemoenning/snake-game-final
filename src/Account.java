import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;




public class Account {
    private String EMAIL;
    private String USERNAME;
    private String PASSWORD;
    private String DATE_CREATED;
    private String HIGH_SCORE;
    private String GAMES_PLAYED;
    private static final String filePath = "account_data.csv";


    // constructor to write the new accounts to the CSV file
    public Account (String email, String username, String password){

        // initialize the file reference
        File csvFile = new File(filePath);

        try {
            // initialize and set up the FileWriter and CSVWriter
            FileWriter fileWriter = new FileWriter(csvFile,true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // creates the account as a String[]
            String dateCreated = String.valueOf(java.time.LocalDate.now());
            String[] accountData = {email, username, password, "0", "0", dateCreated};

            // writes the account into the account_data.csv file
            csvWriter.writeNext(accountData);

            // closes the csvWriter
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // constructor to read account from the CSV file
    public Account(CSVRecord file){
        EMAIL = file.get("EMAIL");
        USERNAME = file.get("USERNAME");
        PASSWORD = file.get("PASSWORD");
        DATE_CREATED = file.get("DATE_CREATED");
        HIGH_SCORE = file.get("HIGH_SCORE");
        GAMES_PLAYED = file.get("GAMES_PLAYED");
    }

    public static ArrayList<Account> getAccounts() throws IOException {
        CSVParser parser;
        parser = CSVFormat.EXCEL.withHeader().parse(new FileReader((filePath)));

        ArrayList<Account> accounts = new ArrayList<>();

        for (CSVRecord data : parser.getRecords()) {
            accounts.add(new Account(data));
        }
        return accounts;
    }



    public void updateHighScore(int newHighScore) {

    }



    // getters for all account variables
    public String getEmail(){
        return EMAIL;
    }
    public String getUsername(){
        return USERNAME;
    }
    public String getPassword(){
        return PASSWORD;
    }
    public String getDateCreated(){
        return DATE_CREATED;
    }
    public String getHighScore(){
        return HIGH_SCORE;
    }
    public String getGamesPlayed(){
        return GAMES_PLAYED;
    }



}
