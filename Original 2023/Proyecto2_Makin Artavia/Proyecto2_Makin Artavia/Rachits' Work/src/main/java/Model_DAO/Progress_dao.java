/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_DAO;

import Model_DTO.Progress_dto;
import com.mycompany.journeytounemployment.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class Progress_dao {

    private Connection connection;
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private String username = "DARTAVIA";
    private String password = "M12345";
    // Initialize the database connection

    public Progress_dao() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Conexion fallida");
        }
    }

    public void savePlayer(Progress_dto player) {
        String sql = "INSERT INTO TBL_PROGRESS (PG_NICKNAME, PG_LEVEL)"
                + "VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, player.getNickName());
            statement.setShort(2, player.getPersonLevel());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar");
        }
    }

    public void DeletePlayer(String nickName) throws SQLException {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("La conexion para eliminar fallo");
        }

        try {
            String query = "DELETE FROM TBL_PROGRESS WHERE PG_NICKNAME = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nickName);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se puede eliminar");
        }
    }
    
     public Player searchPlayer(String name) throws SQLException {
        Player player = new Player();
        try {
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            System.out.println("Conexion fallida al buscar");
        }
        String query = "SELECT PG_NICKNAME, PG_LEVEL FROM TBL_PROGRESS WHERE PG_NICKNAME = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            player = new Player(resultSet.getString("PG_NICKNAME"), resultSet.getShort("PG_LEVEL"));
        } else {
            System.out.println("No se encontro al jugador en la base de datos");
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return player;
    }
     
     public Boolean IsRegister(String name) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            System.out.println("Conexion fallida al buscar");
        }
        String query = "SELECT PG_NICKNAME FROM TBL_PROGRESS WHERE PG_NICKNAME = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return true;
        }
        
        resultSet.close();
        statement.close();
        closeConnection();
        return false;
    }
     
      public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La conexion no fue cerrada al finalizar la busqueda del jugador");
        }
    }
}
