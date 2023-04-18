package duongptph28955.fpoly.kingshoes_duan1.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.Adapter.SanPhamAdapter;
import duongptph28955.fpoly.kingshoes_duan1.DAO.LoaiGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.MauSacDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SizeGiayDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.LoaiGiay;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;

public class fragmentSanPham extends Fragment {
    ImageButton ibtnCamera, ibtnFolder;
    ImageView imgHinh;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;
    RecyclerView recyclerSanPham;
    SanPhamDAO sanPhamDAO;
    TextInputLayout edtNgayNhapSP;
    int masp;
    SizeGiayDAO sizeGiayDAO;
    MauSacDAO mauSacDAO;
    TextView txtSize, txtMau;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham, container, false);
        recyclerSanPham = view.findViewById(R.id.recyclerSanPham);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        sanPhamDAO = new SanPhamDAO(getContext());
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData() {
        ArrayList<SanPham> list = sanPhamDAO.getDSSanPham();

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerSanPham.setLayoutManager(linearLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(getContext(), list, sanPhamDAO, new SanPhamAdapter.ItemClickListener() {
            @Override
            public void onItemClick(SanPham sanPham) {
                masp = sanPham.getMaSP();
                showDialogChiTiet(sanPham);
            }
        });
        recyclerSanPham.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsanpham, null);
        builder.setView(view);

        TextInputLayout edtTenSP = view.findViewById(R.id.edtTenSanPhamDia);
        TextInputLayout edtGiaNhapSP = view.findViewById(R.id.edtGiaNhapDia);
        TextInputLayout edtSoLuongSP = view.findViewById(R.id.edtSoLuongDia);
        edtNgayNhapSP = view.findViewById(R.id.edtNgayNhapDia);
        txtSize = view.findViewById(R.id.txtSize);
        txtMau = view.findViewById(R.id.txtMau);

        view.findViewById(R.id.btnSIZE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSize();
            }
        });
        view.findViewById(R.id.btnMAU).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMau();
            }
        });
        edtNgayNhapSP.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDate();
            }
        });
        Spinner spinnerLoaiSP = view.findViewById(R.id.spnLoaiGiay);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spinnerLoaiSP.setAdapter(simpleAdapter);

        ibtnCamera = view.findViewById(R.id.ibtnCamera);
        ibtnFolder = view.findViewById(R.id.ibtnFolder);
        imgHinh = view.findViewById(R.id.imgHinh);

        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityCompat.requestPermissions(
//                        (Activity) getContext(),
//                        new String[]{android.Manifest.permission.CAMERA},
//                        REQUEST_CODE_CAMERA
//                );
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityCompat.requestPermissions(
//                        (Activity) getContext(),
//                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_FOLDER
//                );
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensp = edtTenSP.getEditText().getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerLoaiSP.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                int gianhap = Integer.parseInt(edtGiaNhapSP.getEditText().getText().toString());
                int soluong = Integer.parseInt(edtSoLuongSP.getEditText().getText().toString());
                String tenMau = txtMau.getText().toString();
                String tenSize = txtSize.getText().toString();
                String ngaynhap = edtNgayNhapSP.getEditText().getText().toString();

                //chuyển data imageview sang mảng byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray); //ép kiểu ảnh về png, độ phân giải ảnh mặc định 100 - cảng nhỏ hơn càng nét(1-100), dữ liệu truyền vào;
                byte[] hinhAnh = byteArray.toByteArray(); //mảng byte để chứa dữ liệu

                boolean check = sanPhamDAO.themGiayMoi(tensp, maloai, gianhap, soluong, ngaynhap, tenMau, tenSize, hinhAnh);
                if (check) {
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        //Chuyển đổi định dạng ngày tháng
        //1. Lấy ngày hiện tại của hệ thông
        Date objDate = new Date(System.currentTimeMillis());
        // Sử dụng Dateformat để định dạng kết quả ngày tháng
        android.text.format.DateFormat dateFormat = new DateFormat();
        String chuoi_ngay_thang_nam = (String) dateFormat.format("dd/MM/yyyy", objDate);
        Log.d("zzzzz", "onCreat: Ngày tháng năm = " + chuoi_ngay_thang_nam);

        //2. Chuyển đổi định dạng chuỗi ngày tháng: dd/mm/yyyy => yyyy-mm-dd
        String chuoi_ngay_vn = "17/03/2023";
        // chuyển định dạng ngày 17/03/2023 ==> 2023-03-17
        Log.d(TAG, "Chuỗi ban đầu: " + chuoi_ngay_vn);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");//Truyền vào
        // mẫu định dạng của chuỗi ban đầu
        try {
            Date objNgay = format.parse(chuoi_ngay_vn);
            String chuoi_moi = (String) dateFormat.format("yyyy-MM-dd", objNgay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //Hàm khi người dùng đồng ý
//
//        switch (requestCode){
//            case REQUEST_CODE_CAMERA:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
//                }else {
//                    Toast.makeText(getContext(), "Bạn không cho phép mơ camera", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case REQUEST_CODE_FOLDER:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Intent intent = new Intent(Intent.ACTION_PICK);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, REQUEST_CODE_FOLDER);
//                }else {
//                    Toast.makeText(getContext(), "Bạn không cho phép truy cập thư viện ảnh", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiSach() {
        LoaiGiayDAO loaiGiayDAO = new LoaiGiayDAO(getContext());
        ArrayList<LoaiGiay> list = loaiGiayDAO.getDSLoaiGiay();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiGiay loai : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", loai.getMaLoai());
            hs.put("tenLoai", loai.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }

    void showDialogDate() {
        // sử dụng đối tượng lịch (calendar) để cài đặt
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        //gắn dữ liệu vào Edittext
                        edtNgayNhapSP.getEditText().setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );
        dialog.show();
    }

    private void showDialogChiTiet(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = ((Activity) getContext()).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_showchitiet, null);
        builder.setView(view);

        //Ánh xạ
        TextView txtMaSP = view.findViewById(R.id.txtMaSPChiTiet);
        TextView txtTenSP = view.findViewById(R.id.txtTenSPChiTiet);
        TextView txtTenLoaiSP = view.findViewById(R.id.txtTenLoaiSPChiTiet);
        TextView txtSoLuongSP = view.findViewById(R.id.txtSoLuongSPChiTiet);
        TextView txtGiaNhapSP = view.findViewById(R.id.txtGiaNhapSPChiTiet);
        TextView txtNgayNhapSP = view.findViewById(R.id.txtNgayNhapSPChiTiet);
        TextView txtTenMauSP = view.findViewById(R.id.txtTenMauSPChiTiet);
        TextView txtTenSizeSP = view.findViewById(R.id.txtTenSizeSPChiTiet);
        ImageView imgHinh = view.findViewById(R.id.imgHinh);
        byte[] hinhAnh = sanPham.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);
        ImageView imgEdit = view.findViewById(R.id.ivEdit);
        ImageView imgDel = view.findViewById(R.id.ivDel);

        //sự kiện Edit

        txtMaSP.setText("Mã sản phẩm: " + sanPham.getMaSP());
        txtTenSP.setText(sanPham.getTenSP());
        txtTenLoaiSP.setText("Loại: " + sanPham.getTenLoai());
        txtSoLuongSP.setText("Số lượng: " + sanPham.getSoLuong());
        txtGiaNhapSP.setText("Giá nhập: " + sanPham.getGiaNhap());
        txtNgayNhapSP.setText("Ngày nhập: " + sanPham.getNgayNhap());
        txtTenMauSP.setText("Màu: "+ sanPham.getTenMau());
        txtTenSizeSP.setText("Size: " + sanPham.getTenSize());

        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(sanPham);
                alertDialog.dismiss();
            }
        });
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masp = sanPham.getMaSP();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("XÓA SẢN PHẨM");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = sanPhamDAO.xoaSanPham(masp);
                        if (check == 1){
                            Toast.makeText(getContext(), "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                        }else if (check == 0){
                            Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }else if (check == -1){
                            Toast.makeText(getContext(), "SẢN PHẨM tồn tại trong HÓA ĐƠN, không được phép xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.dismiss();
                builder.create().show();
            }
        });

    }

    private void showDialogEdit(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editsanpham, null);
        builder.setView(view);

        TextView txtMaSP = view.findViewById(R.id.txtMaSPChiTiet);
        TextInputLayout edtTenSP = view.findViewById(R.id.edtTenSanPhamDia);
        TextInputLayout edtGiaNhapSP = view.findViewById(R.id.edtGiaNhapDia);
        TextInputLayout edtSoLuongSP = view.findViewById(R.id.edtSoLuongDia);
        txtSize = view.findViewById(R.id.txtSize);
        txtMau = view.findViewById(R.id.txtMau);

        edtNgayNhapSP = view.findViewById(R.id.edtNgayNhapDia);
        edtNgayNhapSP.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDate();
            }
        });
        Spinner spinnerLoaiSP = view.findViewById(R.id.spnLoaiGiay);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spinnerLoaiSP.setAdapter(simpleAdapter);

        ibtnCamera = view.findViewById(R.id.ibtnCamera);
        ibtnFolder = view.findViewById(R.id.ibtnFolder);
        imgHinh = view.findViewById(R.id.imgHinh);

        txtMaSP.setText("Mã sản phẩm: "+sanPham.getMaSP());
        byte[] hinhAnh = sanPham.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);
        edtTenSP.getEditText().setText(sanPham.getTenSP());
        edtGiaNhapSP.getEditText().setText(String.valueOf(sanPham.getGiaNhap()));
        edtSoLuongSP.getEditText().setText(String.valueOf(sanPham.getSoLuong()));
        edtNgayNhapSP.getEditText().setText(sanPham.getNgayNhap());

        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityCompat.requestPermissions(
//                        (Activity) getContext(),
//                        new String[]{android.Manifest.permission.CAMERA},
//                        REQUEST_CODE_CAMERA
//                );
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityCompat.requestPermissions(
//                        (Activity) getContext(),
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_FOLDER
//                );
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        view.findViewById(R.id.btnSIZE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSize();
            }
        });
        view.findViewById(R.id.btnMAU).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMau();
            }
        });
        builder.setNegativeButton("Chỉnh sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensp = edtTenSP.getEditText().getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerLoaiSP.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                int gianhap = Integer.parseInt(edtGiaNhapSP.getEditText().getText().toString());
                int soluong = Integer.parseInt(edtSoLuongSP.getEditText().getText().toString());
                String ngaynhap = edtNgayNhapSP.getEditText().getText().toString();
                String mau = txtMau.getText().toString();
                String size = txtSize.getText().toString();

                //chuyển data imageview sang mảng byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray); //ép kiểu ảnh về png, độ phân giải ảnh mặc định 100 - cảng nhỏ hơn càng nét(1-100), dữ liệu truyền vào;
                byte[] hinhAnh = byteArray.toByteArray(); //mảng byte để chứa dữ liệu

                SanPham sanPham = new SanPham(masp, maloai, tensp, gianhap, soluong, ngaynhap,mau, size, hinhAnh);
                if (sanPhamDAO.capNhatSanPham(sanPham)) {
                    loadData();
                }

            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });



        //Chuyển đổi định dạng ngày tháng
        //1. Lấy ngày hiện tại của hệ thông
        Date objDate = new Date(System.currentTimeMillis());
        // Sử dụng Dateformat để định dạng kết quả ngày tháng
        android.text.format.DateFormat dateFormat = new DateFormat();
        String chuoi_ngay_thang_nam = (String) dateFormat.format("dd/MM/yyyy", objDate);
        Log.d("zzzzz", "onCreat: Ngày tháng năm = " + chuoi_ngay_thang_nam);

        //2. Chuyển đổi định dạng chuỗi ngày tháng: dd/mm/yyyy => yyyy-mm-dd
        String chuoi_ngay_vn = "17/03/2023";
        // chuyển định dạng ngày 17/03/2023 ==> 2023-03-17
        Log.d(TAG, "Chuỗi ban đầu: " + chuoi_ngay_vn);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");//Truyền vào
        // mẫu định dạng của chuỗi ban đầu
        try {
            Date objNgay = format.parse(chuoi_ngay_vn);
            String chuoi_moi = (String) dateFormat.format("yyyy-MM-dd", objNgay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialogSize() {
        sizeGiayDAO = new SizeGiayDAO(getContext());
        List<Size> selectItemSize = sizeGiayDAO.getAll();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] sizes = new String[selectItemSize.size()];
        List<String> strSize = Arrays.asList(sizes);
        Boolean[] sizeBool = new Boolean[sizes.length];

        for (int i = 0; i < selectItemSize.size(); i++) {
            sizes[i] = (selectItemSize.get(i).size);
        }
        builder.setTitle("Chọn Size").setMultiChoiceItems(sizes, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                sizeBool[i] =  b;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int j = 0; j < sizeBool.length; j++) {
                    boolean checked = sizeBool[j];
                    if (checked){
                        txtSize.setText(txtSize.getText()+ "\t"+ strSize.get(j));
                    }
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
       builder.create().show();
    }

    private void showDialogMau() {
        mauSacDAO = new MauSacDAO(getContext());
        List<MauSac> selectItemMau = mauSacDAO.getAll();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] maus = new String[selectItemMau.size()];
        List<String> strMau = Arrays.asList(maus);
        Boolean[] mauBool = new Boolean[selectItemMau.size()];

        for (int i = 0; i < selectItemMau.size(); i++) {
            maus[i] = (selectItemMau.get(i).tenMau);
        }
        builder.setTitle("Chọn Màu").setMultiChoiceItems(maus, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                mauBool[i] =  b;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int j = 0; j < mauBool.length; j++) {
                    boolean checked = mauBool[j];
                    if (checked){
                        txtMau.setText(txtMau.getText()+ "\t"+ strMau.get(j));
                    }
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}