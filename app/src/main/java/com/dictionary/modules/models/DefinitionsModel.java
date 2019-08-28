package com.dictionary.modules.models;

import java.io.Serializable;
import java.util.List;

public class DefinitionsModel implements Serializable {
    private List<DefinitionModel> list;

    public List<DefinitionModel> getDefinitionModels() {
        return list;
    }

    public void setList(List<DefinitionModel> list) {
        this.list = list;
    }
}
