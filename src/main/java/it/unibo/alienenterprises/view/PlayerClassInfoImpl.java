package it.unibo.alienenterprises.view;

public class PlayerClassInfoImpl implements PlayerClassInfo {

    private String name;
    private String description;
    private String spriteFilePath;

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
    public String getSpriteFilePath() {
        return spriteFilePath;
    }

    public void setSpriteFilePath(final String spriteFilePath) {
        this.spriteFilePath = spriteFilePath;
    }

}
