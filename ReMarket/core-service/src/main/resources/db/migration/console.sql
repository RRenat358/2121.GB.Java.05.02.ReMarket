select * from orders;


-- число, работает
SELECT COUNT(*)
FROM public.products p
         INNER JOIN public.order_items oi
                    ON p.id = oi.product_id
GROUP BY oi.product_id
ORDER BY COUNT(*) DESC;

SELECT COUNT(*)
FROM products p
         INNER JOIN order_items oi
                    ON p.id = oi.product_id
GROUP BY oi.product_id
ORDER BY COUNT(*) DESC;





-- id и счётчик
SELECT oi.product_id, COUNT(*)
FROM public.products p
         INNER JOIN public.order_items oi
                    ON p.id = oi.product_id
GROUP BY oi.product_id
ORDER BY COUNT(*) DESC;



-- продукт и счётчик
SELECT p, COUNT(*)
FROM public.products p
         INNER JOIN public.order_items oi
                    ON p.id = oi.product_id
GROUP BY p
ORDER BY COUNT(*) DESC;


-- id и счётчик
SELECT product_id, COUNT(*)
FROM public.products p
         INNER JOIN public.order_items oi
                    ON p.id = oi.product_id
GROUP BY oi.product_id
ORDER BY COUNT(*) DESC;





-- ошибка group by
SELECT oi, COUNT(*)
FROM public.order_items oi
         INNER JOIN public.products p
                    ON p.id = oi.product_id
GROUP BY p.id
ORDER BY COUNT(*) DESC












