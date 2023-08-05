package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;

/**
 * Implementation of PowerUpRenderer.
 */
public class PowerUpRendererImpl implements PowerUpRenderer {

    private PowerUp correspondingPwu;
    private String id;
    private String name;
    private String description;
    private String image;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPwu(final PowerUp pwu) {
        this.correspondingPwu = pwu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp getPwu() {
        return this.correspondingPwu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return this.image;
    }

}
