-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.5-MariaDB - mariadb.org binary distribution
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

-- 테이블 데이터 shoppingmall.category:~20 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT IGNORE INTO `category` (`cg_id`, `cg_name`, `cg_product_count`) VALUES
	(0, '전체', 9999999),
	(20, '거실등', 16),
	(30, '식탁등', 5),
	(40, '방등', 17),
	(50, '주방등', 0),
	(60, '스탠드', 6),
	(70, '전기재료', 16),
	(2011, '거실등 30평형', 16),
	(2012, '거실등 40평형', 0),
	(3011, '소형 식탁등', 5),
	(3012, '중형 식탁등', 0),
	(4011, '소형 방등', 17),
	(4012, '대형 방등', 0),
	(5011, '싱크대', 0),
	(5012, '레일형', 0),
	(6011, 'LED스탠드', 3),
	(6012, '일반스탠드', 3),
	(7011, '2구스위치', 0),
	(7012, '멀티탭', 5),
	(7013, '3구스위치', 6),
	(7014, '전화콘센트', 5);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 shoppingmall.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `o_id` int(10) DEFAULT NULL,
  `o_date` varchar(255) DEFAULT NULL,
  `o_total_price` int(10) DEFAULT NULL,
  `o_state` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user테이블의 id\r\n';

