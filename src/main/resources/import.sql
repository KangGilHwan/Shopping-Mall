INSERT INTO user (id, username, password, phone_number, email, role) VALUES(1, 'river', '$2a$10$v0n.ICi8CSDPrwUjZqSsSu0B18.epFOyeH12g3Ko7.gWmy.95nLAa', '01012345678', 'river@test.com', 'ROLE_ADMIN');
INSERT INTO user (id, username, password, phone_number, email) VALUES(2, 'river2', '$2a$10$v0n.ICi8CSDPrwUjZqSsSu0B18.epFOyeH12g3Ko7.gWmy.95nLAa', '01012345678', 'river@test.com');

INSERT INTO product (id, description, name, price, category, seller_id) VALUES(1, 'Coat', 'Classic Trench Coat', 75, 'WOMEN', 1);
INSERT INTO product (id, description, name, price, category, seller_id) VALUES(2, 'Jumper', 'Front Pocket Jumper', 34, 'MEN', 1);
INSERT INTO product (id, description, name, price, category, seller_id) VALUES(3, 'Watch', 'Vintage Inspired Classic', 93, 'ACCESSORIES', 1);
INSERT INTO product (id, description, name, price, category, seller_id) VALUES(4, 'Tee', 'Pieces Metallic Printed', 19, 'MEN', 1);

INSERT INTO attachment (id, original_name, path, saved_name, size, product_id) VALUES(1, 'Coat', 'C:\Users\Kang\shop\item', 'product-04.jpg', 2111, 1);
INSERT INTO attachment (id, original_name, path, saved_name, size, product_id) VALUES(2, 'Jumper', 'C:\Users\Kang\shop\item', 'product-05.jpg', 2111, 2);
INSERT INTO attachment (id, original_name, path, saved_name, size, product_id) VALUES(3, 'Watch', 'C:\Users\Kang\shop\item', 'product-06.jpg', 2111, 3);
INSERT INTO attachment (id, original_name, path, saved_name, size, product_id) VALUES(4, 'Tee', 'C:\Users\Kang\shop\item', 'product-08.jpg', 2111, 4);

INSERT INTO coupon (id, discount, name) VALUES(1, 2000, '9월 할인 쿠폰');
INSERT INTO coupon (id, discount, name) VALUES(2, 5000, '새학기 쿠폰');
INSERT INTO coupon (id, discount, name) VALUES(3, 6000, '특별 회원 쿠폰');

INSERT INTO user_coupon (id, coupon_id, user_id) VALUES(1, 1, 1);
INSERT INTO user_coupon (id, coupon_id, user_id) VALUES(2, 2, 1);
INSERT INTO user_coupon (id, coupon_id, user_id) VALUES(3, 3, 1);
INSERT INTO user_coupon (id, coupon_id, user_id) VALUES(4, 2, 1);
INSERT INTO user_coupon (id, coupon_id, user_id) VALUES(5, 2, 2);
