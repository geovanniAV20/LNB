package edu.itesm.lnb.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.itesm.lnb.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SugerenciasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SugerenciasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SugerenciasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SugerenciasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SugerenciasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SugerenciasFragment newInstance(String param1, String param2) {
        SugerenciasFragment fragment = new SugerenciasFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getArguments().getBoolean("primerP")){
            System.out.println("ARRIBA1");
        }else{
            System.out.println("ABAJO1");
        }

        if(getArguments().getBoolean("segundaP")){
            System.out.println("ARRIBA2");
        }else{
            System.out.println("ABAJO2");
        }

        if(getArguments().getBoolean("terceraP")){
            System.out.println("ARRIBA3");
        }else{
            System.out.println("ABAJO3");
        }

        if(getArguments().getBoolean("cuartaP")){
            System.out.println("ARRIBA4");
        }else{
            System.out.println("ABAJO4");
        }

        if(getArguments().getBoolean("quintaP")){
            System.out.println("ARRIBA5");
        }else{
            System.out.println("ABAJO5");
        }

        if(getArguments().getBoolean("sextaP")){
            System.out.println("ARRIBA6");
        }else{
            System.out.println("ABAJO6");
        }

        if(getArguments().getBoolean("septimaP")){
            System.out.println("ARRIBA7");
        }else{
            System.out.println("ABAJO7");
        }

        if(getArguments().getBoolean("octavaP")){
            System.out.println("ARRIBA8");
        }else{
            System.out.println("ABAJO8");
        }
        return inflater.inflate(R.layout.fragment_sugerencias, container, false);
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