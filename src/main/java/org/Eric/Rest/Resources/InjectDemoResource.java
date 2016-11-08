package org.Eric.Rest.Resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by ericjohn1 on 11/7/2016.
 */
@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotation(@MatrixParam("param") String matrixParam,
                                           @HeaderParam("authToken") String header){
        return "Matrix Param: " + matrixParam + " Header Param: " + header;
    }
    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = headers.getCookies().toString();
        return "Path " + path + "Cookies " +cookies;

    }
}
