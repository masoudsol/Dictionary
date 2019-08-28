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

    public int getThumbs_down() {
        return thumbs_down;
    }

    public double getDefid() {
        return defid;
    }

    public String getWord() {
        return word;
    }

    public void setThumbs_up(int thumbs_up) {
        this.thumbs_up = thumbs_up;
    }

    public void setThumbs_down(int thumbs_down) {
        this.thumbs_down = thumbs_down;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
