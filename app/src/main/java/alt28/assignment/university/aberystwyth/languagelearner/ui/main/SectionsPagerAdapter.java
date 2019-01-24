/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.ui.learn.LearnFragment;
import alt28.assignment.university.aberystwyth.languagelearner.ui.phrases.PhrasesFragment;


/**
 * Manages transitions between fragments
 * Adapted from Chris Loftus's provided workshop material
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    android.content.res.Resources resources;


    /**
     * Constructor for the adapter
     *
     * @param fragmentManager
     * @param resources
     */
    public SectionsPagerAdapter(FragmentManager fragmentManager, android.content.res.Resources resources) {
        super(fragmentManager);
        this.resources = resources;
    }


    /**
     * Returns title of current fragment
     *
     * @param position
     * @return CharSequence
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getText(R.string.phrases_tab);
            case 1:
                return resources.getText(R.string.learn_tab);
        }
        return null;
    }


    /**
     * Returns the fragment at specified index
     *
     * @param position
     * @return Fragment
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhrasesFragment();
            case 1:
                return new LearnFragment();
        }
        return null;
    }


    /**
     * Getter for number of pages
     *
     * @return int
     */
    @Override
    public int getCount() {
        return 2;
    }
}
