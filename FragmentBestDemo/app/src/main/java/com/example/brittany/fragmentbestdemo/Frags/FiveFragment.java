package com.example.brittany.fragmentbestdemo.Frags;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brittany.fragmentbestdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiveFragment extends Fragment {


    public FiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_five, container, false);

        return view;
    }

    public static FiveFragment newInstance() {
        FiveFragment fiveFragment = new FiveFragment();
        return fiveFragment;
    }
}
