/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.webapp1.controller;
 
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Video;
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
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String año = request.getParameter("year");
        String mes = request.getParameter("month");
        String dia = request.getParameter("day");
        URL url = new URL(BASE_URL + "/getInfo?titulo="+URLEncoder.encode(titulo)+"&autor="+URLEncoder.encode(autor)+"&año="+año+"&mes="+mes+"&dia="+dia); 
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
        con.setRequestMethod("GET");

        int status = con.getResponseCode(); 
        
        BufferedReader br = null;
        if (100 <= con.getResponseCode() && con.getResponseCode() <= 399) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        Gson gson = new Gson();
        List<Video> logs = gson.fromJson(br, new TypeToken<List<Video>>(){}.getType());
        
        request.getSession().setAttribute("videoSearch", logs);
        response.sendRedirect("/webapp1/jsp/busqueda.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = request.getParameter("link_url");
        request.getSession().setAttribute("currentLink", link);
        
        String id = request.getParameter("currentVideoId");
        
        AssignParametersToSessionAtributes(request);
        
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
   
        title = URLDecoder.decode(title.substring(0,title.length() - 1));
        author = URLDecoder.decode(author.substring(0,author.length() - 1));
        description = URLDecoder.decode(description.substring(0,description.length() - 1));
        Integer aux =Integer.parseInt(views.substring(0,views.length() - 1)) + 1;
        views = aux.toString();
               
        request.getSession().setAttribute("currentVideoTitle", title);
        request.getSession().setAttribute("currentVideoAuthor", author);
        request.getSession().setAttribute("currentVideoDescription", description);
        request.getSession().setAttribute("currentVideoViews", views);
    } 
}