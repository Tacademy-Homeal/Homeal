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
import android.widget.Toast;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtReserveAdapter;
import com.sm.ej.nk.homeal.data.EtReserveData;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
//
public class EtReserveFragment extends Fragment {
    @BindView(R.id.rv_et_reserve)
    RecyclerView EtReserveView;
    EtReserveAdapter mAdapter;

    public static EtReserveFragment createInstance() {
        final EtReserveFragment pageFragment = new EtReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtReserveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_et_reserve, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        EtReserveView.setAdapter(mAdapter);
        EtReserveView.setLayoutManager(manager);

        initData();

       mAdapter.setOnReviewItemClickListener(new EtReserveAdapter.OnReserveAdapterClick() {

           @Override
           public void onReserveAdapterClick(View view, EtReserveData etReserveData, int position) {
               Toast.makeText(view.getContext(), "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
           }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new EtReserveAdapter();
    }


    private void initData() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            EtReserveData data = new EtReserveData();
            data.setFooname("foodname " + i);
            data.setReservedate("date" + i);
            data.setReserveperson("person" + i);
            data.setCkname("ckname" + i);
            data.setReservestate("state" + i);
            data.setCkpicture(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher));
            data.setBtnname("예약 취소");
            data.setBtnetreviewwrite("후기 작성");
            mAdapter.add(data);
        }
    }

}
