package com.caiojacintho.repository;

import com.caiojacintho.conn.ConnectionFactory;
import com.caiojacintho.dominio.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;


@Log4j2
public class ProducerRepository {
    public static void save(Producer producer) {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try(Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement()){
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Inserted producer '{}' in the database, Database rows affected: '{}' ",producer.getName(), rowsAffected);
        }catch (SQLException e){
            log.error("Error while to insert producer '{}'",producer.getName(), e );
        }
    }
    public static void delete(int id) {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id`= '%d');".formatted(id);
        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement()){
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Deleted producer '{}' from database, Database rows affected: '{}' ",id, rowsAffected);
        }catch (SQLException e){
            log.error("Error while to delete producer '{}'",id, e );
        }
    }

    public static void uptade(Producer producer) {
        String sql = "UPDATE `anime_store`.`producer` SET `name`= '%s' WHERE (`id`= '%d');"
                .formatted(producer.getName(),producer.getId());
        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement()){
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Update producer '{}', Database rows affected: '{}' ",producer.getId(), rowsAffected);
        }catch (SQLException e){
            log.error("Error while to update producer '{}'",producer.getId(), e );
        }
    }
    public static List<Producer> findAll() {
        log.info("Finding all producers");
        return findbyName("");
    }

    public static List<Producer> findbyName(String name) {
        log.info("Finding findByName producers");
        String sql = "SELECT * FROM anime_store.producer where name like '%%%s%%';"
                .formatted( name);
        List<Producer> producers = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                Producer build = Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                producers.add(build);
            }
        }catch (SQLException e){
            log.error("Error while to find all producer", e );
        }
        return producers;
    }
}
