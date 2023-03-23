package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import duongptph28955.fpoly.kingshoes_duan1.fragment.fragmentMauSac;

public class MauSacAdapter extends ArrayAdapter<MauSac> {
    Context context;
    ArrayList<MauSac> list;

    fragmentMauSac frag;

    TextView tvMauSac,tvMaSP,tvSoLuong;

    ImageView ivDel,ivEdit;

    SanPhamDAO dao;

    SanPhamSpinnerAdapter spinnerAdapter;
    int maSP,maSize;

    ArrayList<SanPham> listsp;
    ArrayList<Size> listSize;
    SizeGiayDAO sizeGiayDAO;
    SizeSpinnerAdapter spnSizeAdapter;

    public MauSacAdapter(@NonNull Context context, ArrayList<MauSac> list, fragmentMauSac frag) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.frag = frag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        final int vitri = position;
        if(v== null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_recycler_mausac,null);

        }
        final MauSac item = list.get(vitri);

        if(item != null){
            SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
            SanPham sanPham = sanPhamDAO.getID(String.valueOf(item.maSP));
            SizeGiayDAO sizeGiayDAO = new SizeGiayDAO(context);

            tvMauSac = v.findViewById(R.id.txtMauSac);
            tvMaSP = v.findViewById(R.id.txtTenLoaiSP1);

            tvSoLuong = v.findViewById(R.id.txtSoLuong3);



            tvMauSac.setText("Màu: "+item.tenMau);
            tvMaSP.setText("Tên giày: "+sanPham.tenSP);

            tvSoLuong.setText("Số lương"+item.soLuong);



        }

        ivDel = v.findViewById(R.id.ivDel1);
        ivEdit = v.findViewById(R.id.ivEdit1);
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa "+ item.getTenMau());
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MauSacDAO dao = new MauSacDAO(context);
                        dao.deleteMauSac(item);
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
                View view = inflater.inflate(R.layout.dialog_editmausac,null);
                TextInputLayout edSize = view.findViewById(R.id.edtTenLoai2);
                TextInputLayout edSoLuong = view.findViewById(R.id.edSoLuongSP);
                edSoLuong.getEditText().setText(item.getSoLuong());
                edSize.getEditText().setText(item.getTenMau());
                listsp = new ArrayList<SanPham>();
                dao = new SanPhamDAO(context);
                listsp = dao.getDSSanPham();
                Spinner spn = view.findViewById(R.id.spn_tenSPnew1);
                spinnerAdapter = new SanPhamSpinnerAdapter(context,listsp);
                spn.setAdapter(spinnerAdapter);


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
                        item.setTenMau(edSize.getEditText().getText().toString());
                        item.setMaSP(maSP);
                        item.setSoLuong(Integer.parseInt(edSoLuong.getEditText().getText().toString()));

                        MauSacDAO mauSacDAO = new MauSacDAO(context);
                        if(mauSacDAO.updateMauSac(item) > 0){
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
