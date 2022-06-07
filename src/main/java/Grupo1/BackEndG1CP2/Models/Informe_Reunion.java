package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informe_reunion")
public class Informe_Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha_finalizacion;

    private String area_dep;

    private String ultimo_ciclo_apro;

//    a que hace referencia esto ?
    private String tiempo_dedicacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public String getArea_dep() {
        return area_dep;
    }

    public void setArea_dep(String area_dep) {
        this.area_dep = area_dep;
    }

    public String getUltimo_ciclo_apro() {
        return ultimo_ciclo_apro;
    }

    public void setUltimo_ciclo_apro(String ultimo_ciclo_apro) {
        this.ultimo_ciclo_apro = ultimo_ciclo_apro;
    }

    public String getTiempo_dedicacion() {
        return tiempo_dedicacion;
    }

    public void setTiempo_dedicacion(String tiempo_dedicacion) {
        this.tiempo_dedicacion = tiempo_dedicacion;
    }

    @Override
    public String   toString() {
        return "Informe_Reunion{" +
                "id=" + id +
                ", fecha_finalizacion=" + fecha_finalizacion +
                ", area_dep='" + area_dep + '\'' +
                ", ultimo_ciclo_apro='" + ultimo_ciclo_apro + '\'' +
                ", tiempo_dedicacion='" + tiempo_dedicacion + '\'' +
                '}';
    }
}
