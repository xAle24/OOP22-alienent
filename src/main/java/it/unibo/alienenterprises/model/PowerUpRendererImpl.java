package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.PowerUp;
import it.unibo.alienenterprises.model.api.PowerUpRenderer;

public class PowerUpRendererImpl implements PowerUpRenderer {

    private PowerUp correspondingPwu;
    private String id;
    private String name;
    private String description;
    private String image;

    @Override
    public void setPwu(PowerUp pwu) {
        this.correspondingPwu = pwu;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public PowerUp getPwu() {
        return this.correspondingPwu;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getImage() {
        return this.image;
    }

}
