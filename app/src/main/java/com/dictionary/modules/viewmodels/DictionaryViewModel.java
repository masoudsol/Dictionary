package com.dictionary.modules.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import com.dictionary.modules.repository.Repository;
import com.services.APIServices;

public class DictionaryViewModel extends AndroidViewModel {
    private MutableLiveData<List<String>> definitionMutableLiveData;
    private Repository repo = Repository.getInstance();
    private APIServices apiServices;

    public DictionaryViewModel(@NonNull Application application) {
        super(application);

        definitionMutableLiveData = new MutableLiveData<>();
        apiServices = new APIServices(application);

    }

    public void getDefinition(String word) {
        apiServices.getDefinition(word, new APIServices.CompletionListener() {
            @Override
            public void onCompletion(Boolean success, Exception error) {
                if (success) {
                    definitionMutableLiveData.postValue(repo.getCurrencyNames());
                }
            }
        });
    }

    public LiveData<List<String>> getDefinitionLiveData() {
        return definitionMutableLiveData;
    }
}
