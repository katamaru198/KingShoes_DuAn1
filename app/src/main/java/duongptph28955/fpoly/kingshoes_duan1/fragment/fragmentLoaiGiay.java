package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.LoaiGiayAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.LoaiGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.LoaiGiay;

public class fragmentLoaiGiay extends Fragment {
    RecyclerView recyclerLoaiGiay;
    LoaiGiayDAO loaiGiayDAO;
    TextInputLayout edtTenLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_loaigiay, container, false);

        recyclerLoaiGiay = view.findViewById(R.id.recyclerLoaiGiay);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        loaiGiayDAO = new LoaiGiayDAO(getContext());
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData(){
        ArrayList<LoaiGiay> list = loaiGiayDAO.getDSLoaiGiay();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiGiay.setLayoutManager(linearLayoutManager);
        LoaiGiayAdapter adapter = new LoaiGiayAdapter(list, getContext(), loaiGiayDAO);

        recyclerLoaiGiay.setAdapter(adapter);
    }

    protected void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaigiay, null);
        builder.setView(view);

        edtTenLoai = view.findViewById(R.id.edtTenLoai);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenloai = edtTenLoai.getEditText().getText().toString();
                if (validate()>0){
                    boolean check = loaiGiayDAO.themLoaiGiay(tenloai);
                    if (check){
                        Toast.makeText(getContext(), "Thêm mới loại giầy thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }
    public int validate(){
        int check =1;
        String layoutinput = edtTenLoai.getEditText().getText().toString().trim();
        if (layoutinput.isEmpty()){
//            edtTenLoai.setError("không được để trống");
            Toast.makeText(getContext(),"không được để trống ",Toast.LENGTH_SHORT).show();
            check =-1;
        }


        try {
               edtTenLoai.getEditText().getText().toString().trim();

        }catch (Exception e){
            e.printStackTrace();
                Toast.makeText(getContext(),"không được nhập số",Toast.LENGTH_SHORT).show();
                 check =-1;
        }

        return check;

    }


}
