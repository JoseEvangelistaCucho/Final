DROP PROCEDURE IF EXISTS actualizar_multa; 

CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_multa`(
    in  _pi_id_multa    int,
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
    

    UPDATE `sat`.`multa` 
    SET 
    tipo_multa = _pi_tipo_multa,
    monto = _pi_monto,
    correo = _pi_correo, 
    punto = _pi_punto 
    WHERE  id_multa= _pi_id_multa;

    SET _p_cod_error = 0;
    SET _p_msj_error = 'Se Actualizo.';
END