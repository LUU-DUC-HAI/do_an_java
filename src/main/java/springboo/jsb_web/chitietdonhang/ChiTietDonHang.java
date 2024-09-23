package springboo.jsb_web.chitietdonhang;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import springboo.jsb_web.donhang.DonHang;
import springboo.jsb_web.sanpham.SanPham;


@Entity
@Getter
@Setter
public class ChiTietDonHang
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;
    private String           ten; // tên sản phẩm
    private String        model; // model của sản phẩm
    private int         soLuong;// số lượng mua
    private LocalDate   ngayTao; //@todo Fix lỗi khi sửa form nếu có
    private LocalDate   ngaySua;
    private float        donGia; // đơn giá tại thời điểm đặt hàng
    private float      tongTien; // 

    // #region Nếu không có FK thì bỏ mã này đi
    private int maDonHang;
    private int maSanPham;

    // Mỗi chi tiết đơn hàng phải liên quan đến 1 đơn hàng
    @ManyToOne @JoinColumn(name="maDonHang",insertable=false,updatable=false)
    private DonHang donHang;
    
    // Mỗi chi tiết đơn hàng phải liên quan đến 1 sản phẩm
    @ManyToOne @JoinColumn(name="maSanPham",insertable=false,updatable=false)
    private SanPham sanPham;
    // #endregion


}// end class

