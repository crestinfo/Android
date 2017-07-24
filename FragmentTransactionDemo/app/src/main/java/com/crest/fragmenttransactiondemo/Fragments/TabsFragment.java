package com.crest.fragmenttransactiondemo.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crest.fragmenttransactiondemo.R;


public class TabsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = null;
    private static final String TAG = "TabsFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;


    public TabsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TabsFragment newInstance(String param1) {
        TabsFragment fragment = new TabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        TextView txtTabsTitle = (TextView) view.findViewById(R.id.txtTabsTitle);
        TextView txtColor = (TextView) view.findViewById(R.id.txtColor);
        txtTabsTitle.setText(mParam1);
        switch (mParam1) {
            case "query":
                txtColor.setBackgroundColor(Color.GRAY);
                break;
            case "page":
                txtColor.setBackgroundColor(Color.CYAN);
                break;
            case "country":
                txtColor.setBackgroundColor(Color.YELLOW);
                break;
        }
        return view;
    }
}
