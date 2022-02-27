package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletProfileUsu extends HttpServlet {

    /**
     * servlet init without parameters
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {

    }

    /**
     * servlet init with parameters
     *
     * @param conf
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ... codigo para una peticion GET
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ... codigo para una peticion POST
    }
}
