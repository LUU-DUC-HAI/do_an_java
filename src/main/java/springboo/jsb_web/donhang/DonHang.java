package springboo.jsb_web.donhang;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonHang
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private String maKhachHang; // ko ràng buộc, vì tính cả khách vãng lai maKh = 0
    private String email;
    private String phone;
    private String tenDayDu;
    private String diaChi;
    private String ghiChu;
    // private String tongTien;
    private Double tongTien;
    // private Boolean trangThai;
    @Enumerated(EnumType.STRING) 
    private trangThai trangThai;
    private LocalDate ngayTao;
    private LocalDate ngaySua;

    public enum trangThai{
        Đang_Sử_lý,Đã_Gủi,Đã_Hoàn_Thành,Đã_Hủy
    }

    



}// end class