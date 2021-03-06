package edu.itesm.lnb.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.itesm.lnb.Adapters.NutrimentAdapter;
import edu.itesm.lnb.Models.NutrimentItem;
import edu.itesm.lnb.Models.RecetaItem;
import edu.itesm.lnb.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilteredFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FilteredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilteredFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final String URL_DATA = "https://api.myjson.com/bins/14u0qq";

    private List<NutrimentItem> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public FilteredFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilteredFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilteredFragment newInstance(String param1, String param2) {
        FilteredFragment fragment = new FilteredFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void loadRecyclerViewData(final ArrayList<String> nutrimentosSugeridos) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("nutrimentos");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        JSONArray elementos = o.getJSONArray("elementos");
                        for (int j = 0; j < elementos.length(); j++){
                            JSONObject elemento = elementos.getJSONObject(j);
                            String elementoName = new String(elemento.getString("nombre").getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray recetas = elemento.getJSONArray("recetas");
                            List<RecetaItem> recetaItems = new ArrayList<>();
                            for (int k = 0; k < recetas.length(); k++){
                                JSONObject receta = recetas.getJSONObject(k);
                                String titulo = new String(receta.getString("titulo").getBytes("ISO-8859-1"), "UTF-8");
                                JSONArray ingredientes = receta.getJSONArray("ingredientes");
                                List<String> ingredientesList = new ArrayList<String>();
                                for(int l = 0; l < ingredientes.length(); l++){
                                    JSONObject ingrediente = ingredientes.getJSONObject(l);
                                    ingredientesList.add(new String(ingrediente.getString("nombre").getBytes("ISO-8859-1"), "UTF-8"));
                                }
                                RecetaItem recetaItem = new RecetaItem(
                                        titulo,
                                        ingredientesList,
                                        new String(receta.getString("preparacion").getBytes("ISO-8859-1"), "UTF-8"),
                                        receta.getString("imagen")
                                );
                                recetaItems.add(recetaItem);

                            }

                            if(nutrimentosSugeridos.contains(elementoName)) {
                                NutrimentItem item = new NutrimentItem(
                                        elementoName,
                                        recetaItems

                                );
                                listItems.add(item);
                            }
                        }
                    }

                    adapter = new NutrimentAdapter(listItems, getActivity());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filtered, container, false);

        ArrayList<String> nutrimentosSugeridos = getArguments().getStringArrayList("nutrimentos");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        System.out.println(Arrays.toString(nutrimentosSugeridos.toArray()));

        listItems = new ArrayList<>();
        loadRecyclerViewData(nutrimentosSugeridos);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
