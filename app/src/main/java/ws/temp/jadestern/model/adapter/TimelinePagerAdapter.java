package ws.temp.jadestern.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ws.temp.jadestern.fragment.TimelineBaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimelinePagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = TimelinePagerAdapter.class.getSimpleName();

    private ArrayList<TimelineBaseFragment> fragments = new ArrayList<>();

    public TimelinePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public TimelineBaseFragment getContent(int position) {
        return fragments.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return getContent(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getContent(position).getTabTitle();
    }

    public void add(TimelineBaseFragment fragment) {
        fragments.add(fragment);
    }

    public void addAll(List<TimelineBaseFragment> fs) {
        this.fragments.addAll(fs);
        notifyDataSetChanged();
    }

    public void addAll(TimelineBaseFragment... fs) {
        addAll(Arrays.asList(fs));
    }
}
