-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.4-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- shoppingmall 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `shoppingmall` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shoppingmall`;

-- 테이블 shoppingmall.category 구조 내보내기
CREATE TABLE IF NOT EXISTS `category` (
  `cg_id` int(10) DEFAULT NULL,
  `cg_name` varchar(255) DEFAULT NULL,
  `cg_product_count` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.category:~7 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cg_id`, `cg_name`, `cg_product_count`) VALUES
	(2030, '주방등', 8),
	(20, '등', 8),
	(30, '스탠드', 3),
	(2010, '거실등', 3),
	(2020, '방등', 2),
	(40, '전기재료', 5),
	(0, '전체', 9999999);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 shoppingmall.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `o_id` int(10) DEFAULT NULL,
  `o_date` date DEFAULT NULL,
  `o_total_price` int(10) DEFAULT NULL,
  `o_state` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user테이블의 id\r\n';

-- 테이블 데이터 shoppingmall.orders:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`o_id`, `o_date`, `o_total_price`, `o_state`, `user_id`) VALUES
	(1, '2020-12-23', 200000, '배송중', 'jy0935'),
	(2, '2020-12-23', 50000, '배송완료', 'jy0935'),
	(3, '2021-01-01', 80000, '배송준비중', 'jy0935'),
	(4, '2021-01-02', 100000, '배송중', 'jy0935'),
	(5, '2021-01-04', 5000, '입금완료', 'jy0935'),
	(6, '2021-01-04', 200000, '입금완료', 'jy0935');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 테이블 shoppingmall.order_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `order_detail` (
  `od_id` int(10) DEFAULT NULL,
  `od_product` varchar(255) DEFAULT NULL,
  `od_price` int(10) DEFAULT NULL,
  `od_count` int(10) DEFAULT NULL,
  `o_id` int(10) DEFAULT NULL,
  `p_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='해당상품의 갯수에 따른 해당상품의 총 주문금액';

-- 테이블 데이터 shoppingmall.order_detail:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- 테이블 shoppingmall.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `p_id` int(10) NOT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` int(10) DEFAULT NULL,
  `p_quantity` int(10) DEFAULT NULL,
  `p_order_count` int(10) DEFAULT NULL,
  `p_rank` int(10) DEFAULT NULL,
  `p_category` int(10) DEFAULT NULL,
  `p_views` int(10) DEFAULT NULL,
  `p_text` text DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.product:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`p_id`, `p_name`, `p_price`, `p_quantity`, `p_order_count`, `p_rank`, `p_category`, `p_views`, `p_text`) VALUES
	(1, '거실등', 200000, 3, 30, NULL, 2010, 5, '거실등 입니다.'),
	(2, '방등', 70000, 2, 35, NULL, 2020, 4, '방등 입니다.'),
	(3, '형광등 스탠드', 70000, 3, 20, NULL, 30, 2, '형광등 스탠드입니다.'),
	(4, '멀티탭', 5000, 5, 5, NULL, 40, 2, '멀티탭 입니다.'),
	(5, '식탁등', 150000, 5, 25, NULL, 2030, 3, '식탁등 입니다.'),
	(6, '2구 스위치', 10000, 4, 25, NULL, 40, 3, '2구 스위치 입니다.'),
	(7, 'LED 스탠드', 100000, 3, 15, NULL, 30, 2, 'LED스탠드 입니다.'),
	(123, '제품2', 1500000, 212, 0, NULL, 121, 0, '임시제품'),
	(150, '제품111', 200000, 50, 0, NULL, 50, 0, NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 shoppingmall.product_img 구조 내보내기
CREATE TABLE IF NOT EXISTS `product_img` (
  `pi_id` int(10) DEFAULT NULL,
  `pi_image` varchar(255) DEFAULT NULL,
  `p_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.product_img:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT INTO `product_img` (`pi_id`, `pi_image`, `p_id`) VALUES
	(1, 'livingLamp40', 1),
	(2, 'roomLamp', 2),
	(3, 'lightStand', 3),
	(4, 'multiPlug', 4),
	(5, 'diningLamp', 5),
	(6, 'switchTwo', 6),
	(7, 'ledStand', 7),
	(150, NULL, 150),
	(123, NULL, 123);
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;

-- 테이블 shoppingmall.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(255) NOT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_point` int(255) DEFAULT NULL,
  `user_datetime` date DEFAULT NULL,
  `user_isAccountNonExpired` tinyint(4) DEFAULT NULL,
  `user_isAccountNonLocked` tinyint(4) DEFAULT NULL,
  `user_isCredentialNonExpired` tinyint(4) DEFAULT NULL,
  `user_isEnabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.user:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_password`, `user_name`, `user_address`, `user_phone`, `user_email`, `user_point`, `user_datetime`, `user_isAccountNonExpired`, `user_isAccountNonLocked`, `user_isCredentialNonExpired`, `user_isEnabled`) VALUES
	('jy0935', '11111', '박모씨', '대구시 동구', '010-2354-8451', 'abc@naver.com', 50000, NULL, 1, 1, 1, 1),
	('jy1234', '356484', '김모씨', '대구시 서구', '010-1111-1234', 'abced@naver.com', 500000, NULL, 1, 1, 1, 1),
	('jy12345', '4234', '이모씨', '대구시 수성구', '010-1111-1234', 'fruit@naver.com', 1000, NULL, 1, 1, 1, 1),
	('jy1515', '33311', '김모씨', '대구시 중구', '010-7555-1234', 'jkjk@naver.com', 20000, NULL, 1, 1, 1, 1),
	('kkk333', '1523', '김모씨', '대구시 북구', '010-1111-1234', '123c@naver.com', 10000, NULL, 1, 1, 1, 1),
	('sef', '3423', '박모씨', '대구시 남구', '010-1111-1234', '123c@naver.com', 10000, NULL, 1, 1, 1, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
