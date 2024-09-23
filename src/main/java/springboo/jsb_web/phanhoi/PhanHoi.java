package springboo.jsb_web.phanhoi;


import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhanHoi
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private String ten; // bắt nhập
    private String phone;
    private String email; // bắt nhập
    private String webSite;
    private String diaChi;

    private String tieuDe; // bắt nhập

    @Column(columnDefinition="LONGTEXT")
    //ALTER TABLE `phan_hoi` MODIFY `tin_nhan` LONGTEXT
    private String tinNhan;// bắt nhập, mặc định: varchar(255)
    
    private LocalDate ngayTao;
    private LocalDate ngaySua;

    public Boolean KhongHopLe() {
        var khl = false;

        // if (this.Ten.length() < 2) {
        //     khl = true;
        //     System.out.print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
        // }

        // if (this.Ten.length() > 22) {
        //     khl = true;
        //     System.out.print("\n Lỗi->Tên phải không quá 22 kí tự. ");
        // }

        return khl;
    }

}// end class