package springboo.jsb_web.nhanvien;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboo.jsb_web.nhasanxuat.NhaSanXuat;

@Service
public class DvlNhanVien {
    @Autowired
    private KdlNhanVien kdl;// kho dữ liệu;

    public List<NhanVien> dsNhanVien() // getAllThucThe()
    {

        return kdl.findAll();
    }

    public List<NhanVien> duyệtNhanVien() {
        return kdl.findAll();
    }

    public NhanVien tìmNhanVienTheoId(int id)//
    {
        NhanVien dl = null;

        Optional<NhanVien> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            // throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung
            // !");
        }

        return dl;

    }

    public NhanVien xemNhanVien(int id)//
    {

        NhanVien dl = null;

        Optional<NhanVien> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            // throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung
            // !");
        }

        return dl;

    }

    public void luuNhanVien(NhanVien dl) {
        luu(dl);
    }



    private void luu(NhanVien dl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'luu'");
    }

    public void lưuNhanVien(NhanVien dl) {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }




    public void xóaNhanVien(int id) {
        xoa(id);
    }

    public void xoa(int id) {
        this.kdl.deleteById(id);
    }


    public void sua(NhanVien dl){
        this.kdl.save(dl);
    }

    public NhanVien tìmNhanVienTheoTenDangNhap(String tdn) {
        NhanVien dl = null;

        dl = kdl.findOneByTenDangNhap(tdn);// OK

        return dl;
    }

    public Boolean tồnTạiTênĐăngNhập(String tdn) {
        return kdl.existsByTenDangNhap(tdn);
    }


}
