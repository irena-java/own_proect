SELECT car_brand, color,price FROM car WHERE (color='black' or color='white') and price<15000;


SELECT car_brand, sum(price) FROM car GROUP BY car_brand HAVING sum(price)<10000;


SELECT o.order_number,o.client_name,c.car_brand, c.price from car as c JOIN "order" as o ON c.car_brand=o.car_brand;

/*(Только или я чего-то не поняла, т.к результаты противоречивые, или  в 3ем задании по-хорошему
таблицу заказов нужно связывать с таблицей клиентов по Id-шкам,
а не по бренду, т.е. car.id=order.car_id (цена должна подтягиваться по комбинации бренд+цвет,
а не  только бренд), ну, в с мысле поле order.car_brand уместо в таблице заказов заменить полем order.car_id)*/

