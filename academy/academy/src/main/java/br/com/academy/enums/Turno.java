package br.com.academy.enums;

public enum Turno {

    MANHA("MANHÃ"),
    TARDE("TARDE"),
    NOITE("NOITE");

    private String turno;

    private Turno (String turno) {

        this.turno = turno;

    }

}