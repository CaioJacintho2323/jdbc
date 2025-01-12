package com.caiojacintho.repository;

import com.caiojacintho.conn.ConnectionFactory;
import com.caiojacintho.dominio.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;


@Log4j2
public class ProducerRepository {
    public static void save(Producer producer) {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Inserted producer '{}' in the database, Database rows affected: '{}' ", producer.getName(), rowsAffected);
        } catch (SQLException e) {
            log.error("Error while to insert producer '{}'", producer.getName(), e);
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id`= '%d');".formatted(id);
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Deleted producer '{}' from database, Database rows affected: '{}' ", id, rowsAffected);
        } catch (SQLException e) {
            log.error("Error while to delete producer '{}'", id, e);
        }
    }

    public static void uptade(Producer producer) {
        String sql = "UPDATE `anime_store`.`producer` SET `name`= '%s' WHERE (`id`= '%d');"
                .formatted(producer.getName(), producer.getId());
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            log.info(" Update producer '{}', Database rows affected: '{}' ", producer.getId(), rowsAffected);
        } catch (SQLException e) {
            log.error("Error while to update producer '{}'", producer.getId(), e);
        }
    }

    public static List<Producer> findAll() {
        log.info("Finding all producers");
        return findbyName("");
    }

    public static List<Producer> findbyName(String name) {
        log.info("Finding findByName producers");
        String sql = "SELECT * FROM anime_store.producer where name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producer build = Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                producers.add(build);
            }
        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
        return producers;
    }

    public static void showProducerMetaData() {
        log.info("Showing Producer Meta data");
        String sql = "SELECT * FROM anime_store.producer";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            log.info("Column Count: {}", columnCount);
            for (int i = 1; i <= columnCount; i++) {
                log.info("Table name '{}'", metaData.getTableName(i));
                log.info("Column name '{}'", metaData.getColumnName(i));
                log.info("Column size '{}'", metaData.getColumnDisplaySize(i));
                log.info("Column type '{}'", metaData.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
    }

    public static void showDriverMetaData() {
        log.info("Showing Driver Metadata");
        try (Connection conn = ConnectionFactory.getConnection()) {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                log.info("Supports TYPE_FORWARD_ONLY");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("And Supports CONCUR_UPDATABLE");
                }
            }
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                log.info("Supports TYPE_SCROLL_INSENSITIVE");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("And Supports TYPE_SCROLL_INSENSITIVE");
                }
            }
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                log.info("Supports TYPE_SCROLL_SENSITIVE");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("And Supports TYPE_SCROLL_SENSITIVE");
                }
            }


        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
    }

    public static void showTypeScrollWorking() {
        String sql = "SELECT * FROM anime_store.producer";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            log.info("Showing type scroll working");
            log.info("Last row? '{}'", rs.last());
            log.info("Row  number? '{}'", rs.getRow());
            log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("");
            log.info("First row? '{}'", rs.first());
            log.info("Row number? '{}'", rs.getRow());
            log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("");
            log.info("Row absolute? '{}'", rs.absolute(2));
            log.info("Row number? '{}'", rs.getRow());
            log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            log.info("");
            log.info("Row relative? '{}'", rs.relative(-1));
            log.info("Row number? '{}'", rs.getRow());
            log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());

            log.info("is last ? '{}'", rs.isLast());
            log.info("Row number ? '{}'", rs.getRow());

            log.info("is first ? '{}'", rs.isFirst());
            log.info("Row number ? '{}'", rs.getRow());


            log.info("Last row? '{}'", rs.last());
            log.info("---------------");
            rs.next();
            log.info("After last row? '{}'", rs.isAfterLast());
            while (rs.previous()) {
                log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
            }


        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
    }

    public static List<Producer> findByNameAndUpdateToUpperCase(String name) {
        log.info("Finding findByName producers");
        String sql = "SELECT * FROM anime_store.producer where name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rs.updateString("name", rs.getString("name").toUpperCase());

                rs.updateRow();
                Producer build = Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                producers.add(build);
            }
        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
        return producers;
    }

    public static List<Producer> findByNameAndInsertWhenNotFound(String name) {
        log.info("Finding findByName producers");
        String sql = "SELECT * FROM anime_store.producer where name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return producers;
            insertNewProducer(name, rs);
            producers.add(getProducer(rs));
        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
        return producers;
    }

    public static void findByNameAndDelete(String name) {
        log.info("Finding findByName producers");
        String sql = "SELECT * FROM anime_store.producer where name like '%%%s%%';"
                .formatted(name);
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                log.info("deleting '{}'", rs.getString("name"));
                rs.deleteRow();
            }
        } catch (SQLException e) {
            log.error("Error while to find all producer", e);
        }
    }

    private static void insertNewProducer(String name, ResultSet rs) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("name", name);
        rs.insertRow();
    }

    private static Producer getProducer(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        rs.next();
        Producer build = Producer.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
        return build;
    }

}
