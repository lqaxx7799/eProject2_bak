-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 04, 2020 lúc 01:55 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `cnpm_restaurant_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `email` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `user_name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `address` varchar(256) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `gender` varchar(16) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `start_work_date` datetime NOT NULL,
  `is_working` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `accounts`
--

INSERT INTO `accounts` (`id`, `email`, `password`, `user_name`, `address`, `gender`, `date_of_birth`, `role_id`, `start_work_date`, `is_working`) VALUES
(1, 'abc@gmail.com', '6367c48dd193d56ea7b0baad25b19455e529f5ee', 'abc', 'everywhere', 'female', NULL, 4, '2020-06-14 00:00:00', b'1'),
(4, 'qanh@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'qanh', 'somewhere', 'male', '1999-11-07 00:00:00', 1, '2020-06-15 00:00:00', b'1'),
(6, 'anna@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Anna', '', 'female', NULL, 2, '2020-06-18 00:00:00', b'1'),
(7, 'someone@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'someone', NULL, NULL, NULL, 3, '2020-06-21 10:38:13', b'1'),
(8, 'nobody@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'Lâm Qu?c Anh', '', 'other', NULL, 3, '2020-07-06 00:00:00', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL,
  `ingredient_name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `unit` varchar(16) COLLATE utf8_vietnamese_ci NOT NULL,
  `is_available` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `ingredients`
--

INSERT INTO `ingredients` (`id`, `ingredient_name`, `unit`, `is_available`) VALUES
(1, 'Cá', 'con', b'1'),
(2, 'Gà', 'con', b'1'),
(3, 'V?t', 'con', b'0'),
(4, 'Sugar', 'kg', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ingredient_imports`
--

CREATE TABLE `ingredient_imports` (
  `id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `import_time` datetime NOT NULL,
  `amount` double NOT NULL,
  `cost` double NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `ingredient_imports`
--

INSERT INTO `ingredient_imports` (`id`, `ingredient_id`, `import_time`, `amount`, `cost`, `account_id`) VALUES
(1, 1, '2020-07-06 07:49:16', 10, 200000, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menu_categories`
--

CREATE TABLE `menu_categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `is_available` bit(1) NOT NULL,
  `created_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `menu_categories`
--

INSERT INTO `menu_categories` (`id`, `category_name`, `is_available`, `created_time`) VALUES
(1, 'Súp', b'1', '2020-06-18 11:14:33'),
(2, 'Salad', b'1', '2020-06-18 11:14:33');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menu_items`
--

CREATE TABLE `menu_items` (
  `id` int(11) NOT NULL,
  `item_name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `is_available` bit(1) NOT NULL,
  `price` double NOT NULL,
  `menu_category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `menu_items`
--

INSERT INTO `menu_items` (`id`, `item_name`, `created_time`, `is_available`, `price`, `menu_category_id`) VALUES
(1, 'Súp gà', '2020-06-18 11:16:10', b'1', 20000, 1),
(2, 'Súp bí ngô', '2020-06-18 11:16:10', b'1', 25000, 1),
(3, 'Salad Nga', '2020-06-18 11:16:51', b'1', 35000, 2),
(4, 'Salad cá ngừ', '2020-06-18 11:16:51', b'1', 40000, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `receipts`
--

CREATE TABLE `receipts` (
  `id` int(11) NOT NULL,
  `arrived_time` datetime NOT NULL,
  `paid_time` datetime DEFAULT NULL,
  `is_paid` bit(1) NOT NULL,
  `table_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `receipts`
--

INSERT INTO `receipts` (`id`, `arrived_time`, `paid_time`, `is_paid`, `table_id`, `account_id`) VALUES
(1, '2020-06-02 11:19:05', '2020-06-02 11:59:05', b'1', 1, 6),
(2, '2020-06-06 18:05:05', '2020-06-06 19:25:05', b'1', 2, 6),
(3, '2020-07-05 17:36:04', '2020-07-05 17:36:32', b'1', 1, 6),
(4, '2020-07-06 07:32:45', NULL, b'0', 2, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `receipt_details`
--

CREATE TABLE `receipt_details` (
  `id` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL,
  `menu_item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `is_made` bit(1) NOT NULL,
  `is_served` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `receipt_details`
--

INSERT INTO `receipt_details` (`id`, `receipt_id`, `menu_item_id`, `quantity`, `unit_price`, `is_made`, `is_served`) VALUES
(1, 1, 1, 10, 20000, b'1', b'1'),
(2, 2, 2, 5, 25000, b'1', b'1'),
(3, 2, 4, 5, 40000, b'1', b'1'),
(4, 3, 1, 2, 20000, b'0', b'0'),
(5, 3, 3, 5, 35000, b'0', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role_name` varchar(128) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `role_name`) VALUES
(1, 'chef'),
(2, 'cashier'),
(3, 'waiter'),
(4, 'owner');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `salary_information`
--

CREATE TABLE `salary_information` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `salary` double NOT NULL,
  `from_date` datetime NOT NULL,
  `to_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `salary_information`
--

INSERT INTO `salary_information` (`id`, `account_id`, `salary`, `from_date`, `to_date`) VALUES
(1, 4, 1000000, '2020-06-17 00:00:00', '2020-06-17 00:00:00'),
(2, 4, 2000000, '2020-06-17 00:00:00', NULL),
(3, 6, 10000000, '2020-07-06 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tables`
--

CREATE TABLE `tables` (
  `id` int(11) NOT NULL,
  `table_name` varchar(256) COLLATE utf8_vietnamese_ci NOT NULL,
  `is_occupied` bit(1) NOT NULL,
  `is_available` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `tables`
--

INSERT INTO `tables` (`id`, `table_name`, `is_occupied`, `is_available`) VALUES
(1, 'Bàn 1', b'0', b'1'),
(2, 'Bàn 2', b'1', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `timekeepings`
--

CREATE TABLE `timekeepings` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `in_time` datetime NOT NULL,
  `out_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `timekeepings`
--

INSERT INTO `timekeepings` (`id`, `account_id`, `in_time`, `out_time`) VALUES
(3, 4, '2020-06-17 00:00:00', '2020-06-17 14:53:42'),
(4, 4, '2020-07-05 22:29:01', NULL),
(5, 4, '2020-07-06 07:48:54', '2020-07-06 19:06:05');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- Chỉ mục cho bảng `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `ingredient_imports`
--
ALTER TABLE `ingredient_imports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ingredient_id` (`ingredient_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `menu_categories`
--
ALTER TABLE `menu_categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `menu_items`
--
ALTER TABLE `menu_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `menu_category_id` (`menu_category_id`);

--
-- Chỉ mục cho bảng `receipts`
--
ALTER TABLE `receipts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `table_id` (`table_id`);

--
-- Chỉ mục cho bảng `receipt_details`
--
ALTER TABLE `receipt_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receipt_id` (`receipt_id`),
  ADD KEY `menu_item_id` (`menu_item_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `salary_information`
--
ALTER TABLE `salary_information`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `timekeepings`
--
ALTER TABLE `timekeepings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `ingredient_imports`
--
ALTER TABLE `ingredient_imports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `menu_categories`
--
ALTER TABLE `menu_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `menu_items`
--
ALTER TABLE `menu_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `receipts`
--
ALTER TABLE `receipts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `receipt_details`
--
ALTER TABLE `receipt_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `salary_information`
--
ALTER TABLE `salary_information`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tables`
--
ALTER TABLE `tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `timekeepings`
--
ALTER TABLE `timekeepings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Các ràng buộc cho bảng `ingredient_imports`
--
ALTER TABLE `ingredient_imports`
  ADD CONSTRAINT `ingredient_imports_ibfk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `ingredient_imports_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `menu_items`
--
ALTER TABLE `menu_items`
  ADD CONSTRAINT `menu_items_ibfk_1` FOREIGN KEY (`menu_category_id`) REFERENCES `menu_categories` (`id`);

--
-- Các ràng buộc cho bảng `receipts`
--
ALTER TABLE `receipts`
  ADD CONSTRAINT `receipts_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `tables` (`id`),
  ADD CONSTRAINT `receipts_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `receipt_details`
--
ALTER TABLE `receipt_details`
  ADD CONSTRAINT `receipt_details_ibfk_1` FOREIGN KEY (`receipt_id`) REFERENCES `receipts` (`id`),
  ADD CONSTRAINT `receipt_details_ibfk_2` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_items` (`id`);

--
-- Các ràng buộc cho bảng `salary_information`
--
ALTER TABLE `salary_information`
  ADD CONSTRAINT `salary_information_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `timekeepings`
--
ALTER TABLE `timekeepings`
  ADD CONSTRAINT `timekeepings_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
