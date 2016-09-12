package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.EtHomeAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkJjimAddRequest;
import com.sm.ej.nk.homeal.request.CkJjimDeleteRequest;
import com.sm.ej.nk.homeal.request.CkSearchListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements EtHomeAdapter.OnReviewitemClickListener, EtHomeAdapter.OnJjimitemClickListener, EtHomeAdapter.OnViewClickListener{

    @BindView(R.id.rv_search)
    RecyclerView rv;

    @BindView(R.id.image_search)
    ImageView deleteImage;

    @BindView(R.id.edit_search)
    EditText editText;

    EtHomeAdapter mAdapter;
    LinearLayoutManager mManager;
    private static int PAGENO;
    private static String ROWCOUNT="7";
    private String keyword;

    private boolean isLastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        PAGENO = 1;
        mManager = new LinearLayoutManager(SearchActivity.this);
        mAdapter = new EtHomeAdapter();
        mAdapter.setOnViewClickListener(this);
        mAdapter.setOnReciewClickListener(this);
        mAdapter.setOnJjimitemClickListener(this);

        rv.setLayoutManager(mManager);
        rv.setAdapter(mAdapter);



        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionid, KeyEvent keyEvent) {

                    if(!TextUtils.isEmpty(editText.getText().toString())){
                        Log.e("ssong", "ddddd");
                        mAdapter.clear();
                        keyword = editText.getText().toString();
                        addItem(keyword,""+PAGENO, ROWCOUNT);
                    }else{
                        Log.e("ssong", "aaaaaa");
                        Toast.makeText(SearchActivity.this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                return true;
            }
        });
    }


    private void addItem(String keyword, String pageNo, String rowCount){
        CkSearchListRequest request = new CkSearchListRequest(SearchActivity.this, keyword, pageNo, rowCount);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<EtHomeData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<EtHomeData>>> request, NetworkResult<List<EtHomeData>> result) {
                Toast.makeText(SearchActivity.this, "검색 성공", Toast.LENGTH_SHORT).show();
                mAdapter.addList(result.getResult());
                PAGENO+=1;
                setScrolled();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<EtHomeData>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(SearchActivity.this, "검색 실패", Toast.LENGTH_SHORT).show();
                Log.e("ssong", errorCode+"");
            }
        });
    }

    public static final String INTENT_CK_ID = "asd";
    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(SearchActivity.this, InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_CK_ID, );
        intent.putExtra(INTENT_CK_ID, mAdapter.getDataList().get(position));
        Log.e("ssong", mAdapter.getDataList().get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onJjimitemClick(View view, int position, final EtHomeData data) {
        if(data.getIsBookmark() == 0){
            CkJjimAddRequest request = new CkJjimAddRequest(SearchActivity.this, data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(SearchActivity.this, "찜 추가 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(1);
                    data.setBookmarkCnt(data.getBookmarkCnt()+1);
                    mAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(SearchActivity.this, "찜 추가 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode+"");
                }
            });
        }else{
            CkJjimDeleteRequest request = new CkJjimDeleteRequest(SearchActivity.this, data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(SearchActivity.this, "찜 삭제 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(0);
                    data.setBookmarkCnt(data.getBookmarkCnt()-1);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(SearchActivity.this, "찜 삭제 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode+"");
                }
            });
        }

    }

    @Override
    public void onReviewitemClick(View view, int position, EtHomeData data) {
        Intent intent = new Intent(SearchActivity.this, ReviewInfoActivity.class);
        intent.putExtra(ReviewInfoActivity.INTENT_COOKERNAME, data.getId());

        startActivity(intent);
    }

    private void setScrolled(){
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(isLastItem && newState == RecyclerView.SCROLL_STATE_IDLE){
                    addItem(keyword, ""+PAGENO, ROWCOUNT);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mManager.getItemCount();
                int lastVisibleItemPosition = mManager.findLastCompletelyVisibleItemPosition();
                if(totalItemCount >0 && lastVisibleItemPosition != RecyclerView.NO_POSITION && (totalItemCount -1 <= lastVisibleItemPosition)){
                    isLastItem = true;
                }else{
                    isLastItem = false;
                }
            }
        });
    }

}
