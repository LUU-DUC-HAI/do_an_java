package springboo.jsb_web.loai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
public class Loai
{
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tăng tự động từ 1,2,3,...
    private int id;

    private int       thuTu; // Thứ tự sắp xếp tăng dần từ 1,2,3,...
    private LocalDate ngayTao; // theo dõi xem bản ghi được tạo khi nào (đặt đơn hàng)
    private LocalDate ngaySua; // theo dõi xem bản ghi bị sửa khi nào (đổi trạng thái đơn hàng)
    private Boolean   choPhep; // Cho phép xuất hiện trên trang Public, Store Front hay không
    private String    ten; //tuaDe; //tieuDe
    private String    anh; // địa chỉ tuyệt đối ảnh Online, 
    private String    link;
    @Column(columnDefinition="LONGTEXT")
    private String    moTa; // html form textarea, jquery plugin, rich text editor (summernote)
    @Transient
    private MultipartFile mtFile; // Phục vụ việc upload file, ảnh đại diện của thực thể
    private int       cot; // Cột
    private int       loaiCha = 0; // loại cha ???
    private Boolean   top; // được hiển thị trên Menu Top hay không ?
    private Boolean   noiBat; // được hiển thị trên Menu Top hay không ?
    
    public String getChoPhepVi() 
    {
        if(this.choPhep==null)
            this.choPhep = false;

        return this.choPhep ? "Được Phép" : "Bị Cấm";
    }

    public String getNoiBatVi() 
    {
        if(this.noiBat==null)
            this.noiBat = false;

        return this.noiBat ? "Nổi Bật" : "Không nổi bật";
    }

    public String getTopVi() 
    {


        return this.top ? "Top" : "Dưới Top";
    }

    public String getNgayTaoVi() 
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayTao);
    }

    public String getNgaySuaVi() 
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngaySua);
    }


}// end class
