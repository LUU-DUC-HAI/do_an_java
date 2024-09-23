package springboo.jsb_web.khachhang;

// Thư viện chuẩn: Java Standard Edition(JavaSE)
import java.util.List;
import java.time.LocalDate;

// Thư viện doanh nghiệp: Java Entprise Edition(JavaEE)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// Thư viện web: Java SpringBoot
import org.springframework.ui.Model;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboo.jsb_web.nhanvien.NhanVien;
// import springboo.jsb_web.khachhang.khachhang;
// Thư viện tự định nghĩa
import springboo.jsb_web.qdl.Qdl;

@Controller
public class QdlKhachHang {
    @Autowired
    private DvlKhachHang dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    // @Autowired
    // private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    @GetMapping({
    "/qdl/khachhang",
    "/qdl/khachhang/duyet"
    })
    public String getDuyet(Model model) {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    List<KhachHang> list = dvl.duyetKhachhang();
    model.addAttribute("ds", list);
    model.addAttribute("dl", new KhachHang());
    model.addAttribute("title", "Quản Lý Khách Hàng");
    model.addAttribute("content", "khachhang/duyet.html");
    return "layout/layout.html";
    }

    // @GetMapping("/qdl/khachhang/them")
    // public String getThem(Model model)
    // {
    // if( Qdl.NhanVienChuaDangNhap(request) )
    // return "redirect:/qdl/nhanvien/dangnhap";

    // var dl = new KhachHang();
    // model.addAttribute("dl", dl);
    // model.addAttribute("content", "khachhang/them.html");
    // return "layout/layout.html";
    // }

    @GetMapping("/qdl/khachhang/them")
    public String getThem(Model model) {
        KhachHang dl = new KhachHang();
        model.addAttribute("dl", dl);
        model.addAttribute("content", "khachhang/them.html");
        return "khachhang/them";
    }

    @GetMapping("/qdl/khachhang/them/ajax/modal")
    public String getThemAjaxModal(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = new NhanVien();
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/khachhang/them");
        model.addAttribute("content", "khachhang/them.html");
        return "modal-bs4.html";
    }


    @GetMapping("/qdl/khachhang/sua")
    public String getSua(Model model, @RequestParam("id") int id)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Lấy ra bản ghi theo mã định danh
    var dl = dvl.xem(id);

    // Gửi đối tượng dữ liệu sang giao diện (ui view) html form
    model.addAttribute("dl", dl);

    // Hiển thị giao diện view html
    // Nội dung riêng của trang...
    model.addAttribute("content", "khachhang/sua.html"); // sua.html

    // ...được đặt vào bố cục chung của toàn website
    return "layout/layout.html"; // layout.html
    }

    // @GetMapping("/qdl/khachhang/sua/ajax/{id}")
    // public String getSuaAjax(Model model, @PathVariable(value = "id") int id) {
    // if (Qdl.NhanVienChuaDangNhap(request))
    //     return "redirect:/qdl/nhanvien/dangnhap";

    // var dl = dvl.xem(id);
    // model.addAttribute("dl", dl);
    // return "khachhang/sua"; // Trả về view sửa
    // }

    @GetMapping("/qdl/khachhang/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Lấy ra bản ghi theo id
    var dl = dvl.xem(id);

    // Gửi đối tượng dữ liệu sang giao diện html form xác nhận xóa
    model.addAttribute("dl", dl);

    // Hiển thị giao diện xác nhận xóa
    // Nội dung riêng của trang...
    model.addAttribute("content", "khachhang/xoa.html"); // xoa.html

    // ...được đặt vào bố cục chung của toàn website
    return "layout/layout.html"; // layout.html
    }

    // @GetMapping("/qdl/khachhang/xoa/ajax/{id}")
    // public String getXoaAjax(Model model, @PathVariable(value = "id") int id) {
    // if (Qdl.NhanVienChuaDangNhap(request))
    //     return "redirect:/qdl/nhanvien/dangnhap";

    // var dl = dvl.xem(id);
    // model.addAttribute("dl", dl);
    // return "khachhang/xoa"; // Trả về view xóa
    // }


    @GetMapping("/qdl/khachhang/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Lấy ra bản ghi theo id
    var dl = dvl.xem(id);

    // Gửi đối tượng dữ liệu sang giao diện html form
    model.addAttribute("dl", dl);

    // Hiển thị view giao diện
    // Nội dung riêng của trang...
    model.addAttribute("content", "khachhang/xem.html");

    // ...được đặt vào bố cục chung của toàn website
    return "layout/layout.html"; // layout.html
    }
    // @GetMapping("/qdl/khachhang/xem/ajax/{id}")
    // public String getXemAjax(Model model, @PathVariable(value = "id") int id) {
    // if (Qdl.NhanVienChuaDangNhap(request))
    //     return "redirect:/qdl/nhanvien/dangnhap";

    // var dl = dvl.xem(id);
    // model.addAttribute("dl", dl);
    // return "khachhang/xem"; // Trả về view xem
    // }

    @PostMapping("/qdl/khachhang/them")
    public String postThem(@ModelAttribute("KhachHang") KhachHang dl)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Một số thông tin thêm mới do hệ thống làm
    // bên cạnh dữ liệu mà user gõ trên Form Add New
    dl.setNgayTao(LocalDate.now());
    // dl.setNgaySua(LocalDate.now());

    var inputPassword = dl.getPassword();

    // Mã hóa
    var hash = BCrypt.hashpw(inputPassword, BCrypt.gensalt(12));
    dl.setPassword(hash);

    // dvl.luu(dl);
    dvl.them(dl);

    // Gửi thông báo thành công từ giao diện Thêm Mới sang giao diện Duyệt
    session.setAttribute("message", "Đã hoàn tất việc thêm mới !");

    return "redirect:/qdl/khachhang/duyet";
    }

    @PostMapping("/qdl/khachhang/sua")
    public String postSua(@ModelAttribute("KhachHang") KhachHang dl)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Một số thông tin cập nhật do hệ thống làm
    // bên cạnh dữ liệu mà user gõ trên Form Edit/Update
    // dl.setNgaySua(LocalDate.now());

    dvl.sua(dl);
    // dvl.luu(dl);

    // Gửi thông báo thành công từ view Add/Edit sang view List
    session.setAttribute("message", "Đã hoàn tất việc cập nhật !");

    return "redirect:/qdl/khachhang/duyet";
    }

    // request param phải khớp với name="Id" của thẻ html input
    // Khi xóa thì Java chỉ cần biết mã định danh của bản ghi cần xóa
    @PostMapping("/qdl/khachhang/xoa")
    public String postXoa(Model model, @RequestParam("id") int id)
    {
    if( Qdl.NhanVienChuaDangNhap(request) )
    return "redirect:/qdl/nhanvien/dangnhap";

    // Xoá dữ liệu
    this.dvl.xoa(id);

    // Gửi thông báo thành công từ view Add/Edit sang view List
    session.setAttribute("message", "Đã hoàn tất việc xóa !");

    // Điều hướng sang trang giao diện
    return "redirect:/qdl/khachhang/duyet";
    }

}// end class
