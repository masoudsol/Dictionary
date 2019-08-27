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
    private DefinitionsModel definitionsModel;
//    private List<DefinitionModel> currencies = new ArrayList<>();
    private HashMap<String, DefinitionModel> currencyHashMap = new HashMap<>();

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public synchronized void setCurrencyModels(DefinitionsModel definitionsModel) {
        List<DefinitionModel> definitionModelList = definitionsModel.getDefinitionModels();
        Collections.sort(definitionModelList, new Comparator<DefinitionModel>() {
            @Override
            public int compare(DefinitionModel definitionModel, DefinitionModel t1) {
                if (definitionModel != null && t1 != null) {
                    return definitionModel.getThumbs_up() - t1.getThumbs_up();
                } else {
                    return 0;
                }
            }
        });

        this.definitionsModel = definitionsModel;
//        currencies = definitionModelList;
        for (DefinitionModel definitionModel : definitionModelList) {
            currencyHashMap.put(Double.toString(definitionModel.getDefid()), definitionModel);
        }
    }

    public List<String> getCurrencyNames() {
        List<String> currencyNames = new ArrayList<>();
//        for (DefinitionModel definitionModel : currencies) {
//            currencyNames.add(definitionModel.getName());
//        }
        return currencyNames;
    }

//    public String getConversionRate(int index){
//        return currencies.get(index).getPrice();
//    }
//
//    public String getISOCode(int index){
//        return currencies.get(index).getIso_code();
//    }
//
//    public String getConversionRate(String isoCode){
//        return currencyHashMap.get(isoCode).getPrice();
//    }

//    public DefinitionsModel getDefinitionsModel() {
//        return definitionsModel;
//    }
}











