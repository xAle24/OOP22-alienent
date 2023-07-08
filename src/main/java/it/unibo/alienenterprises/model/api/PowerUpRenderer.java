package it.unibo.alienenterprises.model.api;

/*
 * PowerUpRenderer.
 */
public interface PowerUpRenderer {

    /**
     * set the corresponding PWU.
     * 
     * @param name the PWU
     * @return
     */
    void setPwu(PowerUp pwu);

    /**
     * 
     * set the PWU corresponding id.
     * 
     * @param id the id of the PWU
     * @return
     */
    void setId(String id);

    /**
     * 
     * set the PWU corresponding name.
     * 
     * @param name the name of the PWU
     * @return
     */
    void setName(String name);

    /**
     * 
     * set the PWU description.
     * 
     * @param description the PWU description
     * @return
     */
    void setDescription(String description);

    /**
     * 
     * set the PWU image file path.
     * 
     * @param image the PWU image file path
     * @return
     */
    void setImage(String image);

    /**
     * get the corresponding PWU.
     * 
     * @return
     */
    PowerUp getPwu();

    /**
     * 
     * get the PWU corresponding id.
     * 
     * @return the PWU corresponding id
     */
    String getId();

    /**
     * 
     * get the PWU corresponding name.
     * 
     * @return the name of the PWU
     */
    String getName();

    /**
     * 
     * get the PWU description.
     * 
     * @return the PWU description
     */
    String getDescription();

    /**
     * 
     * set the PWU image file path.
     * 
     * @return the PWU image file path
     */
    String getImage();

}
