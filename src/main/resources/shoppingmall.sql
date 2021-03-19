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

-- 테이블 데이터 shoppingmall.category:~20 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cg_id`, `cg_name`, `cg_product_count`) VALUES
	(0, '전체', 9999999),
	(20, '거실등', 4),
	(30, '식탁등', 4),
	(40, '방등', 4),
	(50, '주방등', 4),
	(60, '스탠드', 4),
	(70, '전기재료', 4),
	(2011, '거실등 30평형', 2),
	(2012, '거실등 40평형', 2),
	(3011, '소형 식탁등', 2),
	(3012, '중형 식탁등', 2),
	(4011, '소형 방등', 2),
	(4012, '대형 방등', 2),
	(5011, '싱크대', 2),
	(5012, '레일형', 2),
	(6011, 'LED스탠드', 2),
	(6012, '일반스탠드', 2),
	(7011, '2구스위치', 2),
	(7012, '멀티탭', 2),
	(7013, '3구스위치', 2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 데이터 shoppingmall.orders:~7 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`o_id`, `o_date`, `o_state`, `user_id`) VALUES
	(1, '2020-12-23', '배송중', 'jy0935'),
	(2, '2020-12-25', '배송완료', 'jy1234'),
	(3, '2021-01-16', '배송중', 'jy0935'),
	(4, '2021-02-16', '배송완료', 'jy0935'),
	(5, '2021-03-16', '배송준비중', 'jy0935'),
	(6, '2019-02-16', '배송완료', 'jy1234'),
	(7, '2020-12-28', '배송완료', 'jy1234');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 테이블 데이터 shoppingmall.order_detail:~13 rows (대략적) 내보내기
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`od_id`, `od_price`, `od_count`, `o_id`, `p_id`) VALUES
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

-- 테이블 데이터 shoppingmall.product:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`p_id`, `p_name`, `p_price`, `p_quantity`, `p_order_count`, `p_rank`, `p_category`, `p_views`, `p_text`) VALUES
	(1, '거실등 1', 200000, 3, 30, NULL, 2011, 5, '30평형 거실등 입니다.'),
	(2, '방등', 70000, 2, 35, NULL, 4011, 4, '소형 방등 입니다.'),
	(3, '형광등 스탠드', 70000, 3, 20, NULL, 6021, 2, '일반 형광등 스탠드입니다.'),
	(4, '멀티탭', 5000, 5, 5, NULL, 7012, 2, '멀티탭 입니다.'),
	(5, '식탁등', 150000, 5, 25, NULL, 3011, 3, '소형 식탁등 입니다.'),
	(6, '2구 스위치', 10000, 4, 25, NULL, 7011, 3, '2구 스위치 입니다.'),
	(7, 'LED 스탠드', 100000, 3, 15, NULL, 6011, 2, 'LED스탠드 입니다.'),
	(8, '3구 스위치', 50000, 6, 20, NULL, 7013, 0, '3구 스위치 입니다.'),
	(9, '거실등 2', 600000, 5, 10, NULL, 2011, 0, '30평형 거실등 입니다.');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 데이터 shoppingmall.product_img:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT INTO `product_img` (`pi_id`, `pi_image`, `p_id`, `pi_unique`) VALUES
	(1, '20210208_1735562.jpg', 1, '20210209_1658426'),
	(2, 'roomLamp', 2, '20210208_1235562'),
	(3, 'lightStand', 3, '20210205_1622562'),
	(4, 'multiPlug', 4, '20210205_1622522'),
	(5, 'diningLamp', 5, '20210209_1635562'),
	(6, '20210206_1631562.jpg', 6, '20210209_1545351'),
	(7, 'ledStand', 7, '20210210_1635562'),
	(8, 'switchThree.JPG', 8, '20210208_1635562'),
	(9, '20210208_1648326.jpg', 9, '20210209_1659745');
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;

-- 테이블 데이터 shoppingmall.user:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_password`, `user_name`, `user_address`, `user_phone`, `user_email`, `user_point`, `user_datetime`, `user_isAccountNonExpired`, `user_isAccountNonLocked`, `user_isCredentialNonExpired`, `user_isEnabled`) VALUES
	('jwkljlkwefj', '$2a$10$7gb4/nnRcK1x1xh2WKLDpOO2oXKkSwoXmeVP6X63oVOhEwaERvTTm', '13251', '53', '135153', '13', 1000, NULL, 1, 1, 1, 1),
	('jy051351', '$2a$10$q2.TsG23qu4GQ0YCig.X0.gyOlzMLW6GXp8AoXvsQCIfeRhfvzmYm', 'jkjkl', 'ekjflw', '10151651', 'fwefewfwe', 1000, NULL, 1, 1, 1, 1),
	('jy0935', '$2a$10$w.e6J3Syz9ImcY5VxbTvI.s4Izi8z/nY6s5Yr.fAUPZSbPO/D.0AS', '주문자1', '대구시 동구', '010-2345-5416', 'abc@naver.com', 1000, NULL, 1, 1, 1, 1),
	('jy09353', '$2a$10$oXGieRAwx7Biior.XTzIYO6W542RmBymoVPUJdenMJqHawROoJdd6', 'wefw', 'fwejkl', '2340', 'wf;lwef', 1000, NULL, 1, 1, 1, 1),
	('jy1234', '1234', '주문자2', '대구시 서구', '010-1111-1234', 'abced@naver.com', 500000, NULL, 1, 1, 1, 1),
	('jy12345', '4234', '이모씨', '대구시 수성구', '010-1111-1234', 'fruit@naver.com', 1000, NULL, 1, 1, 1, 1),
	('jy1515', '33311', '김모씨', '대구시 중구', '010-7555-1234', 'jkjk@naver.com', 20000, NULL, 1, 1, 1, 1),
	('kkk333', '1523', '김모씨', '대구시 북구', '010-1111-1234', '123c@naver.com', 10000, NULL, 1, 1, 1, 1),
	('zzsfdx15', '3423', '박모씨', '대구시 남구', '010-1111-1234', '123c@naver.com', 10000, NULL, 1, 1, 1, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 데이터 shoppingmall.userauth:~18 rows (대략적) 내보내기
/*!40000 ALTER TABLE `userauth` DISABLE KEYS */;
INSERT INTO `userauth` (`user_auth`, `user_id`) VALUES
	('ROLE_ADMIN', 'jy0935'),
	('ROLE_USER', 'jy1234'),
	('ROLE_USER', 'jy9871'),
	('ROLE_USER', 'jy35234234'),
	('ROLE_USER', 'jy0935'),
	('ROLE_USER', 'jy051351'),
	('ROLE_USER', 'fewf'),
	('ROLE_USER', 'jwkljlkwefj'),
	('ROLE_USER', ''),
	('ROLE_USER', ''),
	('ROLE_USER', 'dwd'),
	('ROLE_USER', ''),
	('ROLE_USER', ''),
	('ROLE_USER', ''),
	('ROLE_USER', 'wef'),
	('ROLE_USER', 'few'),
	('ROLE_USER', 'jy09353'),
	('ROLE_USER', 'jyrdgre');
/*!40000 ALTER TABLE `userauth` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
