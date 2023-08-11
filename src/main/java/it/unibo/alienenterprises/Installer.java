package it.unibo.alienenterprises;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that set ups the forders needed to save accounts and passwords.
 */
public final class Installer {

    private static final String SEP = File.separator;
    private static final Logger LOGGER = LoggerFactory.getLogger(Installer.class);

    /**
     * Directory path for modifiable files of the project.
     */
    public static final String DIRECTORY_PATH = System.getProperty("user.home") + SEP + ".alien-enterprises";
    /**
     * Password file name.
     */
    public static final String PASSWORD_FILE = "password.yml";

    private Installer() {
    }

    /**
     * @param fileName
     * @return if the given file actually exist.
     */
    public static boolean doesFileExist(final String fileName) {
        return Files.exists(Path.of(DIRECTORY_PATH + SEP + fileName));
    }

    private static boolean doesDirectoryExist() {
        return Files.isDirectory(Path.of(DIRECTORY_PATH));
    }

    /**
     * Set up the folders needed to start the program.
     */
    public static void init() {
        if (!doesDirectoryExist()) {
            try {
                final File directory = new File(DIRECTORY_PATH);
                if (!directory.mkdir()) {
                    throw new FileNotFoundException();
                }
                LOGGER.info("Directory succesfully created.");
            } catch (final FileNotFoundException e) {
                LOGGER.error("Could not create directory:", e);
            }
        }
        if (!doesFileExist(PASSWORD_FILE)) {
            final File passwordFile = new File(DIRECTORY_PATH + SEP + PASSWORD_FILE);
            try (FileWriter writer = new FileWriter(passwordFile)) {
                LOGGER.info("password file succesfully created at:" + DIRECTORY_PATH);
            } catch (final IOException e) {
                LOGGER.error("Cannot create password file:", e);
            }
        }
    }

}
