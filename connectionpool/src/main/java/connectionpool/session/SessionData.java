package connectionpool.session;

import javax.sql.DataSource;
import java.sql.Connection;

public class SessionData {
    private static String sessionID;
    private static DataSource dataSource;

    public static String getSessionID() {
        return sessionID;
    }

    public static void setDataSource(DataSource dataSource) {
        SessionData.dataSource = dataSource;
    }

    public static void setSessionID(String sessionID) {
        SessionData.sessionID = sessionID;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
