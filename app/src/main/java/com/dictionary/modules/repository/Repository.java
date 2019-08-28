package com.dictionary.modules.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.dictionary.modules.models.DefinitionModel;
import com.dictionary.modules.models.DefinitionsModel;

/**
 * Singleton pattern
 */
public class Repository {

    private static Repository instance;
    private List<DefinitionModel> definitions = new ArrayList<>();
    private HashMap<String, List<DefinitionModel>> definitionCacheHashMap = new HashMap<>();

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public synchronized void setDefinitionsModel(DefinitionsModel definitionsModel) {
        definitions = definitionsModel.getDefinitionModels();

        if (definitions.size() > 0) {
            definitionCacheHashMap.put(definitions.get(0).getWord().toLowerCase(), definitions);
        }
    }

    public List<DefinitionModel> getDefinitions(String word) {
        definitions = definitionCacheHashMap.get(word.toLowerCase());
        return getDefinitions();
    }

    public List<DefinitionModel> getDefinitions() {
        return definitions;
    }

    public void sort(final boolean sortThumbsUp){
        Collections.sort(definitions, new Comparator<DefinitionModel>() {
            @Override
            public int compare(DefinitionModel definitionModel, DefinitionModel t1) {
                if (definitionModel != null && t1 != null) {
                    if (sortThumbsUp) {
                        return t1.getThumbs_up() - definitionModel.getThumbs_up();
                    } else {
                        return t1.getThumbs_down() - definitionModel.getThumbs_down();
                    }
                } else {
                    return 0;
                }
            }
        });
    }
}











