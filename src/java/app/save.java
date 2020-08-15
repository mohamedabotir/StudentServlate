/*
 *. To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import static app.login.getEmail;
import static app.login.getPassword;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Threading
 */
@WebServlet(name = "save", urlPatterns = {"/save"})
public class save extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
  

    
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
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(save.class.getName()).log(Level.SEVERE, null, ex);
        }
        student o=new student();
        SimpleDateFormat    formate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date    dtsub=new Date();
     o.setLog(formate.format(dtsub));
        o.setName(name);
        o.setPassword(password);
        o.setEmail(email);
        o.setCountry(country);
        DB save=new DB();
       int num= save.save(o);
       if(num==1)
       {
            
out.println("Successful Add");


       }
       else if(num==0){
       RequestDispatcher r=request.getRequestDispatcher("register.html");
r.include(request, response);
out.println("Email Invalid");
       }
       else if(num==-1)
       {RequestDispatcher r=request.getRequestDispatcher("register.html");
r.include(request, response);
out.println("This Account Already used");
}
       else if(num==-2){
        RequestDispatcher r=request.getRequestDispatcher("register.html");
r.include(request, response);
out.println("Invalid name");
       }
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
