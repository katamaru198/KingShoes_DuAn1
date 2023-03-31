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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.MauSacAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SanPhamAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SanPhamSpinnerAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SizeGiayAdapter;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.TenMauSpinnerAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.MauSacDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SizeGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;

public class fragmentSizeGiay extends Fragment {
    ListView lv;
    FloatingActionButton fab,fabSearch;

    ArrayList<Size> list;
    Size item;
    SizeGiayAdapter adapter;
    SizeGiayDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_size, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lvSize);
        fab = view.findViewById(R.id. floatAdd);
        dao = new SizeGiayDAO(getActivity());
        fabSearch = view.findViewById(R.id.floatSearchSize);
        capNhatLv();
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        dialog.setContentView(R.layout.dialog_timsizegiay);//set layout cho dialog
        TextInputLayout edTimKiem = dialog.findViewById(R.id.edTimSize);
        Button btnSearch = dialog.findViewById(R.id.btnTimSize);
        Button btnHuy = dialog.findViewById(R.id.btnHuySize);
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
                    Toast.makeText(context, "Nhập size cần tìm: ", Toast.LENGTH_SHORT).show();
                }else{
                    dao.getTimKiem(timKiem);
                    list = (ArrayList<Size>) dao.getTimKiem(timKiem);
                    adapter = new SizeGiayAdapter(getContext(),list,fragmentSizeGiay.this);
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
        View v = inflater.inflate(R.layout.dialog_themsizegiay,null);
        builder.setView(v);
        TextInputLayout edSize = v.findViewById(R.id.edSize);
        builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item = new Size();
                item.size = edSize.getEditText().getText().toString();

                dao = new SizeGiayDAO(ct);
                if(dao.checkSize(item.size) <0){
                    if(dao.insertSize(item)>0){
                        Toast.makeText(ct, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ct, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }else{
                    Toast.makeText(ct, "Size đã tồn tại", Toast.LENGTH_SHORT).show();
                }

                capNhatLv();

            }

        });
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<Size>) dao.getAll();
        adapter = new SizeGiayAdapter(getContext(),list,this);
        lv.setAdapter(adapter);
    }

}
