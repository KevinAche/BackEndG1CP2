package Grupo1.BackEndG1CP2.Models;

public class solicitud_e {
    private  String pendiente;
    private  String respuesta;

    public solicitud_e() {
    }

    public solicitud_e(String pendiente, String respuesta) {
        this.pendiente = pendiente;
        this.respuesta = respuesta;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
