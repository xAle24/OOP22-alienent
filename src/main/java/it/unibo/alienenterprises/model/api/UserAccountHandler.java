package it.unibo.alienenterprises.model.api;

import java.io.IOException;

import it.unibo.alienenterprises.model.UserAccountImpl;

public interface UserAccountHandler {

    /**
     * 
     * Loads all the user information from the saving file.
     * If the saving files does not exist, a new account and following file is
     * created
     * 
     * @param nickname account's nickname. It is used to differentiate the saving
     *                 files
     * @return an implementation of UserAccountImpl
     * @throws IOException
     * 
     */
    UserAccountImpl load(String nickname, String password) throws IOException;

    /**
     * 
     * Saves all the user information in the saving file
     * 
     * @param account the account from which the methos takes the information to be
     *                saved
     * @return
     * 
     */
    void save(UserAccountImpl account);

}
