package com.truongpq.drkara.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.truongpq.drkara.DividerItemDecoration;
import com.truongpq.drkara.InforActivity;
import com.truongpq.drkara.R;
import com.truongpq.drkara.adapters.SongsAdapter;
import com.truongpq.drkara.databases.DatabasesAdapter;
import com.truongpq.drkara.models.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaraFragment extends Fragment implements SearchView.OnQueryTextListener {

    private String tableName;

    private DatabasesAdapter db;
    private RecyclerView rvSongs;
    private SongsAdapter adapter;
    private List<Song> songs;
    private List<Song> searchSongs;

    public static KaraFragment newInstance(String tableName) {
        KaraFragment fragment = new KaraFragment();
        Bundle args = new Bundle();
        args.putString("tableName", tableName);
        fragment.setArguments(args);
        return fragment;
    }

    public KaraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableName = getArguments().getString("tableName", SongsAdapter.TABLE_ARIRANG);
        db = new DatabasesAdapter(getActivity(), tableName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kara, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        rvSongs = (RecyclerView) getActivity().findViewById(R.id.rvSongs);
        songs = db.getAll();

        searchSongs = new ArrayList<>();
        searchSongs.clear();
        searchSongs.addAll(songs);

        adapter = new SongsAdapter(songs);
        rvSongs.setAdapter(adapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSongs.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvSongs.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new SongsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getActivity(), InforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("TABLE_NAME", tableName);
                bundle.putString("ID", searchSongs.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.setFilter(songs);
                searchSongs.clear();
                searchSongs.addAll(songs);
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Song> filteredSongList = filter(songs, newText);
        adapter.setFilter(filteredSongList);
        searchSongs.clear();
        searchSongs.addAll(filteredSongList);
        return true;
    }

    private List<Song> filter(List<Song> songs, String newText) {
        newText = newText.toLowerCase();

        List<Song> filteredSongList = new ArrayList<>();
        for (Song song : songs) {
            String name = song.getNameClean().toLowerCase();
            String lyric = song.getLyricClean().toLowerCase();
            String musican = song.getMusicanClean().toLowerCase();

            if (name.contains(newText)) {
                filteredSongList.add(song);
            }
            if (lyric.contains(newText)) {
                filteredSongList.add(song);
            }
            if (musican.contains(newText)) {
                filteredSongList.add(song);
            }
        }
        return filteredSongList;
    }
}
