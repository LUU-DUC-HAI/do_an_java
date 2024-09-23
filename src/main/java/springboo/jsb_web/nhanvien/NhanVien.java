package springboo.jsb_web.nhanvien;


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
public class NhanVien
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int Id;

    private String    tenDangNhap; //username
    private String    email;
    private String    tenDayDu; // fullname: hiện lên trên lời chào
    private String    matKhau; // password
    private String    xacNhanMatKhau; // passwordConfirm
    private String    anhDaiDien; // avatar
    private Boolean   trangThai; // status: cấm hay không
    private int       dienThoai;
    private String    moTa;
    private int       thuTu; // thứ tự sắp xếp (do id ko sửa được, và khi xóa thì id cũng ko tăng dần đều)

    private LocalDate ngayTao; // Tài khoản n.v được cấp khi nào ???
    private LocalDate ngaySua; // Tài khoản n.v bị sửa vào khi nào ???
    private LocalDate ngayHetHan; // cũng là một biện pháp khóa nhân viên.


    

         
    public String getNgayTaoText() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayTao);
    }

    public String getNgayHetHanText() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayHetHan);
    }


    public Boolean KhongHopLe() {
        var khl = false;

        return khl;
    }

}// end class

