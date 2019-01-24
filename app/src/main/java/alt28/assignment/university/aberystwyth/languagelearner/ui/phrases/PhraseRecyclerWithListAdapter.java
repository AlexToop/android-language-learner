/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.phrases;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.databinding.RecyclerViewPhraseItemBinding;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;


/**
 * Provides phrases to phrase list recycler
 * Adapted from Chris Loftus's provided workshop material faaversion7 26/08/2018
 */
public class PhraseRecyclerWithListAdapter extends RecyclerView.Adapter<PhraseRecyclerWithListAdapter.ViewHolder> {
    private final Context context;
    private List<Phrase> dataSet;
    private View.OnClickListener clickListener;


    /**
     * Constructor
     *
     * @param context
     */
    PhraseRecyclerWithListAdapter(Context context) {
        this.context = context;
    }


    /**
     * Binds the phrases to the recyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewPhraseItemBinding phraseItemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            phraseItemBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(clickListener);
        }

        void bindDataSet(Phrase phrase) {
            phraseItemBinding.setPhrase(phrase);
        }
    }


    /**
     * Returns the numbers of phrases to display
     *
     * @return int size of the dataset
     */
    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }


    /**
     * Called by the recycler for objects to use
     *
     * @param parent
     * @param viewType
     * @return ViewHolder
     */
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_view_phrase_item, parent, false);
        return new ViewHolder(view);
    }


    /**
     * Populates the view with dataSet phrases
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 int position) {
        if (dataSet != null) {
            holder.bindDataSet(dataSet.get(position));
        }
    }


    /**
     * Lets this class know if data has changed
     *
     * @param dataSet
     */
    void changeDataSet(List<Phrase> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }


    /**
     * Catches click events
     *
     * @param itemClickListener
     */
    void setOnClickListener(View.OnClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}