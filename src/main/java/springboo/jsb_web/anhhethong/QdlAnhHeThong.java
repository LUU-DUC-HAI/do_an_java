package springboo.jsb_web.anhhethong;


// Thư viện chuẩn: Java Standard Edition(JavaSE)
import java.util.List;
import java.time.LocalDate;

// Thư viện doanh nghiệp: Java Entprise Edition(JavaEE)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// Thư viện web: Java SpringBoot
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Thư viện tự định nghĩa
import springboo.jsb_web.qdl.Qdl;


@Controller
public class QdlAnhHeThong 
{
    @Autowired
    private DvlAnhHeThong dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private HttpServletRequest request; 

    @Autowired
    private HttpSession session;

    //@Autowired
    //private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/qdl/anhhethong",
            "/qdl/anhhethong/duyet"
    })
    public String getDuyet(Model model) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<AnhHeThong> list = dvl.duyet();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        model.addAttribute("title", "Quản Lý Ảnh Hệ Thống");

        // Nội dung riêng của trang...
        model.addAttribute("content", "anhhethong/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; 
    }

    @GetMapping("/qdl/anhhethong/them")
    public String getThem(Model model) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        AnhHeThong dl = new AnhHeThong();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "anhhethong/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; 
    }

    // @GetMapping("/qdl/anhhethong/sua/{id}")
    @GetMapping("/qdl/anhhethong/sua")
    public String getSua(Model model, @RequestParam("id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // trangSua(Model model, @PathVariable(value = "id") int id) {
        // Lấy ra bản ghi theo id
        AnhHeThong dl = dvl.xem(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "anhhethong/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; // layout.html
    }

    // @GetMapping("/qdl/anhhethong/xoa/{id}")
    // public String // Giao diện xác nhận xoá
    // trangXoa(Model model, @PathVariable(value = "id") int id) {
    @GetMapping("/qdl/anhhethong/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        AnhHeThong dl = dvl.timTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "anhhethong/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; // layout.html
    }

    @GetMapping("/qdl/anhhethong/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        AnhHeThong dl = dvl.xem(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "anhhethong/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html";    // layout.html
    }

    @PostMapping("/qdl/anhhethong/them")
    public String postThem(@ModelAttribute("AnhHeThong") AnhHeThong dl) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());
        
        // System.out.print("save action...");
        dvl.luu(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc thêm mới !");

        return "redirect:/qdl/anhhethong/duyet";
    }

    @PostMapping("/qdl/anhhethong/sua")
    public String postSua(@ModelAttribute("AnhHeThong") AnhHeThong dl) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";
        
        dl.setNgaySua(LocalDate.now());
        
        dvl.luu(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc cập nhật !");

        return "redirect:/qdl/anhhethong/duyet";
    }

    // @PostMapping("/qdl/anhhethong/xoabo/{id}")
    // public String // Hàm xử lý yêu cầu xoá 1 bản ghi
    //         xoabo(Model model, @PathVariable(value = "id") int id) 
    
    @PostMapping("/qdl/anhhethong/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Xoá dữ liệu
        this.dvl.xoaId(id);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc xóa !");

        // Điều hướng sang trang giao diện
        return "redirect:/qdl/anhhethong/duyet";
    }



}// end class

