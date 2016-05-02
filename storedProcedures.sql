-- Function: public.getrole(integer)

-- DROP FUNCTION public.getrole(integer);

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






-- Function: public.getuserinfo(integer)

-- DROP FUNCTION public.getuserinfo(integer);

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






  -- Function: public.insertuser(character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION public.insertuser(character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertuser(user_name character varying, pass_word character varying, nombre_ character varying, apellido_ character varying, rol character varying)
  RETURNS integer AS
$BODY$
DECLARE
id int;
table_key int;
BEGIN
	SELECT COALESCE(
		(SELECT MAX(id_user)
		FROM localiza_user)
	, 0)
	INTO id;
	id := id+1;
	INSERT INTO localiza_user VALUES (id, pass_word, user_name);
	IF rol = 'Ejecutiva' THEN
		SELECT COALESCE(
			(SELECT MAX(id_ejecutiva) 
			FROM localiza_ejecutivas)
		, 0)
		INTO table_key;
		table_key := table_key+1;
		INSERT INTO localiza_ejecutivas VALUES (table_key, apellido_, nombre_, id);
	END IF;
	IF rol = 'Monitoreo' THEN
		SELECT COALESCE(
			(SELECT MAX(id_monitoreo) 
			FROM localiza_monitoreo)
		, 0)
		INTO table_key;
		table_key := table_key+1;
		INSERT INTO localiza_monitoreo VALUES (table_key, apellido_, nombre_, id);
	END IF;
	RETURN id;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insertuser(character varying, character varying, character varying, character varying, character varying)
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






CREATE OR REPLACE FUNCTION public.uniqueuser(user_name character varying)
  RETURNS integer AS
$BODY$
DECLARE 
	result int;
BEGIN
	SELECT COUNT(*) AS result
	INTO result
	FROM localiza_user
	WHERE username = user_name;
	RETURN result;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.uniqueuser(character varying)
  OWNER TO postgres;







CREATE OR REPLACE FUNCTION updateuser(user_name character varying, pass_word character varying, nombre_ character varying, apellido_ character varying, rol character varying, password_flag boolean, id integer)
  RETURNS boolean AS
$BODY$
BEGIN
	IF password_flag THEN
		UPDATE localiza_user SET username = user_name, pass = pass_word WHERE id_user= id;
		IF rol = 'Ejecutiva' THEN
			UPDATE localiza_ejecutivas SET nombre = nombre_, apellido = apellido_ WHERE fk_usuario = id;
		END IF;
		IF rol ='Monitoreo' THEN
			UPDATE localiza_monitoreo SET nombre = nombre_, apellido = apellido_ WHERE fk_usuario = id;
		END IF;
	ELSE
		UPDATE localiza_user SET username = user_name WHERE id_user= id;
		IF rol = 'Ejecutiva' THEN
			UPDATE localiza_ejecutivas SET nombre = nombre_, apellido = apellido_ WHERE fk_usuario = id;
		END IF;
		IF rol ='Monitoreo' THEN
			UPDATE localiza_monitoreo SET nombre = nombre_, apellido = apellido_ WHERE fk_usuario = id;
		END IF;
		IF rol = 'Tecnico' THEN
			UPDATE localiza_tecnicos SET nombre = nombre_, apellido = apellido_ WHERE id_tecnico = id;
		END IF;
	END IF;
	IF FOUND THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION updateuser(character varying, character varying, character varying, character varying, character varying, boolean, integer)
  OWNER TO postgres;
