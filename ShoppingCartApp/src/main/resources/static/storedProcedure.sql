DELIMITER //

CREATE PROCEDURE GET_TOTAL_ORDERS_BY_PERSON(IN p_id bigint)
BEGIN
	SELECT Count(*)  FROM user_orders uo WHERE uo.person_id = p_id;
END //

DELIMITER ;