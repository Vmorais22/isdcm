/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Victor
 */
@WebServlet(name = "servletREST", urlPatterns = {"/servletREST"})
public class servletREST extends HttpServlet {
    
    String BASE_URL = "http://localhost:8080/webapp2/resources/javaee8";
    
    String title,author,description,views;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //get para la busqueda
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = request.getParameter("link_url");
        request.getSession().setAttribute("currentLink", link);
        
        String id = request.getParameter("currentVideoId");
        
        AssignParametersToSessionAtributes(request);
        System.out.println("titulo: " + title + " autor: " + author + " description: " + description + " views " + views);
        
        URL url = new URL(BASE_URL + "/putInfo?id="+id); 
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
        con.setRequestMethod("PUT");

        int status = con.getResponseCode();

        request.getSession().setAttribute("status", status);
        response.sendRedirect("/webapp1/jsp/visorVid.jsp"); 
   }

    private void AssignParametersToSessionAtributes(HttpServletRequest request) {
        title = request.getParameter("currentVideoTitle");
        author = request.getParameter("currentVideoAuthor");
        description = request.getParameter("currentVideoDescription");
        views = request.getParameter("currentVideoViews");
       
        title = title.substring(0,title.length() - 1);
        author = author.substring(0,author.length() - 1);
        description = description.substring(0,description.length() - 1);
        Integer aux =Integer.parseInt(views.substring(0,views.length() - 1)) + 1;
        views = aux.toString();
        
        request.getSession().setAttribute("currentVideoTitle", title);
        request.getSession().setAttribute("currentVideoAuthor", author);
        request.getSession().setAttribute("currentVideoDescription", description);
        request.getSession().setAttribute("currentVideoViews", views);
    } 
}