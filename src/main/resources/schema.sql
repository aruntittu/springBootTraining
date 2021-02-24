DROP PROCEDURE IF EXISTS GET_TOTAL_ORDERS_BY_PERSON^;
CREATE PROCEDURE GET_TOTAL_ORDERS_BY_PERSON(IN p_id bigint, OUT total_orders int)
BEGIN
SELECT count(*) INTO total_orders FROM user_orders uo WHERE uo.person_id = p_id;
END ^;

