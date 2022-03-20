package isdcm.webapp2.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("javaee8")
public class JavaEE8Resource {
   
    /**
     * Sample of GET method
     * @param titulo
     * @param autor
     * @param fecha
     * @return
     */
    @Path("getInfo")
    @GET    
    @Produces("text/html")
    public String getInfo (@QueryParam("titulo") String titulo,
                            @QueryParam("autor") String autor,
                            @QueryParam("fecha") String fecha) {
       
        return "<html><head></head> <body> Titulo: " + titulo + " de autor " + autor + " en fecha " + fecha + " </body></html>";
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
     * @param info
     * @param fecha
     * @return
     */
    @Path("putInfo")  
    @PUT    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String putInfo (  @FormParam("info") String info,
                             @FormParam("fecha") String fecha)
    {                
        return "<html><head></head> <body> Informaci&oacute;n recibida " + info + " en fecha " + fecha + " </body></html>";
    }
}