/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Threading
 */
@WebServlet(name = "getAll", urlPatterns = {"/getAll"})
public class getAll extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<title>Servlet getAll</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ClientData</h1>");
           
            out.print("<table border=2 width=100%");
            out.print("<tr><th>ID</th><th>Name</th><th>Email</th><th>Region</th><th>Delete</th></tr>");
          Connection con = null;
            DB c=new DB();
            PreparedStatement stmp = null;
            ResultSet rs = null;
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=c.getConnection();
            stmp=con.prepareStatement("select * from account");
            rs=stmp.executeQuery();
            while(rs.next()){
            out.print("<tr><td>"+rs.getInt("id")+"</td>"+"<td>"+rs.getString("name")+"</td>"+"<td>"+rs.getString("email")+"</td>"+"<td>"+rs.getString("region")+"</td>"+"<td>"+"<a href='delete?id="+rs.getInt("id")+"'>delete</a></td>");
             
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (SQLException ex) {
                Logger.getLogger(getAll.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
            if(con!=null)
               
                try { 
                    stmp.close();
                    rs.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(getAll.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           out.print("</table>"); 
            
            
            
            out.println("<a href=\"getAll\" class=\"btn btn-info\" role=\"button\">ViewStudent</a>");out.println("<a href=\"index.html\" class=\"btn btn-info\" role=\"button\">Home</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
