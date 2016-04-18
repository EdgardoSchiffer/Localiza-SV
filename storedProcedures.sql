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
				result := 'Admin';
			ELSE
				result := 'Monitoreo';
			END IF;
		ELSE
			result := 'Ejecutiva';
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
DECLARE
	tbl integer;
BEGIN
	SELECT COUNT(*)
	INTO tbl 
	FROM localiza_monitoreo 
	WHERE fk_usuario = user_id;
	IF tbl  = 1 THEN
		RETURN QUERY
		SELECT m.nombre, m.apellido, u.username 
		FROM localiza_monitoreo m
		JOIN localiza_user u
		ON u.id_user = m.fk_usuario
		WHERE u.id_user = user_id
		ORDER BY m.nombre;
	ELSE
		RETURN QUERY
		SELECT m.nombre, m.apellido, u.username 
		FROM localiza_ejecutivas m
		JOIN localiza_user u
		ON u.id_user = m.fk_usuario
		WHERE u.id_user = user_id
		ORDER BY m.nombre;
	END IF;
	
	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION public.getuserinfo(integer)
  OWNER TO postgres;














  CREATE OR REPLACE FUNCTION public.insertuser(
    user_name character varying,
    pass_word character varying,
    nombre_ character varying,
    apellido_ character varying,
    rol character varying)
  RETURNS integer AS
$BODY$
DECLARE
id int;
table_key int;
BEGIN
	SELECT MAX(id_user)
	INTO id
	FROM localiza_user;
	id := id+1;
	INSERT INTO localiza_user VALUES (id, pass_word, user_name);
	IF rol = 'ejecutiva' THEN
		SELECT MAX(id_ejecutiva) 
		INTO table_key
		FROM localiza_ejecutivas;
		table_key := table_key+1;
		INSERT INTO localiza_ejecutivas VALUES (table_key, apellido_, nombre_, id);
	END IF;
	IF rol = 'monitoreo' THEN
		SELECT MAX(id_monitoreo)
		INTO table_key
		FROM localiza_monitoreo;
		table_key := table_key+1;
		INSERT INTO localiza_monitoreo VALUES (table_key, apellido_, nombre_, id);
	END IF;
	RETURN id;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.insertuser(character varying, character varying, character varying, character varying, character varying)
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