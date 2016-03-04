package ws.temp.jadestern.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import butterknife.Bind;
import butterknife.ButterKnife;
import ws.temp.jadestern.R;
import ws.temp.jadestern.fragment.TimelineFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;


    @Bind(R.id.toolbar)
    public Toolbar toolbar;

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

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, new TimelineFragment())
                .commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ButterKnife.unbind(this);
    }
}
