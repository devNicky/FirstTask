package com.example.firstyalantistask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IToaster{
    @Bind(R.id.name) TextView name;
    @Bind(R.id.status) TextView status;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.rlCreated) RelativeLayout rlCreated;
    @Bind(R.id.rlRegistered) RelativeLayout rlRegistered;
    @Bind(R.id.rlSolve) RelativeLayout rlSolve;
    @Bind(R.id.rlResponsible) RelativeLayout rlResponsible;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setOCL();
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(addLinks(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    //Returns links List of images from resources
    List<String> addLinks(){
        List<String> links = new ArrayList<>();
        String[] resourceLinks = getResources().getStringArray(R.array.urls);
        for(int i = 0; i<resourceLinks.length; i++){
            links.add(resourceLinks[i]);
        }return links;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Set OnClickListener for all Views and calls setToaster method for ViewGroups
    void setOCL(){
        name.setOnClickListener(this);
        status.setOnClickListener(this);
        description.setOnClickListener(this);
        setToaster(rlCreated);
        setToaster(rlRegistered);
        setToaster(rlSolve);
        setToaster(rlResponsible);
    }

    @Override
    public void onClick(View v) {
        toast(v);
    }

    //set key "back" close application
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //Makes a String with name of pressed control and makes toast with it
    @Override
    public void toast(View view) {
        StringBuilder toastText = new StringBuilder().append(getString(R.string.pressed_view)).append(" ")
                .append(view.getClass().getSimpleName());
        Toast.makeText(this,  toastText , Toast.LENGTH_SHORT).show();
    }

    //Set onClickListener for Views in ViewGroup
    public void setToaster(ViewGroup viewGroup) {
        ViewGroup vg = viewGroup;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setOnClickListener(this);
        }
    }
}