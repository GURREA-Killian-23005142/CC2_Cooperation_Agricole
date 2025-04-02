package fr.univamu.fr.agricole;

import java.io.Closeable;
import java.sql.*;
public class BDAccess implements Closeable {
    private Connection connection;
    public BDAccess() {}

    public Connection BDAccessPDO() throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.connection = DriverManager.getConnection( "jdbc:mariadb://mysql-archilogi000.alwaysdata.net/archilogi000_cc2", "395521_cc2", "Projetcc2" ) ;
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