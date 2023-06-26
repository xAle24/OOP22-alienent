package it.unibo.alienenterprises.model.api;

import java.util.Optional;

import it.unibo.alienenterprises.model.UserAccountImpl;

/**
 * UserAccountHandler.
 */
public interface UserAccountHandler {

    /**
     * 
     * Loads all the user information from the saving file.
     * 
     * @param nickname account's nickname. It is used to differentiate the saving
     *                 files
     * @param password
     * @return an implementation of UserAccountImpl if everything is right,
     *         otherwise an optional empty
     * 
     */
    Optional<UserAccountImpl> login(String nickname, String password);

    /**
     * 
     * A new account and following file is created.
     * 
     * @param nickname account's nickname. It is used to differentiate the saving
     *                 files
     * @param password
     * @return an implementation of UserAccountImpl
     * @throws IOException
     * 
     */
    Optional<UserAccountImpl> registration(String nickname, String password);

    /**
     * 
     * Saves all the user information in the saving file.
     * 
     * @param account the account from which the methos takes the information to be
     *                saved
     * @return
     * 
     */
    void save(UserAccountImpl account);

}
