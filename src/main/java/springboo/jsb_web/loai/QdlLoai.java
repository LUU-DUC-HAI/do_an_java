package springboo.jsb_web.loai;

// Thư viện chuẩn: Java Standard Edition(JavaSE)
import java.util.List;
import java.time.LocalDate;

// Thư viện doanh nghiệp: Java Entprise Edition(JavaEE)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.nhanvien.NhanVien;
import springboo.jsb_web.nhasanxuat.DvlNhaSanXuat;
import springboo.jsb_web.nhasanxuat.NhaSanXuat;
import springboo.jsb_web.qdl.Qdl;
import springboo.jsb_web.sanpham.SanPham;

// Thư viện web: Java SpringBoot
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Thư viện tự định nghĩa

@Controller
public class QdlLoai {
    @Autowired
    private DvlLoai dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    // @Autowired
    // private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    @GetMapping("/qdl/loai")
    public String getPhanTrang(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        String sortField; // tên cột sắp xếp
        String sortDir; // sort direction, chiều sắp xếp: asc, desc
        String sortRev; // sort reversion, đảo chiều sắp xếp
        int pageNumber; // current page number, no: số thứ tự của trang hiện tại
        int pageSize; // kích thước của mỗi trang (số phần tử (tối đa)trên mỗi trang).

        try {
            pageNumber = request.getParameter("pageNumber") == null ? 1
                    : Integer.parseInt(request.getParameter("pageNumber"));
            pageSize = request.getParameter("pageSize") == null ? 5
                    : Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageNumber = 1;
            pageSize = 5;
        }

        if (pageNumber < 1)
            pageNumber = 1;
        if (pageSize < 1)
            pageSize = 5;

        sortField = request.getParameter("sortField");
        sortDir = request.getParameter("sortDir");

        if (sortField == null || sortField.trim().isEmpty()) {
            sortField = "ten";
        }

        if (sortDir == null || sortDir.trim().isEmpty()) {
            sortDir = "asc";
            sortRev = "desc";
        } else if (sortDir.equals("asc")) {
            sortRev = "desc";
        } else if (sortDir.equals("desc")) {
            sortRev = "asc";
        } else {
            sortDir = "asc";
            sortRev = "desc";
        }

        String linkSortTen = "/qdl/loai/phantrang?pageNumber=" + pageNumber + "&pageSize=" + pageSize
                + "&sortField=ten&sortDir=" + sortRev;
        String linkPage = "/qdl/loai/phantrang?sortField=" + sortField + "&sortDir=" + sortDir + "&pageSize="
                + pageSize + "&pageNumber=";

        Page<Loai> page = dvl.getPaged(pageNumber, pageSize, sortField, sortDir);
        List<Loai> list = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageItems", list);
        model.addAttribute("pageCount", list.size());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortRev", sortRev);
        model.addAttribute("linkSortTen", linkSortTen);
        model.addAttribute("linkPage", linkPage);
        model.addAttribute("ds", list);
        model.addAttribute("dl", new Loai());
        model.addAttribute("title", "Phân Trang Loại Sản Phẩm ");
        model.addAttribute("content", "loai/duyet.html");

        return "layout/layout.html";
    }

    // @GetMapping({
    // // "/qdl/loai",
    // "/qdl/loai/duyet"
    // })
    // public String getDuyet(Model model)
    // {
    // // Xác minh danh tính nhân viên đang cố truy cập vào phần Quản Trị
    // if( Qdl.NhanVienChuaDangNhap(request) )
    // return "redirect:/qdl/nhanvien/dangnhap";
    // List<Loai> list = dvl.duyet();
    // model.addAttribute("ds", list);
    // model.addAttribute("dl", new Loai());
    // model.addAttribute("title", "Quản Lý Loại(Danh Mục)");
    // model.addAttribute("content", "loai/duyet.html");
    // return "layout/layout.html";
    // }
    @GetMapping("/duyet")
    public String duyet(Model model, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Loai> loaiPage = DvlLoai.layTatCa(PageRequest.of(page - 1, size));

        model.addAttribute("loaiPage", loaiPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", loaiPage.getTotalPages());

        return "loai/duyet";
    }
    

    @GetMapping("/qdl/loai/them")
    public String postThem(Model model) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = new Loai();
        model.addAttribute("dl", dl);
        model.addAttribute("content", "loai/them.html");
        return "loai/them";
    }

    @GetMapping("/qdl/loai/them/ajax/modal")
    public String postThemAjaxModal(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = new Loai();
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/laoi/them");
        model.addAttribute("content", "loai/them.html");
        return "modal-bs4.html";
    }

    @GetMapping("/qdl/loai/sua")
    public String getSua(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = dvl.xem(id);

        model.addAttribute("dl", dl);
        model.addAttribute("content", "loai/sua.html"); // sua.html
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/loai/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        var dl = dvl.xem(id);

        // Gửi đối tượng dữ liệu sang giao diện html form xác nhận xóa
        model.addAttribute("dl", dl);

        // Hiển thị giao diện xác nhận xóa
        // Nội dung riêng của trang...
        model.addAttribute("content", "loai/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout.html"; // layout.html
    }

    /**
     * Đường dẫn của phần xem chi tiết sẽ hơi khác chút so với sửa, xóa
     * ví dụ:
     * http://localhost:6868/qdl/thucthe/xem/3
     * nghĩa là xem chi tiết thực thể có mã định danh = 3
     * 
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/qdl/loai/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Lấy ra bản ghi theo id
        var dl = dvl.xem(id);

        // Gửi đối tượng dữ liệu sang giao diện html form
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "loai/xem.html");

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout.html"; // layout.html
    }

    @PostMapping("/qdl/loai/them")
    public String postThem(@ModelAttribute("NhaSanXuat") Loai dl) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Một số thông tin thêm mới do hệ thống làm
        // bên cạnh dữ liệu mà user gõ trên Form Add New
        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());

        // dvl.luu(dl);
        dvl.them(dl);

        // Gửi thông báo thành công từ giao diện Thêm Mới sang giao diện Duyệt
        session.setAttribute("message", "Đã hoàn tất việc thêm mới !");

        return "redirect:/qdl/loai/duyet";
    }

    /**
     * 
     * @param dl Dữ Liệu mà HTM Form gửi lên
     * @return
     */
    @PostMapping("/qdl/loai/sua")
    public String postSua(@ModelAttribute("Loai") Loai dl) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Một số thông tin cập nhật do hệ thống làm
        // bên cạnh dữ liệu mà user gõ trên Form Edit/Update
        dl.setNgaySua(LocalDate.now());

        dvl.sua(dl);
        // dvl.luu(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc cập nhật !");

        return "redirect:/qdl/loai/duyet";
    }

    // request param phải khớp với name="Id" của thẻ html input
    // Khi xóa thì Java chỉ cần biết mã định danh của bản ghi cần xóa
    @PostMapping("/qdl/loai/xoa")
    public String postXoa(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Xoá dữ liệu
        this.dvl.xoa(id);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        session.setAttribute("message", "Đã hoàn tất việc xóa !");

        // Điều hướng sang trang giao diện
        return "redirect:/qdl/loai/duyet";
    }

}// end class
