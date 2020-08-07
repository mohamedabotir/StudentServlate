/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Threading
 */
public class DB implements Operations{
    private int port;
    private String servername,username,password,db;
    DB(){
    this.db="web";
    this.port=3307;
    this.servername="localhost";
    this.password="Kampo";
    this.username="root";
   
    }
    public Connection getConnection() throws SQLException{
        Properties data=new Properties();
        data.put("user",username);
        data.put("password", password);
        return DriverManager.getConnection("jdbc:mysql://"+servername+":"+port+"/"+db,data);
    }
    

    @Override
    public int save(student o) {
        DB c=new DB();
        int exc=0;
        String sql1="select * from account where email='"+o.getEmail()+"'";
        String sql="insert into account (name,password,email,region) values(?,?,?,?)";
        Connection co=null;
        PreparedStatement stmp=null;
        Statement st;
        ResultSet r;
        int count=0;
        try {
            co=c.getConnection();
            st=co.createStatement();
            r=st.executeQuery(sql1);
            while(r.next())
                count++;
            r.close();
            st.close();
            if(count==0)
            {stmp=co.prepareStatement(sql);
            stmp.setString(1, o.getName());
            stmp.setString(2, o.getPassword());
            stmp.setString(3, o.getEmail());
            stmp.setString(4, o.getCountry());
           
            exc=stmp.executeUpdate();
            stmp.close();
            }
            else{
            exc=-1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        if(stmp!=null)
           try {
               stmp.close();
               co.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
        return exc;
    }

    @Override
    public int update(student o) {
        DB c=new DB();
        int exc=0;
        String sql="update account set name=?,region=? where email=?&&password=?";
        Connection co=null;
        PreparedStatement stmp=null;
        try {
            co=c.getConnection();
            stmp=co.prepareStatement(sql);
            stmp.setString(1, o.getName());
            stmp.setString(2, o.getCountry());  
            stmp.setString(4, o.getPassword());
            stmp.setString(3, o.getEmail());
          
           
            exc=stmp.executeUpdate();
          
            stmp.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        if(stmp!=null)
           try {
               stmp.close();
               co.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
        return exc;

    }

    @Override
    public void delete(student o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
