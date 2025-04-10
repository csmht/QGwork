package com.csmht.dao;

import java.sql.*;
import java.util.List;

public class JDBC {


    public static int add(Connection conn,String where,String... values) throws SQLException {//列+内容

        Statement stmt = conn.createStatement();
        StringBuilder one = new StringBuilder("INSERT INTO `" + where + "` (");
        StringBuilder two = new StringBuilder(" VALUES (");
        for(int i=0;i<values.length;i=i+2) {
            String value = values[i];
            if(i==values.length-2){
                one.append(value).append(")");
            }else{

                one.append(value).append(",");
            }
        }
        for(int i=1;i<values.length;i=i+2) {


            if(i==values.length-1){
                two.append("'").append(values[i]).append("')");
            }else{
                two.append("'").append(values[i]).append("',");
            }}
        String sql = one + two.toString();

        return stmt.executeUpdate(sql);
    }


    public static int delete(Connection conn,String where,String...who) throws SQLException {

        Statement stmt = conn.createStatement();

        String sql = "DELETE FROM " + where +" ";

        for(int i=0;i<who.length;i=i+2) {
            if(i==0){
                sql += "WHERE";
            }
            sql += " "+ who[i] +" = '" + who[i+1] + "' ";
            if(i!=who.length-2){
                sql += " AND ";
            }
        }

        return stmt.executeUpdate(sql);
    }




    public static ResultSet find(Connection conn,String where,Object...values) throws SQLException {

        ResultSet rs = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM `").append(where).append("`");
        if(values.length>0){sql.append(" WHERE ");}
        for(int i=0;i<values.length;i+=2) {
            if(i>0){
                sql.append(" AND ");

            }sql.append(values[i]).append(" = ?");}

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        for(int i=0;i<values.length;i=i+2) {
            Object value = values[i + 1];
            int index = (i / 2) + 1;

            if(values[i] instanceof Date){
                Time time = new Time(((Date) value).getTime());
                pstmt.setTime(index,time);
            }else {
                pstmt.setObject(index,value.toString());
            }
        }

        return pstmt.executeQuery();

    }



    public static ResultSet find(Connection conn,String main,List<String> where, String...values) throws SQLException {//where 链接表 主表链接列 连接表链接列

        ResultSet rs = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM `" + main + "` ");
        for(int i=0;i<where.size();i+=3) {
            sql.append("LEFT JOIN ").append(where.get(i)).append(" ON ").append(where.get(i+1)).append("=").append(where.get(i+2)).append(" ");
        }
        if(values.length>0){sql.append(" WHERE ");}
        for(int i=0;i<values.length;i+=2) {
            if(i>0){
                sql.append(" AND ");
            }sql.append(values[i]).append(" = '").append(values[i+1]).append("'");}
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        return pstmt.executeQuery();
    }



    public static int Edit(Connection conn,String where,String[] who,String...what) throws SQLException {

        ResultSet rs = null;
        StringBuilder sql = new StringBuilder("UPDATE `" + where + "` SET ");

        for(int i=0;i<what.length;i+=2) {
            sql.append(what[i]).append(" = '").append(what[i+1]).append("' ");
            if(i!=what.length-2){
                sql.append(" , ");
            }
        }

        for(int i=0;i<who.length;i+=2) {
            if(i==0){
                sql.append(" WHERE ");
            }
            sql.append(who[i]).append(" = ").append(who[i+1]);
            if(i!=who.length-2){
                sql.append(" AND ");
            }
        }

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        int count = pstmt.executeUpdate();


        return count;
    }

}