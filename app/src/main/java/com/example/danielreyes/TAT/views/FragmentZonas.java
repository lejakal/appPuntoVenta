package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.danielreyes.TAT.R;

import com.example.danielreyes.TAT.models.mdl_Rutas;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentZonas extends Fragment {
    private OnFragmentInteractionListener mListener;
    private String variableIDUSER;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static FragmentZonas newInstance() {
        FragmentZonas fragmentZonas = new FragmentZonas();
        fragmentZonas.setArguments(new Bundle());
        return fragmentZonas;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.variableIDUSER = getArguments().getString("ID_USER");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String format = new SimpleDateFormat("EEEE").format(new Date());
        View inflate = layoutInflater.inflate(R.layout.fragment_zonas, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_zonas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        mdl_Rutas mdl_rutas = new mdl_Rutas(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdapterRutas(mdl_rutas.zonasList(this.variableIDUSER), R.layout.zonas_list, getActivity()));
        return inflate;
    }

    public void onButtonPressed(Uri uri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
