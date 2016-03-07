package ws.temp.jadestern.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ws.temp.jadestern.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.TimelineViewHolder> {
    private ArrayList<Tweet> tweets = new ArrayList<>();

    public TimelineRecyclerViewAdapter() {
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_timeline_tweet, parent, false);

        TimelineViewHolder holder = new TimelineViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void add(Tweet tweet) {
        this.tweets.add(tweet);
    }

    public void addAll(List<Tweet> ts) {
        this.tweets.addAll(ts);
        notifyDataSetChanged();
    }

    public void addAll(Tweet... ts) {
        addAll(Arrays.asList(ts));
    }

    public static class Tweet {
        public Tweet() {
        }
    }

    public class TimelineViewHolder extends RecyclerView.ViewHolder {
        public TimelineViewHolder(View itemView) {
            super(itemView);
        }
    }
}
