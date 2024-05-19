package br.com.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Conection {

  public static final String DBHOST = "//localhost:3306/";
  public static final String DBUSER = "root";
  public static final String DBPASS = "toor";
  public static final String DB = "sitescape";

  public static Connection conectar() throws SQLException {
    return DriverManager.getConnection("jdbc:mysql:" + DBHOST + DB, DBUSER, DBPASS);
  }

}
