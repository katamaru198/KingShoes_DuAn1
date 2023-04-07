package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.Top10Adapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.Top10DAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;


public class FragmentTop10 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10, container, false);

        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);

        Top10DAO top10DAO = new Top10DAO(getContext());
        ArrayList<SanPham> list = top10DAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerTop10.setAdapter(adapter);

        return view;
    }
}
