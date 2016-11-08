package org.Eric.Rest.Resources;

import Model.Profile;
import Services.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ericjohn1 on 11/4/2016.
 */
@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces((MediaType.APPLICATION_JSON))
public class ProfileResource {

    private ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles(){
        return profileService.getAllProfiles();
    }
    @POST
    public Profile addProfile(Profile profile){
        return profileService.addProfile(profile);
    }
    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){
        return profileService.getProfile(profileName);
    }
    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(Profile profile, @PathParam("profileName") String profileName){
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }
    @DELETE
    @Path("/{profileName}")
    public void deleteProfile(@PathParam("profileName") String profileName){
        profileService.deleteProfile(profileName);
    }
}
