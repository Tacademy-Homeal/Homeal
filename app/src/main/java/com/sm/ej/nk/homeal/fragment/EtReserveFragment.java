package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sm.ej.nk.homeal.DividerItemDecoration;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtReserveAdapter;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ReserveData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.EtReserveRequest;
import com.sm.ej.nk.homeal.request.ReservationListRequest;

import java.util.List;

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
    List<ReserveData> datas;

    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;


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
        //add ItemDecoration
        EtReserveView.addItemDecoration(new DividerItemDecoration(getActivity()));

        EtReserveView.setAdapter(mAdapter);
        EtReserveView.setLayoutManager(manager);

        ReservationListRequest request = new ReservationListRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<ReserveData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ReserveData>>> request, NetworkResult<List<ReserveData>> result) {
                datas = result.getResult();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ReserveData>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


//
//                mAdapter.setOnReviewItemClickListener(new EtReserveAdapter.OnReserveAdapterClick() {
//                    @Override
//                    public void onReserveAdapterClick(View view, EtReserveData etReserveData, int position) {
//
//
//                        switch (0) {
//                            case TYPE_REQUEST:
//                                showDialog();
//                                break;
//                            case TYPE_REQUEST_COMPLETE:
//                                showDialog();
//                                break;
//                            case TYPE_EAT_COMPLETE:
//                                Intent intent = new Intent(getActivity(), EtWriteReviewActivity.class);
//                                startActivity(intent);
//                                break;
//                            case TYPE_END:
//                                break;
//                        }
//                    }
//                });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        EtReserveRequest request = new EtReserveRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<ReserveData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ReserveData>>> request, NetworkResult<List<ReserveData>> result) {
                datas = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ReserveData>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.et_reservation_cancle));
        builder.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new EtReserveAdapter();
    }

}
