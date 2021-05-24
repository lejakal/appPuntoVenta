package com.example.danielreyes.TAT.views;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.danielreyes.TAT.R;

import com.example.danielreyes.TAT.models.DBconexion;
import com.example.danielreyes.TAT.models.DBtablas;
import java.util.ArrayList;

public class orderFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String ID_SUSPENDED;
    String ID_USER;
    Context context;
    DBconexion controller = new DBconexion(this.context);
    private Cursor listCursor;
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static orderFragment newInstance(String str, String str2) {
        orderFragment orderfragment = new orderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        orderfragment.setArguments(bundle);
        return orderfragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ID_SUSPENDED = getArguments().getString("ID_SUSPENDED");
        this.ID_USER = getArguments().getString("ID_USER");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        populateList();
    }

    public void populateList() {
        DBconexion dBconexion = new DBconexion(getActivity());
        dBconexion.open();
        String[] r1 = new String[2];
        int i = 0;
        r1[0] = "id_subcategoria";
        r1[1] = DBtablas.subcategoria_nombre;
        this.listCursor = dBconexion.select(DBtablas.TABLA_SUBCATEGORIAS, r1);
        ArrayList arrayList = new ArrayList();
        int count = this.listCursor.getCount();
        while (i < count) {
            arrayList.add(this.listCursor.getString(this.listCursor.getColumnIndex(DBtablas.subcategoria_nombre)));
            this.listCursor.moveToNext();
            i++;
        }
        ListView listView = (ListView) getActivity().findViewById(R.id.listViewSubcategory);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), 17367043, arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                ((TextView) view2.findViewById(16908308)).setTextColor(-1);
                return view2;
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                orderFragment.this.subcategoryClicked(i);
            }
        });
        dBconexion.close();
    }

    public void subcategoryClicked(int i) {
        this.listCursor.moveToPosition(i);
        String string = this.listCursor.getString(0);
        String string2 = this.listCursor.getString(1);
        FragmentActivity activity = getActivity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IDsubcat: ");
        stringBuilder.append(string);
        stringBuilder.append("\nSubcategoria: ");
        stringBuilder.append(string2);
        Toast.makeText(activity, stringBuilder.toString(), 0).show();
        orderItemsFragment orderitemsfragment = new orderItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idsubcategoria", string);
        bundle.putString("IDE_SUSPENDED", this.ID_SUSPENDED);
        bundle.putString("ID_USER", this.ID_USER);
        orderitemsfragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.contentFragmentitems, orderitemsfragment, "tag");
        beginTransaction.addToBackStack("tag");
        beginTransaction.commit();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_order, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.valor_suspended);
        ((TextView) inflate.findViewById(R.id.id_suspended)).setText(this.ID_SUSPENDED);
        DBconexion dBconexion = new DBconexion(getActivity());
        dBconexion.open();
        String totalSuspend = dBconexion.totalSuspend(this.ID_SUSPENDED, dBconexion.getAllsuspend_itemsIDsuspend(this.ID_SUSPENDED).size());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$ ");
        stringBuilder.append(totalSuspend);
        textView.setText(stringBuilder.toString());
        dBconexion.close();
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
