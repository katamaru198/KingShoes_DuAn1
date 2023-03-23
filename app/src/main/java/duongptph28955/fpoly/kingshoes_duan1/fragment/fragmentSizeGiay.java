package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    FloatingActionButton fab;
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
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext());
            }
        });
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
                if(dao.insertSize(item)>0){
                    Toast.makeText(ct, "them thanh cong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ct, "them that bai", Toast.LENGTH_SHORT).show();
                }
                capNhatLv();
                dialog.dismiss();
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
