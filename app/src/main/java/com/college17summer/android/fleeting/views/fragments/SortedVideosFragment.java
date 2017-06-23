package com.college17summer.android.fleeting.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.college17summer.android.fleeting.R;
import com.college17summer.android.fleeting.adapters.VideoListAdapter;
import com.college17summer.android.fleeting.models.VideoEntity;
import com.college17summer.android.fleeting.models.VideoLabEntity;

import java.util.List;

/**
 * Created by zgh on 17-6-19.
 */

public class SortedVideosFragment extends Fragment {
    private static final String ARG_KIND = "mKind";
    private RecyclerView recyclerView;
    private VideoListAdapter adapter;

    public static SortedVideosFragment newInstance(String mKind) {
        SortedVideosFragment fragment = new SortedVideosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KIND, mKind);
        fragment.setArguments(args);
        return fragment;
    }



    // Update UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.fragment_list_sorted, container, false);
        recyclerView = (RecyclerView) result.findViewById(R.id.sorted_video_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return result;
    }

    // TODO: Implement concrete functions
    private void updateUI() {
        List<VideoEntity> videoEntityList = VideoLabEntity.get(getActivity()).getVideos();
        adapter = new VideoListAdapter(getContext(), videoEntityList);
        recyclerView.setAdapter(adapter);
    }
}
