package main.com.example.multimodule.dominio;


public class JugadorDominio {
    private Long codigo;
    private String nombre;
    //@EqualsAndHashCode.Include
    private String identificacion;
    private Long fechaNacimiento;
    private EquipoDominio fkEquipo;

    public JugadorDominio(Long codigo, String nombre, String identificacion, Long fechaNacimiento, EquipoDominio fkEquipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.fkEquipo = fkEquipo;
    }

    public JugadorDominio() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Long fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EquipoDominio getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(EquipoDominio fkEquipo) {
        this.fkEquipo = fkEquipo;
    }
}
