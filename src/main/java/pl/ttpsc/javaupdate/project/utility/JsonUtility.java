package pl.ttpsc.javaupdate.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

public final class JsonUtility {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    private JsonUtility() {
    }

    public static String jsonToString(JSONObject json, String objectName) {
        return (String)json.get(objectName);
    }
}
