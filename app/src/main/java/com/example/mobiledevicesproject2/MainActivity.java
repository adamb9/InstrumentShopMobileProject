package com.example.mobiledevicesproject2;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    String stringInstruments[];
    String stringPrices[];
    int stringImages[] = {R.drawable.acousticguitar, R.drawable.electricguitar, R.drawable.bass, R.drawable.banjo, R.drawable.violin} ;

    String percussionInstruments[];
    String percussionPrices[];
    int percussionImages[] = {R.drawable.drums, R.drawable.electricdrums, R.drawable.bongos, R.drawable.tambourine, R.drawable.xylophone};

    String keyInstruments[];
    String keyPrices[];
    int keyImages[] = {R.drawable.keyboard, R.drawable.synth, R.drawable.grandpiano, R.drawable.electricorgan, R.drawable.accordion};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringInstruments = getResources().getStringArray(R.array.stringInstruments);
        stringPrices = getResources().getStringArray(R.array.stringPrices);
        percussionInstruments = getResources().getStringArray(R.array.percussionInstruments);
        percussionPrices = getResources().getStringArray(R.array.percussionPrices);
        keyInstruments = getResources().getStringArray(R.array.keysInstruments);
        keyPrices = getResources().getStringArray(R.array.keysPrices);

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
            //noinspection ResultOfMethodCallIgnored
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
        adapter.addFrag(new DummyFragment(stringInstruments, stringPrices, stringImages), "String");
        adapter.addFrag(new DummyFragment(percussionInstruments, percussionPrices, percussionImages), "Percussion");
        adapter.addFrag(new DummyFragment(keyInstruments, keyPrices, keyImages), "Keys");
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        tabLayout = findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        toast("1");
                        break;
                    case 1:
                        toast("2");
                        break;
                    case 2:
                        toast("3");
                        break;
                }
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
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                // do your code
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