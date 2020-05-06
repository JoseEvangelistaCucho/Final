DELIMITER $$
DROP PROCEDURE IF EXISTS insertar_multa;
CREATE PROCEDURE insertar_multa(
    IN  _pi_dni        VARCHAR(8),
    IN  _pi_fecha      DATE,
    IN  _pi_punto      INT,
    IN  _pi_monto      NUMERIC,
    IN  _pi_correo     VARCHAR(80),
    IN  _pi_tipo_multa VARCHAR(20),
    OUT _p_cod_error   INT,
    OUT _p_msj_error   VARCHAR(100),
    OUT _po_id_multa   INT
)
this_proc:BEGIN
    DECLARE _v_cant_multas INT;
    DECLARE _v_id_multa    INT;
    --
    IF _pi_monto > 1000 THEN
        SET _p_cod_error = 1;
        SET _p_msj_error = 'La multa no puede ser mayor a 1000 soles.';
        LEAVE this_proc;
    END IF;
    IF _pi_tipo_multa = 'Pico placa' THEN
        SET _p_cod_error = 2;
        SET _p_msj_error = 'La multa para pico y placa no puede ser menor a 500.';
        LEAVE this_proc;
    END IF;
    SELECT COUNT(1)
      INTO _v_cant_multas
      FROM multa
     WHERE dni            = _pi_dni
       AND DATE(fec_regi) = _pi_fecha;
    IF _v_cant_multas >= 2 THEN
        SET _p_cod_error = 3;
        SET _p_msj_error = 'No se puede registrar mas de 2 multas por día.';
        LEAVE this_proc;
    END IF;
    -- DEMAS VALIDACIONES
    
    -- INSERT
    INSERT INTO `sat`.`multa`
             (`dni`  , `tipo_multa`  , `monto`  , `correo`  , `punto`  , `fec_regi`)
      VALUES (_pi_dni, _pi_tipo_multa, _pi_monto, _pi_correo, _pi_punto, _pi_fecha)
    ;
    SELECT LAST_INSERT_ID() INTO _v_id_multa;
    SET _p_cod_error = 0;
    SET _p_msj_error = 'Se registró la multa.';
    SET _po_id_multa = _v_id_multa;
END$$
DELIMITER ;



/*Trigers*/
select * from multa;
create table infractores_eliminados 
(id_multa int, dni varchar(8),tipo_multa varchar(30), 
monto numeric(8,2), correo varchar(30), punto int,fec_eliminado date);

create trigger eliminacion_multa after delete on multa 
for each row 
insert into infractores_eliminados
(id_multa,dni,tipo_multa,monto,correo,punto,fec_eliminado)
values (old.id_multa,old.dni,old.tipo_multa,old.monto,old.correo,old.punto,curdate());

**procediemitento

delimiter $$
create procedure procedure_restaurar(
IN  dni        VARCHAR(8))
begin
insert into multa (dni,tipo_multa,monto,correo,punto,fec_regi)
select infractores_eliminados.dni,infractores_eliminados.tipo_multa,infractores_eliminados.monto,
infractores_eliminados.correo,infractores_eliminados.punto,current_date()
from infractores_eliminados
where infractores_eliminados.dni = dni;

delete from infractores_eliminados where dni=dni;
END$$;
DELIMITER ;


select * from infractores_eliminados;
select * from multa;


call procedure_restaurar("47789733");