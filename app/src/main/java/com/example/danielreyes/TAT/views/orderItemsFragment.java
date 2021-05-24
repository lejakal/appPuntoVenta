package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danielreyes.TAT.R;
import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import com.example.danielreyes.TAT.models.mdl_Items;

import java.util.ArrayList;

public class orderItemsFragment extends Fragment {
    String ID_SUSPENDEDED;
    String ID_USER;
    private Cursor listCursor;
    private AdapterOrderItems mLeadsAdapter;
    private ListView mLeadsList;
    private OnFragmentInteractionListener mListener;
    String variableID;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static orderFragment newInstance() {
        orderFragment orderfragment = new orderFragment();
        orderfragment.setArguments(new Bundle());
        return orderfragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.variableID = getArguments().getString("idsubcategoria");
        this.ID_SUSPENDEDED = getArguments().getString("IDE_SUSPENDED");
        this.ID_USER = getArguments().getString("ID_USER");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void itemsList(String str) {
        DBconexion dBconexion = new DBconexion(getActivity());
        dBconexion.open();
        String[] r2 = new String[2];
        int i = 0;
        r2[0] = "item_id";
        r2[1] = "name";
        this.listCursor = dBconexion.select(DBtablas.TABLA_ITEMS, r2, "id_subcategoria", str, "name", "ASC");
        ArrayList arrayList = new ArrayList();
        int count = this.listCursor.getCount();
        while (i < count) {
            arrayList.add(this.listCursor.getString(this.listCursor.getColumnIndex("name")));
            this.listCursor.moveToNext();
            i++;
        }
        dBconexion.close();
    }

    public void itemsClicked(int i) {
        this.listCursor.moveToPosition(i);
        String string = this.listCursor.getString(0);
        String string2 = this.listCursor.getString(1);
        FragmentActivity activity = getActivity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(string);
        stringBuilder.append("\nCategory: ");
        stringBuilder.append(string2);
        Toast.makeText(activity, stringBuilder.toString(), 0).show();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_order_items, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_itemsOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        mdl_Items mdl_items = new mdl_Items(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdapterItems(mdl_items.itemsListOrder(this.variableID, this.ID_SUSPENDEDED, this.ID_USER), R.layout.order_items, getActivity()));
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
