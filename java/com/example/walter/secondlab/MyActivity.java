package com.example.walter.secondlab;

import android.app.Activity;
import android.net.MailTo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.walter.secondlab.core.Planet;
import com.example.walter.secondlab.models.Sphere;
import com.example.walter.secondlab.renders.GlRenderer;
import com.example.walter.secondlab.views.HorizontalListItem;
import com.example.walter.secondlab.views.SolarGLSurfaceView;

import java.util.LinkedList;


public class MyActivity extends Activity{

    private SolarGLSurfaceView solarGLSurfaceView;
    private RelativeLayout planetsContainer, mainFrame;
    private LinearLayout horizontalScrollView;
    private LinkedList<HorizontalListItem> items = new LinkedList<HorizontalListItem>();

    private final Planet[] planets = {new Planet("Earth", R.drawable.earth_for_view, R.drawable.earth, 2), new Planet("Moon", R.drawable.moon_for_view, R.drawable.moon, 1),
            new Planet("Mercury", R.drawable.mercury_for_view, R.drawable.mercury_map, 1), new Planet("Venus", R.drawable.venus_for_view, R.drawable.venus_map, 1), new Planet("Mars", R.drawable.mars_for_view, R.drawable.mars_map, 2),
            new Planet("Jupiter", R.drawable.jupiter_for_view, R.drawable.jupiter_map, 4), new Planet("Saturn", R.drawable.saturn_for_view, R.drawable.saturn_map, 3), new Planet("Uranus", R.drawable.uranus_for_view, R.drawable.uranus_map, 3),
            new Planet("Neptune", R.drawable.neptune_for_view, R.drawable.neptune_map, 3), new Planet("Sun", R.drawable.sun_for_view, R.drawable.sun, 5)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        solarGLSurfaceView = new SolarGLSurfaceView(this);
        planetsContainer = (RelativeLayout) findViewById(R.id.planets_container);
        mainFrame = (RelativeLayout) findViewById(R.id.main_frame);
        planetsContainer.addView(solarGLSurfaceView);

        horizontalScrollView = (LinearLayout) findViewById(R.id.horizontal_view);
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int i = 0; i < planets.length; ++i){
            HorizontalListItem currentLayout = (HorizontalListItem) inflater.inflate(R.layout.list_item, null, false);
            items.add(currentLayout);
            TextView currentText = (TextView) currentLayout.findViewById(R.id.item_label);
            currentText.setText(planets[i].getPlanetName());
            ImageView currentImage = (ImageView) currentLayout.findViewById(R.id.item_picture);
            currentImage.setImageDrawable(getResources().getDrawable(planets[i].getDrawableId()));
            CheckBox currentBox = (CheckBox) currentLayout.findViewById(R.id.item_checkbox);
            currentLayout.setCheckBox(currentBox);
            horizontalScrollView.addView(currentLayout);


        }

        for(int i = 0; i < items.size(); ++i){
            items.get(i).getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    for(int j = 0; j < items.size();  ++j){
                        if(items.get(j).getCheckBox() != (CheckBox)buttonView && isChecked){
                            items.get(j).getCheckBox().setChecked(false);
                        }
                    }
                    for(int j = 0; j < items.size(); ++j){
                        if(items.get(j).getCheckBox().isChecked()) {
                            items.get(j).setBackground(getResources().getDrawable(R.drawable.item_checked));
                            solarGLSurfaceView.getmRenderer().setmMoon(new Sphere(3, planets[j].getRadius(), planets[j].getMapId()));
                            //solarGLSurfaceView.getmRenderer().loadTexture();
                        } else {
                            items.get(j).setBackground(getResources().getDrawable(R.color.black));
                        }
                    }
                }
            });
        }

    }
}
