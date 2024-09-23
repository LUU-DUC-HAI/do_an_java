package springboo.jsb_web.nhasanxuat;



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

// import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

// Làm mẫu cho clas ThucThe
@Entity
@Getter
@Setter
public class NhaSanXuat
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

    public String getChoPhepVi() 
    {
        if(this.choPhep==null)
            this.choPhep = false;

        return this.choPhep ? "Được Phép" : "Bị Cấm";
    }


    public String getMoTaUnEscaped()
    {
        return HtmlUtils.htmlUnescape(this.moTa);
    }


    public String getMoTaHtmlDecoded()
    {
        System.out.println(this.moTa);
        System.out.println("\n \n \n");
        // return StringEscapeUtils.unescapeHtml4(this.moTa);
        String test = "ket qua html decode: "+htmlDecode(this.moTa);

        System.out.println(test);
        return test;
    }
    

     // Sử dụng Maven Lombok để tạo get, set ngầm định

    public String getNgayTaoVi() 
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngayTao);
    }

    public String getNgaySuaVi() 
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.ngaySua);
    }


    public String htmlDecode(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        s = s.replace("&nbsp;", " ");
        s = s.replace("&quot;", "\"");
        s = s.replace("&apos;", "'");
        s = s.replace("&#39;", "'");
        s = s.replace("&lt;", "<");
        s = s.replace("&gt;", ">");
        s = s.replace("&amp;", "&");

        // whitespace patterns
        String zeroOrMoreWhitespaces = "\\s*?";
        String oneOrMoreWhitespaces = "\\s+?";

        // replace <br/> by \n
        s = s.replaceAll(
                "<" + zeroOrMoreWhitespaces + "br" + zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + ">",
                "\n");
        // replace HTML-tabs by \t
        s = s.replaceAll("<" + zeroOrMoreWhitespaces + "span" + oneOrMoreWhitespaces + "style"
                + zeroOrMoreWhitespaces + "=" + zeroOrMoreWhitespaces + "\"white-space:pre\""
                + zeroOrMoreWhitespaces + ">&#9;<" + zeroOrMoreWhitespaces + "/" + zeroOrMoreWhitespaces + "span"
                + zeroOrMoreWhitespaces + ">", "\t");

        return s;
    }

    public  int length(String s) {
        if (s == null) {
            return 0;
        } else {
            return s.length();
        }
    }

    /**
     * replace plain text without using regex
     */
    public String replace(String s, String sOld, String sNew) {
        sNew = (sNew == null ? "" : sNew);
        if (s == null || sOld == null) {
            return s;
        }
        return s.replace(sOld, sNew);
    }



}// end class


