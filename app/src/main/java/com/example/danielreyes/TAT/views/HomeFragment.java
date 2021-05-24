package com.example.danielreyes.TAT.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.example.danielreyes.TAT.R;

public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        ((FloatingActionButton) inflate.findViewById(R.id.fab)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        return inflate;
    }
}
