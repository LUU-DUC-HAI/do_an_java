-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 23, 2024 lúc 05:34 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `web_ban_hang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `anh_he_thong`
--

CREATE TABLE `anh_he_thong` (
  `id` int(11) NOT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `duong_dan` varchar(255) DEFAULT NULL,
  `mo_ta` longtext DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `thu_tu` int(11) NOT NULL,
  `tua_de` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `anh_he_thong`
--

INSERT INTO `anh_he_thong` (`id`, `cho_phep`, `duong_dan`, `mo_ta`, `ngay_sua`, `ngay_tao`, `thu_tu`, `tua_de`) VALUES
(1, b'1', '/anhhethong/6676b338-bc96-4e6f-a3ee-cd920cdfc1fd_cam.jpg', '', '2024-08-23', '2024-08-23', 0, 'Cam Siêu ngọt'),
(2, b'1', '/anhhethong/fb0019ce-72de-4ba8-8d90-11b87763d2d1_bo.jpg', '', '2024-08-23', NULL, 0, 'Cam Siêu ngọt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `anh_san_pham`
--

CREATE TABLE `anh_san_pham` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `ma_san_pham` int(11) NOT NULL,
  `thu_tu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cai_dat`
--

CREATE TABLE `cai_dat` (
  `id` int(11) NOT NULL,
  `gia_tri` varchar(255) DEFAULT NULL,
  `khoa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_don_hang`
--

CREATE TABLE `chi_tiet_don_hang` (
  `id` int(11) NOT NULL,
  `don_gia` float NOT NULL,
  `ma_don_hang` int(11) NOT NULL,
  `ma_san_pham` int(11) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `so_luong` int(11) NOT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tong_tien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_don_hang`
--

INSERT INTO `chi_tiet_don_hang` (`id`, `don_gia`, `ma_don_hang`, `ma_san_pham`, `model`, `ngay_sua`, `ngay_tao`, `so_luong`, `ten`, `tong_tien`) VALUES
(1, 200000, 1, 5, 'Việt quất', '2024-09-23', '2024-09-23', 1, 'Việt Quất', 200000),
(2, 60000, 2, 7, 'Bơ sáp', '2024-09-23', '2024-09-23', 1, 'Bơ sáp', 60000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang`
--

CREATE TABLE `don_hang` (
  `id` int(11) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ma_khach_hang` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ten_day_du` varchar(255) DEFAULT NULL,
  `tong_tien` double DEFAULT NULL,
  `trang_thai` enum('Đang_Sử_lý','Đã_Gủi','Đã_Hoàn_Thành','Đã_Hủy') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `don_hang`
--

INSERT INTO `don_hang` (`id`, `dia_chi`, `email`, `ghi_chu`, `ma_khach_hang`, `ngay_sua`, `ngay_tao`, `phone`, `ten_day_du`, `tong_tien`, `trang_thai`) VALUES
(1, 'Hà Nôi', 'van@gmail.com', 'cần gấp', '', '2024-09-23', '2024-09-23', '0123456789', 'Nguyễn văn A', 200000, 'Đang_Sử_lý'),
(2, 'Hà Nôi', 'nguyenvana@gmail.com', 'cần gấp', '', '2024-09-23', '2024-09-23', '7787655465', 'Nguyễn văn B', 60000, 'Đang_Sử_lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE `khach_hang` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `thu_tu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`id`, `anh`, `cho_phep`, `dia_chi`, `email`, `ngay_sua`, `ngay_tao`, `password`, `phone`, `ten`, `thu_tu`) VALUES
(1, NULL, b'0', NULL, 'admin1@gmail.com', NULL, '2024-08-23', '$2a$12$HwoDrYnq9xLiONi0PLMm2.CLbe2WHmdFyxYzCnrgNvl0lD1cGcTIm', '7787655465', 'admin1', 0),
(2, NULL, b'1', NULL, 'nguyenvana@gmail.com', NULL, '2024-08-28', '$2a$12$xY/LbIWBNDH05O8dfxkpx.fAJJLw.V7np7H8vNlc1TXYgIhw3sbj2', '0123456789', 'Nguyễn Văn A', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lien_he`
--

CREATE TABLE `lien_he` (
  `id` int(11) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `dien_thoai` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tieu_de` text DEFAULT NULL,
  `tin_nhan` longtext DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `cot` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `loai_cha` int(11) NOT NULL,
  `mo_ta` longtext DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `noi_bat` bit(1) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `thu_tu` int(11) NOT NULL,
  `top` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`id`, `anh`, `cho_phep`, `cot`, `link`, `loai_cha`, `mo_ta`, `ngay_sua`, `ngay_tao`, `noi_bat`, `ten`, `thu_tu`, `top`) VALUES
