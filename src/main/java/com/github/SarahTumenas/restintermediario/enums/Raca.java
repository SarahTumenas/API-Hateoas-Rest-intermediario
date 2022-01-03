package com.github.SarahTumenas.restintermediario.enums;

import java.util.stream.Stream;

public enum Raca {
    HUMANO("humno"),
    ELFO("elfo"),
    ORK("ork"),
    ANAO("anao");

    private String value;

    Raca (String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public static Raca of(String value){
        return Stream.of(Raca.values())
                .filter(it -> it.getValue().equals(value))
                .findFirst().orElseThrow();
    }
}
