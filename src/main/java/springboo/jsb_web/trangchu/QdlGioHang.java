package springboo.jsb_web.trangchu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.chitietdonhang.ChiTietDonHang;
import springboo.jsb_web.chitietdonhang.DvlChiTietDonHang;
import springboo.jsb_web.donhang.DonHang;
import springboo.jsb_web.donhang.DvlDonHang;
import springboo.jsb_web.khachhang.DvlKhachHang;
import springboo.jsb_web.khachhang.KhachHang;
import springboo.jsb_web.sanpham.DvlSanPham;
import springboo.jsb_web.sanpham.SanPham;

@Controller
@RequestMapping("/giohang")
public class QdlGioHang {

    @Autowired
    private HttpSession session;

    @Autowired
    private DvlSanPham dvlSanPham;

    @Autowired
    private DvlDonHang dvlDonHang;

    @Autowired
    private DvlChiTietDonHang dvlChiTietDonHang;

    @Autowired
    private DvlKhachHang dvlKhachHang;

    @GetMapping("/ajax/get-html")
    public String getGioHangAjax(Model model) {
        if (!gioHangCoSanPham()) {
            return "trangchu/giohang-trong.html";
        }

        Map<Integer, Integer> cartMap = getCartFromSession();
        List<Map<String, String>> cartData = getCartData(cartMap);

        model.addAttribute("cartData", cartData);
        model.addAttribute("tongGiaTriGioHang", tongGiaTriGioHang());
        model.addAttribute("tongGiaTriGioHangVi", tongGiaTriGioHangVi());
        return "trangchu/giohang.html";
    }

    @PostMapping(path = "/them/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postGioHangThemMoi(
            @RequestParam("id_sanpham") int id,
            @RequestParam("soluong") int soluong,
            @RequestParam("ten") String ten) {

        // Kiểm tra nếu sản phẩm không tồn tại
        SanPham sp = dvlSanPham.xem(id);
        if (sp == null) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("error", "Sản phẩm không tồn tại");
            return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
        }

        Map<Integer, Integer> cartMap = getCartFromSession();
        Integer cartQuantity = (Integer) session.getAttribute("SoSanPhamTrongGioHang");

        if (cartMap.containsKey(id)) {
            cartMap.put(id, cartMap.get(id) + soluong);
        } else {
            cartMap.put(id, soluong);
        }
        cartQuantity += soluong;

        session.setAttribute("cart", cartMap);
        session.setAttribute("SoSanPhamTrongGioHang", cartQuantity);

        Map<String, Object> data = new HashMap<>();
        data.put("success", "Đã thêm thành công vào giỏ hàng sản phẩm " + ten);
        data.put("total", demSanPhamTrongGioHang());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/sua/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postGioHangSuaAjax(Model model,
            @RequestParam("id_sanpham") int id,
            @RequestParam("so_luong") int soluong,
            @RequestParam("ten") String ten) {
        // ... existing code ...

        Map<Integer, Integer> cartMap = getCartFromSession(); // Lấy giỏ hàng từ session

        if (cartMap == null) {
            cartMap = new HashMap<>(); // Khởi tạo nếu cartMap là null
        }
        // Tính tổng giá trị giỏ hàng
        double tongGiaTri = 0.0;
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            double price = getPriceById(productId); // Lấy giá sản phẩm theo ID
            tongGiaTri += price * quantity; // Cộng dồn tổng giá trị
        }

        // Gửi dữ liệu JSON trở lại cho View
        Map<String, Object> data = new HashMap<>();
        data.put("success", "Đã cập nhật giỏ hàng, số lượng mới cho sản phẩm " + ten);
        data.put("total", soluong);
        data.put("tongGiaTri", tongGiaTri); // Thêm tổng giá trị vào response

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // @PostMapping(path = "/giohang/sua/ajax", produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> postGioHangSuaAjax(Model model,
    // @RequestParam("id_sanpham") int id,
    // @RequestParam("so_luong") int soluong,
    // @RequestParam("ten") String ten) {
    // if (session.getAttribute("cart") == null) {
    // session.setAttribute("cart", new HashMap<Integer, Integer>());
    // session.setAttribute("SoSanPhamTrongGioHang", 0);
    // }

    // @SuppressWarnings("unchecked")
    // Map<Integer, Integer> cartMap = (Map<Integer, Integer>)
    // session.getAttribute("cart");
    // Integer cartQuantity = (Integer)
    // session.getAttribute("SoSanPhamTrongGioHang");

    // // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng mới
    // if (cartMap.containsKey(id)) {
    // int so_luong_cu = cartMap.get(id);
    // cartMap.put(id, soluong);
    // cartQuantity += (soluong - so_luong_cu);
    // } else {
    // // Nếu sản phẩm mới được thêm vào giỏ hàng
    // cartMap.put(id, soluong);
    // cartQuantity += soluong;
    // }

    // session.setAttribute("SoSanPhamTrongGioHang", cartQuantity);

    // // Cập nhật giỏ hàng trong Session
    // session.setAttribute("cart", cartMap);

    // System.out.println("Đã cập nhật giỏ hàng với sản phẩm id: " + id);

    // // Tính tổng giá trị giỏ hàng
    // double tongGiaTri = 0.0;
    // for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
    // int productId = entry.getKey();
    // int quantity = entry.getValue();
    // // Giả sử bạn có phương thức để lấy giá sản phẩm theo ID
    // double price = getPriceById(productId); // Phương thức này bạn cần định nghĩa
    // tongGiaTri += price * quantity;
    // }

    // // Gửi dữ liệu JSON trở lại cho View
    // Map<String, Object> data = new HashMap<>();
    // data.put("success", "Đã cập nhật giỏ hàng, số lượng mới cho sản phẩm " +
    // ten);
    // data.put("total", cartQuantity);
    // data.put("tongGiaTri", tongGiaTri); // Thêm tổng giá trị vào response

    // return new ResponseEntity<>(data, HttpStatus.OK);
    // }

