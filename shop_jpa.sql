insert into shopping.user(user.id,user.email,user.password,user.name,user.birth,user.gender) select user.id,user.email,user.password,user.name,user.birth,user.gender from shop.user;
select * from shopping.user;

insert into shopping.banner(id, content) select banner.id, banner.content from shop.banner;
select * from shopping.banner;

insert into shopping.basket(id, product_id, user_id) select basket.id, product_id, user_id from shop.basket;
select * from basket;

insert into product (id, content, image, name, price, sale_price) select id, content, image, name, price, sale_price from shop.product;
select * from product;

insert into product_detail (id, content, image, product_id) select id, content, image,  product_id from shop.product_detail;
select * from product_detail;

insert into token (token, user_id) select token, user_id from shop.token;
