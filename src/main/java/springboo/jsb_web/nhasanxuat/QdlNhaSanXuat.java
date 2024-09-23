package springboo.jsb_web.nhasanxuat;

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
import org.springframework.web.bind.annotation.ResponseBody;
// Phan Trang
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

// Thư viện tự định nghĩa
import springboo.jsb_web.qdl.Qdl;

@Controller
public class QdlNhaSanXuat {
    @Autowired
    private DvlNhaSanXuat dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private DvlNhaSanXuat DvlNhaSanXuat;
    

    @GetMapping("/qdl/nhasanxuat")
    public String getPhanTrang(Model model) {
        if( Qdl.NhanVienChuaDangNhap(request) )
        return "redirect:/qdl/nhanvien/dangnhap";
        String sortField; // tên cột sắp xếp
        String sortDir; // sort direction, chiều sắp xếp: asc, desc
        String sortRev; // sort reversion, đảo chiều sắp xếp
        int pageNumber; // current page number, no: số thứ tự của trang hiện tại
        int pageSize; // kích thước của mỗi trang (số phần tử (tối đa)trên mỗi trang).
        
        try {
            pageNumber = request.getParameter("pageNumber") == null ? 1
                    : Integer.parseInt(request.getParameter("pageNumber"));
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (Exception e) {
            pageNumber = 1;
            pageSize = 5;
            // @todo: Liệu có đọc được ra từ setting ???
        }

        if (pageNumber < 1)
            pageNumber = 1;
        if (pageSize < 1)
            pageSize = 5;

        sortField = request.getParameter("sortField");
        sortDir = request.getParameter("sortDir");
        if (sortField == null || sortField.trim().isEmpty())
            sortField = "ten";

        // Tinh chỉnh, chuẩn hóa giá trị của sortDir và sortRev:
        // xác định chiều sắp xếp:
        if (sortDir == null || sortDir.trim().isEmpty()) {
            sortDir = "asc";
            sortRev = "desc";
        } else if (sortDir.equals("asc")) {
            sortRev = "desc";
        } else if (sortDir.equals("desc")) {
            sortRev = "asc";
        } else { // url có chứa sortDir, nhưng giá trị không đúng, ko phù hợp
            sortDir = "asc";
            sortRev = "desc";
        }

        // Các đường link gửi sang bênview
        // Link sắp xếp theo cột: ten
        String linkSortTen = "/qdl/nhasanxuat/phantrang?pageNumber=" + pageNumber + "&pageSize=" + pageSize
                + "&sortField=ten&sortDir=" + sortRev;
        // Link gắn vào các nút phân trang, có số trang là đang chờ để lắp ghép bên view
        String linkPage = "/qdl/nhasanxuat/phantrang?sortField=" + sortField + "&sortDir=" + sortDir + "&pageSize="
                + pageSize + "&pageNumber=";

        Page<NhaSanXuat> page; // biến mô phỏng thông tin trang hiện tại
        List<NhaSanXuat> list; // danh sách các thực thể sẽ xuất hiện trên trang hiện tại (current page)
        page = dvl.getPaged(pageNumber, pageSize, sortField, sortDir);
        list = page.getContent();
        model.addAttribute("currentPage", pageNumber); // trang hiện tại
        model.addAttribute("pageNumber", pageNumber); // trang hiện tại
        model.addAttribute("pageSize", pageSize); // kích thước mỗi trang
        model.addAttribute("pageItems", list); // các phần tử (object) trên trang
        model.addAttribute("pageCount", list.size()); // các phần tử (object) trên trang
        model.addAttribute("totalPages", page.getTotalPages()); // tổng số trang
        model.addAttribute("totalItems", page.getTotalElements()); // tổng số phần tử tìm thấy
        model.addAttribute("totalElements", page.getTotalElements()); // tổng số phần tử tìm thấy
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortRev", sortRev);
        model.addAttribute("linkSortTen", linkSortTen);
        model.addAttribute("linkPage", linkPage);
        model.addAttribute("ds", list);
        model.addAttribute("dl", new NhaSanXuat());
        model.addAttribute("title", "Phân Trang Nhà Sản Xuất"); // duyet.html
        model.addAttribute("content", "nhasanxuat/duyet.html"); // duyet.html
        return "layout/layout.html";
    }

    @GetMapping("/qdl/nhasanxuat/duyet")
    public String getDuyet(Model model) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        List<NhaSanXuat> list = dvl.duyet();
        model.addAttribute("ds", list);
        model.addAttribute("dl", new NhaSanXuat());
        model.addAttribute("title", "Quản Lý Nhà Sản Xuất");
        model.addAttribute("content", "nhasanxuat/duyet.html");
        return "/qdl/nhasanxuat";
    }


