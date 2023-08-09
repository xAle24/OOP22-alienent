package it.unibo.alienenterprises.controller;

public class ImageProp {

    private String id;
    private String file;
    private double scale;
    private double height;
    private double width;

    public ImageProp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(final String file) {
        this.file = file;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(final double scale) {
        this.scale = scale;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        ImageProp other = (ImageProp) obj;
        if (id == null) {
            if (other.id != null){
                return false;
            }
        } else if (!id.equals(other.id)){
            return false;
        }
        if (file == null) {
            if (other.file != null){
                return false;
            }
        } else if (!file.equals(other.file)){
            return false;
        }
        if (Double.doubleToLongBits(scale) != Double.doubleToLongBits(other.scale)){
            return false;
        }
        return true;
    }

}
