package repository;

/**
 * @author Joanne Zhuo
 */
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {

    /**
     * Hard-coded database access information
     */
    private static final String SERVER = "SERVER";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String DATABASE = "DATABASE";
    // as long as you're using mysql, leave this alone.
    private static final String DATABASE_TYPE = "mysql";

    /* ------------- SQL Variables ------------- */
    /**
     * The SQL connection to the database
     */
    static Connection con;

    /**
     * Gets a connection to for the manager and makes a good effort to make sure
     * it is open
     *
     * @return either an open connection or null
     */
    static synchronized Connection getConnection() {
	try {
	    if (con != null && con.isClosed()) {
		con = null;
	    }
	} catch (SQLException e) {
	    con = null;
	}
	connect();
	return con;
    }

    /**
     * Closes the database connection
     */
    public static void disconnect() {
	if (con == null) {
	    return;
	}

	try {
	    con.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	con = null;
    }

    /**
     * Utility method to validate connection is valid.
     *
     * @return true if DATABASE is available; false otherwise.
     */
    public static boolean isConnected() {
	if (con == null) {
	    return false;
	}

	try {
	    return !con.isClosed();
	} catch (SQLException e) {
	    e.printStackTrace();

	    return false;
	}
    }

    public static boolean connect() {
	// already connected.
	if (con != null) {
	    return true;
	}

	// Register the JDBC driver for MySQL. Simply accessing the class
	// will properly initialize everything.
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("Unable to locate mySQL drivers");
	    return false;
	}

	Properties dbConfig = new Properties();
	try {
	    dbConfig.load(new FileReader(new File("C:/Users/Pratham/Documents/NetBeansProjects/ClubUML/db.config")));
	} catch (Exception e1) {
	    System.err.println("Unable to locate db.config configuration.");
	    return false;
	}

	// Define URL for database server
	// NOTE: must fill in DATABASE NAME
	String url = "jdbc:" + DATABASE_TYPE + "://"
		+ dbConfig.getProperty(SERVER) + "/"
		+ dbConfig.getProperty(DATABASE);
	System.out.println(url);
	try {
	    // Get a connection to the database for a
	    // user with the given user name and password.
	    con = DriverManager.getConnection(url, dbConfig.getProperty(USER),
		    dbConfig.getProperty(PASSWORD));
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
}
