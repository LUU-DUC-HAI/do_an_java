package springboo.jsb_web.lienhe;

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
public class LienHe
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private String ten; // Java String <=> MySQL varchar(255)
    private String email;

    @Column(columnDefinition="TEXT") // vẫn chỉ là varchar(255)
    private String tieuDe;

    @Column(columnDefinition="LONGTEXT")
    private String tinNhan;


    private String dienThoai;
    private String diaChi;
    private String webSite;
    
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