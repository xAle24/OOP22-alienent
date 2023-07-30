package it.unibo.alienenterprises.controller;

public class Parameter {

    public static String TYPE = "type";
    public static String VALUE = "value";

    public enum ParameterTypes {
        CLASS,
        METHOD;
    }
    private String type;
    private Object value;

    public Parameter() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ComponentProp [type=" + type + ", value=" + value + "]";
    }


}
