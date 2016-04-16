CREATE OR REPLACE FUNCTION public.getrole(id integer)
  RETURNS character varying AS
$BODY$
DECLARE
	result character varying;
	found int;
BEGIN
	IF id > 0 THEN
		SELECT COUNT(*)
		INTO found
		FROM localiza_ejecutivas
		WHERE fk_usuario = id;
		IF found < 1 THEN
			SELECT COUNT(*)
			INTO found
			FROM localiza_monitoreo
			WHERE fk_usuario = id;
			IF found < 1 THEN
				result := 'admin';
			ELSE
				result := 'monitoreo';
			END IF;
		ELSE
			result := 'ejecutiva';
		END IF;
	ELSE
		result := 'false';
	END IF;
	RETURN result;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.getrole(integer)
  OWNER TO postgres;







  CREATE OR REPLACE FUNCTION public.getuserinfo(IN user_id integer)
  RETURNS TABLE(nombre character varying, apellido character varying, username character varying) AS
$BODY$
BEGIN
	RETURN QUERY
	SELECT m.nombre, m.apellido, u.username 
	FROM localiza_monitoreo m
	JOIN localiza_user u
	ON u.id_user = m.fk_usuario
	WHERE u.id_user = user_id
	ORDER BY m.nombre;
	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION public.getuserinfo(integer)
  OWNER TO postgres;









CREATE OR REPLACE FUNCTION public.loginvalidate(
    loginusername character varying,
    loginpass character varying)
  RETURNS integer AS
$BODY$	
declare 
	result integer;
BEGIN
	SELECT id_user into result 
	FROM localiza_user
	WHERE username = loginUsername AND pass = loginPass;
	IF COUNT(result) < 1 THEN
		result := 0;
	END IF;	
	RETURN result;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.loginvalidate(character varying, character varying)
  OWNER TO postgres;
