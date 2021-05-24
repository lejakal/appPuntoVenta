package com.example.danielreyes.TAT.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.danielreyes.TAT.R;

public class SettingsFragment extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String string = getArguments().getString("ID_USER");
        String string2 = getArguments().getString("NAME_USER");
        String string3 = getArguments().getString("NOMBRE_USER");
        String string4 = getArguments().getString("APELLIDO_USER");
        getActivity().setTitle(string2);
        View inflate = layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.user);
        TextView textView2 = (TextView) inflate.findViewById(R.id.user_name);
        TextView textView3 = (TextView) inflate.findViewById(R.id.user_apell);
        ((TextView) inflate.findViewById(R.id.user_ide)).setText(string);
        textView.setText(string2);
        textView2.setText(string3);
        textView3.setText(string4);
        return inflate;
    }
}
