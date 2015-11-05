package exdesystems.se.thormobile50.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.android.gms.maps.SupportMapFragment;

import exdesystems.se.thormobile50.R;

public class MapOverLayActivity extends FragmentActivity {
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_over_lay);

        MapOverLayFragment mOLF1 = new MapOverLayFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment1, mOLF1).commit();

        MapOverLayFragment mOLF2 = new MapOverLayFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2, mOLF2).commit();

        MapOverLayFragment mOLF3 = new MapOverLayFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment3, mOLF3).commit();

        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.actionbar);

        getActionBar().getCustomView().findViewById(R.id.changeLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnapDialog();
            }
        });

        getActionBar().getCustomView().findViewById(R.id.changeContent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeContent();
            }
        });

    }

    private void changeLayout(int layout) {

        findViewById(R.id.fragment1).setBackgroundColor(Color.BLUE);
        findViewById(R.id.fragment2).setBackgroundColor(Color.YELLOW);
        findViewById(R.id.fragment3).setBackgroundColor(Color.RED);
        switch(layout)
        {

            case 1:
                findViewById(R.id.parent1).setVisibility(View.VISIBLE);
                findViewById(R.id.parent1).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,30));

                findViewById(R.id.parent2).setVisibility(View.VISIBLE);
                findViewById(R.id.parent2).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,0));

                findViewById(R.id.parent3).setVisibility(View.VISIBLE);
                findViewById(R.id.parent3).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,0));
                break;
            case 2:
                findViewById(R.id.parent1).setVisibility(View.VISIBLE);
                findViewById(R.id.parent1).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 20));

                findViewById(R.id.parent2).setVisibility(View.VISIBLE);
                findViewById(R.id.parent2).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,10));

                findViewById(R.id.parent3).setVisibility(View.VISIBLE);
                findViewById(R.id.parent3).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0));
                break;
            case 3:
                findViewById(R.id.parent1).setVisibility(View.VISIBLE);
                findViewById(R.id.parent1).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,10));

                findViewById(R.id.parent2).setVisibility(View.VISIBLE);
                findViewById(R.id.parent2).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,10));

                findViewById(R.id.parent3).setVisibility(View.VISIBLE);
                findViewById(R.id.parent3).setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 10));
                break;
        }
    }

    private boolean SwapFragment(int fragmentPosition, android.support.v4.app.Fragment fragmentType)
    {
        try
        {
            getSupportFragmentManager().beginTransaction().replace(fragmentPosition, fragmentType).commit();
        }
        catch (Exception e)
        {
            Log.i("ERROR", "" + e.getMessage());
            return false;
        }
        return true;
    }

    private void changeContent(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.overlay);
        dialog.findViewById(R.id.textView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwapFragment(R.id.fragment1, SupportMapFragment.newInstance());
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwapFragment(R.id.fragment2, SupportMapFragment.newInstance());
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwapFragment(R.id.fragment3, SupportMapFragment.newInstance());
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void SnapDialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.overlay);
        dialog.findViewById(R.id.textView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(1);
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(2);
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(3);
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
