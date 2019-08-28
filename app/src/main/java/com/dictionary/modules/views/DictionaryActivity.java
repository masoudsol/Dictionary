package com.dictionary.modules.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import com.dictionary.R;
import com.dictionary.modules.Adapters.ListRecyclerAdapter;
import com.dictionary.modules.models.DefinitionModel;
import com.dictionary.modules.viewmodels.DictionaryViewModel;

public class DictionaryActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private DictionaryViewModel dictionaryViewModel;
    private ListRecyclerAdapter mAdapter;
    private EditText inputText;
    private Button searchButton;
    private ProgressBar progressBar;
    private boolean sortVisible = false;
    private boolean sortThumbsUp = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ListRecyclerAdapter();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        dictionaryViewModel = ViewModelProviders.of(this).get(DictionaryViewModel.class);
        dictionaryViewModel.getDefinitionLiveData().observe(this,new Observer<List<DefinitionModel>>() {
            @Override
            public void onChanged(@Nullable List<DefinitionModel> definitions) {
                mAdapter.setDefinitionModels(definitions);
                mAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                sortVisible = true;
                invalidateOptionsMenu();
            }
        });

        inputText = findViewById(R.id.edittext);
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int textlength = charSequence.length();
                if (textlength>=1) {
                    searchButton.setEnabled(true);
                } else if (textlength == 0) {
                    searchButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDefinition();

            }
        });

        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dictionaryViewModel.getDefinitionLiveData().removeObservers(this);
        searchButton.setOnClickListener(null);
    }

    private void fetchDefinition(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);
        progressBar.setVisibility(View.VISIBLE);
        dictionaryViewModel.getDefinition(inputText.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        menu.getItem(0).setVisible(sortVisible);
        if (sortThumbsUp) {
            menu.getItem(0).setTitle("Sort by Thumbs up");
        } else {
            menu.getItem(0).setTitle("Sort by Thumbs down");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        dictionaryViewModel.sort(sortThumbsUp);
        fetchDefinition();
        sortThumbsUp = !sortThumbsUp;
        return true;
    }
}
