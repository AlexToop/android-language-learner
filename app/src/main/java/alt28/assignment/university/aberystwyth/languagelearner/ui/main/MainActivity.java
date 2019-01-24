/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.main;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.MenuViewModel;


/**
 * Starts on app launch
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private View floatingActionButton;
    MenuViewModel menuViewModel;
    Intent chooseLanguageIntent;


    /**
     * Initialises app
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionButton = findViewById(R.id.fab_add);
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        chooseLanguageIntent = new Intent(this, LanguagePickerActivity.class);

        setupToolbar();
        setupPager();
        ensureLanguageIsDefined();
    }


    /**
     * Deals with pager and related tabs
     */
    private void setupPager() {
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(addPageChangeListener());
    }


    /**
     * User should be redirected to define their languages if they have not already
     */
    private void ensureLanguageIsDefined() {
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;
        sharedPref = getSharedPreferences("LlPreferences", Context.MODE_PRIVATE);
        if (sharedPref.getBoolean("app_need_lang_def", true) == true) {
            startActivity(chooseLanguageIntent);
        }
    }


    /**
     * Ensures navigation drawer can be used
     */
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawer,
                        toolbar,
                        R.string.nav_open_navigation_drawer,
                        R.string.nav_close_navigation_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    /**
     * Called when an item in the navigation drawer is selected
     *
     * @param item menuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals(getResources().getText(R.string.nav_change_language))) {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getText(R.string.title_reset_confirm))
                    .setMessage(getResources().getText(R.string.body_reset_confirm))
                    .setNegativeButton(android.R.string.no, null) // Do nothing
                    .setPositiveButton(R.string.generic_okay, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            menuViewModel.deleteAllPhrases();

                            SharedPreferences sharedPref;
                            SharedPreferences.Editor editor;

                            sharedPref = getSharedPreferences("LlPreferences", Context.MODE_PRIVATE);
                            editor = sharedPref.edit();

                            editor.remove("user_native_language");
                            editor.remove("user_learn_language");
                            editor.remove("app_need_lang_def");
                            editor.apply();

                            startActivity(chooseLanguageIntent);
                        }
                    }).create().show();
        }
        if (item.getTitle().equals("About")) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }
        return true;
    }


    /**
     * Ensures fab viability dependant on current page
     * Adaptation of Chris Loftus's provided workshop material faaversion7 26/08/2018
     *
     * @return ViewPager.OnPageChangeListener
     */
    private ViewPager.OnPageChangeListener addPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }


            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        floatingActionButton.setVisibility(View.VISIBLE);
                        break;
                    default:
                        floatingActionButton.setVisibility(View.GONE);
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }

}
