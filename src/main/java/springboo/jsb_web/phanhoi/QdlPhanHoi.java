package springboo.jsb_web.phanhoi;




// Thư viện chuẩn: Java Standard Edition(JavaSE)
import java.util.List;
import java.time.LocalDate;

// Thư viện doanh nghiệp: Java Entprise Edition(JavaEE)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.qdl.Qdl;

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
// import spring.jsb_web_shop.qdl.Qdl;


@Controller
public class QdlPhanHoi 
{
    @Autowired
    private DvlPhanHoi dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private HttpServletRequest request; 

    @Autowired
    private HttpSession session;

    //@Autowired
    //private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/qdl/phanhoi",
            "/qdl/phanhoi/duyet"
    })
    public String getDuyet(Model model) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<PhanHoi> list = dvl.duyệtPhanHoi();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        model.addAttribute("title", "Quản Lý Phản Hồi Khách Hàng");

        // Nội dung riêng của trang...
        model.addAttribute("content", "phanhoi/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; 
    }

    @GetMapping("/qdl/phanhoi/them")
    public String getThem(Model model) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        PhanHoi dl = new PhanHoi();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "phanhoi/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
        return "layout.html"; 
    }

    // @GetMapping("/qdl/phanhoi/sua/{id}")
    @GetMapping("/qdl/phanhoi/sua")
    public String getSua(Model model, @RequestParam("id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // trangSua(Model model, @PathVariable(value = "id") int id) {
        // Lấy ra bản ghi theo id
        PhanHoi dl = dvl.xemPhanHoi(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "phanhoi/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-admin.html"; // layout.html
    }

    // @GetMapping("/qdl/phanhoi/xoa/{id}")
    // public String // Giao diện xác nhận xoá
    // trangXoa(Model model, @PathVariable(value = "id") int id) {
    @GetMapping("/qdl/phanhoi/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        PhanHoi dl = dvl.tìmPhanHoiTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "phanhoi/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-admin.html"; // layout.html
    }

    @GetMapping("/qdl/phanhoi/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        PhanHoi dl = dvl.xemPhanHoi(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "phanhoi/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-admin.html";    // layout.html
    }

    @PostMapping("/qdl/phanhoi/them")
    public String postThem(@ModelAttribute("PhanHoi") PhanHoi dl) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());
        
        // System.out.print("save action...");
        dvl.lưuPhanHoi(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc thêm mới !");

        return "redirect:/qdl/phanhoi/duyet";
    }

    @PostMapping("/phanhoi/sua")
    public String postSua(@ModelAttribute("PhanHoi") PhanHoi dl) 
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";
        
        dl.setNgaySua(LocalDate.now());
        
        dvl.lưuPhanHoi(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc cập nhật !");

        return "redirect:/phanhoi/duyet";
    }

    // @PostMapping("/phanhoi/xoabo/{id}")
    // public String // Hàm xử lý yêu cầu xoá 1 bản ghi
    //         xoabo(Model model, @PathVariable(value = "id") int id) 
    
    @PostMapping("/phanhoi/xoa")
    public String postXoa(Model model, @RequestParam("Id") int id) // request param phải khớp với name="Id" của thẻ html input
    {
        if( Qdl.NhanVienChuaDangNhap(request) )
            return "redirect:/qdl/nhanvien/dangnhap";

        // Xoá dữ liệu
        this.dvl.xóaPhanHoi(id);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc xóa !");

        // Điều hướng sang trang giao diện
        return "redirect:/phanhoi/duyet";
    }



}// end class