    // @GetMapping("/qdl/nhasanxuat/them")
    // public String getThem(Model model) {
    //     if (Qdl.NhanVienChuaDangNhap(request))
    //         return "redirect:/qdl/nhanvien/dangnhap";
    //     var dl = new NhaSanXuat();
    //     model.addAttribute("dl", dl);
    //     model.addAttribute("action", "/qdl/nhasanxuat/them");
    //     model.addAttribute("content", "nhasanxuat/them.html");
    //     return "layout/layout.html";
    // }

    // @GetMapping("/qdl/nhasanxuat/them/ajax/modal")
    // public String getThemAjaxModal(Model model) {
    //     if (Qdl.NhanVienChuaDangNhap(request))
    //         return "redirect:/qdl/nhanvien/dangnhap";
    //     var dl = new NhaSanXuat();
    //     model.addAttribute("dl", dl);
    //     model.addAttribute("content", "nhasanxuat/them.html");
    //     return "nhasanxuat/modal-bs4";
    // }

    @GetMapping("/qdl/nhasanxuat/sua")
    public String getSua(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        var dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/nhasanxuat/sua");
        model.addAttribute("content", "nhasanxuat/them.html"); // sua.html
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/nhasanxuat/sua/ajax/modal")
    public String getSuaAjaxModal(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        var dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/nhasanxuat/sua");
        model.addAttribute("content", "nhasanxuat/them.html"); // sua.html
        return "nhasanxuat/modal-bs4.html"; // layout.html
    }

    @GetMapping("/qdl/nhasanxuat/suaajax")
    public String getSuaAjax(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        var dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/nhasanxuat/sua");
        return "nhasanxuat/them.html"; // layout.html
    }

    @GetMapping("/qdl/nhasanxuat/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        var dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhasanxuat/xoa.html"); // xoa.html
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/nhasanxuat/xoa/ajax/modal")
    public String getXoaAjaxModal(Model model, @RequestParam(value = "id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = dvl.xem(id);

        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhasanxuat/xoa.html"); // xoa.html
        return "nhasanxuat/modal-bs4.html"; // layout.html
    }


    @GetMapping("/qdl/nhasanxuat/xem")
    public String getXem(@RequestParam("id") int id, Model model) {
    NhaSanXuat nhaSanXuat = DvlNhaSanXuat.timTheoId(id);
    if (nhaSanXuat == null) {
        return "redirect:/qdl/nhasanxuat";
    }
    model.addAttribute("nhaSanXuat", nhaSanXuat);
    model.addAttribute("nhaSanXuat", nhaSanXuat);
    return "nhasanxuat/xem";
    }
   
    
    

    @PostMapping("/qdl/nhasanxuat/them")
    public String postThem(@ModelAttribute("NhaSanXuat") NhaSanXuat dl) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());
        dvl.them(dl);
        session.setAttribute("message", "Đã hoàn tất việc thêm mới !");
        return "redirect:/qdl/nhasanxuat";
    }


    @PostMapping("/qdl/nhasanxuat/sua")
    public String postSua(@ModelAttribute("NhaSanXuat") NhaSanXuat dl) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        dl.setNgaySua(LocalDate.now());

        dvl.sua(dl);
        session.setAttribute("message", "Đã hoàn tất việc cập nhật !");
        return "redirect:/qdl/nhasanxuat";
    }

    @PostMapping("/qdl/nhasanxuat/xoa")
    public String postXoa(Model model, @RequestParam("id") int id) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        this.dvl.xoa(id);

        session.setAttribute("message", "Đã hoàn tất việc xóa !");
        return "redirect:/qdl/nhasanxuat";
    }

}// end class
