package springboo.jsb_web.quangcao;

//package congty.tdl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QuangCao {
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private String tuaDe;
    private String tuaDePhu;
    private String moTa;
    private float giaTien;
    private Boolean trangThai;
    private String anh;
    private String link;
    private String thuTu;

    // Sử dụng Maven Lombok để tạo get, set ngầm định

    private LocalDate ngayTao;
    private LocalDate ngaySua;

    public String getTrangThaiVi() {
        return this.trangThai ? "Cho Phép" : "Cấm, Không Cho";
    }

    // public String getChoPhepVi() {

    //     if(this.choPhep==null)
    //         this.choPhep = false;
            
    //     return this.choPhep ? "Cho Phép" : "Cấm, Không Cho";
    // }

    public Boolean KhongHopLe() {
        var khl = false;

        return khl;
    }

}// end class
