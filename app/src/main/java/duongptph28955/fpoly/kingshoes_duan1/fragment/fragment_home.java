package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.PhotoAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SPHomeAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.Top10DAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.Photo;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import me.relex.circleindicator.CircleIndicator;

public class fragment_home extends Fragment {
    TextView txtTatCaSP;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter adapter;
    private List<Photo> mlistPhoto;
    private Timer mTimer;
    RecyclerView recyclerHome;
    Top10DAO top10DAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        recyclerHome = view.findViewById(R.id.recyclerHome);
        txtTatCaSP = view.findViewById(R.id.txtTatCaSP);

        txtTatCaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new fragmentSanPham();
                fragmentManager.beginTransaction().replace(R.id.linerLayout,fragment).commit();
            }
        });
        top10DAO = new Top10DAO(getContext());
        loadData();

        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        mlistPhoto = getListPhoto();
        adapter = new PhotoAdapter(getContext(), mlistPhoto);
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImg();
        return view;
    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.aaa));
        list.add(new Photo(R.drawable.bbb));
        list.add(new Photo(R.drawable.ccc));
        list.add(new Photo(R.drawable.ddd));

        return list;
    }

    private void autoSlideImg(){
        if (mlistPhoto == null || mlistPhoto.isEmpty() || viewPager == null){
            return;
        }

        if (mTimer == null){
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentItem = viewPager.getCurrentItem();
                        int totalItem = mlistPhoto.size() - 1;
                        if (curentItem < totalItem){
                            curentItem ++;
                            viewPager.setCurrentItem(curentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void loadData() {
        ArrayList<SanPham> list = top10DAO.getHome();

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerHome.setLayoutManager(linearLayoutManager);
        SPHomeAdapter spHomeAdapter = new SPHomeAdapter(getContext(), list);
        recyclerHome.setAdapter(spHomeAdapter);
    }
}
