package Services;

import Database.DatabaseClass;
import Model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ericjohn1 on 11/4/2016.
 */
public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService(){
        profiles.put("Eric", new Profile(1L, "EJJ", "Eric","Johnson" ));
    }

    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName){
        return profiles.get(profileName);

    }
    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profiles.put(profile.getProfileName(),profile);
        return profile;
    }
    public Profile updateProfile(Profile profile){
        if (profile.getId() <= 0){
            return null;
        }
        profiles.put(profile.getProfileName(),profile);
            return profile;
    }
    public Profile deleteProfile(String profileName){
        return profiles.remove(profileName);
    }
}
