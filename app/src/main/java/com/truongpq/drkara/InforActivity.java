package com.truongpq.drkara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.truongpq.drkara.databases.DatabasesAdapter;
import com.truongpq.drkara.models.Song;

public class InforActivity extends AppCompatActivity {

    private DatabasesAdapter db;

    private TextView tvID, tvName, tvMusican, tvLyric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        db = new DatabasesAdapter(this, bundle.getString("TABLE_NAME"));

        Song song = db.findByID(bundle.getString("ID"));

        tvID = (TextView) findViewById(R.id.tv_infor_id);
        tvID.setText(song.getSongID());

        tvName = (TextView) findViewById(R.id.tv_infor_name);
        tvName.setText(song.getName());

        tvMusican = (TextView) findViewById(R.id.tv_infor_musican);
        tvMusican.setText(song.getMusician());

        tvLyric = (TextView) findViewById(R.id.tv_infor_lyric);
        tvLyric.setText(song.getLyric());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
