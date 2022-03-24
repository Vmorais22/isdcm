package isdcm.webapp2.resources;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import model.Video;

/**
 *
 * @author
 */
@Path("javaee8")
public class JavaEE8Resource {
   
    /**
     * Sample of GET method
     * @param id
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Path("getInfo")
    @GET    
    @Produces("text/html")
    public String getInfo (@QueryParam("id") String id) throws ClassNotFoundException, SQLException {
        new Video().updateVideoById(Integer.parseInt(id));
        return "<html><head></head> <body> Titulo: " + id + "</body></html>";
    }
   
    /**
     * Sample of POST method
     *
     * @param info
     * @param fecha
     * @return
     */
    @Path("postInfo")  
    @POST    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String postInfo (  @FormParam("info") String info,
                              @FormParam("fecha") String fecha)
    {                
        return "<html><head></head> <body> Informaci&oacute;n recibida " + info + " en fecha " + fecha + " </body></html>";
    }    
   
    /**
     * Sample of PUT method
     *
     * @param id
     * @return
     */
    @Path("putInfo")  
    @PUT    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String putInfo (@QueryParam("id") String id) 
    {    
        System.out.print("entro en el putInfo con id:" + id);
        try
        {
           String videoId = id.substring(0,id.length()-1);
           new Video().updateVideoById(Integer.parseInt(videoId)); 
        }
        catch(ClassNotFoundException | NumberFormatException | SQLException e){
            System.err.print("errorcito al canto causado por: " + e);
        }
        
        return "<html><head></head> <body> Informaci&oacute;n recibida " + id + " en fecha " + id + " </body></html>";
    }
}

