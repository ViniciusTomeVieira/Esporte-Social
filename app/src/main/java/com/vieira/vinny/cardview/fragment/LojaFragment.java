package com.vieira.vinny.cardview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vieira.vinny.cardview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LojaFragment extends Fragment {


    public LojaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loja, container, false);
    }

}
