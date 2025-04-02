package fr.univamu.fr.agricole;

import java.io.Closeable;
import java.sql.*;
public class BDAccess implements Closeable {
    private Connection connection;
    public BDAccess() {}

    public Connection BDAccessPDO() throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.connection = DriverManager.getConnection( "jdbc:mariadb://mysql-archilogici.alwaysdata.net/archilogici_agricole_bd", "396957_agricole", "agricole1234!" ) ;
        return connection;
    }

    @Override
    public void close() {
        try{
            this.connection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}