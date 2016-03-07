package ws.temp.jadestern.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import butterknife.Bind;
import butterknife.ButterKnife;
import ws.temp.jadestern.R;
import ws.temp.jadestern.fragment.TimelineBaseFragment;
import ws.temp.jadestern.model.AnalLogger;
import ws.temp.jadestern.model.adapter.TimelinePagerAdapter;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.main_pager)
    public ViewPager pager;

    @Bind(R.id.pagerTitle)
    public PagerTitleStrip pagerTitleStrip;

    public TimelinePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> {
            drawerLayout.openDrawer(Gravity.LEFT);
        });

        pagerAdapter = new TimelinePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        // TODO: 16/03/07 loading config and deployment the tab
        pagerAdapter.addAll(
            new TimelineBaseFragment(),
            new TimelineBaseFragment(),
            new TimelineBaseFragment(),
            new TimelineBaseFragment()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnalLogger.trackScreenView(R.string.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ButterKnife.unbind(this);
    }
}
