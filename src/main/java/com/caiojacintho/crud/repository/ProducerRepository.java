package com.caiojacintho.crud.repository;

import com.caiojacintho.crud.conn.ConnectionFactory;
import com.caiojacintho.crud.dominio.Producer;
import lombok.extern.log4j.Log4j2;

import javax.swing.event.InternalFrameEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;


@Log4j2
public class ProducerRepository {

    public static List<Producer> findbyName(String name) {
        log.info("Finding producer by '{}'", name);
        List<Producer> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementFindByName(conn, name);
             ResultSet rs = ps.executeQuery();
        ) {
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

    public static void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementDelete(conn, id);) {
            ps.execute();
            log.info(" Deleted producer '{}' from database", id);
        } catch (SQLException e) {
            log.error("Error while to delete producer '{}'", id, e);
        }
    }

    private static PreparedStatement createPrepareStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM anime_store.producer where name like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    private static PreparedStatement createPrepareStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id`= ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }
}
