package it.unibo.alienenterprises.controller;

public class PlayerClassInfoImpl implements PlayerClassInfo {

    private String name;
    private String description;

    public PlayerClassInfoImpl() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(final String desciption) {
        this.description = desciption;
    }

    @Override
    public String toString() {
        return "PlayerClassInfoImpl [name=" + name + ", description=" + description + "]";
    }

}
