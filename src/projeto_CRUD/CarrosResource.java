package projeto_CRUD;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.*;
import java.sql.SQLException;
import java.util.*;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;

@Path("/Carros")
@Produces(MediaType.APPLICATION_JSON)
public class CarrosResource {    

    private CarrosDAO dao;
    
    public CarrosResource(CarrosDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<Carros> read() throws SQLException {
        return dao.read();
    }
    
    @POST
    public Carros create(Carros c) {
        return this.dao.create(c);
    }
    
    @GET
    @Path("{id}")
    public Carros readOne(@PathParam("id") LongParam id) {
        long idCarros = id.get();
        // Precisa implementar no DAO
        return null;
    }
    
//
//    @PUT
//    @Path("{id}")
//    public Carros update(@PathParam("id") LongParam id, Carros p) {
//        for (Carros professor: carros) {
//            if (professor.getId() == id.get()) {
//                professor.setNome(p.getNome());
//                professor.setMatricula(p.getMatricula());
//                return professor;
//            }
//        }
//        return null;
//    }
//    
//    @DELETE
//    @Path("{id}")
//    public Response delete(@PathParam("id") LongParam id) {
//        Carros p = null;
//        for (Carros professor: carros) {
//            if (professor.getId() == id.get()) {
//                p = professor;
//                break;
//            }
//        }
//        if (p != null) { 
//            carros.remove(p); 
//        }
//        else {
//            throw new WebApplicationException("Carros com id=" + id.get() 
//                                              + " não encontrado!", 404);
//        }
//        return Response.ok().build();
//    }
}
