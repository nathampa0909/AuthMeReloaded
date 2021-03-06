package fr.xephi.authme.datasource;

import fr.xephi.authme.cache.auth.PlayerAuth;
import fr.xephi.authme.security.crypts.HashedPassword;

import java.util.List;

/**
 */
public interface DataSource {

    /**
     * Method isAuthAvailable.
     *
     * @param user String
     *
     * @return boolean
     */
    boolean isAuthAvailable(String user);

    /**
     * Method getPassword.
     *
     * @param user String
     *
     * @return String
     */
    HashedPassword getPassword(String user);

    /**
     * Method getAuth.
     *
     * @param user String
     *
     * @return PlayerAuth
     */
    PlayerAuth getAuth(String user);

    /**
     * Method saveAuth.
     *
     * @param auth PlayerAuth
     *
     * @return boolean
     */
    boolean saveAuth(PlayerAuth auth);

    /**
     * Method updateSession.
     *
     * @param auth PlayerAuth
     *
     * @return boolean
     */
    boolean updateSession(PlayerAuth auth);

    /**
     * Method updatePassword.
     *
     * @param auth PlayerAuth
     *
     * @return boolean
     */
    boolean updatePassword(PlayerAuth auth);

    boolean updatePassword(String user, HashedPassword password);

    /**
     * Method purgeDatabase.
     *
     * @param until long
     *
     * @return int
     */
    int purgeDatabase(long until);

    /**
     * Method autoPurgeDatabase.
     *
     * @param until long
     *
     * @return List of String
     */
    List<String> autoPurgeDatabase(long until);

    /**
     * Method removeAuth.
     *
     * @param user String
     *
     * @return boolean
     */
    boolean removeAuth(String user);

    /**
     * Method updateQuitLoc.
     *
     * @param auth PlayerAuth
     *
     * @return boolean
     */
    boolean updateQuitLoc(PlayerAuth auth);

    /**
     * Method getIps.
     *
     * @param ip String
     *
     * @return int
     */
    int getIps(String ip);

    /**
     * Method getAllAuthsByName.
     *
     * @param auth PlayerAuth
     *
     * @return List of String
     */
    List<String> getAllAuthsByName(PlayerAuth auth);

    /**
     * Method getAllAuthsByIp.
     *
     * @param ip String
     *
     * @return List of String * @throws Exception
     */
    List<String> getAllAuthsByIp(String ip);

    /**
     * Method getAllAuthsByEmail.
     *
     * @param email String
     *
     * @return List of String * @throws Exception
     */
    List<String> getAllAuthsByEmail(String email);

    /**
     * Method updateEmail.
     *
     * @param auth PlayerAuth
     *
     * @return boolean
     */
    boolean updateEmail(PlayerAuth auth);

    void close();

    void reload();

    /**
     * Method purgeBanned.
     *
     * @param banned List of String
     */
    void purgeBanned(List<String> banned);

    /**
     * Method getType.
     *
     * @return DataSourceType
     */
    DataSourceType getType();

    /**
     * Method isLogged.
     *
     * @param user String
     *
     * @return boolean
     */
    boolean isLogged(String user);

    /**
     * Method setLogged.
     *
     * @param user String
     */
    void setLogged(String user);

    /**
     * Method setUnlogged.
     *
     * @param user String
     */
    void setUnlogged(String user);

    void purgeLogged();

    /**
     * Method getAccountsRegistered.
     *
     * @return int
     */
    int getAccountsRegistered();

    /**
     * Method updateName.
     *
     * @param oldOne String
     * @param newOne String
     */
    void updateName(String oldOne, String newOne);

    /**
     * Method getAllAuths.
     *
     * @return List of PlayerAuth
     */
    List<PlayerAuth> getAllAuths();

    /**
     * Method getLoggedPlayers.
     *
     * @return List of PlayerAuth
     */
    List<PlayerAuth> getLoggedPlayers();

    enum DataSourceType {
        MYSQL,
        FILE,
        SQLITE
    }
}
