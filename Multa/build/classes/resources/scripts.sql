mysql -u root -p

mysql -u admin -h mysql-sise.cni3eljkljt0.us-east-1.rds.amazonaws.com -P 3306 -p

create database sat;

CREATE TABLE IF NOT EXISTS `multa` (
`id_multa` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `dni` varchar(8) DEFAULT NULL,
  `tipo_multa` varchar(30) DEFAULT NULL,
  `monto` decimal(6,2) DEFAULT NULL,
  `correo` varchar(80) DEFAULT NULL,
  `punto` int(11) DEFAULT NULL,
  `fec_regi` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;


1. Crear la columna punto entero.
2. Agregar la columna punto en el formulario (en el registro y la tabla)

create table tipo_multa(id_tipo int primary key auto_increment, desc_tipo_multa varchar(40));

--usuario
create table usuario (id int(11) not null PRIMARY KEY AUTO_INCREMENT,idusuario varchar(20), claveusuario varchar(10));
insert into usuario (idusuario,claveusuario) values ("jose","123456");
select * from usuario

select * from Tipo_multa ;
alter table  Tipo_multa add(monto numeric(5.2));

DELIMITER $$
DROP PROCEDURE IF EXISTS nueva_multa;
CREATE PROCEDURE nueva_multa(
    IN  _nombremulta       VARCHAR(30),
    IN  _monto      NUMERIC
    )
    INSERT INTO `sat`.`tipo_multa`
             (`desc_tipo_multa`  , `monto`)
      VALUES (_nombremulta , _monto);
    
END$$
DELIMITER ;

call nueva_multa("Mal Estacionado",190);