    @PostMapping(path = "/xoa/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postGioHangXoaAjax(
            @RequestParam("id_sanpham") int id,
            @RequestParam("ten") String ten) {

        Map<Integer, Integer> cartMap = getCartFromSession();
        Integer cartQuantity = (Integer) session.getAttribute("SoSanPhamTrongGioHang");

        if (cartMap.containsKey(id)) {
            cartQuantity -= cartMap.get(id); // Giảm số lượng sản phẩm trong giỏ hàng
            cartMap.remove(id); // Xóa sản phẩm khỏi giỏ hàng
        } else {
            return new ResponseEntity<>("Sản phẩm không tồn tại trong giỏ hàng", HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("cart", cartMap);
        session.setAttribute("SoSanPhamTrongGioHang", cartQuantity);

        Map<String, Object> data = new HashMap<>();
        data.put("success", "Đã xóa sản phẩm " + ten + " khỏi giỏ hàng.");
        data.put("total", demSanPhamTrongGioHang()); // Cập nhật tổng số sản phẩm trong giỏ hàng

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // Phương thức giả định để lấy giá sản phẩm theo ID
    private double getPriceById(int productId) {
        // Cần có logic để lấy giá sản phẩm từ database hoặc source khác
        return 100.0; // Ví dụ giá mặc định
    }

    private int demSanPhamTrongGioHang() {
        Map<Integer, Integer> cartMap = getCartFromSession();

        if (cartMap == null || cartMap.isEmpty())
            return 0;

        int tongSoSanPham = 0;
        for (Integer maSanPham : cartMap.keySet()) {
            tongSoSanPham += cartMap.get(maSanPham);
        }

        return tongSoSanPham;
    }

    private boolean gioHangCoSanPham() {
        Map<Integer, Integer> cartMap = getCartFromSession();

        return cartMap != null && !cartMap.isEmpty();
    }

    private String tongGiaTriGioHangVi() {
        return String.format("%,.0f", tongGiaTriGioHang());
    }

    private float tongGiaTriGioHang() {
        if (!gioHangCoSanPham())
            return 0;

        float tong = 0;
        Map<Integer, Integer> cartMap = getCartFromSession();

        for (Integer maSanPham : cartMap.keySet()) {
            SanPham sp = dvlSanPham.xem(maSanPham);
            if (sp == null)
                continue; // Bỏ qua nếu sản phẩm không tồn tại
            int soluong = cartMap.get(maSanPham);
            tong += sp.getDonGia() * soluong;
        }

        return tong;
    }

    private Map<Integer, Integer> getCartFromSession() {
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cartMap == null) {
            cartMap = new HashMap<>();
            session.setAttribute("cart", cartMap);
            session.setAttribute("SoSanPhamTrongGioHang", 0);
        }
        return cartMap;
    }

    private List<Map<String, String>> getCartData(Map<Integer, Integer> cartMap) {
        List<Map<String, String>> cartData = new ArrayList<>();
        for (Integer maSanPham : cartMap.keySet()) {
            SanPham sp = dvlSanPham.xem(maSanPham);
            if (sp == null)
                continue; // Bỏ qua nếu sản phẩm không tồn tại
            float thanhTien = cartMap.get(maSanPham) * sp.getDonGia();

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(sp.getId()));
            map.put("ten", sp.getTen());
            map.put("donGia", String.valueOf(sp.getDonGia()));
            map.put("donGiaVi", String.format("%,.0f", sp.getDonGia()));
            map.put("anh", sp.getAnh());
            map.put("soluong", String.valueOf(cartMap.get(maSanPham)));
            map.put("thanhTien", String.format("%,.0f", thanhTien));

            cartData.add(map);
        }
        return cartData;
    }

    @GetMapping("/chitiet/ajax/get-html")
    public ResponseEntity<Object> getGioHangChiTiet(@RequestParam("id_sanpham") int id) {
        SanPham sp = dvlSanPham.xem(id);
        Map<String, Object> data = new HashMap<>();

        if (sp != null) {
            data.put("id", sp.getId());
            data.put("ten", sp.getTen());
            data.put("donGia", sp.getDonGia());
            data.put("anh", sp.getAnh());
        } else {
            data.put("error", "Sản phẩm không tồn tại");
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/chitiet")
    public String getGioHangChiTiet(Model model) {
        if// nếu không có sản phẩm
        (!gioHangCoSanPham()) {
            model.addAttribute("content", "trangchu/giohang-trong.html");

            // ...được đặt vào bố cục chung của toàn website
            return "layout/layout-home.html"; // layout.html
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");

        List<Map<String, String>> cartData = new ArrayList<Map<String, String>>();

        for (Integer maSanPham : cartMap.keySet()) {
            // System.out.println(key + ":" + map.get(key));
            // tongSoSanPham += cartMap.get(maSanPham);
            SanPham sp = dvlSanPham.xem(maSanPham);
            var donGiaStr = String.valueOf(sp.getDonGia());
            float thanhTien = cartMap.get(maSanPham) * sp.getDonGia();

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(sp.getId()));
            map.put("ten", sp.getTen());
            map.put("donGia", donGiaStr);

            map.put("donGiaVi", String.format("%,.2f", sp.getDonGia()));
            map.put("anh", sp.getAnh());
            map.put("soluong", String.valueOf(cartMap.get(maSanPham)));
            map.put("thanhTien", String.format("%,.0f", thanhTien));

            cartData.add(map);
        }

        // Gửi dữ liệu giỏ hàng sang bên View
        model.addAttribute("cartData", cartData);
        model.addAttribute("tongGiaTriGioHang", tongGiaTriGioHang());
        model.addAttribute("tongGiaTriGioHangVi", tongGiaTriGioHangVi());

        model.addAttribute("content", "trangchu/giohang-chitiet.html");

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-home.html"; // layout.html

    }

    @GetMapping("/thanhtoan")
    public String getGioHangThanhToan(Model model) {
        if// nếu không có sản phẩm
        (!gioHangCoSanPham()) {
            model.addAttribute("content", "trangchu/giohang-trong.html");

            // ...được đặt vào bố cục chung của toàn website
            return "layout/layout-home.html"; // layout.html
        }

        DonHang donHang = new DonHang();
        donHang.setNgayTao(LocalDate.now());
        Integer maKhachHang = (Integer) session.getAttribute("makhachHang");
        if (maKhachHang != null) {
            // khách hàng hội viên khi đăng nhập
            KhachHang khachHang = dvlKhachHang.xemKhachhang(maKhachHang);
            // donHang.setMaKhachHang(khachHang.getId());
        } else {
            // khách hàng vãn lai
            String email = "someEmail@example.com"; // Hoặc nhận từ request/form
            String phone = "123456789";
            String tenDayDu = "Nguyen Van A";
            String diaChi = "123 Đường ABC, Thành Phố XYZ";

            donHang.setEmail(email);
            donHang.setPhone(phone);
            donHang.setTenDayDu(tenDayDu);
            donHang.setDiaChi(diaChi);
        }

        // donHang.setGhiChu(ghiChu);
        // donHang.setTrangThai(DonHang.trangThai.Đang_Sử_lý);

        // dvlDonHang.save(donHang);

        // Kiểm tra xem, khách hàng là vãng lai(anonymous), hay thành viên (membership)
        // int maKhachHang = 0; // vãng lai
        // donHang.setEmail(email);
        // donHang.setPhone(phone);

        // Nếu khách đăng nhập rồi (session.CUS_LOGGED)
        // thì cập nhật lại giá trị cho maKhachHang

        // để còn gán mã khách hàng vào form thanh toán
        // var dh = new DonHang();

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");

        List<Map<String, String>> cartData = new ArrayList<Map<String, String>>();

        // String phone = ""; // Thay thế bằng giá trị thực tế nếu có
        // String trangThai = ""; // Thay thế bằng giá trị thực tế nếu có

        for (Integer maSanPham : cartMap.keySet()) {
            // System.out.println(key + ":" + map.get(key));
            // tongSoSanPham += cartMap.get(maSanPham);
            SanPham sp = dvlSanPham.xem(maSanPham);
            var donGiaStr = String.valueOf(sp.getDonGia());
            float thanhTien = cartMap.get(maSanPham) * sp.getDonGia();

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(sp.getId()));
            map.put("ten", sp.getTen());
            map.put("donGia", donGiaStr);
            // map.put("phone", phone);
            map.put("donGiaVi", String.format("%,.2f", sp.getDonGia()));
            map.put("anh", sp.getAnh());
            map.put("soluong", String.valueOf(cartMap.get(maSanPham)));
            map.put("thanhTien", String.format("%,.0f", thanhTien));
            // map.put("trangThai", trangThai);
            System.out.println("Trạng thái đơn hàng: " + donHang.getTrangThai());
            donHang.setTrangThai(DonHang.trangThai.Đang_Sử_lý);
            cartData.add(map);
        }

        // Gửi dữ liệu giỏ hàng sang bên View
        model.addAttribute("cartData", cartData);
        model.addAttribute("tongGiaTriGioHang", tongGiaTriGioHang());
        model.addAttribute("tongGiaTriGioHangVi", tongGiaTriGioHangVi());

        // model.addAttribute("dl", dh); // gửi dữ liệu khách hàng sang
        model.addAttribute("maKhachHang", maKhachHang); // gửi dữ liệu khách hàng sang
        model.addAttribute("content", "trangchu/giohang-thanhtoan.html");

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-home.html"; // layout.html

    }

    @PostMapping(path = "/luu")
    public String postGioHangLuu(@ModelAttribute DonHang dh) {
        dh.setNgayTao(LocalDate.now());
        dh.setNgaySua(LocalDate.now());
        dh.setTrangThai(DonHang.trangThai.Đang_Sử_lý);

        var donHangMoi = this.dvlDonHang.luuDonHang(dh);
        int maDon = donHangMoi.getId();

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");

        for (Integer maSanPham : cartMap.keySet()) {
            SanPham sp = dvlSanPham.xem(maSanPham);
            int soLuong = cartMap.get(maSanPham);

            var ctdh = new ChiTietDonHang();
            ctdh.setMaDonHang(maDon);
            ctdh.setMaSanPham(maSanPham);
            ctdh.setTen(sp.getTen());
            ctdh.setModel(sp.getModel());
            ctdh.setDonGia(sp.getDonGia());
            ctdh.setSoLuong(soLuong);
            ctdh.setTongTien(soLuong * sp.getDonGia());
            ctdh.setNgayTao(LocalDate.now());
            ctdh.setNgaySua(LocalDate.now());

            this.dvlChiTietDonHang.lưuChiTietDonHang(ctdh);
        }

        // Xóa sạch sản phẩm trong giỏ hàng Session
        khoiTaoGioHang();

        session.setAttribute("THONGBAO_TRANGCHU", "Đơn hàng đã được đặt thành công !");

        return "redirect:/trangchu";

    }

    private void khoiTaoGioHang() {
        session.setAttribute("cart", new HashMap<Integer, Integer>());
        session.setAttribute("SoSanPhamTrongGioHang", 0);
    }

    @GetMapping("/trangchu")
    public String trangChu(Model model, HttpSession session) {
        // Xóa thông báo sau khi nó đã được hiển thị
        session.removeAttribute("THONGBAO_TRANGCHU");
        return "layout/layout-home.html";
    }

}
