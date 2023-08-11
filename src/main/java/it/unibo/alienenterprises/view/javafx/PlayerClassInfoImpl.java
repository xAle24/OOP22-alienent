package it.unibo.alienenterprises.view.javafx;

/**
 * PlayerClassInfoImpl.
 */
public class PlayerClassInfoImpl implements PlayerClassInfo {

    private String name;
    private String description;

    /**
     * Void constuctor to be used with yaml.
     */
    @SuppressWarnings("PMD.UnnecessaryConstructor")
    public PlayerClassInfoImpl() {
        // this Constructor is needed to work whith snakeyaml
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * setter for the name.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * setter for the description.
     * 
     * @param desciption
     */
    public void setDescription(final String desciption) {
        this.description = desciption;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PlayerClassInfoImpl [name=" + name + ", description=" + description + "]";
    }

}
