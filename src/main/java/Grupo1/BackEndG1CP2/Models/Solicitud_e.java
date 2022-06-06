package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "solicitud_e")
public class Solicitud_e {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String pendiente;
    private  String respuesta;

    public Solicitud_e() {
    }

    public Solicitud_e(String pendiente, String respuesta) {
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
