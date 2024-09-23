package springboo.jsb_web.khachhang;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import springboo.jsb_web.Khachhang.Khachhang;

@Service
public class DvlKhachHang 
//implements DviKhachHang
{
    @Autowired private KdlKhachHang kdl;// kho dữ liệu;

    // // @Override 
    // public List<KhachHang> dsKhachHang() // getAllThucThe()
    // {
  
    //     return kdl.findAll();
    // }

    // // @Override 
    // public List<KhachHang>  duyệtKhachHang() 
    // {
    //     return duyet();
    // }

    // public List<KhachHang>  duyet() 
    // {
    //     return kdl.findAll();
    // }

    // // @Override 
    // public KhachHang  tìmKhachHangTheoId(int id)// 
    // {
    //     // TODO Auto-generated method stub
    //     // return null;

    //     // return kdl.findById(id);

    //     KhachHang dl = null;

    //     Optional<KhachHang> optional = kdl.findById(id);

    //     if// nếu
    //     (optional.isPresent()) // tìm thấy bản ghi trong kho
    //     {
    //         dl = optional.get();
    //     } else// ngược lại
    //     {
    //         //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
    //     }

    //     return dl;

    // }

    // // @Override 
    // public KhachHang xemKhachHang(int id)
    // {
    //     return xem(id);
    // }
    // public KhachHang xem(int id)
    // {

    //     KhachHang dl = null;

    //     Optional<KhachHang> optional = kdl.findById(id);

    //     if// nếu
    //     (optional.isPresent()) // tìm thấy bản ghi trong kho
    //     {
    //         dl = optional.get();
    //     } else// ngược lại
    //     {
    //         //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
    //     }

    //     return dl;

    // }

    // // @Override
    // public void lưuKhachHang(KhachHang dl)
    // {
    //     // TODO Auto-generated method stub
    //     this.kdl.save(dl);
    // }

    // public void them(KhachHang dl)
    // {
    //     this.kdl.save(dl);
    // }

    // public void sua(KhachHang dl)
    // {
    //     this.kdl.save(dl);
    // }

    // // @Override
    // public void 
    // xóaKhachHang(int id)
    // {
    //     // TODO Auto-generated method stub
    //     this.kdl.deleteById(id);
    // }

    // public void xoa(int id)
    // {
    //     this.kdl.deleteById(id);
    // }

    // @Override
    public List<KhachHang> dsKhachhang() {
        return kdl.findAll();
    }

    public List<KhachHang> duyet() {
        return kdl.findAll();
    }

    public List<KhachHang> duyetKhachhang() {
        return duyet();
    }

    // @Override
    public List<KhachHang> duyệtKhachhang() {
        return duyet();
    }

    public KhachHang timKhachhangTheoId(int id) {
        return timTheoId(id);
    }

    // @Override
    public  KhachHang timTheoId(int id)//
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        KhachHang dl = null;

        Optional<KhachHang> optional = kdl.findById(id);

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

    public KhachHang xemKhachhang(int id)//
    {
        return xem(id);
    }

    // @Override
    public KhachHang xem(int id) {

        KhachHang dl = null;

        Optional<KhachHang> optional = kdl.findById(id);

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

    // @Override
    public void lưuKhachhang(KhachHang dl) {
        luu(dl);
    }

    public void luu(KhachHang dl) {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    public void them(KhachHang dl) {
        this.kdl.save(dl);
    }

    public void sua(KhachHang dl) {
        this.kdl.save(dl);
    }

    // @Override
    public void xóaKhachhang(int id) {
        xoa(id);
    }

    public void xoa(int id) {
        this.kdl.deleteById(id);
    }

    public static KhachHang findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }


}


