package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtHomeFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtHomeFragment extends Fragment {

    @BindView(R.id.rv_et_home_ft)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    EtHomeFragmentAdapter mAdapter;

    public static EtHomeFragment createInstance(){
        final EtHomeFragment pageFragment = new EtHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_et_home, container, false);
        ButterKnife.bind(this, v);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        initData();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new EtHomeFragmentAdapter();

    }

    private void initData() {
        for (int i = 0; i < 10 ; i++) {
            mAdapter.add(ContextCompat.getDrawable(getContext(), R.drawable.ic_launcher));
        }
    }
}
