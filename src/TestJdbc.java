package fr.lndr.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TestJdbc {

	public static void main(String[] args) throws Exception {
		
		ArrayList<Article> articles = new ArrayList<Article>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//récupérer une connection à partir d'une url + id + pwd
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "";
		
		try(Connection connection = DriverManager.getConnection(url, login, password)){	//Connection de java.sql
			String strSql = "SELECT * FROM T_Articles";				// une fois connecté, réalisation d'une requête
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(strSql))	{	//ResultSet de java.sql
					while(resultSet.next()) {
						int rsIdUSer = resultSet.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
						String rsDescription = resultSet.getString(2);
						String rsMarque = resultSet.getString(3);
						double rsPrixUnitaire = resultSet.getDouble(4);
						articles.add((new Article(rsIdUSer, rsDescription, rsMarque, rsPrixUnitaire)));
					}
					
				}
			}
			
			for(Article a : articles)
				System.out.println(a.getId() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getPrice());
			
		}
	}

}
