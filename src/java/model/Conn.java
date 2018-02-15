package model;

public class Conn {

    static  {
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the JDBC driver!");
        }
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getDriver() {
        return DRIVER;
    }
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/coursera_livros";
    private static final String USER = "postgres";
    private static final String PASSWORD = "dbuser";

}
