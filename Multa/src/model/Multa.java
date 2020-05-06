package model;

import java.util.Date;

public class Multa {
    
    private int idMulta;
    private String dni;
    private String multa;
    private Double monto;
    private String correo;
    private int punto;
    private Date fecha;
    private int id;
    private String idusuario;
    private String clave;
    private Double montomulta;
    private String nombremulta;
    private Date fec_eliminado;


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Double getMontomulta() {
        return montomulta;
    }

    public void setMontomulta(Double montomulta) {
        this.montomulta = montomulta;
    }

    public String getNombremulta() {
        return nombremulta;
    }

    public void setNombremulta(String nombremulta) {
        this.nombremulta = nombremulta;
    }

    public Date getFec_eliminado() {
        return fec_eliminado;
    }

    public void setFec_eliminado(Date fec_eliminado) {
        this.fec_eliminado = fec_eliminado;
    }

}