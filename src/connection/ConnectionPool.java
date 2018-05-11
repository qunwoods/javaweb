package connection;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionPool {
  private ArrayList<Connection> pool;
  private String url;
  private String username;
  private String password;
  private String driverClassName;
  private int poolSize = 10;
  private static ConnectionPool instance = null;
  private ConnectionPool() {
    init();
  }
  private static ConnectionPool getInstance() {
    if (instance == null) {
      instance = new ConnectionPool();
    }
    return instance;
  }
  private void init() {
    pool = new ArrayList<Connection>(poolSize);
    readConfig();
    addConnection();
  }
  private void readConfig() {
    try {
      String path = System.getProperty("dbpool.properties");
      FileInputStream is = new FileInputStream(path);
      Properties props = new Properties();
      props.load(is);
      this.driverClassName = props.getProperty("driverClassName");
      this.url = props.getProperty("url");
      this.username = props.getProperty("username");
      this.password = props.getProperty("props");
      this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
    } catch (Exception e){
      e.printStackTrace();
      System.out.print("读取文件出错");
    }
  }
  private void addConnection() {
    Connection conn = null;
    for (int i = 0; i < poolSize; i++) {
      try {
        Class.forName(driverClassName);
        conn = java.sql.DriverManager.getConnection(url, username, password);
        pool.add(conn);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (SQLException e){
        e.printStackTrace();
      }
    }
  }
  private synchronized void release(Connection conn) {
    pool.add(conn);
  }
  private synchronized Connection getConnection() {
    if (pool.size() > 0) {
      Connection conn = pool.get(0);
      pool.remove(conn);
      return conn;
    } else {
      return null;
    }
  }
  private synchronized  void close() {
    for (int i = 0; i < pool.size(); i++) {
      try {
        ((Connection) pool.get(i)).close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      pool.remove(i);
    }
  }
}
