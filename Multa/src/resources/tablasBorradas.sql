
/*Trigers*/
use sat;
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
DELIMITER $$
DROP PROCEDURE IF EXISTS procedure_restaurar;
create procedure procedure_restaurar(
IN  id_multa        int)
begin
insert into multa (dni,tipo_multa,monto,correo,punto,fec_regi)
select infractores_eliminados.dni,infractores_eliminados.tipo_multa,infractores_eliminados.monto,
infractores_eliminados.correo,infractores_eliminados.punto,current_date()
from infractores_eliminados
where infractores_eliminados.id_multa = id_multa;
END$$;
DELIMITER ;


use sat;
select * from infractores_eliminados;
select * from multa;