(1, 'https://teenypizza.com/nhung-loai-trai-cay-tot-cho-suc-khoe/imager_3_26211_700.jpg', b'1', 0, NULL, 1, 'ngon', '2024-09-05', '2024-09-05', b'1', 'Cam Ngọt', 1, b'1'),
(3, 'https://vnn-imgs-a1.vgcloud.vn/image.vtcns.com/files/ctv.kinhte/2019/10/08/nho1-0526304.png', b'1', 0, NULL, 1, 'ngon ngọt', '2024-09-16', '2024-09-16', b'1', 'Nho Sữa ', 2, b'1'),
(4, 'https://media.truyenhinhdulich.vn/upload/news/1377_bo_sap_dac_san_dak_lak.jpg', b'1', 0, NULL, 2, 'ngon', '2024-09-16', '2024-09-16', b'1', 'Bơ sáp', 2, b'1'),
(5, 'https://food-map.s3.ap-southeast-1.amazonaws.com/news/2022/07/42-ri6.jpg', b'1', 0, NULL, 1, 'thơm ngon ', '2024-09-16', '2024-09-16', b'1', 'Sầu Riêng ', 3, b'1'),
(6, 'https://thuyanhfruits.com/wp-content/uploads/2020/11/thanh-long-do.jpg', b'1', 0, NULL, 1, 'ngon ngọt ', '2024-09-16', '2024-09-16', b'1', 'Thanh Long ruột đỏ', 4, b'1'),
(7, 'https://exson.com.vn/wp-content/uploads/2019/03/chuoi-gia-nam-my-chin-e1507686366387.jpg', b'1', 0, NULL, 1, 'ngon ngọt ', '2024-09-16', '2024-09-16', b'1', 'Chuối', 5, b'1'),
(8, 'https://thanhnien.mediacdn.vn/Uploaded/minhnguyet/2022_04_24/qua-viet-quoc-781.jpg', b'1', 0, NULL, 1, 'ngon ngọt', '2024-09-18', '2024-09-18', b'1', 'Việt Quất', 6, b'1'),
(9, 'https://hoaquafuji.com/storage/app/media/kiwi2.jpg', b'1', 0, NULL, 1, 'ngon ngọt ', '2024-09-18', '2024-09-18', b'1', 'Kiwi', 7, b'1'),
(10, 'https://www.conngongvang.com/wp-content/uploads/2019/01/xoai_cat_hoa_loc.jpg', b'1', 0, NULL, 1, 'ngon ngọt', '2024-09-18', '2024-09-18', b'1', 'Xoài Cát ', 8, b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `id` int(11) NOT NULL,
  `anh_dai_dien` varchar(255) DEFAULT NULL,
  `dien_thoai` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `ngay_het_han` date DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten_dang_nhap` varchar(255) DEFAULT NULL,
  `ten_day_du` varchar(255) DEFAULT NULL,
  `thu_tu` int(11) NOT NULL,
  `trang_thai` bit(1) DEFAULT NULL,
  `xac_nhan_mat_khau` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `anh_dai_dien`, `dien_thoai`, `email`, `mat_khau`, `mo_ta`, `ngay_het_han`, `ngay_sua`, `ngay_tao`, `ten_dang_nhap`, `ten_day_du`, `thu_tu`, `trang_thai`, `xac_nhan_mat_khau`) VALUES
(2, '', 0, 'admin@gmail.com', '$2a$12$Zes6Y4mHpcJqFqISR4DSJeFr68WvmRpm1hWpiV8lxAatkvmSoN8ny', '', NULL, '2024-06-24', '2024-06-24', 'admin1', 'admin1', 0, b'1', '123456');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nha_san_xuat`
--

CREATE TABLE `nha_san_xuat` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `mo_ta` longtext DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `thu_tu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nha_san_xuat`
--

INSERT INTO `nha_san_xuat` (`id`, `anh`, `cho_phep`, `link`, `mo_ta`, `ngay_sua`, `ngay_tao`, `ten`, `thu_tu`) VALUES
(1, 'https://img.lovepik.com/free-png/20211214/lovepik-juicy-oranges-png-image_401625067_wh1200.png', b'1', 'https://thu-gian-voi-hinh-anh-trai-cay-cuc-dang-yeu%207.com', NULL, '2024-09-18', '2024-09-18', 'Cam Ngọt', 0),
(2, 'https://ttol.vietnamnetjsc.vn/images/2024/03/07/10/11/xoai-1.jpg', b'1', 'https://ttol.vietnamnetjsc.vn/images/2024/03/07/10/11/xoai-1.com', NULL, '2024-09-18', '2024-09-18', 'Xoài Cát ', 0),
(3, 'https://giongcay.net/uploads/products/cach-nhan-giong-oi-khong-hat.jpg', b'1', 'https://giongcay.net/uploads/products/cach-nhan-giong-oi-khong-hat.com', NULL, '2024-09-18', '2024-09-18', 'Ổi Ngọt Không Hạt', 0),
(4, 'https://exson.com.vn/wp-content/uploads/2019/03/chuoi-gia-nam-my-chin-e1507686366387.jpg', b'1', 'https://chuoi-gia-nam-my-chin-e1507686366387.exson.com.vn', NULL, '2024-09-18', '2024-09-18', 'Chuối', 0),
(5, 'https://cdn.nhathuoclongchau.com.vn/unsafe/800x0/https://cms-prod.s3-sgn09.fptcloud.com/tac_dung_qua_luu_do_1_a7aee3e111.jpg', b'1', 'https://tac_dung_qua_luu_do_1_a7aee3e111./cdn.nhathuoclongchau.com.vn', NULL, '2024-09-18', '2024-09-18', 'Lựu Đỏ ', 0),
(6, 'https://cf.shopee.vn/file/86105bcc4c00786ef0ec51e61f84daf7', b'1', 'https://86105bcc4c00786ef0ec51e61f84daf7.cf.shopee.vn', NULL, '2024-09-18', '2024-09-18', 'Cóc Non', 0),
(7, 'https://www.shutterstock.com/image-photo/isolated-apples-whole-red-pink-260nw-736739470.jpg', b'1', 'https://-260nw-736739470.www.shutterstock.com', NULL, '2024-09-18', '2024-09-18', 'Táo', 0),
(8, 'https://vnn-imgs-a1.vgcloud.vn/image.vtcns.com/files/ctv.kinhte/2019/10/08/nho1-0526304.png', b'1', 'https://ctv.kinhte/2019/10/08/nho1-0526304.vnn-imgs-a1.vgcloud.vn', NULL, '2024-09-18', '2024-09-18', 'Nho Sữa ', 0),
(9, 'https://hoaquafuji.com/storage/app/media/kiwi2.jpg', b'1', 'https://hoaquafuji.com/storage/app/media/kiwi2.com', NULL, '2024-09-18', '2024-09-18', 'Kiwi', 0),
(10, 'https://thanhnien.mediacdn.vn/Uploaded/minhnguyet/2022_04_24/qua-viet-quoc-781.jpg', b'1', 'https://thanhnien.mediacdn.vn/Uploaded/minhnguyet/2022_04_24/qua-viet-quoc-781.com', NULL, '2024-09-18', '2024-09-18', 'Việt Quất', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phan_hoi`
--

CREATE TABLE `phan_hoi` (
  `id` int(11) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tieu_de` varchar(255) DEFAULT NULL,
  `tin_nhan` longtext DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quang_cao`
--

CREATE TABLE `quang_cao` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `gia_tien` float NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `thu_tu` varchar(255) DEFAULT NULL,
  `trang_thai` bit(1) DEFAULT NULL,
  `tua_de` varchar(255) DEFAULT NULL,
  `tua_de_phu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san_pham`
--

CREATE TABLE `san_pham` (
  `id` int(11) NOT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `ban_chay` bit(1) DEFAULT NULL,
  `cho_phep` bit(1) DEFAULT NULL,
  `don_gia` float NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `ma_loai` int(11) DEFAULT NULL,
  `mo_ta` longtext DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `ngay_het_han` date DEFAULT NULL,
  `ngay_sua` date DEFAULT NULL,
  `ngay_tao` date DEFAULT NULL,
  `noi_bat` bit(1) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `thu_tu` int(11) NOT NULL,
  `trang_thai` bit(1) DEFAULT NULL,
  `ma_nha_san_xuat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `san_pham`
--

INSERT INTO `san_pham` (`id`, `anh`, `ban_chay`, `cho_phep`, `don_gia`, `link`, `ma_loai`, `mo_ta`, `model`, `ngay_het_han`, `ngay_sua`, `ngay_tao`, `noi_bat`, `tag`, `ten`, `thu_tu`, `trang_thai`, `ma_nha_san_xuat`) VALUES
(1, 'https://www.conngongvang.com/wp-content/uploads/2019/01/xoai_cat_hoa_loc.jpg', b'1', b'1', 45000, NULL, 10, 'ngon ngọt', 'Xoài Cát', '2024-09-18', NULL, NULL, b'1', 'Xoài Cát', 'Xoài Cát ', 0, b'1', 2),
(2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm4cvs5IY7kevbt2eOwVqu3oGa-KW0O9v8hA&s', b'1', b'1', 35000, NULL, 1, 'ngon ngọt', 'Cam', '2024-09-18', NULL, NULL, b'1', 'cam', 'Cam Ngọt', 0, b'1', 1),
(3, 'https://exson.com.vn/wp-content/uploads/2019/03/chuoi-gia-nam-my-chin-e1507686366387.jpg', b'1', b'1', 20000, NULL, 7, 'thơm ngon ', 'Chuối', '2024-09-18', NULL, NULL, b'1', 'Chuối', 'Chuối ', 0, b'1', 4),
(4, 'https://vnn-imgs-a1.vgcloud.vn/image.vtcns.com/files/ctv.kinhte/2019/10/08/nho1-0526304.png', b'1', b'1', 60000, NULL, 3, 'ngon ngọt', 'Nho', '2024-09-18', NULL, NULL, b'1', 'nho', 'Nho Sữa ', 0, b'1', 8),
(5, 'https://thanhnien.mediacdn.vn/Uploaded/minhnguyet/2022_04_24/qua-viet-quoc-781.jpg', b'1', b'1', 200000, NULL, 8, 'ngon nọt', 'Việt quất', '2024-09-23', NULL, NULL, b'1', 'việt quất', 'Việt Quất', 0, b'1', 10),
(6, 'https://hoaquafuji.com/storage/app/media/kiwi2.jpg', b'1', b'1', 150000, NULL, 9, 'ngon ', 'Kiwi', '2024-09-23', NULL, NULL, b'1', 'Kiwi', 'Kiwi', 0, b'1', 9),
(7, 'https://media.truyenhinhdulich.vn/upload/news/1377_bo_sap_dac_san_dak_lak.jpg', b'1', b'1', 60000, NULL, 4, 'ngon', 'Bơ sáp', '2024-09-23', NULL, NULL, b'1', 'bơ', 'Bơ sáp', 0, b'1', 1),
(8, 'https://thuyanhfruits.com/wp-content/uploads/2020/11/thanh-long-do.jpg', b'1', b'1', 45000, NULL, 6, 'ngọt', 'Thanh Long', '2024-09-23', NULL, NULL, b'1', 'thanh long', 'Thanh Long ruột đỏ', 0, b'1', 5);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `anh_he_thong`
--
ALTER TABLE `anh_he_thong`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKky7qr9exf3pfmmeqni2vhj8bv` (`ma_san_pham`);

--
-- Chỉ mục cho bảng `cai_dat`
--
ALTER TABLE `cai_dat`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9wl3houbukbxpixsut6uvojhy` (`ma_don_hang`),
  ADD KEY `FK3ry84nmdxgoarx53qjxd671tk` (`ma_san_pham`);

--
-- Chỉ mục cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `lien_he`
--
ALTER TABLE `lien_he`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nha_san_xuat`
--
ALTER TABLE `nha_san_xuat`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `phan_hoi`
--
ALTER TABLE `phan_hoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `quang_cao`
--
ALTER TABLE `quang_cao`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdf96mdibfgf7yuri83yp6j6n9` (`ma_loai`),
  ADD KEY `FKcpd2cfvae7b6pipxrjmsfg04o` (`ma_nha_san_xuat`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `anh_he_thong`
--
ALTER TABLE `anh_he_thong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cai_dat`
--
ALTER TABLE `cai_dat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `lien_he`
--
ALTER TABLE `lien_he`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `loai`
--
ALTER TABLE `loai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `nha_san_xuat`
--
ALTER TABLE `nha_san_xuat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `phan_hoi`
--
ALTER TABLE `phan_hoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `quang_cao`
--
ALTER TABLE `quang_cao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  ADD CONSTRAINT `FKky7qr9exf3pfmmeqni2vhj8bv` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`id`);

--
-- Các ràng buộc cho bảng `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD CONSTRAINT `FK3ry84nmdxgoarx53qjxd671tk` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`id`),
  ADD CONSTRAINT `FK9wl3houbukbxpixsut6uvojhy` FOREIGN KEY (`ma_don_hang`) REFERENCES `don_hang` (`id`);

--
-- Các ràng buộc cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `FKcpd2cfvae7b6pipxrjmsfg04o` FOREIGN KEY (`ma_nha_san_xuat`) REFERENCES `nha_san_xuat` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKdf96mdibfgf7yuri83yp6j6n9` FOREIGN KEY (`ma_loai`) REFERENCES `loai` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