-- 테이블 데이터 shoppingmall.orders:~7 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT IGNORE INTO `orders` (`o_id`, `o_date`, `o_total_price`, `o_state`, `user_id`) VALUES
	(1, '2020-12-23', NULL, '배송중', 'jy0935'),
	(2, '2020-12-25', NULL, '배송완료', 'jy1234'),
	(3, '2021-01-16', NULL, '배송중', 'jy0935'),
	(4, '2021-02-16', NULL, '배송완료', 'jy0935'),
	(5, '2021-03-16', NULL, '배송준비중', 'jy0935'),
	(6, '2019-02-16', NULL, '배송완료', 'jy1234'),
	(7, '2020-12-28', NULL, '배송완료', 'jy1234');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 테이블 shoppingmall.order_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `order_detail` (
  `od_id` int(10) DEFAULT NULL,
  `od_price` int(10) DEFAULT NULL,
  `od_count` int(10) DEFAULT NULL,
  `o_id` int(10) DEFAULT NULL,
  `p_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='해당상품의 갯수에 따른 해당상품의 총 주문금액';

-- 테이블 데이터 shoppingmall.order_detail:~13 rows (대략적) 내보내기
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT IGNORE INTO `order_detail` (`od_id`, `od_price`, `od_count`, `o_id`, `p_id`) VALUES
	(1, 200000, 2, 1, 1),
	(2, 70000, 2, 1, 2),
	(3, 70000, 4, 1, 3),
	(5, 150000, 4, 2, 5),
	(6, 70000, 3, 2, 3),
	(7, 70000, 2, 3, 3),
	(8, 70000, 1, 3, 2),
	(9, 200000, 1, 4, 1),
	(10, 10000, 3, 4, 6),
	(11, 150000, 2, 5, 5),
	(12, 200000, 3, 5, 1),
	(13, 70000, 2, 6, 2),
	(14, 5000, 10, 7, 4);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- 테이블 shoppingmall.point 구조 내보내기
CREATE TABLE IF NOT EXISTS `point` (
  `po_id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `po_content` varchar(50) DEFAULT NULL,
  `po_point` int(255) DEFAULT NULL,
  `po_total_point` int(255) DEFAULT NULL,
  `po_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`po_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.point:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT IGNORE INTO `point` (`po_id`, `user_id`, `user_name`, `po_content`, `po_point`, `po_total_point`, `po_date`) VALUES
	(1, 'jy0935', '주문자1', '회원가입 축하', 1000, 1000, '20210101_1530429'),
	(3, 'jy1234', '주문자2', '회원가입 축하', 1000, 1000, '20210221_1936429'),
	(5, 'jy0935', '주문자1', '테스트33', 1000, 2000, '20210321_1936429'),
	(33, 'jy0935', '주문자1', '테스트333', 50000, 52000, '20210321_2111538'),
	(35, 'jy0935', '주문자1', '테스트111', 5000, 57000, '20210322_2132270'),
	(49, 'yir123', 'fwefwf', '회원가입 축하', 1000, 1000, '20210323_115316'),
	(50, 'yir123', 'fwefwf', 'wefwef', 2000, 3000, '20210323_115338'),
	(51, 'yir123', 'fwefwf', 'gwrgwrg', 345, 3345, '20210323_115347'),
	(53, 'jy1111', '주문자3', '회원가입 축하', 1000, 1000, '20210327_162043'),
	(57, 'jy1234', '주문자2', 'test', 5000, 6000, '20210327_181552');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;

-- 테이블 shoppingmall.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `p_id` int(10) NOT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` int(10) DEFAULT NULL,
  `p_quantity` int(10) DEFAULT NULL,
  `p_rank` int(10) DEFAULT NULL,
  `p_category` int(10) DEFAULT NULL,
  `p_views` int(10) DEFAULT NULL,
  `p_text` text DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.product:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT IGNORE INTO `product` (`p_id`, `p_name`, `p_price`, `p_quantity`, `p_rank`, `p_category`, `p_views`, `p_text`) VALUES
	(1, '거실등 1', 200000, 3, NULL, 2011, 5, '30평형 거실등 입니다.'),
	(2, '방등', 70000, 2, NULL, 4011, 4, '소형 방등 입니다.'),
	(3, '형광등 스탠드', 70000, 3, NULL, 6012, 2, '일반 형광등 스탠드입니다.'),
	(4, '멀티탭', 5000, 5, NULL, 7012, 2, '멀티탭 입니다.'),
	(5, '식탁등', 150000, 5, NULL, 3011, 3, '소형 식탁등 입니다.'),
	(6, '2구 스위치', 10000, 4, NULL, 7011, 3, '2구 스위치 입니다.'),
	(7, 'LED 스탠드', 100000, 3, NULL, 6011, 2, 'LED스탠드 입니다.'),
	(8, '3구 스위치', 50000, 6, NULL, 7013, 0, '3구 스위치 입니다.'),
	(9, '거실등 2', 600000, 5, NULL, 2011, 0, '30평형 거실등 입니다.'),
	(10, '거실등 3', 300000, 5, NULL, 2011, 0, '30평형 거실등 입니다.'),
	(11, '거실등 4', 400000, 3, NULL, 2011, 0, '30평형 거실등 입니다.'),
	(12, '방등2', 80000, 5, NULL, 4011, 0, '소형 방등 입니다.'),
	(13, '방등 3', 90000, 5, NULL, 4011, 0, '소형 방등 입니다.'),
	(14, '방등 4', 90000, 5, NULL, 4011, 0, '소형 방등 입니다.'),
	(15, '전화콘센트', 6000, 5, NULL, 7014, 0, '전화콘센트 입니다.');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 shoppingmall.product_img 구조 내보내기
CREATE TABLE IF NOT EXISTS `product_img` (
  `pi_id` int(10) DEFAULT NULL,
  `pi_image` varchar(255) DEFAULT NULL,
  `p_id` int(10) DEFAULT NULL,
  `pi_unique` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.product_img:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT IGNORE INTO `product_img` (`pi_id`, `pi_image`, `p_id`, `pi_unique`) VALUES
	(1, '20210208_1735562.jpg', 1, '20210209_1658426'),
	(2, 'roomLamp', 2, '20210208_1235562'),
	(3, 'lightStand', 3, '20210205_1622562'),
	(4, 'multiPlug', 4, '20210205_1622522'),
	(5, 'diningLamp', 5, '20210209_1635562'),
	(6, '20210206_1631562.jpg', 6, '20210209_1545351'),
	(7, 'ledStand', 7, '20210210_1635562'),
	(8, 'switchThree.JPG', 8, '20210208_1635562'),
	(9, '20210208_1648326.jpg', 9, '20210209_1659745'),
	(10, '거실등3.jpg', 10, '20210401_0813966'),
	(11, '거실등4.jpg', 11, '20210401_0820674'),
	(12, '방등 2.jpg', 12, '20210401_0826935'),
	(13, '방등 3.JPG', 13, '20210401_083068'),
	(14, '방등4.JPG', 14, '20210401_0835343'),
	(15, '전화콘센트.JPG', 15, '20210401_084034');
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

-- 테이블 데이터 shoppingmall.user:~7 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`user_id`, `user_password`, `user_name`, `user_address`, `user_phone`, `user_email`, `user_point`, `user_datetime`, `user_isAccountNonExpired`, `user_isAccountNonLocked`, `user_isCredentialNonExpired`, `user_isEnabled`) VALUES
	('jy0935', '$2a$10$w.e6J3Syz9ImcY5VxbTvI.s4Izi8z/nY6s5Yr.fAUPZSbPO/D.0AS', '주문자1', '대구시 동구', '010-2345-5416', 'abc@naver.com', 1000, NULL, 1, 1, 1, 1),
	('jy1111', '$2a$10$4hSgMSKIQyFJ2LHLtgJmyucFIZt28uN6a1PZsVpbG5uy2tQZZrWri', '주문자3', '대구시 동구', '010-8888-8888', 'wfwe@naver.com', NULL, NULL, 1, 1, 1, 1),
	('jy1234', '$2a$10$W5rNJC0jHCHosERzmB0SduVya53mOu8OFHUV69geo3exixisqjq0a', '주문자2', '대구시 중구', '010-1231-4845', 'wefw11@naver.com', NULL, NULL, 1, 1, 1, 1),
	('jy12345', '4234', '이모씨', '대구시 수성구', '010-1111-1234', 'fruit@naver.com', 1000, NULL, 1, 1, 1, 1),
	('jy1515', '33311', '김모씨', '대구시 중구', '010-7555-1234', 'jkjk@naver.com', NULL, NULL, 1, 1, 1, 1),
	('kkk333', '1523', '김모씨', '대구시 북구', '010-1111-1234', '123c@naver.com', 10000, NULL, 1, 1, 1, 1),
	('yir123', '$2a$10$czmw0F/oD94BnCUmcsOYI.yxRcyWCpDipxfcWTSa//7nqPjVaHfXm', 'fwefwf', 'fwefwef', 'fwefwefw', 'fwefwef', NULL, NULL, 1, 1, 1, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 shoppingmall.userauth 구조 내보내기
CREATE TABLE IF NOT EXISTS `userauth` (
  `user_auth` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.userauth:~31 rows (대략적) 내보내기
/*!40000 ALTER TABLE `userauth` DISABLE KEYS */;
INSERT IGNORE INTO `userauth` (`user_auth`, `user_id`) VALUES
	('ROLE_ADMIN', 'jy0935'),
	('ROLE_USER', 'jy1234'),
	('ROLE_USER', 'jy9871'),
	('ROLE_USER', 'jy35234234'),
	('ROLE_USER', 'jy0935'),
	('ROLE_USER', 'jy051351'),
	('ROLE_USER', 'fewf'),
	('ROLE_USER', 'jwkljlkwefj'),
	('ROLE_USER', 'wef'),
	('ROLE_USER', 'few'),
	('ROLE_USER', 'jy09353'),
	('ROLE_USER', 'jyrdgre'),
	('ROLE_USER', 'test1'),
	('ROLE_USER', 'wefewf'),
	('ROLE_USER', 'ewfwefwefew'),
	('ROLE_USER', 'wefwef'),
	('ROLE_USER', 'jy0935ef'),
	('ROLE_USER', 'jy09351'),
	('ROLE_USER', 'reg'),
	('ROLE_USER', 'yjergergrg'),
	('ROLE_USER', 'erge'),
	('ROLE_USER', 'jy0912'),
	('ROLE_USER', 'jy05135'),
	('ROLE_USER', 'jy1515154'),
	('ROLE_USER', 'yj05616'),
	('ROLE_USER', 'hk0512'),
	('ROLE_USER', 'khg1351'),
	('ROLE_USER', 'yir123'),
	('ROLE_USER', 'jy151548'),
	('ROLE_USER', 'jy1111'),
	('ROLE_USER', 'jy1234');
/*!40000 ALTER TABLE `userauth` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
