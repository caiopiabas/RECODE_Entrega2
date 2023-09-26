package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
  
  // Nome de usuário do MySQL
  private static final String USERNAME = "root";
  
  // Senha do MySQL
  private static final String PASSWORD = "123456";
  
  //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
  private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/agenciadeviagem";
  /**
   * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário e senha fornecidos
   * @param username
   * @param senha
   * @return uma conexão com o banco de dados
   * @throws Exception
   */
  public static Connection createConnectionToMySQL() throws
  Exception{
    Class.forName("com.mysql.cj.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
  
    //Cria a conexão com o banco de dados
    Connection connection =
  DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    
    return connection;
  }
  public static void main(String[] args) throws Exception{
    
    //Recupera uma conexão com o banco de dados
    Connection con = createConnectionToMySQL();
    
    //Testa se a conexão é nula
    if(con != null){
      System.out.println("Conexão obitida com sucesso" + con);
    con.close();
    }
  }
}