package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.MauSacAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SanPhamSpinnerAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SizeGiayAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SizeSpinnerAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.MauSacDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SizeGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;

public class fragmentMauSac extends Fragment {
    ListView lv;
    FloatingActionButton fab,fabSearch;
    ArrayList<MauSac> list;
    MauSac item;
    MauSacAdapter adapter;
    MauSacDAO dao;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mausac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lvMauSac);
        fab = view.findViewById(R.id. floatAddMauSac);
        fabSearch = view.findViewById(R.id.floatSearchMauSac);
        dao = new MauSacDAO(getActivity());
        capNhatLv();
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            timkiem(getActivity());
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext());
            }
        });
    }
    public void timkiem(final Context context){
        Dialog dialog = new Dialog(context);//khởi tạo dialog
        dialog.setContentView(R.layout.dialog_searchmausac);//set layout cho dialog
        TextInputLayout edTimKiem = dialog.findViewById(R.id.edTimMauSac);
        Button btnSearch = dialog.findViewById(R.id.Sachseach_btnSearch);
        Button btnHuy = dialog.findViewById(R.id.Sachseach_btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timKiem = edTimKiem.getEditText().getText().toString();
                if(edTimKiem.getEditText().getText().toString().equals("")){
                    Toast.makeText(context, "Nhập tên màu cần tìm: ", Toast.LENGTH_SHORT).show();
                }else{
                    dao.getTimKiem(timKiem);
                    list = (ArrayList<MauSac>) dao.getTimKiem(timKiem);
                    adapter = new MauSacAdapter(getContext(),list,fragmentMauSac.this);
                    lv.setAdapter(adapter);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    protected void openDialog(final Context ct){
        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_themmausac,null);
        builder.setView(v);
        TextInputLayout edMauSac = v.findViewById(R.id.edtTenLoai);



        builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item = new MauSac();
                item.tenMau = edMauSac.getEditText().getText().toString();
                if (dao.checkMau(item.tenMau)<0){
                    if(dao.insertMauSac(item)>0){
                        Toast.makeText(ct, "them thanh cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ct, "them that bai", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }else {
                    Toast.makeText(ct, "Màu đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                capNhatLv();

            }

        });
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<MauSac>) dao.getAll();
        adapter = new MauSacAdapter(getContext(),list,this);
        lv.setAdapter(adapter);
    }


}
