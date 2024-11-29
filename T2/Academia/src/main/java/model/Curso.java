package model;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Curso {
    @BsonProperty("name")
    private String nombre;
    @BsonProperty("sig")
    private String siglas;
    @BsonProperty("level")
    private int nivel;

    // {name: Desarrollo, sig: DAM, level: 2}
}
