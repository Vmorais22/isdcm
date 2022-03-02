package controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Exceptions.UserAlreadyExistsException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletUsuarios extends HttpServlet {

    private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE userId = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("holaaaaa get");
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            
            boolean result = new Usuario().queryTest(username, passwd);
            System.out.println("resultado del get: " + result);
            if (result){
                request.getSession().setAttribute("currentUser", username);
                response.sendRedirect("/webapp1/jsp/profileUsu.jsp");
            }
            else response.sendRedirect(request.getContextPath());    
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("holaaaaa post");
        try {
            System.out.println("holaaaaa post.try");
            if(createNewUser(request)){
                request.getSession().setAttribute("currentUser", request.getParameter("username"));
                response.sendRedirect("/webapp1/jsp/profileUsu.jsp");
            }
            response.setStatus(HttpServletResponse.SC_OK);
            
        } 
        catch (UserAlreadyExistsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println("User already exists");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.err.println("Unexpected error ocurred: " + e);
        }              

        
    }

    private static boolean exists(String username) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM USERS WHERE username = ?");
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery().next();
    }
    
    private boolean createNewUser(HttpServletRequest request) throws UserAlreadyExistsException, MalformedURLException, SQLException, ClassNotFoundException {
        System.out.println("holaaaaa createNewUser");
        if (!exists(request.getParameter("username"))) {
            System.out.println("entro porque no existe");
            
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement preparedStatement = c.prepareStatement("SELECT MAX(USERID) as USERID FROM USERS");
            ResultSet r = preparedStatement.executeQuery();
            
            System.out.println("r:" + r.next());
            Integer userId = r.getInt("USERID")+1;
            System.out.println("userId:" + userId);
            
            Usuario newUser = new Usuario(
                    userId,
                    request.getParameter("username"),
                    request.getParameter("realName"),
                    request.getParameter("surName"),
                    request.getParameter("passwd"),
                    request.getParameter("email"),
                    parseInt(request.getParameter("age")),
                    request.getParameter("description")
                    //,
                    //new URL(request.getParameter("photo"))
            );
            System.out.println("Usuario creado con username: " + newUser.getUserName());
            
            return newUser.storeUserInDb();
        } 
        else 
        {
            throw new UserAlreadyExistsException();
        }
    }
}
