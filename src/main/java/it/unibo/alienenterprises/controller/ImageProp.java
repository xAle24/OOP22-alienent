package it.unibo.alienenterprises.controller;

/**
 * ImageProp
 * A class that contains the images of an image
 * It's build to work with snakeyaml
 */
public final class ImageProp {

    private String id;
    private String file;
    private double scale;
    private double height;
    private double width;

    /**
     * Void constructor to work whith yaml
     */
    public ImageProp() {
    }

    /**
     * @return the id relative to the image
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id relative to the image
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the path relative to the image
     */
    public String getFile() {
        return file;
    }

    /**
     * Set the file name of the image
     * 
     * @param file
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * @return the Scale of the image
     */
    public double getScale() {
        return scale;
    }

    /**
     * set the scale of the image
     * 
     * @param scale
     */
    public void setScale(final double scale) {
        this.scale = scale;
    }

    /**
     * @return the height of the image
     */
    public double getHeight() {
        return height;
    }

    /**
     * set the height of the image
     * 
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the width of the image
     */
    public double getWidth() {
        return width;
    }

    /**
     * the width of the image
     * 
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        long temp;
        temp = Double.doubleToLongBits(scale);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ImageProp other = (ImageProp) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (file == null) {
            if (other.file != null) {
                return false;
            }
        } else if (!file.equals(other.file)) {
            return false;
        }
        if (Double.doubleToLongBits(scale) != Double.doubleToLongBits(other.scale)) {
            return false;
        }
        return true;
    }

}
