package com.dictionary.modules.models;

import java.io.Serializable;

public class DefinitionModel implements Serializable {
    private String definition;
    private String permalink;
    private int thumbs_up;
    private String author;
    private String word;
    private double defid;
    private String current_vote;
    private String written_on;
    private String example;
    private int thumbs_down;

    public String getDefinition() {
        return definition;
    }

    public int getThumbs_up() {
        return thumbs_up;
    }

    public double getDefid() {
        return defid;
    }

    public String getWord() {
        return word;
    }
}
