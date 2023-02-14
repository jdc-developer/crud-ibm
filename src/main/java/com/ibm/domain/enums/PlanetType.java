package com.ibm.domain.enums;

public enum PlanetType {

    GAS_GIANT(1, "Gas Giant"),
    ICE_GIANT(2, "Ice Giant"),
    TERRESTRIAL(3, "Terrestrial");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code= code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private PlanetType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static PlanetType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for(PlanetType x : PlanetType.values()) {
            if(cod.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
