package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.R;

import java.util.ArrayList;

/**
 * Created by mark63 on 23/5/17.
 */

public class ProfileTwoFragment extends Fragment {
    private ArrayList<String> mCategoryIds;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_two, container, false);

        return view;

    }


}
