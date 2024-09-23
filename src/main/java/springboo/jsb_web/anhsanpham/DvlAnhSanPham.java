package springboo.jsb_web.anhsanpham;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DvlAnhSanPham 
//implements DviAnhSanPham
{
    @Autowired private KdlAnhSanPham kdl;// kho dữ liệu;

    //@Override 
    public List<AnhSanPham> dsAnhSanPham() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    // @Override 
    public List<AnhSanPham>  duyệtAnhSanPham() 
    {
        return duyet();
    }

    public List<AnhSanPham>  duyet() 
    {
        return kdl.findAll();
    }

    //@Override 
    public AnhSanPham  tìmAnhSanPhamTheoId(int id)
    {
        return tim(id);
    }
    
    // @Override 
    public AnhSanPham  tim(int id)// 
    {

        AnhSanPham dl = null;

        Optional<AnhSanPham> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    // @Override 
    public AnhSanPham xemAnhSanPham(int id)
    {
        return xem(id);
    }

    public AnhSanPham xem(int id)// 
    {

        AnhSanPham dl = null;

        Optional<AnhSanPham> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    // @Override
    public void lưuAnhSanPham(AnhSanPham dl)
    {
        luu(dl);
    }

    public void luu(AnhSanPham dl)
    {
        // MultipartFile file = dl.getMtFile();
        // if (file != null && !file.isEmpty()) 
        // {
        //     try {
        //         // ược sử dụng để lấy tên gốc của file mà người dùng đã tải lên
        //         String fileName = file.getOriginalFilename();
        //         String uploadDir = "src/main/resources/static/images/sanpham/";
       
        //         // kiểm tra thu mục tồn tại hay không nếu chưa có thì nó sẽ tạo thư mục đó
        //         if (!Files.exists(Paths.get(uploadDir))) {
        //             Files.createDirectories(Paths.get(uploadDir));
        //         }

        //         String filePath = uploadDir + UUID.randomUUID().toString() + "_" + fileName;
               
        //         //  sao chép nội dung của file ảnh từ  MultipartFile  vào đường dẫn được chỉ định trong biến  filePath
        //         Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
       
               
        //         // được sử dụng để lấy tên file sau khi đã được lưu vào thư mục đích. Cụ thể:
        //         String savedFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        //         // anh san pham được lưu vào đây src/main/resoures/static/images/sanpham/
        //         dl.setAnh("/images/sanpham/" + savedFileName);
        //     } catch (IOException e) {
        //         // Xử lý lỗi nếu có
        //     }
        // }
        
        // lưu dữ liệu vào database sau khi xử lý ảnh
        this.kdl.save(dl);
    }

    public void them(AnhSanPham dl)
    {
        this.kdl.save(dl);
    }

    public void sua(AnhSanPham dl)
    {
        this.kdl.save(dl);
    }

    // @Override
    public void xóaAnhSanPham(int id)
    {
        xoa(id);
    }

    public void xoa(int id)
    {
        this.kdl.deleteById(id);
    }

}

