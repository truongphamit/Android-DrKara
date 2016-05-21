package com.truongpq.drkara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truongpq.drkara.R;
import com.truongpq.drkara.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    public static final String TABLE_ARIRANG = "ZSONG_ARIRANG";
    public static final String TABLE_CALIFORNIA = "ZSONG_CALIFORNIA";
    public static final String TABLE_MUSICCORE = "ZSONG_MUSICCORE";
    public static final String TABLE_VIETKTV = "ZSONG_VIETKTV";

    // Define listener member variable
    private static OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Song> songs;

    public SongsAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View songView = inflater.inflate(R.layout.item_song, parent, false);

        ViewHolder viewHolder = new ViewHolder(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = songs.get(position);

        TextView tvSongID = holder.tvSongID;
        tvSongID.setText(song.getSongID());

        TextView tvSongName = holder.tvSongName;
        tvSongName.setText(song.getName());

        TextView tvMusican = holder.tvMusican;
        tvMusican.setText(song.getMusician());

        TextView tvLyric = holder.tvLyric;
        tvLyric.setText(song.getLyric());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSongID;
        public TextView tvSongName;
        public TextView tvMusican;
        public TextView tvLyric;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvSongID = (TextView) itemView.findViewById(R.id.tv_song_id);
            tvSongName = (TextView) itemView.findViewById(R.id.tv_song_name);
            tvMusican = (TextView) itemView.findViewById(R.id.tv_musican);
            tvLyric = (TextView) itemView.findViewById(R.id.tv_lyric);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    public void setFilter(List<Song> songs) {
        this.songs = new ArrayList<>();
        this.songs.addAll(songs);
        notifyDataSetChanged();
    }
}
