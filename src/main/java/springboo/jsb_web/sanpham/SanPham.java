package springboo.jsb_web.sanpham;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import springboo.jsb_web.loai.Loai;
import springboo.jsb_web.nhasanxuat.NhaSanXuat;
// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class SanPham {
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;
    // private int thuTu; // Thứ tự sắp xếp tăng dần từ 1,2,3,...
    private LocalDate ngayTao; // theo dõi xem bản ghi được tạo khi nào (đặt đơn hàng)
    private LocalDate ngaySua; // theo dõi xem bản ghi bị sửa khi nào (đổi trạng thái đơn hàng)
    private Boolean choPhep; // Cho phép xuất hiện trên trang Public, Store Front hay không
    private String ten; // tuaDe; //tieuDe
    private String anh; // địa chỉ tuyệt đối ảnh Online,
    private String link;
    @Column(columnDefinition = "LONGTEXT")
    private String moTa; // html form textarea, jquery plugin, rich text editor (summernote)
    @Transient
    private MultipartFile mtFile; // Phục vụ việc upload file, ảnh đại diện của thực thể
    private String tag;  //mã định danh cho thực thể
    private String model; // là tên sản phẩm cộng với chi tiết về cấu hình
    private float donGia;
    private Boolean trangThai = true; // còn hàng, hết hàng, hàng cũ vs hàng mới
    private LocalDate ngayHetHan;
    private Boolean banChay;
    private Boolean noiBat;

    // private Integer maNhaSanXuat;
    // @ManyToOne @JoinColumn(name="maNhaSanXuat",insertable=false,updatable=false)
    // private NhaSanXuat nhaSanXuat;

    private Integer maNhaSanXuat;


    public Integer getMaNhaSanXuat() {
        return maNhaSanXuat;
    }

    public void setMaNhaSanXuat(Integer maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }

    @ManyToOne
    @JoinColumn(name = "maNhaSanXuat", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    
    private NhaSanXuat nhaSanXuat;



    private Integer maLoai;
    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    // @ManyToOne
    // @JoinColumn(name = "ma_loai", nullable = false)
    @ManyToOne
    @JoinColumn(name = "maLoai", insertable = false, updatable = false)
    private Loai loai;
    // #endregion

    public String getDonGiaVi() {
        return String.format("%,d", Math.round(this.donGia));
    }

    public String getChoPhepVi() {
        if (this.choPhep == null)
            this.choPhep = false;

        return this.choPhep ? "Được Phép" : "Bị Cấm";
    }

    public String getNgayTaoVi() {
        if (ngayTao == null) {
        }
        return "Chưa Cập Nhật";
    }

    // public String getNgayTaoSQL()
    // {
    // return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(this.ngayTao);
    // }
    public String getNgayTaoSQL() {
        if (this.ngayTao == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.ngayTao.format(formatter);
    }

    public String getNgayHetHanSQL() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(this.ngayHetHan);
    }

    public String getNgaySuaVi() {
        if (ngaySua == null) {
        }
        return "Chưa Cập Nhật";
        // return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngaySua);
    }

    public String getNgayHetHanVi() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayHetHan);
    }

    public static void them(SanPham sanPham) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'them'");
    }

}
