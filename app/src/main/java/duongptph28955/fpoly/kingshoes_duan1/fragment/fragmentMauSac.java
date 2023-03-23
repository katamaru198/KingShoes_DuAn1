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
    FloatingActionButton fab;
    ArrayList<MauSac> list;
    MauSac item;
    MauSacAdapter adapter;
    MauSacDAO dao;
    SanPhamSpinnerAdapter spinnerAdapter;
    ArrayList<SanPham> listsp;
    SanPhamDAO sanPhamDAO;
    int maSP,maSize;
    ArrayList<Size> listSize;
    SizeSpinnerAdapter sizespn;
    SizeGiayDAO sizeGiayDAO;

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
        dao = new MauSacDAO(getActivity());
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
        View v = inflater.inflate(R.layout.dialog_themmausac,null);
        builder.setView(v);
        TextInputLayout edMauSac = v.findViewById(R.id.edtTenLoai);
        Spinner spn = v.findViewById(R.id.spn_tenSPnew);
        listsp = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO(ct);
        listsp = sanPhamDAO.getDSSanPham();
        spinnerAdapter = new SanPhamSpinnerAdapter(getContext(),listsp);
        spn.setAdapter(spinnerAdapter);


        TextInputLayout edSoLuong = v.findViewById(R.id.edSoLuongSanPham);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSP = listsp.get(position).maSP;
                Toast.makeText(ct, "Chon: "+listsp.get(position).getTenSP(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                item.maSP = maSP;
                item.soLuong = Integer.parseInt(edSoLuong.getEditText().getText().toString());
                if(dao.insertMauSac(item)>0){
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
        list = (ArrayList<MauSac>) dao.getAll();
        adapter = new MauSacAdapter(getContext(),list,this);
        lv.setAdapter(adapter);
    }


}
