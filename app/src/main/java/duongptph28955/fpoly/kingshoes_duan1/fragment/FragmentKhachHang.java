package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.KhachHangAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.KhachHangDao;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;

public class FragmentKhachHang extends Fragment {
    ListView listView ;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMakhachhang;
    TextInputLayout edTenKhachHang,edSoDienThoai,edDiaChi;
    Button btn_LuuKhachHang,btn_Huy;
    static KhachHangDao dao;
    KhachHang item;
    ArrayList<KhachHang> list;
    KhachHangAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khachhang,container,false);
        listView =view.findViewById(R.id.lv_khachhang);
        fab = view.findViewById(R.id.fabKhachHang);
        dao = new KhachHangDao(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogKhachHang(getActivity(),0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item =list.get(position);
                ShowDialogKhachHang(getContext(),1);

                return false;
            }
        });

        return view;
    }

    protected void ShowDialogKhachHang(final Context context,final int type){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_themkhachhang);
        edMakhachhang =dialog.findViewById(R.id.edMaKhachHang);
        edTenKhachHang =dialog.findViewById(R.id.edttenkhachhang);

        edSoDienThoai =dialog.findViewById(R.id.edtsodienthoai);
        edDiaChi =dialog.findViewById(R.id.edtdiachi);

        btn_LuuKhachHang=dialog.findViewById(R.id.btnSaveKhachHang);
        btn_Huy=dialog.findViewById(R.id.btnCancelKhachHang);

        edMakhachhang.setEnabled(false);

        if(type !=0){

            edMakhachhang.setText(String.valueOf(item.getMaKH()));
            edTenKhachHang.getEditText().setText(item.tenKH);
            edSoDienThoai.getEditText().setText(item.soDTKH);
            edDiaChi.getEditText().setText(item.diaChiKH);
        }

        btn_LuuKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new KhachHang();
                item.tenKH =edTenKhachHang.getEditText().getText().toString();
                item.soDTKH =edSoDienThoai.getEditText().getText().toString();
                item.diaChiKH =edDiaChi.getEditText().getText().toString();
                if (validate()>0){
                    if(type==0){
                        if (dao.insertKhachHang(item)>0){
                            Toast.makeText(context,"thêm khách hàng thành công",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(context,"thêm khách hàng không thành công",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        item.maKH=Integer.parseInt(edMakhachhang.getText().toString());
                        if (dao.updateKhachHang(item)>0){
                            Toast.makeText(context,"sửa khách hàng thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"sửa khách hàng không thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("bạn có chắc muốn xóa không ");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dao.deleteKhachHang(id)>0){
                    Toast.makeText(getActivity(),"Xóa Khách hàng thành công ",Toast.LENGTH_SHORT).show();
                    capNhatLV();

                }else{
                    Toast.makeText(getActivity(),"Xóa Khách hàng không thành công ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog =builder.create();
        builder.show();
    }

    void capNhatLV(){
        list= (ArrayList<KhachHang>) dao.getAll();
        adapter =new KhachHangAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);

    }
    public int validate(){
        int check =1;

        String layoutTenKhachHang = edTenKhachHang.getEditText().getText().toString();
        String layoutsodienthoai = edSoDienThoai.getEditText().getText().toString();
        String layoutdiachi = edDiaChi.getEditText().getText().toString();

        if(layoutTenKhachHang.isEmpty() && layoutsodienthoai.isEmpty()
        && layoutdiachi.isEmpty()){
            edTenKhachHang.setError("không được để trống");
            edSoDienThoai.setError("không được để trống");
            edDiaChi.setError("không được để trống");
            check=-1;
        }else if(layoutTenKhachHang.isEmpty() ){
            edTenKhachHang.setError("không được để trống");
            edSoDienThoai.setError(null);
            edDiaChi.setError(null);
            check=-1;
        }else if(layoutsodienthoai.isEmpty()){
            edTenKhachHang.setError(null);
            edSoDienThoai.setError("không được để trống");
            edDiaChi.setError(null);
            check=-1;
        }else if(layoutdiachi.isEmpty() ){
            edTenKhachHang.setError(null);
            edSoDienThoai.setError(null);
            edDiaChi.setError("không được để trống");
            check=-1;
        }else{
            try {
                Integer.parseInt(edSoDienThoai.getEditText().getText().toString()) ;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(),"số điện thoại ko nhận chữ ",Toast.LENGTH_SHORT).show();
                check =-1;
            }
        }



        return check;
    }
}
