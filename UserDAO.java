// Author: Andre Luiz Gomes Santos
// Universidade Tecnologica Federal do Paraná
//
// Esse código é adaptado de um servletpara MySQL que eu criei,
// pode ser que precise mais do que pequenas alteracoes para que funcione.

//

package net.java.usermanage;

import java.sql.*;				// Para uso do sql, driver, logging, command line, etc...
import java.util.ArrayList;		// Para o GET-All do MySQL
import java.util.List;			// Para o GET-All do MySQL

// import da Classe User
import net.java.usermanage.model.User;

// para uso do mySQL, talvez de para modificar para mongoDB ou salvar em JSON
public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/sampledb";
	private String jdbcUser = "root";
	private String jdbcSenha = "1234";
	private String driver = "com.mysql.jdbc.Driver";
	 
	
	private static final String INSERT_USER_SQL = "INSERT INTO sampledb.users ( name, birth, email, work) VALUES ( ?, ?, ?, ?) ;";
	private static final String SELECT_USER_BY_ID = "SELECT id, name, birth, email, work FROM sampledb.users WHERE id = ? ;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM sampledb.users ;";
	private static final String DELETE_USERS_SQL = "DELETE FROM sampledb.users WHERE id = ? ;";
	private static final String UPDATE_USERS_SQL = "UPDATE sampledb.users SET name = ?, email = ?, birth = ?, work = ? WHERE id = ? ;";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			
			Class.forName(driver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcSenha);
			
		} 
		catch (SQLException e) { e.printStackTrace();}
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		
		return connection;
	}
	
	
	//* INSERT
	// Mesmo local onde o writeJSON ocorre
	//endpoint(@PUT)
	public void insertUser(User user) throws SQLException {
		try(Connection connection = getConnection(); 
				PreparedStatement prepStat = connection.prepareStatement(INSERT_USER_SQL);){
			
			user.invertDate();
			user.twoNames();
			user.writeJSON();
			
			prepStat.setString(1, user.getName());
			prepStat.setString(2, user.getBirth());
			prepStat.setString(3, user.getEmail());
			prepStat.setString(4, user.getWork());
			
			prepStat.executeUpdate();
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	
	
	//* SELECT by user_id
	//endpoint(@GET)
	public User selectUser(int id) {
		
		User user = null;
		try(Connection connection = getConnection(); 
				PreparedStatement prepStat = connection.prepareStatement(SELECT_USER_BY_ID);) {
			
			prepStat.setInt(1, id);
			System.out.println(prepStat);
			ResultSet res = prepStat.executeQuery();
			
			while (res.next()) {
				String name = res.getString("name");
				String birth = res.getString("birth");
				String email = res.getString("email");
				String work = res.getString("work");
				user = new User(id, name, birth, email, work);
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return user;
	}


	//* SELECT ALL
	// Esse é um @GET-All do MySQL
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		
		@SuppressWarnings("unused")
		User user = null;
		try(Connection connection = getConnection(); 
				PreparedStatement prepStat = connection.prepareStatement(SELECT_ALL_USERS);) {
			
			System.out.println(prepStat);
			ResultSet res = prepStat.executeQuery();
			
			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String birth = res.getString("birth");
				String email = res.getString("email");
				String work = res.getString("work");
				users.add(new User(id, name, birth, email, work));
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return users;
	}
	
	
	//* UPDATE by user_id
	public boolean updateUser(User user) throws SQLException {
		boolean rowUp;
		
		try(Connection connection = getConnection(); 
		PreparedStatement prepStat = connection.prepareStatement(UPDATE_USERS_SQL);){
			
			prepStat.setString(1, user.getName());
			prepStat.setString(2, user.getBirth());
			prepStat.setString(3, user.getEmail());
			prepStat.setString(4, user.getWork());
			
			rowUp = prepStat.executeUpdate() > 0;
		}
		
		return rowUp;
	}

	
	//* (DELETE by user_id)
	public boolean deleteUser(int id) throws SQLException {
		boolean row;
		try(Connection connection = getConnection(); 
		PreparedStatement prepStat = connection.prepareStatement(DELETE_USERS_SQL);){
			
			prepStat.setInt(1, id);
			row = prepStat.executeUpdate() > 0;
		}
		return row;
	}
	
	
}
