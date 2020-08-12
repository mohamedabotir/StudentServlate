/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Threading
 */
public class DB <T>implements Operations{
    private final int port;
    private final String servername,username,password,db;
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
    SimpleDateFormat    formate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date    dtsub=new Date();

        DB c=new DB();
        int exc=0;
        String sql1="select * from account where email='"+o.getEmail()+"'";
        String sql2="insert into account ";
        String sql="insert into account (name,password,email,region,adds) values(?,?,?,?,?)";
        Connection co=null;
        PreparedStatement stmp=null;
        Statement st;
        ResultSet r;
        logs log=new logs();  
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
            {
            stmp=co.prepareStatement(sql);
            stmp.setString(1, o.getName());
            stmp.setString(2, o.getPassword());
            stmp.setString(3, o.getEmail());
            stmp.setString(4, o.getCountry());
            stmp.setString(5, o.getLog());
           
            exc=stmp.executeUpdate();
            log.Write("Logs add Name:"+o.getName()+" Email:"+o.getEmail()+" "+formate.format(dtsub));
            stmp.close();
            }
            else{
            exc=-1;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }  finally{
        if(co!=null)
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
        String sql="update account set name=?,region=?,lmodified=? where email=?&&password=?";
        Connection co=null;
        PreparedStatement stmp=null;
        try {
            co=c.getConnection();
            stmp=co.prepareStatement(sql);
            stmp.setString(1, o.getName());
            stmp.setString(2, o.getCountry());  
            stmp.setString(5, o.getPassword());
            stmp.setString(4, o.getEmail());
            stmp.setString(3, o.getLog());
          
           
            exc=stmp.executeUpdate();
          
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        if(co!=null)
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
    public int delete(int id) {
        DB c=new DB();
      int exc=0;
      String sql="delete from account where id=?"; 
      PreparedStatement stmp=null;
      Connection co=null;
        try {
            co=c.getConnection();
            stmp=co.prepareStatement(sql);
            stmp.setInt(1,id);
            exc=stmp.executeUpdate();
            stmp.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        if(co!=null)
           try {
               stmp.close();
               co.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } return exc;}

    @Override
    public int login(student o) {
       
String sql="select * from account";
Statement st=null ;
Statement st1=null;
int exc = 0;
Connection co = null;
SimpleDateFormat    formate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date    dtsub=new Date();
     ResultSet rs=null;
        try {
            co=getConnection();
            st1=st=co.createStatement();
            
         rs=  st.executeQuery(sql);
            while(rs.next())
            {
            String email=rs.getString("email");
            String pass=rs.getString("password");
            if(email.equals("")&&pass.equals(""))
                break;
            if(email.equals(o.getEmail())&&pass.equals(o.getPassword()))
            {
                
            exc=1;
            st1.executeUpdate("update  account set llogin='"+formate.format(dtsub)+"' where email='"+email+"'&&password='"+pass+"'");
            break;
            
            }
            else
                exc=0;
            }
              
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        if(co!=null)
        {
            try {
                st.close();
                co.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        }
            
            return exc;
        } 
       
        student getOne(int id){
            student Student =new student();
        Connection co=null;
        PreparedStatement stmp=null;
        String sql="select * from account where id=?";
        ResultSet rs=null;
        try {
            co=getConnection();
            stmp=co.prepareStatement(sql);
            stmp.setInt(1,id);
            rs=stmp.executeQuery();
            if(rs.next()){
                Student.setId(rs.getInt(1));
                Student.setName(rs.getString(2));
                Student.setPassword(rs.getString(3));
                Student.setEmail(rs.getString(4));
                Student.setCountry(rs.getString(rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        if(co!=null)
        {
            try {
                stmp.close();
                co.close(); 
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
        }
        return Student;
        } 
        
     public   List<student>getAll(){
         DB c=new DB();
        student Student=new student();
        List<student>list=new ArrayList<student>();
        Connection co=null;
        PreparedStatement stmp=null;
        String sql="select * from account";
        ResultSet rs=null;
        try {
            co=c.getConnection();
            stmp=co.prepareStatement(sql);
            rs=stmp.executeQuery();
            while(rs.next()){
                Student.setId(rs.getInt(1));
                Student.setName(rs.getString(2));
                Student.setPassword(rs.getString(3));
                Student.setEmail(rs.getString(4));
                Student.setCountry(rs.getString(5));
                list.add(Student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        if(co!=null)
        {
            try {
                stmp.close();
                co.close(); 
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
        }
        return list;
        }
        
 public    ArrayList<student>get() {
         DB c=new DB();
         
         
        student Student=new student();
        ArrayList<student> list=new ArrayList<student> ();
        Connection co=null;
        Statement stmp=null;
        String sql="select * from account";
        ResultSet rs=null;
        try {
            co=c.getConnection();
            stmp=co.createStatement();
            rs=stmp.executeQuery(sql);
            while(rs.next()){
                Student.setId(rs.getInt("id"));
                Student.setName(rs.getString("name"));
                Student.setEmail(rs.getString("email"));
                Student.setCountry(rs.getString("region"));
                list.add(Student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        if(co!=null)
        {
            try {
                stmp.close();
                co.close(); 
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
        }
        return list;
        } 
    
}
