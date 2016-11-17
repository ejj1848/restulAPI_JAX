package Exception;


import Model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by ericjohn1 on 11/10/2016.
 */

@Provider
public class PersistenceMapper implements ExceptionMapper<PersistenceException>{

    @Override
    public Response toResponse(PersistenceException ex) {

        if (ex.getCause() instanceof DataNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
         return null;
    }
}
