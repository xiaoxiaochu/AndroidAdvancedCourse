package com.go.android.course.communication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.go.android.course.R;
import com.go.android.course.appwidget.NewAppWidgetConfigureActivity;
import com.go.android.course.sensor.MotionAndPositionActivity;
import com.go.android.course.sensor.SensorActivity;

import java.util.List;

/**
 * Created by go on 2018/1/3.
 */

public class CommunicationMainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;

    public  enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //    Intent intent = getIntent();

//        if (intent.getExtras() != null){
//            Intent intent1 = new Intent(this, NewAppWidgetConfigureActivity.class);
//            startActivity(intent1);
//        }

        setContentView(R.layout.activity_song_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        if (findViewById(R.id.song_detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.song_list);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS));




    }


    @Override
    protected void onResume() {
        super.onResume();
       // Intent intent = getIntent();
//        if (intent.getExtras() != null){
//            Intent intent1 = new Intent(this, SensorActivity.class);
//            startActivity(intent1);
//        }
    }

    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <RecyclerView.ViewHolder> {

        private final List<SongUtils.Song> mValues;



        SimpleItemRecyclerViewAdapter(List<SongUtils.Song> items) {
            mValues = items;
        }

        /**
         * This method inflates the layout for the song list.
         * @param parent ViewGroup into which the new view will be added.
         * @param viewType The view type of the new View.
         * @return
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()){
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.song_list_content, parent, false);
                return new ViewHolder(view);
            }else {
                return new ImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_motion_position, parent, false));
            }
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a song title, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the song detail.
         * If mTwoPane is not true, it starts SongDetailActivity
         * using an intent with extra data about which song title was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the song in the array.
         */
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof ViewHolder){

                ViewHolder holder1 = (ViewHolder) holder;

                if (position > 2){
                    position--;
                }
                holder1.mItem = mValues.get(position);
                holder1.mIdView.setText(String.valueOf(position + 1));
                holder1.mContentView.setText(mValues.get(position).song_title);



                holder1.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTwoPane) {
                            int selectedSong = holder.getAdapterPosition();
                            SongDetailFragment fragment =
                                    SongDetailFragment.newInstance(selectedSong);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.song_detail_container, fragment)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                          //  Context context = v.getContext();
                            Intent intent = new Intent(CommunicationMainActivity.this, SensorActivity.class);
//                            intent.putExtra(SongUtils.SONG_ID_KEY,
//                                    holder.getAdapterPosition());
                            startActivity(intent);
                        }
                    }
                });
            }else if (holder instanceof ImgViewHolder){
                ((ImgViewHolder) holder).textView.setText("Another Type");

                ((ImgViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CommunicationMainActivity.this, MotionAndPositionActivity.class);
                        startActivity(intent);
                    }
                });
            }

        }

        /**
         * Get the count of song list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size() + 1;
        }


        @Override
        public int getItemViewType(int position) {
          //  return super.getItemViewType(position);
            return position == 2 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();

        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            SongUtils.Song mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView =view.findViewById(R.id.id);
                mContentView = view.findViewById(R.id.content);
            }
        }

        class ImgViewHolder extends RecyclerView.ViewHolder{
            final TextView textView;

            public ImgViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tx_motion);
            }
        }
    }
}
