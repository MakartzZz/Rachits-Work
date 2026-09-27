/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_DAO;

import Model_DTO.Progress_dto;
import com.mycompany.journeytounemployment.Player;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author david
 */
public class Progress_dao {

    private static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.dir"), "data");
    private static final Path DATA_FILE = DATA_DIRECTORY.resolve("progress.txt");

    public Progress_dao() {
        initializeStorage();
    }

    public void savePlayer(Progress_dto player) {
        try {
            Properties properties = loadProperties();
            properties.setProperty(player.getNickName(), String.valueOf(player.getPersonLevel()));
            saveProperties(properties);
        } catch (IOException e) {
            System.out.println("Error al agregar");
        }
    }

    public void DeletePlayer(String nickName) {
        try {
            Properties properties = loadProperties();
            properties.remove(nickName);
            saveProperties(properties);
        } catch (IOException e) {
            System.out.println("No se puede eliminar");
        }
    }
    
     public Player searchPlayer(String name) {
        Player player = new Player();
        try {
            Properties properties = loadProperties();
            String level = properties.getProperty(name);
            if (level != null) {
                player = new Player(name, Short.valueOf(level));
            } else {
                System.out.println("No se encontro al jugador en el archivo de progreso");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Conexion fallida al buscar");
        }
        return player;
    }
     
     public Boolean IsRegister(String name) {
        try {
            Properties properties = loadProperties();
            return properties.containsKey(name);
        } catch (IOException e) {
            System.out.println("Conexion fallida al buscar");
        }
        return false;
    }
     
      public void closeConnection() {
        // Ya no existe una conexion externa que cerrar.
    }

    private void initializeStorage() {
        try {
            Files.createDirectories(DATA_DIRECTORY);
            if (Files.notExists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo inicializar el archivo de progreso", e);
        }
    }

    private Properties loadProperties() throws IOException {
        initializeStorage();
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
        return properties;
    }

    private void saveProperties(Properties properties) throws IOException {
        initializeStorage();
        try (Writer writer = Files.newBufferedWriter(DATA_FILE, StandardCharsets.UTF_8)) {
            properties.store(writer, "Progreso de Journey To Unemployment");
        }
    }
}
