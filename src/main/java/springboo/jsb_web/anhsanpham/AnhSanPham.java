package springboo.jsb_web.anhsanpham;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import springboo.jsb_web.sanpham.SanPham;


@Entity
@Getter
@Setter
public class AnhSanPham
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private String anh; // online url hoặc /path to image
    private int thuTu;
    // private LocalDate ngayTao;
    // private LocalDate ngaySua;
    
    private int maSanPham;
    // bổ sung insertable và updatable trong @JoinColumn để
    // cột MaLop không bị insert 2 lần khiến nó lỗi !
    @ManyToOne @JoinColumn(name="maSanPham",insertable=false,updatable=false)
    private SanPham sanPham;
    // #endregion

    // private LocalDate NgayThang;
    
    // public String getNgayThangVi() {
    // return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.NgayThang);
    // }


    @Transient
    MultipartFile mtFile;
    
    // public Boolean KhongHopLe() {
    //     var khl = false;

    //     if (this.Ten.length() < 2) {
    //         khl = true;
    //         System.out.print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
    //     }

    //     if (this.Ten.length() > 22) {
    //         khl = true;
    //         System.out.print("\n Lỗi->Tên phải không quá 22 kí tự. ");
    //     }

    //     return khl;
    // }

}// end class


