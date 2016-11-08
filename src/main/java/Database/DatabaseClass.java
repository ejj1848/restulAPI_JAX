package Database;
import Model.Message;
import Model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericjohn1 on 11/3/2016.
 */
public class DatabaseClass{

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long,Message> getMessages(){return messages;
    }
    public static Map<String,Profile>getProfiles(){return profiles;
    }
}
