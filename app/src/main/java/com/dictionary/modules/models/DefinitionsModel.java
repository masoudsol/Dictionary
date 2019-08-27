package com.dictionary.modules.models;

import java.io.Serializable;
import java.util.List;

public class DefinitionsModel implements Serializable {
    private List<DefinitionModel> definitionModels;

    public List<DefinitionModel> getDefinitionModels() {
        return definitionModels;
    }
}
