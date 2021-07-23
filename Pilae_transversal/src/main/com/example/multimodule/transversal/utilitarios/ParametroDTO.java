package main.com.example.multimodule.transversal.utilitarios;

public class ParametroDTO {

    private String clave;
    private String valor;

    private ParametroDTO(String clave, String valor) {
        super();
        setClave(clave);
        setValor(valor);
    }

    public static ParametroDTO crear(String clave, String valor) {
        return new ParametroDTO(clave, valor);
    }

    private void setClave(String clave) {
        this.clave = UtilTexto.aplicarTrim(clave);
    }

    private void setValor(String valor) {
        this.valor = UtilTexto.aplicarTrim(valor);
    }

    public String getClave() {
        return clave;
    }

    public String getValor() {
        return valor;
    }

}
