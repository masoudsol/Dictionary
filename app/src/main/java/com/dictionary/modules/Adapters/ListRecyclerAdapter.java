package com.dictionary.modules.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dictionary.R;
import com.dictionary.modules.models.DefinitionModel;

import java.util.ArrayList;
import java.util.List;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.MyViewHolder> {
    private List<DefinitionModel> definitionModels;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView definition;
        TextView thumbsup;
        MyViewHolder(RelativeLayout v) {
            super(v);
            definition = v.findViewById(R.id.definition);
            thumbsup = v.findViewById(R.id.thumbsup);
        }
    }

    public ListRecyclerAdapter() {
        this.definitionModels = new ArrayList<>();
    }

    public void setDefinitionModels(List<DefinitionModel> definitionModels) {
        this.definitionModels = definitionModels;
    }

    @NonNull
    @Override
    public ListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_definition_list_item, viewGroup, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRecyclerAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.definition.setText(definitionModels.get(i).getDefinition());
        viewHolder.thumbsup.setText(""+definitionModels.get(i).getThumbs_up());
    }

    @Override
    public int getItemCount() {
        return definitionModels.size();
    }
}
