package springboo.jsb_web.khachhang;

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
public class KhachHang
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private int thuTu;

    private String ten;
    private String anh;
    private String diaChi;

    private String email;
    private String phone;
    private String password;
    
    private Boolean choPhep;

    private LocalDate ngayTao;
    private LocalDate ngaySua;


    public String getChoPhepVi() 
    {
        if(this.choPhep==null)
            this.choPhep = false;

        return this.choPhep ? "Được Phép" : "Bị Cấm";
    }
}// end class