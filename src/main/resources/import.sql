CREATE VIEW listar_alumnos
AS select p.id_persona, p.cedula, p.primer_nombre,p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.correo, p.direccion, p.fecha_nac, p.telefono,
          a.ciclo, a.paralelo, a.promedio,
          c.nombre as "carrera"
from persona p, alumno a, carreras c
where p.id_persona=a.id_persona and a.id_carrera=c.id_carrera;

