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
import com.sm.ej.nk.homeal.adapter.CkReserveAdapter;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.ReserveData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.ReservationListRequest;
import com.sm.ej.nk.homeal.request.ReservationsChangeRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkReserveFragment extends Fragment {
    @BindView(R.id.rv_ck_reserve)
    RecyclerView CkReserveView;
    CkReserveAdapter mAdapter;

    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;

    List<ReserveData> datas;
    ReservationsChangeRequest request;

    public static CkReserveFragment createInstance() {
        final CkReserveFragment pageFragment = new CkReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkReserveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ck_reserve, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        CkReserveView.addItemDecoration(new DividerItemDecoration(getActivity()));
        CkReserveView.setAdapter(mAdapter);
        CkReserveView.setLayoutManager(manager);

        mAdapter.setOnAgreeButtonClickListener(new CkReserveAdapter.OnAagreeButtonClickLIstener() {
            @Override
            public void onAagreeButtonClick(View view, ReserveData data, int position) {
                request = new ReservationsChangeRequest(getContext(),data.getRid(),TYPE_REQUEST_COMPLETE);
                cancelDialog(request,TYPE_REQUEST_COMPLETE);
            }
        });

        mAdapter.setOnDisagreeButtonClickLIstener(new CkReserveAdapter.OnDisagreeButtonClickLIstener() {
            @Override
            public void onDisagreeButtonClick(View view, ReserveData data, int position) {
                request = new ReservationsChangeRequest(getContext(),data.getRid(),TYPE_REQUEST_REJECT);
                cancelDialog(request,TYPE_REQUEST_REJECT);
            }
        });

       mAdapter.setOnCancelAdapterItemClickListener(new CkReserveAdapter.OncancelButtonClickLIstener() {
           @Override
           public void oncancelAdapterItemClick(View view, ReserveData data, int position) {
               request = new ReservationsChangeRequest(getContext(), data.getRid(),TYPE_COOKER_CANCLE);
               cancelDialog(request,TYPE_COOKER_CANCLE);
            }
       });
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
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

    public void reFresh(){
        ReservationListRequest request = new ReservationListRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<ReserveData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ReserveData>>> request, NetworkResult<List<ReserveData>> result) {
                datas = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ReserveData>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void cancelDialog(final ReservationsChangeRequest request,int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        reFresh();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        switch (type){
            case TYPE_REQUEST_COMPLETE:
                builder.setMessage(getResources().getString(R.string.et_ok));
                break;
            case TYPE_REQUEST_REJECT:
                builder.setMessage(getResources().getString(R.string.et_reject));
                break;
            case TYPE_COOKER_CANCLE:
                builder.setMessage(getResources().getString(R.string.et_reservation_cancle));
                break;
        }
        builder.show();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new CkReserveAdapter();

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
    }

}
