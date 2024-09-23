package springboo.jsb_web.anhhethong;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

// Lớp thực thể chứa thông tin của ảnh upload
// lên thư mục cục bộ của hệ thống.
// Đường dẫn ảnh sẽ là bán tuyệt đối.
@Entity
@Getter
@Setter
public class AnhHeThong
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private Boolean   choPhep;
    private int       thuTu;

    private String tuaDe;

    @Column(columnDefinition="LONGTEXT")
    private String moTa;

    @Transient
    private MultipartFile mtFile; // Phục vụ việc upload file, ảnh đại diện của thực thể

    private String duongDan; // ví dụ: /images/abcdyxzldhl.jpg

    public String getChoPhepVi() {

        if(this.choPhep==null)
            this.choPhep = false;
            
        return this.choPhep ? "Cho Phép" : "Cấm, Không Cho";
    }

    public Boolean KhongHopLe() {
        var khl = false;

        return khl;
    }

}// end class
