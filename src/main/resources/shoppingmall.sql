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

-- 테이블 데이터 shoppingmall.category:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cg_id`, `cg_name`, `cg_product_count`) VALUES
	(20, '조명', 8),
	(2010, '등', 5),
	(2020, '스탠드', 3),
	(201010, '거실등', 3),
	(201020, '방등', 2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 shoppingmall.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `p_id` int(10) DEFAULT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` int(10) DEFAULT NULL,
  `p_quantity` int(10) DEFAULT NULL,
  `p_order_count` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.product:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`p_id`, `p_name`, `p_price`, `p_quantity`, `p_order_count`) VALUES
	(1, '거실등', 200000, 3, 30),
	(2, '방등', 80000, 2, 35),
	(3, '일반스탠드', 70000, 3, 20);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 shoppingmall.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_point` int(255) DEFAULT NULL,
  `user_datetime` date DEFAULT NULL,
  `user_isAccountNonExpired` tinyint(4) DEFAULT NULL,
  `user_isAccountNonLocked` tinyint(4) DEFAULT NULL,
  `user_isCredentialNonExpired` tinyint(4) DEFAULT NULL,
  `user_isEnabled` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 shoppingmall.user:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_password`, `user_name`, `user_phone`, `user_point`, `user_datetime`, `user_isAccountNonExpired`, `user_isAccountNonLocked`, `user_isCredentialNonExpired`, `user_isEnabled`) VALUES
	('jy0935', '123', 'jy', '010-1111-1234', 10000, NULL, 1, 1, 1, 1),
	('abc', '1234', '김모씨', '010-4864-1234', 5000, NULL, 1, 1, 1, 1),
	('aaaa', '1234', '이모씨', '010-1231-1111', 20000, NULL, 1, 1, 1, 1),
	('jy0935', '123', 'jy', '010-1111-1234', 10000, NULL, 1, 1, 1, 1),
	('abc', '1234', '김모씨', '010-4864-1234', 5000, NULL, 1, 1, 1, 1),
	('aaaa', '1234', '이모씨', '010-1231-1111', 20000, NULL, 1, 1, 1, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
