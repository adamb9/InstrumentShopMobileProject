package com.example.mobiledevicesproject2.home;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mobiledevicesproject2.Item_Instrument;
import com.example.mobiledevicesproject2.login.LoginActivity;
import com.example.mobiledevicesproject2.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    private ArrayList<Item_Instrument> stringInstruments = new ArrayList<>();
    private ArrayList<Item_Instrument> percussionInstruments = new ArrayList<>();
    private ArrayList<Item_Instrument> keyInstruments = new ArrayList<>();

    int stringImages[] = {R.drawable.acousticguitar, R.drawable.electricguitar, R.drawable.bass, R.drawable.banjo, R.drawable.violin} ;
    int percussionImages[] = {R.drawable.drums, R.drawable.electricdrums, R.drawable.bongos, R.drawable.tambourine, R.drawable.xylophone};
    int keyImages[] = {R.drawable.keyboard, R.drawable.synth, R.drawable.grandpiano, R.drawable.electricorgan, R.drawable.accordion};

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadInstruments("string_inst.json", stringInstruments, stringImages);
        loadInstruments("percussion_inst.json", percussionInstruments, percussionImages);
        loadInstruments("key_inst.json", keyInstruments, keyImages);

        initToolBar();
        initViewPager();
        initTabLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loadJSONFromAsset(String fileName)
    {
        String json;
        try
        {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadInstruments(String fileName, ArrayList<Item_Instrument> instList, int imageList[])
    {
        String myJSONStr = loadJSONFromAsset(fileName);
        try
        {
            //Get root JSON object node
            JSONObject rootJSONObject = new JSONObject(myJSONStr);
            //Get employee array node
            JSONArray instrumentJSONArray = rootJSONObject.getJSONArray("instrument");
            for (int i = 0; i < instrumentJSONArray.length(); i++)
            {
                //Create a temp object of the instrument model class
                Item_Instrument instrument = new Item_Instrument();
                //Get employee JSON object node
                JSONObject jsonObject = instrumentJSONArray.getJSONObject(i);
                //Get employee details
                instrument.setName((jsonObject.getString("name")));
                instrument.setPrice((jsonObject.getString("price")));
                instrument.setId((jsonObject.getString("id")));
                instrument.setInstrumentImage(imageList[i]);
                //Add employee object to the list
                instList.add(instrument);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);
        // For back navigation button use this
        // if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewpager_home);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummyFragment(stringInstruments), "String");
        adapter.addFrag(new DummyFragment(percussionInstruments), "Percussion");
        adapter.addFrag(new DummyFragment(keyInstruments), "Keys");
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        tabLayout = findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                return true;
            case R.id.action_basket:
                // do your code
                return true;
            case R.id.action_home:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}