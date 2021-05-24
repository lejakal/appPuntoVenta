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

/**
 * A simple {@link Fragment} subclass.
 */
public class ZonasFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ZonasFragment.OnFragmentInteractionListener mListener;

    //parameters
    private String variableIDUSER;

    public ZonasFragment() {
        // Required empty public constructor
    }


    public static ZonasFragment newInstance() {
        ZonasFragment fragment = new ZonasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableIDUSER = getArguments().getString("ID_USER");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        View view;
        view = inflater.inflate(R.layout.fragment_zonas, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler_zonas);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        mdl_Rutas crud = new mdl_Rutas(getActivity());
        recycler.setLayoutManager(lm);
        recycler.setAdapter(new AdapterRutas(crud.zonasList(variableIDUSER), R.layout.zonas_list, getActivity()));//FALTA Q SOLO MUESTRE LAS ZONAS CORRESPONDIENTES A LA FECHA DE LA SEMAN
        //recycler.setAdapter(new AdapterRutas(getActivity(), crud.zonasList(),newInstance.orderFragment));


        return view;

    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ZonasFragment.OnFragmentInteractionListener) {
            mListener = (ZonasFragment.OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
