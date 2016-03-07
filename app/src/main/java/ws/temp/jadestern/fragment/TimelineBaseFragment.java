package ws.temp.jadestern.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import ws.temp.jadestern.R;
import ws.temp.jadestern.model.Util;
import ws.temp.jadestern.model.adapter.TimelineRecyclerViewAdapter;

public class TimelineBaseFragment extends Fragment {
    private static final String TAG = TimelineBaseFragment.class.getSimpleName();

    @Bind(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;

    public TimelineRecyclerViewAdapter recyclerViewAdapter;

    @Getter
    @Setter
    private String tabTitle = "hoge";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setColorSchemeResources(R.color.blue_300);
        swipeRefreshLayout.setOnRefreshListener(this::refreshItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new TimelineRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.addAll(
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet(),
            new TimelineRecyclerViewAdapter.Tweet()
        );

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void refreshItems() {
        // TODO: 16/03/07 implements
        Log.d(TAG, "refreshItems: not implements");

        Util.setTimeout(4000, this::onItemsLoadComplete);
    }

    public void onItemsLoadComplete() {
        // TODO: 16/03/07 implements
        Log.d(TAG, "onItemsLoadComplete: not implements");
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
