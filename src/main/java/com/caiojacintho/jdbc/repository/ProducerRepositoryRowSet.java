package com.caiojacintho.jdbc.repository;

import com.caiojacintho.jdbc.conn.ConnectionFactory;
import com.caiojacintho.jdbc.dominio.Producer;
import com.caiojacintho.jdbc.listener.CustomRowSetListener;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerRepositoryRowSet {


    public static List<Producer> findByNameJdbcRowSet (String name) {
        String sql = "select * from anime_store.producer where name like ?;";
        List<Producer> producers = new ArrayList<>();
        try(JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet()){
            jrs.addRowSetListener(new CustomRowSetListener());
            jrs.setCommand(sql);
            jrs.setString(1, String.format("%%%s%%", name));
            jrs.execute();
            while (jrs.next()) {
                Producer build = Producer.builder()
                        .id(jrs.getInt("id"))
                        .name(jrs.getString("name"))
                        .build();
                producers.add(build);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producers;
    }
//    public static void updateJdbcRowSet (Producer producer) {
//        String sql = "UPDATE `anime_store`.`producer` SET `name`= ?  WHERE (`id`= ?);";
//        try(JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet()){
//            jrs.setCommand(sql);
//            jrs.setString(1, producer.getName());
//            jrs.setInt(2, producer.getId());
//            jrs.execute();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
    public static void updateJdbcRowSet (Producer producer) {
        String sql = " select * from anime_store.producer WHERE (`id`= ?);";
        try(JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet()){
            jrs.addRowSetListener(new CustomRowSetListener());
            jrs.setCommand(sql);
            jrs.setInt(1, producer.getId());
            jrs.execute();
            if (!jrs.next()) return;
            jrs.updateString("name", producer.getName());
            jrs.updateRow();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateCachedRowSet (Producer producer) {
        String sql = " select * from producer WHERE (`id`= ?);";
        try(CachedRowSet crs = ConnectionFactory.getCachedRowSet();
        Connection con = ConnectionFactory.getConnection()){
            con.setAutoCommit(false);
            crs.setCommand(sql);
            crs.setInt(1, producer.getId());
            crs.execute(con);
            if (!crs.next()) return;
            crs.updateString("name", producer.getName());
            crs.updateRow();
            crs.acceptChanges();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
