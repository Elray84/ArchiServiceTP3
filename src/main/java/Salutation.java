import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Path("/data")
public class Salutation {

    private Map<Integer, HashMap<String, String>> data = new HashMap<Integer, HashMap<String, String>>();
    private static int ID = 0;

    @GET
    @Produces("application/json")
    public Map<String, String> getSalutation(@QueryParam("ID") int ID){
        if(data.containsKey(ID)){
            return data.get(ID);
        }
        Map<String, String> err = new HashMap<String, String>();
        err.put("nom", "404");
        return err;
    }

    @POST
    @Produces("text/plain")
    public int postSalutation(@FormParam("nom") String nom, @FormParam("date") int date){
        int ID = Salutation.ID ++;
        HashMap<String, String> data_piece = new HashMap<String, String>();
        data_piece.put("nom", nom);
        data_piece.put("date", Integer.toString(date));
        data.put(ID, data_piece);
        return 201;
    }

    @PUT
    @Produces("text/plain")
    public int putSalutation(@QueryParam("ID") int ID, @FormParam("nom") String nom, @FormParam("date") int date){
        if(data.containsKey(ID)){
            HashMap<String, String> data_piece = new HashMap<String, String>();
            data_piece.put("nom", nom);
            data_piece.put("date", Integer.toString(date));
            data.put(ID, data_piece);
            return 201;
        }
        return 404;
    }

    @DELETE
    @Produces("text/plain")
    public int deleteSalutation(@QueryParam("ID") int ID){
        if(data.containsKey(ID)){
            data.remove(ID);
            return 200;
        }
        return 404;
    }

    public static void main(String[] args) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(Salutation.class);
        sf.setResourceProvider(
                Salutation.class,
                new SingletonResourceProvider(new Salutation())
        );
        sf.setAddress("http://localhost:9001/");
        sf.setProvider(new JacksonJaxbJsonProvider());
        sf.create();

        System.out.println("Saisir car+return pour stopper le serveur");
        new Scanner(System.in).next();

        System.out.println("Fin");
    }
}
