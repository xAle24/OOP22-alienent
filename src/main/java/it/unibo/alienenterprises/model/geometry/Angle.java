package it.unibo.alienenterprises.model.geometry;

/**
 * A simple Class to define limits of an angle.
 */
public class Angle {

    /**
     * Define the maximum value that an angle can obtain.
     */
    private static final int MAX_ANGLE = 360;

    private final int angle;

    /**
     * @param angle
     */
    public Angle(final int angle) {
        var temp = angle;
        while (temp < 0 || temp >= MAX_ANGLE) {
            if (temp < 0) {
                temp = MAX_ANGLE - temp;
            } else {
                temp = temp - MAX_ANGLE;
            }
        }
        this.angle = temp;
    }

    /**
     * @return the angle value
     */
    public int getValue() {
        return angle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + angle;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Angle other = (Angle) obj;
        if (angle != other.angle)
            return false;
        return true;
    }

}
