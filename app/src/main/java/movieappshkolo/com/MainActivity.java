package movieappshkolo.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import movieappshkolo.com.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    //CUSTOM CONSTANT KEYS
    public static final String GENRE_KEY = "GENRE";
    public static final String GENRE_ACTION = "ACTION";
    public static final String GENRE_ADVENTURE = "ADVENTURE";
    public static final String GENRE_DRAMA = "DRAMA";
    public static final String GENRE_COMEDY = "COMEDY";
    public static final String CURRENT_MOVIE = "CURRENT MOVIE";

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CALL INIT TABLAYOUT
        initTabLayout();
        //CALL THE LISTENERS
        initListeners();

    }


    //CUSTOM FRAGMENT PAGER ADAPTER CLASS
    class ViewPagerAdapter extends FragmentPagerAdapter {

        //VIEW PAGER ADAPTER CONSTRUCTOR
        ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        //METHOD THAT RETURNS A FRAGMENT BASED ON THE MOVIE GENRE
        public Fragment getItem(int position) {
            MainFragment mainFragment = new MainFragment();

            switch (position){
                case 0:
                    addFrag(mainFragment,GENRE_KEY,GENRE_ACTION);
                    break;
                case 1:
                    addFrag(mainFragment,GENRE_KEY,GENRE_ADVENTURE);
                    break;
                case 2:
                    addFrag(mainFragment,GENRE_KEY,GENRE_DRAMA);
                    break;
                case 3:
                    addFrag(mainFragment,GENRE_KEY,GENRE_COMEDY);
                    break;
                default:
                    break;
            }

            return mainFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        //CUSTOM METHOD THAT ADDS CREATES A NEW BUNDLE FOR EACH GENRE
        void addFrag(Fragment fragment, String key, String movieGenre){
            Bundle bundle=new Bundle();
            bundle.putString(key,movieGenre);
            fragment.setArguments(bundle);
        }
    }



    // INITIALIZES THE TABLAYOUT AND VIEWPAGER FROM XML LAYOUT
    public void initTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText(GENRE_ACTION));
        tabLayout.addTab(tabLayout.newTab().setText(GENRE_ADVENTURE));
        tabLayout.addTab(tabLayout.newTab().setText(GENRE_DRAMA));
        tabLayout.addTab(tabLayout.newTab().setText(GENRE_COMEDY));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

    }
    //CREATES THE LISTENERS FOR THE TABLAYOUT AND VIEWPAGER
    public void initListeners(){
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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


}
