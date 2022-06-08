package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "act_diarias")
public class Actividades_Diarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividadesD;

    private Date fecha;

    private String horaLlegada;

    private String horaSalida;

    private String descripcion;

    private double numHoras;

    @ManyToOne
    @JoinColumn(name = "idRegistroA",referencedColumnName = "idRegistroAsistencia")
    private Registro_Asistencias registroA;

    @OneToOne(mappedBy = "actividadesDiarias")
    private Actividades_Cronograma actividadesCronograma;

    public Actividades_Diarias() {
    }

    public Long getIdActividadesD() {
        return idActividadesD;
    }

    public void setIdActividadesD(Long idActividadesD) {
        this.idActividadesD = idActividadesD;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(double numHoras) {
        this.numHoras = numHoras;
    }

    public Registro_Asistencias getRegistroA() {
        return registroA;
    }

    public void setRegistroA(Registro_Asistencias registroA) {
        this.registroA = registroA;
    }

    public Actividades_Cronograma getActividadesCronograma() {
        return actividadesCronograma;
    }

    public void setActividadesCronograma(Actividades_Cronograma actividadesCronograma) {
        this.actividadesCronograma = actividadesCronograma;
    }
}
