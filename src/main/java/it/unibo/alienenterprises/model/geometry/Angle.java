package it.unibo.alienenterprises.model.geometry;

public class Angle {

    private static final int MAX_ANGLE = 360;

    private final int angle;

    public Angle(final int angle) {
        var temp = angle;
        while(temp<0 || temp>MAX_ANGLE){
            if(temp<0){
                temp = MAX_ANGLE - temp;
            } else {
                temp = temp - MAX_ANGLE;
            }
        }
        this.angle = temp;
    }

    public int getValue() {
        return angle;
    }   
}
