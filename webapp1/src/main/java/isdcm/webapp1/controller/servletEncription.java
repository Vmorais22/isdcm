/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.webapp1.controller;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "servletEncription", urlPatterns = {"/servletEncription"})
public class servletEncription extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("llegamos al get de servletEnription");
        ArrayList<Video> list = (ArrayList<Video>) request.getSession().getAttribute("listVideos");
        System.out.println("la list pasada como session attribute tiene un size de: " + list.size());
        int indexTratado = Integer.parseInt(request.getParameter("vid").substring(0, request.getParameter("vid").length()-1));
        System.out.println("index tratado: " + indexTratado);
        boolean encrypted = list.get(indexTratado).getEncripted();
        System.out.println("esta encriptado? "+ encrypted);
        list.get(indexTratado).setEncripted(!encrypted);
        request.getSession().setAttribute("listVideos", list);
        response.sendRedirect("/webapp1/jsp/listadoVid.jsp");
    }
}
