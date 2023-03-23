package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.DAO.MauSacDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SizeGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;
import duongptph28955.fpoly.kingshoes_duan1.fragment.fragmentSizeGiay;

public class SizeGiayAdapter extends ArrayAdapter<Size> {
    Context context;
    ArrayList<Size> list;

    fragmentSizeGiay fragment;

    TextView tvMaSize,tvSize,tvMaSP,tvTenMau,tvsoLuong;

    ImageView ivDel,ivEdit;
    
    SanPhamDAO dao;
    
    SanPhamSpinnerAdapter spinnerAdapter;
    int maSP,maMau;

    MauSacDAO mauSacDAO;
    TenMauSpinnerAdapter tmAdapter;

    ArrayList<MauSac> listmau;

    ArrayList<SanPham> listsp;
    public SizeGiayAdapter(@NonNull Context context, ArrayList<Size> list, fragmentSizeGiay fragment) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        final int vitri = position;
        if(v== null){
            
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_recycler_sizegiay,null);

        }
        final Size item = list.get(vitri);

        if(item != null){
            SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
            SanPham sanPham = sanPhamDAO.getID(String.valueOf(item.maSP));
            MauSacDAO mauSacDAO = new MauSacDAO(context);
            MauSac mauSac = mauSacDAO.getID(String.valueOf(item.maMau));

            tvMaSize = v.findViewById(R.id.txtMaSize);
            tvSize = v.findViewById(R.id.txtSize);
            tvMaSP = v.findViewById(R.id.txtMaSP);
            tvTenMau = v.findViewById(R.id.txtTenMau);
            tvsoLuong = v.findViewById(R.id.txtsoLuong);


            tvMaSize.setText("Ma Size: "+item.maSize);
            tvSize.setText("Size: "+item.size);
            tvMaSP.setText("Ten giay: "+sanPham.tenSP);
            tvTenMau.setText("Màu: "+mauSac.tenMau);
            tvsoLuong.setText("So luong: "+item.soLuong);



        }

        ivDel = v.findViewById(R.id.ivDel);
        ivEdit = v.findViewById(R.id.ivEdit);
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa "+ item.getSize());
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SizeGiayDAO dao = new SizeGiayDAO(context);
                        dao.deleteSize(item);
                        list.remove(item);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_editsizegiay,null);
                TextInputLayout edSize = view.findViewById(R.id.edtDiaEditSize);
                edSize.getEditText().setText(item.getSize());
                listsp = new ArrayList<SanPham>();
                dao = new SanPhamDAO(context);
                listsp = dao.getDSSanPham();
                Spinner spn = view.findViewById(R.id.spnGiay);
                spinnerAdapter = new SanPhamSpinnerAdapter(context,listsp);
                spn.setAdapter(spinnerAdapter);

                mauSacDAO = new MauSacDAO(context);
                listmau = (ArrayList<MauSac>) mauSacDAO.getAll();
                Spinner spnMau = view.findViewById(R.id.spnMau1);
                tmAdapter = new TenMauSpinnerAdapter(context,listmau);
               spnMau.setAdapter(tmAdapter);
               TextInputLayout edsoLuong = view.findViewById(R.id.edtDiaEditsoLuong);
               edsoLuong.getEditText().setText(Integer.toString(item.getSoLuong()));
                spnMau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maMau = listmau.get(position).getMaMau();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maSP = listsp.get(position).getMaSP();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                builder.setView(view);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        item.setSize(edSize.getEditText().getText().toString());
                        item.setMaSP(maSP);
                        item.soLuong = Integer.parseInt(edsoLuong.getEditText().getText().toString());
                        item.maMau = maMau;
                        SizeGiayDAO sizeGiayDAO = new SizeGiayDAO(context);
                        if(sizeGiayDAO.updateSize(item) > 0){
                            list.set(vitri,item);
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });
        return v;
    }

}
