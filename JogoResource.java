
package projeto_CRUD;

/**
 *
 *  Welker e Kelly
 */

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/jogo")
@Produces(MediaType.APPLICATION_JSON)
public class JogoResource {

    private JogoDAO dao;

    public JogoResource(JogoDAO dao) {
        this.dao = dao;
    }

    @GET
    public List<Jogo> read() {
        return dao.read();
    }

    @POST
    public Jogo create(Jogo a) {
        return this.dao.create(a);
    }
    
  }
