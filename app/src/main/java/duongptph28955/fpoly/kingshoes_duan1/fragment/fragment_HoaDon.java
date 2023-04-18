package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.AdapterHoaDon;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.AdapterSpinnerKH;
import duongptph28955.fpoly.kingshoes_duan1.Adapter.SanPhamSpinnerAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.HoaDonDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.KhachHangDao;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.HoaDon;
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;

public class fragment_HoaDon extends Fragment {
    ListView lv;
    ArrayList<HoaDon> list;
    AdapterHoaDon adapter;

    HoaDon item;
    HoaDonDAO dao;
    FloatingActionButton fab;
    TextInputLayout edGiaXuat, edNgayXuat, edtTimKiem;
    EditText edMau, edSize;
    Spinner spnSP, spnKH;
    CheckBox chkTrangThai;
    Button btnMau, btnSize;
    SanPhamDAO sanPhamDAO;
    SanPhamSpinnerAdapter SPAdapterSPN;
    ArrayList<SanPham> listSP;
    KhachHangDao khachHangDao;
    AdapterSpinnerKH adapterSpinnerKH;
    ArrayList<KhachHang> listKH;
    int maKH, maSP;

    String itemSelectedColor, itemSelectedSize;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lvHoaDon);
        fab = view.findViewById(R.id.floatAddHoaDon);
        dao = new HoaDonDAO(getContext());
        edtTimKiem = view.findViewById(R.id.edtTimKiem);

        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd(getContext());
            }
        });
    }

    void showDialogDate() {
        // sử dụng đối tượng lịch (calendar) để cài đặt
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Khởi tạo dialog ngày tháng
        //DatePickerDialog(@android.annotation.NonNull android.content.Context context, @android.annotation.Nullable android.app.DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth)
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1; // Nhận giá trị từ 0 ==> 11
                        int ngay = i2;

                        //gắn dữ liệu vào Edittext
                        edNgayXuat.getEditText().setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );
        dialog.show();
    }

    public void openDialogAdd(final Context ct){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_hd, null);
        builder.setView(view);
        edGiaXuat = view.findViewById(R.id.edGiaXuat);
        edMau = view.findViewById(R.id.edMau);
        edSize = view.findViewById(R.id.edSize);
        edNgayXuat = view.findViewById(R.id.edNgayXuat);
        spnSP = view.findViewById(R.id.spnSanPham);
        spnKH = view.findViewById(R.id.spnKhachHang);
        sanPhamDAO = new SanPhamDAO(ct);
        listSP = sanPhamDAO.getDSSanPham();



        edNgayXuat.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDate();
            }
        });
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        SPAdapterSPN = new SanPhamSpinnerAdapter(ct, listSP);
        spnSP.setAdapter(SPAdapterSPN);
        spnSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSP = listSP.get(position).getMaSP();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.btn_chkMau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(maSP));
                String tenMau = sanPham.getTenMau();
                String[] arrTenMau = tenMau.split("\t");
                arrTenMau = Arrays.copyOfRange(arrTenMau, 1, arrTenMau.length);
                itemSelectedColor = arrTenMau[0];
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ct);
                String[] finalArrTenMau = arrTenMau;
                builder1.setTitle("Chọn màu").setSingleChoiceItems(arrTenMau, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemSelectedColor = finalArrTenMau[which];
                    }
                });
                builder1.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edMau.setText(itemSelectedColor);
                    }
                });
                builder1.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.show();
            }
        });

        view.findViewById(R.id.btn_chkSize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(maSP));
                String size = sanPham.getTenSize();
                String[] arrTenSize = size.split("\t");
                arrTenSize = Arrays.copyOfRange(arrTenSize, 1, arrTenSize.length);
                itemSelectedSize = arrTenSize[0];
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ct);
                String[] finalArrTenSize = arrTenSize;
                builder2.setTitle("Chọn màu").setSingleChoiceItems(arrTenSize, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemSelectedSize = finalArrTenSize[which];
                    }
                });
                builder2.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edSize.setText(itemSelectedSize);
                    }
                });
                builder2.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder2.show();
            }
        });

        khachHangDao = new KhachHangDao(ct);
        listKH = (ArrayList<KhachHang>) khachHangDao.getAll();
        adapterSpinnerKH = new AdapterSpinnerKH(ct, listKH);
        spnKH.setAdapter(adapterSpinnerKH);
        spnKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKH = listKH.get(position).maKH;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item = new HoaDon();
                item.setMaSP(maSP);
                item.setMaKH(maKH);
                try {
                    item.setGiaXuat(Integer.parseInt(edGiaXuat.getEditText().getText().toString()));
                } catch (Exception e){
                    e.getStackTrace();
                }
                item.setNgayXuat(edNgayXuat.getEditText().getText().toString());
                item.setTenMau(edMau.getText().toString());
                item.size = (edSize.getText().toString());
                if (!chkTrangThai.isChecked()){
                    item.trangThai = 0;
                } else {
                    item.trangThai = 1;
                }
                if (edSize.getText().toString().isEmpty()||edMau.getText().toString().isEmpty()||edGiaXuat.getEditText().getText().toString().isEmpty()||edNgayXuat.getEditText().getText().toString().isEmpty()){
                    Toast.makeText(ct, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (dao.insertHoaDon(item)>0){
                        Toast.makeText(ct, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        ArrayList<SanPham> sanPhamArrayList = sanPhamDAO.getDSSanPham();
                        for (int i = 0; i < sanPhamArrayList.size(); i++) {
                            if (sanPhamArrayList.get(i).getMaSP() == item.getMaSP()){
                                int soluong = sanPhamArrayList.get(i).getSoLuong();
                                int soLuongDaTru = soluong - 1;
                                sanPhamArrayList.get(i).setSoLuong(soLuongDaTru);
                                sanPhamDAO.truSanPham(sanPhamArrayList.get(i));
                            }
                        }
                        capNhatLV();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ct, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    void capNhatLV(){
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new AdapterHoaDon(getContext(), list, this);
        lv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }
}
