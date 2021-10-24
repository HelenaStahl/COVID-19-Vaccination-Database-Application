import java.sql.* ;
import java.util.Scanner;

public class VaccineApp {

    static Scanner sc = new Scanner(System.in);
    static int sqlCode=0;      // Variable to hold SQLCODE
    static String sqlState="00000";  // Variable to hold SQLSTATE
    static String your_userid = null;
    static String your_password = null;
    //The url to use for DB2
    static String url = "___";

    public static void main (String[] args) throws SQLException {

        //Register the driver
        try {
            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

        int op = menu();
        if (op == 1) { addPerson(); }
        else if (op == 2) { assignSlot(); }
        else if (op == 3) { vaccInfo(); }
        else if (op == 4) { System.exit(0);
        } else {
            System.out.println("Please enter a number between 1 and 4\n");
            menu();
        }
    }

    //Add a new record into Person table
    public static void addPerson() throws SQLException {

        System.out.println("You have requested to register a new vaccine applicant. Please enter your health insurance number:");
        String hinsurnum = sc.nextLine();

        String input;
        if (hinsurnumCheck(hinsurnum)) {
            System.out.println("Your health insurance number has already been registered. Do you wish to update the existing information? Enter yes or no.");
            input = sc.nextLine().toLowerCase();
            if (input.equals("no")){
                menu();
            } else if (input.equals("yes")) {
                update(hinsurnum);
            }
        }

        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your phone number:");
        String phone = sc.nextLine();
        System.out.println("Enter your date of birth:");
        String dateOfBirth = sc.nextLine();
        System.out.println("Enter your gender:");
        String gender = sc.nextLine();
        System.out.println("Enter your postal code:");
        String postalCode = sc.nextLine();
        System.out.println("Enter your street address:");
        String streetAddress = sc.nextLine();
        System.out.println("Enter the registration date:");
        String registrationDate = sc.nextLine();
        System.out.println("Enter your category:");
        String category = sc.nextLine();
        System.out.println("Enter your priority:");
        int priority = sc.nextInt();


        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( );
        //Inserting Data into the Person table
        try
        {
            String insertSQL = "INSERT INTO Person VALUES ('" + hinsurnum + "','" + name + "','" + phone + "','" + dateOfBirth + "','" + gender + "','" + postalCode + "','" + streetAddress  + "','" + registrationDate  + "','" + category  + "','" + priority + "')";
            statement.executeUpdate ( insertSQL );

        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;
        System.out.println("You have successfully registered as a vaccine applicant.");
        menu();
    }

    //Queries the Person table with the user-inputted health insurance number to see if it exists in the table
    public static boolean hinsurnumCheck(String hinsurnum) throws SQLException {

        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( ) ;
        int count = 0;

        try
        {
            String querySQL = "SELECT COUNT(hinsurnum) from Person WHERE hinsurnum = '" + hinsurnum + "'";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while (rs.next()) {
                count = rs.getInt ( 1 ) ;
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;

        return count > 0;
    }

    //Update an existing record in Person table
    public static void update(String hinsurnum) throws SQLException {

        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your phone number:");
        String phone = sc.nextLine();
        System.out.println("Enter your date of birth:");
        String dateOfBirth = sc.nextLine();
        System.out.println("Enter your gender:");
        String gender = sc.nextLine();
        System.out.println("Enter your postal code:");
        String postalCode = sc.nextLine();
        System.out.println("Enter your street address:");
        String streetAddress = sc.nextLine();
        System.out.println("Enter the registration date:");
        String registrationDate = sc.nextLine();
        System.out.println("Enter your category:");
        String category = sc.nextLine();
        System.out.println("Enter your priority:");
        int priority = sc.nextInt();


        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( );

        //Updating a Person record
        try
        {
            String updateSQL = "UPDATE Person SET name = '" + name + "', phone = '" + phone + "', dateOfBirth = '" + dateOfBirth + "', gender = '" + gender + "', postalCode = '" + postalCode + "', streetAddress = '" + streetAddress  + "', registrationDate = '" + registrationDate  + "', category = '" + category  + "', priority = " + priority + " WHERE hinsurnum = '" + hinsurnum + "'";
            statement.executeUpdate(updateSQL);
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        System.out.println("You have successfully updated your information.");

        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;
        menu();
    }

    //Allocate a vaccination slot for a person
    public static void assignSlot() throws SQLException {
        System.out.println("You have requested to register for a vaccination slot. Please enter your health insurance number:");
        String hinsurnum = sc.nextLine();

        //check if the user is eligible to register
        slotEligibility(hinsurnum);

        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( ) ;
        int count=1;
        String slotNumber = null;
        String time = null;
        String date = null;
        String location = null;

        //slots that are in the future and are not registered to anybody's health insurance number are available for selection
        try
        {
            String querySQL = "SELECT slotNumber, time, date, locationName from SLOT WHERE hinsurnum IS NULL AND date > '2021-03-16'";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            //print all available slots for the user to choose
            while (rs.next()) {
                System.out.print("Slot " + count  + "\t\t");
                slotNumber = rs.getString(1);
                time = rs.getString ( 2 ) ;
                date = rs.getString (3);
                location = rs.getString (4);
                System.out.println ("Slot id: " + slotNumber + " Time:  " + time + " Date:  " + date + " Location: " + location);
                count++;
            }
            if (count == 1) {
                System.out.println("No slots available at the moment.");
                return;
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }

        System.out.println("Which slot would you like to register for? Enter the number:");
        int slotChosen = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the current date in the format YYYY-MM-DD: ");
        String allocateDate = sc.nextLine();
        int option = 0;

        //store the chosen slot information in order to update the Slot table
        try
        {
            String querySQL = "SELECT slotNumber, time, date, locationName from SLOT WHERE hinsurnum IS NULL AND DATE >= '2020-03-16'";
            //System.out.println (querySQL) ;
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while (rs.next()) {
                slotNumber = rs.getString(1);
                time = rs.getString ( 2 ) ;
                date = rs.getString (3);
                location = rs.getString (4);
                option++;
                if (option == slotChosen) {
                    break;
                }
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }

        //Updating Data in the Slot table to reflect user's slot choice
        try
        {
        String updateSQL = "UPDATE Slot SET hinsurnum = '" + hinsurnum + "', allocateDate = '" +  allocateDate + "' WHERE slotNumber = '" + slotNumber + "' AND time = '" + time + "' AND date = '" + date + "' AND locationName = '" + location + "'";
        statement.executeUpdate ( updateSQL );
        }
            catch (SQLException e)
        {
        sqlCode = e.getErrorCode(); // Get SQLCODE
        sqlState = e.getSQLState(); // Get SQLSTATE
        System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        System.out.println(e);
        }
        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;
        System.out.println("You have successfully registered for a slot.");
        menu();
    }

    //determine if the user is eligible to register for a slot
    public static void slotEligibility(String hinsurnum) throws SQLException {

        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( ) ;
        int count = 0;
        int numDoses = 0;
        String vaccineName = null;

        //check if this health insurance number is registered (i.e. there exists a record in Person table)
        if (!hinsurnumCheck(hinsurnum)) {
            System.out.println("Your health insurance number is not in our system. We suggest you register as a new applicant.");
            menu();
        };

        //check if the user has been allocated any slots already
        try
        {
            String querySQL = "SELECT COUNT(*) FROM SLOT WHERE HINSURNUM = '" + hinsurnum + "'";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while(rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0 ) {
                System.out.println("You have yet to be allocated any slot(s). You are eligible to sign up for one.");
                return;
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }

        //this person has been allocated a slot. see which vaccine they received
        try
        {
            String querySQL = "SELECT VACCINENAME FROM SLOT WHERE HINSURNUM = '" + hinsurnum + "'";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while(rs.next()) {
                vaccineName = rs.getString(1);
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }

        //check if the number of vaccinations they have received is less than or equal to the number of doses required for their given vaccine brand
        try
        {
            String querySQL = "SELECT NUMBEROFDOSES FROM VACCINE WHERE VACCINENAME = '" + vaccineName + "'";
            java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

            while(rs.next()) {
                numDoses = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        if (numDoses == count) {
            System.out.println("You have either already received the required number of doses for the " + vaccineName + " vaccine or you are already registered for future slots where you will receive said doses. \nYou are ineligible to register for any slots.");
            menu();
        } else {
            System.out.println("\nYou are eligible to register for a slot as you have only received " + count + " dose(s) of the required " + numDoses + " doses for the " + vaccineName + " vaccine. Please choose a slot:");
            System.out.println();
        }
        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;
    }

    //record in the system that a certain Person was vaccinated and store all relevant information
    public static void vaccInfo() throws SQLException {
        System.out.println("You have selected to record your vaccination information. Please enter your health insurance number:");
        String hinsurnum = sc.nextLine();

        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( ) ;

        System.out.println("Enter the nurse's license number:");
        int licenseNumber = sc.nextInt();
        System.out.println("Enter the vial number:");
        int vialNumber = sc.nextInt();
        System.out.println("Enter the batch number:");
        int batchNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the vaccine name:");
        String vaccineName = sc.nextLine();

        //check if the user has entered the same vaccine as their previous shot
        if  (!checkPrevShot(hinsurnum, vaccineName)){
            System.out.println("The " + vaccineName + " vaccine is different from your previous shot.");
            menu();
        }

        //Updating the Slot table with the vaccination information
        try
        {
            String updateSQL = "UPDATE Slot SET licenseNumber = '" + licenseNumber + "', vialNumber = '" + vialNumber + "', batchNumber = '" + batchNumber + "', vaccineName = '" + vaccineName + "' WHERE hinsurnum = '" + hinsurnum + "' AND vialNumber IS NULL";
            statement.executeUpdate(updateSQL);
        }
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        //close the statement and connection
        statement.close ( ) ;
        con.close ( ) ;
        System.out.println("\nYou have successfully inputted the vaccination information.");
        menu();
    }

    //check if the vaccine the user entered is the same as the vaccine they have previously received
    public static boolean checkPrevShot(String hinsurnum, String vaccineName) throws SQLException {

        Connection con = DriverManager.getConnection(url, your_userid, your_password);
        Statement statement = con.createStatement();
        String prevVaccine = null;

        try {
            String querySQL = "SELECT VACCINENAME FROM SLOT WHERE hinsurnum = '" + hinsurnum + "'";
            java.sql.ResultSet rs = statement.executeQuery(querySQL);

            while (rs.next()) {
                prevVaccine = rs.getString(1);
            }

        } catch (SQLException e) {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
            System.out.println(e);
        }
        //close the statement and connection
        statement.close();
        con.close();
        return prevVaccine.equals(vaccineName);
    }

    public static int menu() {
        int option;
        System.out.println();
        System.out.println("VaccineApp Main Menu");
        System.out.println("\t 1. Add a Person");
        System.out.println("\t 2. Assign a slot to a Person");
        System.out.println("\t 3. Enter Vaccination information");
        System.out.println("\t 4. Exit Application");
        System.out.println("Please Enter Your Option (input a number only):");
        option = sc.nextInt();
        sc.nextLine();
        return option;
    }

}
