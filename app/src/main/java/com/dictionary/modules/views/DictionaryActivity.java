package com.dictionary.modules.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

import com.dictionary.R;
import com.dictionary.modules.viewmodels.DictionaryViewModel;

public class DictionaryActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private DictionaryViewModel dictionaryViewModel;
    private ArrayAdapter<String> adapter;
    private EditText inputText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionaryViewModel = ViewModelProviders.of(this).get(DictionaryViewModel.class);
        dictionaryViewModel.getDefinitionLiveData().observe(this,new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> currencyModels) {
                adapter = new ArrayAdapter<String>(DictionaryActivity.this, R.layout.support_simple_spinner_dropdown_item, currencyModels);

            }
        });

        inputText = findViewById(R.id.edittext);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dictionaryViewModel.getDefinition(inputText.toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dictionaryViewModel.getDefinitionLiveData().removeObservers(this);
    }
}
