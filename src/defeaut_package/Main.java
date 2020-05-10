package defeaut_package;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    	
        Person person1 = new Person("John","Smith",      24, 373798467,467542578);
        Person person2 = new Person("Linda", "Johnson", 31, 675849365,214683567);
        Person person3 = new Person("David", "Williams", 10, 753575446,459834761);
        Person person4 = new Person("Richard", "Jones", 54, 546574435,453291723);
        Person person5 = new Person("Susan", "Miller", 21, 766853765,547863491);

        createTable();
        
        insertPerson(person1);
        insertPerson(person2);
        insertPerson(person3);
        insertPerson(person4);
        insertPerson(person5);

        System.out.println(selectPerson(2));
        for(Person p: findAllPeople()){
            System.out.println(p);
        }
        deletePerson(2);

        for(Person p: findAllPeople()){
            System.out.println(p);
        }

    }

    public static Connection deletePerson(int id){
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            stmt = connection.createStatement();
            String sql = "DELETE from TABLE1 where ID=" + id + ";";
            stmt.executeUpdate(sql);
            connection.commit();
            connection.close();

            System.out.println("Deleted person "+ id + " successfully");
        } catch ( Exception e ) {
            System.out.println(e);
            connection = null;
        }
        return connection;


    }

    public static ArrayList<Person> findAllPeople(){
        Connection connection = null;
        Statement statement = null;
        ArrayList<Person> person = new ArrayList<>();

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM TABLE1;");
            while ( rs.next() ) {
                person.add(new Person(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"), rs.getInt("ssn"),rs.getInt("creditcard")));
            }
            rs.close();
            statement.close();
            connection.close();

            System.out.println("Found all the people successfully");
        } catch ( Exception e ) {
            System.out.println(e);
            person = null;
        }
        return person;

    }

    public static Person selectPerson(int id){
        Connection connection = null;
        Statement statement = null;
        Person person = new Person();
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM TABLE1 where ID=" + id + ";");
            while ( rs.next() ) {
                person.setFirstName(rs.getString("firstname"));
                person.setLastName(rs.getString("lastname"));
                person.setAge(rs.getInt("age"));
                person.setSsn(rs.getInt("ssn"));
                person.setCreditCard(rs.getInt("creditcard"));
            }
            rs.close();
            statement.close();
            connection.close();

            System.out.println("Selected person " + id + " successfully");
        } catch ( Exception e ) {
            System.out.println(e);
            person = null;
        }
            return person;

    }

    public static Connection insertPerson(Person person){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String sql = "INSERT INTO TABLE1 (FIRSTNAME,LASTNAME,AGE,SSN,CREDITCARD) " +
                    "VALUES ("+
                          "'" + person.getFirstName() + "'," +
                          "'" + person.getLastName() + "'," +
                                person.getAge() + "," +
                                person.getSsn() + "," +
                                person.getCreditCard() + " );";
            statement.executeUpdate(sql);

            statement.close();
            connection.commit();
            connection.close();

            System.out.println("Inserted " + person.getFirstName() + " " + person.getLastName() + " successfully");
        } catch ( Exception e ) {
            System.out.println(e);
            connection = null;
        }

            return connection;
    }


    public static Connection createTable(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();

            statement = connection.createStatement();
            String sql = "CREATE TABLE TABLE1 " +
                         "(ID INTEGER PRIMARY KEY        AUTOINCREMENT, " +
                         "FIRSTNAME           CHAR(30)   NOT NULL, " +
                         "LASTNAME            CHAR(30)   NOT NULL, " +
                         "AGE                 INT                , " +
                         "SSN                 BIGINT             , " +
                         "CREDITCARD          BIGINT             ) ";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

            System.out.println("Table created successfully");
        }catch (Exception e){
            System.out.println(e);
            connection = null;
        }

        return connection;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:_D_B_.db");
            System.out.println("Opened database successfully");
        }catch (Exception e){
            System.out.println(e);
            connection = null;
        }

        return connection;
    }


}