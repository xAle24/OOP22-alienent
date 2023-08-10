package it.unibo.alienenterprises.model.api;

/**
 * PowerUpRenderer.
 * This stores the informations that will be used to set up the view.
 * 
 * @author Ginevra Bartolini
 */
public interface PowerUpRenderer {

    /**
     * Sets the corresponding power up.
     * 
     * @param pwu the power up
     */
    void setPwu(PowerUp pwu);

    /**
     * 
     * Sets the power up corresponding id.
     * 
     * @param id the id of the power up
     */
    void setId(String id);

    /**
     * 
     * Sets the power up corresponding name.
     * 
     * @param name the name of the power up
     */
    void setName(String name);

    /**
     * 
     * Sets the power up description.
     * 
     * @param description the power up description
     */
    void setDescription(String description);

    /**
     * 
     * Sets the power up image file name.
     * 
     * @param image the power up image file name
     */
    void setImage(String image);

    /**
     * Gets the corresponding power up.
     * 
     * @return the corresponding PwU
     */
    PowerUp getPwu();

    /**
     * 
     * Gets the power up corresponding id.
     * 
     * @return the power up corresponding id
     */
    String getId();

    /**
     * 
     * Gets the power up corresponding name.
     * 
     * @return the name of the power up
     */
    String getName();

    /**
     * 
     * Gets the power up description.
     * 
     * @return the power up description
     */
    String getDescription();

    /**
     * 
     * Gets the power up image file name.
     * 
     * @return the power up image file name
     */
    String getImage();

}
