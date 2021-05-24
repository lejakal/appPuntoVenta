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

import com.example.danielreyes.TAT.models.mdl_Pedidodt;

public class DTPedidoItemsFragment extends Fragment {
    private com.example.danielreyes.TAT.views.FragmentZonas.OnFragmentInteractionListener mListener;
    public String variableIDSUSPEND;
    public String variableIDUSER;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static DTPedidoItemsFragment newInstance() {
        DTPedidoItemsFragment dTPedidoItemsFragment = new DTPedidoItemsFragment();
        dTPedidoItemsFragment.setArguments(new Bundle());
        return dTPedidoItemsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.variableIDUSER = getArguments().getString("ID_USER");
        this.variableIDSUSPEND = getArguments().getString("ID_SUSPENDED");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dtpedido_items, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_pedido_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        mdl_Pedidodt mdl_pedidodt = new mdl_Pedidodt(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdapterPedidoItems(mdl_pedidodt.pedidoItemsDetalle(this.variableIDSUSPEND), R.layout.detalle_pedidoitems, getActivity()));
        return inflate;
    }

    public void onButtonPressed(Uri uri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof com.example.danielreyes.TAT.views.FragmentZonas.OnFragmentInteractionListener) {
            this.mListener = (com.example.danielreyes.TAT.views.FragmentZonas.OnFragmentInteractionListener) context;
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
