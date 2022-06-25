DROP TABLE listar_alumnos;
DROP TABLE listar_docentes;
DROP TABLE listar_personal;
DROP TABLE listar_tutores_emp;
DROP TABLE obtener_empresa_desig_tutor_a;
DROP TABLE lista_solicitudes_empresa;
DROP TABLE lista_tutores_acad;
DROP TABLE lista_anexo9;
DROP TABLE lista_reponsables_ppp;
DROP TABLE lista_actividades_empresa;
DROP TABLE lista_sol_generar;
DROP TABLE lista_convocatorias_lanzadas;
DROP TABLE tutores_empresa;
DROP TABLE lista_empleado_mi_empresa;
DROP TABLE alumnos_solicitudes_aceptadas;
DROP TABLE informes_finales_estudiantes;


CREATE VIEW listar_personal AS select p.id_persona, p.cedula, p.primer_nombre,p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.correo, p.direccion, p.fecha_nac, p.telefono, t.abrev_titulo, t.cargo, e.nombre_empresa from persona p, personal_empresa t, empresa e where p.id_persona=t.id_persona and t.id_empresa=e.id_empresa;
CREATE VIEW listar_docentes AS select p.id_persona, p.cedula, p.primer_nombre,p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.correo, p.direccion, p.fecha_nac, p.telefono, d.titulo, d.area, d.abrev_titulo, c.nombre as "carrera" from persona p, docente d, carreras c where p.id_persona=d.id_persona and d.id_carrera=c.id_carrera;
CREATE VIEW listar_alumnos AS select p.id_persona,a.id_alumno, p.cedula, p.primer_nombre,p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.correo, p.direccion, p.fecha_nac, p.telefono, a.ciclo, a.paralelo, a.promedio, c.nombre as "carrera" from persona p, alumno a, carreras c where p.id_persona=a.id_persona and a.id_carrera=c.id_carrera;
CREATE VIEW listar_tutores_emp AS select p.id_persona, p.cedula, p.primer_nombre ||' '|| p.segundo_nombre as "nombres_t", p.primer_apellido||' '||p.segundo_apellido as "apellidos_t", te.control, te.doc_asignacion, p1.primer_nombre ||' '|| p1.segundo_nombre as "nombres_e", p1.primer_apellido||' '||p1.segundo_apellido as "apellidos_e" from persona p, persona p1, personal_empresa t, alumno a, tutor_empresarial te where p.id_persona=t.id_persona and p1.id_persona = a.id_persona and a.id_alumno=te.id_alumno and te.id_personal = t.id_personal;
CREATE VIEW obtener_empresa_desig_tutor_a AS SELECT a.id_alumno,p.cedula, p.primer_nombre||' '||p.segundo_nombre as "nombres", p.primer_apellido||' '||p.segundo_apellido as "apellidos", a.ciclo, a.paralelo, a.promedio, car.nombre as "carrera", s.id_solicitud_alumno, c.id_convocatoria, se.id_solicitud_empresa , emp.nombre_empresa FROM alumno a JOIN persona p on p.id_persona=a.id_persona JOIN solicitud_alumno s ON a.id_alumno = s.id_alumno JOIN convocatoria c ON c.id_convocatoria = s.id_convocatoria JOIN solicitudes_empresa se ON se.id_solicitud_empresa = c.id_solicitud_empresa JOIN carreras car ON car.id_carrera = se.id_carrera JOIN personal_empresa pe on pe.id_personal = se.id_empleado JOIN empresa emp ON emp.id_empresa = pe.id_empresa where a.id_alumno not in (select id_alumno from tutor_academico);
CREATE VIEW lista_solicitudes_empresa AS select s. id_solicitud_empresa, s.fecha_emision, s.fecha_inicio, s.numero_alumnos, s.pdf_solicitud, s.respuesta,  e.primer_nombre||' '||e.primer_apellido as "empleado", r.primer_nombre||' '||e.primer_apellido as "responsable" from solicitudes_empresa s JOIN personal_empresa pe ON pe.id_personal = s.id_empleado JOIN persona e on e.id_persona = pe.id_persona JOIN responsable_ppp rp ON rp.id_responsableppp = s.id_responsableppp JOIN docente d on d.id_docente = rp.id_docente JOIN persona r ON r.id_persona = d.id_persona;
CREATE VIEW lista_tutores_acad AS SELECT t.id_tutor_academico, pa.primer_nombre||' '||pa.segundo_nombre as "a_nombres", pa.primer_apellido||' '||pa.segundo_apellido as "a_apellidos", pd.primer_nombre||' '||pd.segundo_nombre as "d_nombres", pd.primer_apellido||' '||pd.segundo_apellido as "d_apellidos", t.doc_asignacion from tutor_academico t JOIN alumno al ON al.id_alumno = t.id_alumno JOIN persona pa ON pa.id_persona = al.id_persona JOIN docente d ON d.id_docente = t.id_docente JOIN persona pd ON pd.id_persona = d.id_persona;
CREATE VIEW lista_anexo9 as select reg.id_registro_asistencia, alumno.id_alumno, peralumno.cedula as "cedula_a", peralumno.primer_nombre||' '||peralumno.segundo_nombre as "nombre_a", peralumno.primer_apellido||' '||peralumno.segundo_apellido as "apellido_a", caralumno.id_carrera, caralumno.nombre as "nombre_carrera", solicituda.id_solicitud_alumno,  solicituda.estado, tutoremp.id_tutor_empresarial, pertutor.primer_nombre||' '||pertutor.segundo_nombre as "nombre_t", pertutor.primer_apellido||' '||pertutor.segundo_apellido as "apellido_t", empresa.id_empresa, empresa.nombre_empresa from reg_asistencias reg  JOIN alumno alumno on alumno.id_alumno = reg.id_alumno JOIN persona peralumno on peralumno.id_persona = alumno.id_persona JOIN carreras caralumno on caralumno.id_carrera = alumno.id_carrera JOIN solicitud_alumno solicituda on solicituda.id_alumno = reg.id_alumno and upper(solicituda.estado) like '%ACEPTADO%' JOIN tutor_empresarial tutoremp on tutoremp.id_alumno = reg.id_alumno JOIN personal_empresa pe ON pe.id_personal = tutoremp.id_personal JOIN persona pertutor on pertutor.id_persona = pe.id_persona JOIN empresa empresa on empresa.id_empresa = pe.id_empresa;
CREATE VIEW lista_reponsables_ppp AS select rp.id_responsableppp, d.id_docente, d.titulo, d.abrev_titulo, c.id_carrera, c.nombre as "nombre_carrera", pr.id_persona, pr.cedula, pr.primer_nombre||' '||pr.segundo_nombre as "nombres_r", pr.primer_apellido||' '||pr.segundo_apellido as "apellidos_r" from responsable_ppp rp JOIN docente d on d.id_docente= rp.id_docente JOIN carreras c ON c.id_carrera = d.id_carrera JOIN persona pr ON pr.id_persona = d.id_persona;
CREATE VIEW lista_actividades_empresa AS  SELECT ac.id_actividad, ac.descripcion, ac.area, e.id_empresa, e.nombre_empresa, p.cedula ,p.primer_nombre||' '||p.segundo_nombre as "nombresrp", p.primer_apellido||' '||p.segundo_apellido as "apellidosrp",ca.nombre as "carrera" ,d.titulo, d.abrev_titulo FROM actividades ac JOIN convenios c ON ac.id_convenio = c.id_convenio JOIN personal_empresa pe ON pe.id_personal = c.id_gerente JOIN empresa e ON e.id_empresa = pe.id_empresa JOIN responsable_ppp rp ON rp.id_responsableppp = c.id_responsableppp JOIN docente d ON d.id_docente = rp.id_docente JOIN carreras ca on d.id_carrera = ca.id_carrera JOIN persona p ON p.id_persona = d.id_persona;
CREATE VIEW lista_sol_generar AS select se.id_solicitud_empresa, e.id_empresa, e.nombre_empresa, se.fecha_emision, se.fecha_inicio,  se.pdf_solicitud, pem.id_personal, pe.primer_nombre||' '||pe.segundo_nombre as "nombre_emp", pe.primer_apellido||' '||pe.segundo_apellido as "apellido_emp", c.nombre as "carrera", c.abreviatura from solicitudes_empresa se JOIN personal_empresa pem on se.id_empleado = pem.id_personal JOIN persona pe on pe.id_persona = pem.id_persona JOIN empresa e ON e.id_empresa = pem.id_empresa JOIN carreras c on c.id_carrera = se.id_carrera;
CREATE VIEW lista_convocatorias_lanzadas AS select con.id_convocatoria, con.doc_convocatoria, con.estado, con.fecha_emision, con.fecha_maxima, con.nombre_convocatoria||'-'||con.id_convocatoria as "nombre_convocatoria", sol.id_solicitud_empresa, e.nombre_empresa from convocatoria con JOIN solicitudes_empresa sol on con.id_solicitud_empresa = sol.id_solicitud_empresa JOIN personal_empresa per on per.id_personal = sol.id_empleado JOIN empresa e ON e.id_empresa = per.id_empresa ORDER BY con.fecha_emision DESC;
CREATE VIEW tutores_empresa AS select t.id_tutor_empresarial, al.primer_nombre||' '||al.segundo_nombre as "a_nombres", al.primer_apellido||' '||al.segundo_apellido as "a_apellidos", pe.primer_nombre||' '||pe.segundo_nombre as "e_nombres", pe.primer_apellido||' '||pe.segundo_apellido as "e_apellidos", t.doc_asignacion, e.id_empresa, e.nombre_empresa from tutor_empresarial t JOIN alumno a ON a.id_alumno = t.id_alumno JOIN persona al ON a.id_persona = al.id_persona JOIN personal_empresa per ON per.id_personal = t.id_personal JOIN persona pe ON pe.id_persona = per.id_persona JOIN empresa e ON e.id_empresa = per.id_empresa;
CREATE VIEW lista_empleado_mi_empresa AS select p.id_personal, pe.cedula, pe.primer_nombre||' '||pe.segundo_nombre as "p_nombres", pe.primer_apellido||' '||pe.segundo_apellido as "p_apellidos", p.abrev_titulo, p.cargo, e.id_empresa, e.nombre_empresa from personal_empresa p JOIN persona pe ON pe.id_persona = p.id_persona JOIN empresa e ON e.id_empresa = p.id_empresa;
CREATE VIEW alumnos_solicitudes_aceptadas AS select a.id_alumno, al.cedula, al.primer_nombre||' '||al.segundo_nombre as "a_nombres", al.primer_apellido||' '||al.segundo_apellido as "a_apellidos", sa.estado as "estado_solicitud", e.id_empresa, e.nombre_empresa from solicitud_alumno sa JOIN alumno a on a.id_alumno = sa.id_alumno JOIN convocatoria c on c.id_convocatoria =sa.id_convocatoria JOIN solicitudes_empresa se ON c.id_solicitud_empresa = se.id_solicitud_empresa JOIN personal_empresa pe on pe.id_personal = se.id_empleado JOIN empresa e on e.id_empresa = pe.id_empresa JOIN persona al on al.id_persona = a.id_alumno where upper(sa.estado) like '%ACEPTADO%' OR upper(sa.estado) like '%ACEPTADA%';
CREATE VIEW informes_finales_estudiantes AS SELECT if.id_informe_final, if.fecha_emision, if.estado, p.primer_nombre||' '||p.segundo_nombre as "a_nombres", p.primer_apellido||' '||p.segundo_apellido as "a_apellidos", p.cedula FROM informe_final if JOIN alumno al ON if.id_alumno = al.id_alumno JOIN persona p ON p.id_persona = al.id_persona;


INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0150287671', 'aaa@gmail.com','Monay','2000-11-10','AGUILAR','KEVIN','LITUMA','VINICIO','0991663079');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('1723489742', 'bbb@gmail.com','Monay','2002-11-10','AGUILAR','XIMENA','LITUMA','MARIANA','0962381723');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('1234567893', 'ccc@gmail.com','Biblian','2001-1-2','LOPEZ','KATHERINE','LIMA','ANA','0962538361');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('1112233454', 'ddd@gmail.com','San Jose','2002-2-5','ROMERO','ANDREA','LITUMA','INES','0991456789');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('8746380945', 'eee@hotmail.com','Rosario','2002-11-10','AGUILAR','ANDRES','SUAREZ','LUIS','0991663079');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('9846382766', 'fff@hotmail.com','Monay','2000-11-10','PAZ','MARLENE','SUAREZ','ANDREA','0991663079');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('1827364597', 'ggg@gmail.com','Cuenca','2000-11-10','ANDRADE','ALEXANDER','SUAREZ','VINICIO','0972537281');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('9736485768', 'hhh@gmail.com','Valle','2001-11-10','AREVALO','MARIA','LOPEZ','ANA','0968364123');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('9273649179', 'iii@gmail.com','Cochapamba','2002-1-2','SALINA','PEDRO','LUNA','JUAN','0975382712');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0273947110', 'jjj@gmail.com','Miraflores','2003-2-5','ORTIZ','ROSA','SANTOS','ZOILA','0972381923');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0184628411', 'kkk@hotmail.com','Monay','2001-11-10','CUEVA','MARLON','APA','KEVIN','0963482345');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0142738412', 'lll@hotmail.com','Ricaurte','2002-11-10','NIEVES','KARLA','SUAREZ','ANDREA','0961523456');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('8372839413', 'mmm@gmail.com','El Valle','2002-11-10','LOPEZ','MARIO','PENALOZA','VINICIO','0982645283');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0927346114', 'nnn@gmail.com','Cuenca','2002-9-10','ANGAMARCA','SANDRA','ROBLES','EMILIA','0962962812');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192734615', 'ooo@gmail.com','San Bartolo','2000-10-2','ASENCIO','ODALIS','IBARRA','GEOVANNA','0981723456');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192739116', 'ppp@gmail.com','San Jose','2002-2-5','CASTILLO','DANIELA','COELLO','LUCIA','0912347234');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192734517', 'qqq@hotmail.com','Ricaurte','2003-4-10','RAMIREZ','CARLA','SOLIS','SOFIA','0951423412');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192749318', 'rrr@hotmail.com','Totoracocha','2001-6-10','ROMERO','ALBA','SANTANA','MARTINA','0991528172');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0188734519', 'sss@gmail.com','El Arenal','2000-6-10','MORENO','SARA','PINCAY','JULIA','0918273172');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182652920', 'ttt@gmail.com','Santa Ana','2002-7-10','MULLER','CARMEN','AVILA','PAULA','0927391630');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192436221', 'uuu@gmail.com','Chilcapamba','2001-11-2','PENALOZA','LARA','BRIONES','VALERIA','0927430271');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182334122', 'vvv@gmail.com','El Vado','2002-2-5','ORTIZ','MIA','MERA','EMMA','0992538101');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182462723', 'www@hotmail.com','Vergel','2000-11-10','PINOS','OLIVIA','PALACIOS','KEVIN','0973194721');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182534824', 'xx@hotmail.com','Sidcay','2000-1-10','ARGUDO','CASANDRA','HERAS','MONICA','0917253910');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0172537125', 'yyy@gmail.com','Cuenca','2000-2-10','MARTINEZ','MARINA','ROBLES','ANDREA','09263548192');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182627326', 'zzz@gmail.com','Monay','2001-3-10','AGUDO','LAURA','LITUMA','INES','0926482612');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0172547327', 'aaaa@gmail.com','El Valle','2001-2-2','LOPEZ','ROCIO','ROBLES','ANA','0982538162');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0272517328', 'aaab@gmail.com','Biblian','2000-3-5','LOPEZ','ALICIA','LITUMA','INES','0982548364');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182540129', 'aaac@hotmail.com','Orquideas','2001-11-10','PAZ','CLARA','SUAREZ','LUIS','0938648561');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192539130', 'aaaf@hotmail.com','El Valle','2001-11-10','PAZ','NORA','PALACIOS','ANDREA','0983540182');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0172537331', 'aaag@gmail.com','Cuenca','2001-1-10','ANDRADE','LIA','PINCAY','ANDREA','0926384612');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('1825374932', 'aaah@gmail.com','Sinincay','2000-5-10','AREVALO','ADRIANA','SANTOS','ANA','0927364812');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192639133', 'aaai@gmail.com','Ricaurte','2001-8-2','ARGUDO','ELSA','ROBLES','NANCY','0987290182');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('3816293134', 'aaaj@gmail.com','Miraflores','2000-2-5','AGUDO','ISABEL','SANTOS','ZOILA','0993617283');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182301835', 'aaak@hotmail.com','Monay','2000-1-10','HERNANDEZ','IRIA','PALACIOS','NANCY','0926381538');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182638136', 'aaal@hotmail.com','Ricaurte','2001-9-10','VERA','MARLA','PALACIOS','ANDREA','0937152937');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192736137', 'aaam@gmail.com','Vergel','2001-6-10','CODNO','ANDREA','PENALOZA','NORA','0926384712');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182413238', 'aaan@gmail.com','Miraflores','2001-6-10','CEPEDA','MARIA','ROBLES','NANCY','0937495712');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192735139', 'aaao@gmail.com','San Bartolo','2001-3-2','ASENCIO','JUANA','IBARRA','LAURA','0938461827');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182634540', 'aaap@gmail.com','Vergel','2000-4-5','CASTILLO','PAMELA','COELLO','LUCIA','0938462817');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192638441', 'aaaq@hotmail.com','Ricaurte','2000-4-10','ROBLES','MARIBEL','PALACIOS','NANCY','0938463712');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182371242', 'aaar@hotmail.com','Totoracocha','2000-5-10','LEON','NANCY','SANTOS','MARTINA','0938471623');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192836443', 'aaas@gmail.com','El Arenal','2001-5-10','CEDENO','LISSETH','PINCAY','NORA','0937461039');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182548244', 'aaat@gmail.com','Santa Ana','2001-2-10','CONTRERAS','MARINA','SANTOS','IRIA','0983518364');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182638245', 'aaau@gmail.com','Miraflores','2002-3-2','PALACIOS','JHOANA','BRIONES','VALERIA','0947253712');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0273516246', 'aaav@gmail.com','El Vado','2000-3-5','REMACHE','KARINA','MERA','EMMA','0947589234');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0182639247', 'aaaw@hotmail.com','Vergel','2001-3-10','MERINO','SOFIA','SANTOS','MARIBEL','0983548571');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0192630148', 'aaax@hotmail.com','San Bartolo','2001-5-10','PEREZ','VALENTINA','PINCAY','INES','0947182650');
INSERT INTO public.persona( cedula, correo, direccion, fecha_nac, primer_apellido, primer_nombre, segundo_apellido, segundo_nombre, telefono) VALUES ('0152354981', 'xander@gmail.com','Totoracocha','1998-7-17','HERNANDEZ','KEVIN','VASQUEZ','ALEXANDER','0995846413');



INSERT INTO public.carreras(abreviatura, modalidad, nombre,duracion) VALUES ('TSDS','PRESENCIAL','DESARROLLO DE SOFTWARE','2 años');
INSERT INTO public.carreras(abreviatura, modalidad, nombre,duracion) VALUES ('TSED','PRESENCIAL','ENTRENAMIENTO DEPORTIVO','2 años');
INSERT INTO public.carreras(abreviatura, modalidad, nombre,duracion) VALUES ('TSSP','DUAL','ELECTRICIDAD','2 años');
INSERT INTO public.carreras(abreviatura, modalidad, nombre,duracion) VALUES ('TSAS','PRESENCIAL','DESARROLLO DE SOFTWARE','2 años');

INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('B','M5B',10,1,1);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('A','MAB',9,1,2);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('A','MAB',6,1,3);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('B','MAB',9,1,4);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('B','M5B',7,1,5);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('A','M5B',7,1,6);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('B','M5B',6,1,7);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('C','M5B',7,1,8);
INSERT INTO public.alumno(ciclo, paralelo, promedio, id_carrera, id_persona) VALUES ('B','M5B',7,1,36);

INSERT INTO public.docente(coordinador, abrev_titulo, area, titulo, id_carrera, id_persona) VALUES (false, 'Ing', 'TICS', 'Ingeniero', 1, 6);
INSERT INTO public.docente(coordinador, abrev_titulo, area, titulo, id_carrera, id_persona) VALUES (false, 'Mgtr', 'Ciberseguridad', 'Magister', 1, 7);
INSERT INTO public.docente(coordinador, abrev_titulo, area, titulo, id_carrera, id_persona) VALUES (false, 'Tec', 'Calentamientos', 'Tecnologo', 2, 8);
INSERT INTO public.docente(coordinador, abrev_titulo, area, titulo, id_carrera, id_persona) VALUES (false, 'Lic', 'Crossfit', 'Licenciado', 2, 9);
INSERT INTO public.docente(coordinador, abrev_titulo, area, titulo, id_carrera, id_persona) VALUES (false, 'Ing', 'Circuitos', 'Ingeniero', 3, 10);

insert into responsable_ppp(id_docente) VALUES (3);
insert into responsable_ppp(id_docente) VALUES (4);
insert into responsable_ppp(id_docente) VALUES (5);

INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('kevin@gmail.com', 'Kevin', '$2a$10$qZ.qx0k5Q1ZjW0mO0lh/sOfGoZwyA0ozGfCueF8qug0BQXw3ZcpDK', '0152354981', 49);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('kevina@gmail.com', 'KevinA', '$2a$10$F6SWj4JrH7v6meZg0ITN5.hPWyb1JyJkUldgNruB.SBnvSyIcaP/G', '0150287671', 1);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('bbb@gmail.com', 'Ximena', '$2a$10$i2e3bUgO7eUOFZyMbLcY8O02uF1U3L6dJqPc6i2gTNgeb27.Thqa6', '1723489742', 2);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('ccc@gmail.com', 'Katherine', '$2a$10$ZAh3PYY2IIN198QqWgiV6eN/VRW88.nqDqAk70aIPqTNfqan1b.6e', '1234567893', 3);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('ddd@gmail.com', 'Andrea', '$2a$10$iiwBeFJpDODp0eKiRM0reOWWniYzqvDtM6sdivjI7Saay8MpjKx76', '1112233454', 4);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('eee@gmail.com', 'Andres', '$2a$10$DGyujI68FNJtF5Uw8rpqde9aRPnPNbzWs60J/F0btbxuWjyjtMcxC', '8746380945', 5);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('fff@gmail.com', 'Marlene', '$2a$10$eDYVjmjH/JfdJ.FUXGo7Ueg8nR9MGCx6gZQZVpN6agTssnF8U9dtK', '9846382766', 6);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('kkk@hotmail.com', 'KEVIN', '$2a$10$UuxlrgiVkuijq.dIg1SsDu756sgVHnL.2amYB3GBE1QMsRWlvjcgu', '0184628411',11);
INSERT INTO public.usuario(email, nombre, password, username, id_persona) VALUES ('aaal@hotmail.com', 'MARLA', '$2a$10$IkaUAjDBdzIz8Gw.BkFps.Y7atoznGFI8MSTk/QDRDy9XhkOVd6yi', '0182638136',36);


INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_ADMIN');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_DOCENTE');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_ESTUDIANTE');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_RESPONSABLEPPP');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_TUTORACADEMICO');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_TUTOREMPRESARIAL');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_EMPLEADO');
INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_CARRERA');

INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (1, 1);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (2, 3);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (3, 4);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (4, 5);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (5, 6);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (6, 7);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (7, 8);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (8, 7);
INSERT INTO public.usuario_rol(usuario_id, rol_id) VALUES (9, 2);



INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Av. Abelardo J', 'Clara', 'Avils', '010223231', '0721564', 'Maus', 'Pública');
INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Centro', 'Mision de Prueba Ejemplo', 'AcheAsociados', '1234567890123', '0987654321', 'Nuestra Vision Ejemplo Prueba', 'Privada');
INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Mall', 'Mision de Prueba Ejemplo 1', 'AligarAsociados', '1234567890456', '0987654321', 'Nuestra Vision Ejemplo Prueba 1', 'Pública');
INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Sur', 'Mision de Prueba Ejemplo 2', 'SalinasAsociados', '1234567890789', '0987654321', 'Nuestra Vision Ejemplo Prueba 2', 'Pública');
INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Mall', 'Mision de Prueba Ejemplo 3', 'RemacheAsociados', '1234567890101', '0987654321', 'Nuestra Vision Ejemplo Prueba 3', 'Privada');
INSERT INTO public.empresa(direccion, mision, nombre_empresa, ruc, telefono, vision, naturaleza) VALUES ('Centro', 'Mision de Prueba Ejemplo 4', 'CuevaAsociados', '1234567890102', '0987654321', 'Nuestra Vision Ejemplo Prueba 4', 'Privada');

INSERT INTO public.personal_empresa(cargo, abrev_titulo, id_empresa, id_persona)VALUES ('Gerente', 'Ing',1, 11);
INSERT INTO public.personal_empresa(cargo, abrev_titulo, id_empresa, id_persona)VALUES ('Bodeguero', 'Dct',2, 12);
INSERT INTO public.personal_empresa(cargo, abrev_titulo, id_empresa, id_persona)VALUES ('Secretario', 'Ing',3, 13);
INSERT INTO public.personal_empresa(cargo, abrev_titulo, id_empresa, id_persona)VALUES ('Jefe Planta', 'Ing',4, 14);
INSERT INTO public.personal_empresa(cargo, abrev_titulo, id_empresa, id_persona)VALUES ('RRHH', 'Lcd' ,5, 15);

INSERT INTO public.convenios(documento, duracion, fecha_emision, id_gerente, id_responsableppp)VALUES ('CONVENIO 1', 6, '2022-6-19', 1, 1);
INSERT INTO public.convenios(documento, duracion, fecha_emision, id_gerente, id_responsableppp)VALUES ('CONVENIO 2', 5, '2022-6-18', 4, 1);


INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'TAP', 5, 'TENDENCIAS ACTUALES DE PROGRAMACION', 1);
INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'PAW', 5, 'PROGRAMACION DE APLICACIONES WEB', 1);
INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'DDI', 4, 'DISENO DE INTERFACES GRAFICAS', 1);
INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'BDA', 5, 'BASE DE DATOS AVANZADA', 1);
INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'MDS', 5, 'METODOLOGIAS DE DESARROLLO DE SOFTWARE', 1);
INSERT INTO public.asignaturas(abreviatura, ciclo, nombre_asignatura, id_carrera)VALUES ( 'POO', 5, 'PROGRAMACION ORIENTEDA A OBJETOS', 1);

INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','CREACION DE FORMULARIOS EN ANGULAR',2,1);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','CREACION DE LABELS Y REDIRECCIONAMIENTO A OTRAS RUTSAS',2,1);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','DESPLIEGUE DE APLCIACIONES WEB POR MEDIO DE AZURE',2,1);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','MANTENIMEINTO DE BASE DE DATOS EN SGBD POSTGRES',5,1);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','DESPLIEGUE DE APLICACIONES MOVILES EN AMBIENTE WEB',4,2);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','GESTION DE DESARROLLO DE INTERFACES CARGASDAS EN LA NUBE',4,2);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','MODIFICACION DE INTERFACES MOVILES',1,2);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','MODIFCAIONES EN BASE DE DATOS Y CREDENCIALES',4,2);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','GESTIONAMIENTO DE VERSIONES EN SGBD',4,2);
INSERT INTO public.actividades(area, descripcion, id_asignatura, id_convenio) VALUES ('PROGRAMACION WEB','DESPLIGUE DE APLICACIONES EN LA NUBE',2,1);


insert into solicitudes_empresa(estado,fecha_emision,fecha_inicio,numero_alumnos,pdf_solicitud,respuesta,id_carrera,id_empleado,id_responsableppp) values(false,'2022-7-20','2022-07-25',20,'fgfdf','fdfd',1,1,1);
insert into solicitudes_empresa(estado,fecha_emision,fecha_inicio,numero_alumnos,pdf_solicitud,respuesta,id_carrera,id_empleado,id_responsableppp)  values(false,'2022-7-12','2022-8-23',10,'fgfdf','fdfd',1,1,2);
insert into solicitudes_empresa(estado,fecha_emision,fecha_inicio,numero_alumnos,pdf_solicitud,respuesta,id_carrera,id_empleado,id_responsableppp)  values(false,'2022-7-12','2022-7-23',5,'fgfdf','fdfd',3,3,3);


INSERT INTO CONVOCATORIA (nombre_convocatoria,doc_convocatoria,fecha_emision,fecha_maxima,id_solicitud_empresa, estado)VALUES('Practicas Don Pepe','ASZDA2W','2022-3-22','2022-5-5',1, 'ABIERTO');
INSERT INTO CONVOCATORIA (nombre_convocatoria,doc_convocatoria,fecha_emision,fecha_maxima,id_solicitud_empresa, estado)VALUES('Practicas Etapa','AASDZDA2W','2022-4-30','2022-8-5',2, 'ABIERTO');
INSERT INTO CONVOCATORIA (nombre_convocatoria,doc_convocatoria,fecha_emision,fecha_maxima,id_solicitud_empresa, estado)VALUES('Practicas El Valle','iifjsjfjs','2022-5-29','2022-6-05',3, 'ABIERTO');

insert into solicitud_alumno(documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria) values('doc1','Pendiente','2022-3-20',240,1,1);
insert into solicitud_alumno(documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria)  values('doc2','Aceptado','2022-4-15',120,2,1);
insert into solicitud_alumno (documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria) values('doc3','Pendiente','2022-5-25',120,3,1);
insert into solicitud_alumno (documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria) values('doc4','Aceptado','2022-5-25',120,4,2);
insert into solicitud_alumno (documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria) values('doc5','Aceptado','2022-5-25',120,5,2);
insert into solicitud_alumno (documento_soli_estudiante,estado,fecha_emision,horasppp,id_alumno,id_convocatoria) values('doc6','Aceptado','2022-5-25',120,9,1);


INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis1', 1);
INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis2', 2);
INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis3', 3);
INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis4', 4);
INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis5', 5);
INSERT INTO public.reg_asistencias (doc_registroa, id_alumno) VALUES ('docRegis6', 9);



INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionUno', '2022-12-13', '12:00', '17:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionDos', '2022-12-11', '13:00', '18:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionTres', '2022-12-14', '11:00', '16:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionCuatro', '2022-12-16', '09:00', '14:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionCinco', '2022-12-17', '12:00', '17:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionSeis', '2022-12-17', '12:00', '17:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionSiete', '2022-12-17', '12:00', '17:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionOcho', '2022-12-17', '12:00', '17:00', 5, 1);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionNueve', '2022-12-17', '12:00', '17:00', 5, 6);
INSERT INTO public.act_diarias(descripcion, fecha, hora_llegada, hora_salida, num_horas, id_registroa) VALUES ('DescripcionDiez', '2022-12-18', '12:00', '17:00', 5, 6);


INSERT INTO public.tutor_academico(doc_asignacion, id_alumno, id_docente) VALUES ('docasignadotutorA1',1, 1);
INSERT INTO public.tutor_academico(doc_asignacion, id_alumno, id_docente) VALUES ('docasignadotutorA2',3, 1);
INSERT INTO public.tutor_academico(doc_asignacion, id_alumno, id_docente) VALUES ('docasignadotutorA3',2, 2);
INSERT INTO public.tutor_academico(doc_asignacion, id_alumno, id_docente) VALUES ('docasignadotutorA3',9, 1);


INSERT INTO public.tutor_empresarial(control, doc_asignacion ,id_alumno, id_personal) VALUES ('matutino','2473290932dsjffds',1, 1);
INSERT INTO public.tutor_empresarial(control, doc_asignacion ,id_alumno, id_personal) VALUES ('matutino','hfsdkj87234632',2, 1);
INSERT INTO public.tutor_empresarial(control, doc_asignacion ,id_alumno, id_personal) VALUES ('matutino','doc de prueba',9, 1);


INSERT INTO public.cronograma (doc_cronograma, id_tutor_academico) VALUES ('cronograma1', 2);
INSERT INTO public.cronograma (doc_cronograma, id_tutor_academico) VALUES ('cronograma3', 1);



INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-11', '2022-2-11', 1, 'completa satifactoriamente', 100, 1, 1);
INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-12', '2022-2-12', 2, 'completa insatifactoriamente', 100, 2, 2);
INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-13', '2022-2-13', 3, 'completa satifactoriamente', 70, 3, 2);
INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-14', '2022-2-14', 4, 'completa insatifactoriamente', 80, 4, 2);
INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-15', '2022-2-15', 5, 'completa satifactoriamente', 100, 9, 1);
INSERT INTO public.act_cronograma(fecha_finalizacion, fecha_seguimiento, num_actividad, observacion, porcentaje, id_actividadesd, id_cronograma) VALUES ('2022-2-15', '2022-2-15', 5, 'completa satifactoriamente', 100, 10, 1);


insert into informe_final (estado, doc_informe_final,fecha_emision,id_alumno) values ('APROBADO', 'gvbhggbgh','2022-3-20',2);
insert into informe_final (estado, doc_informe_final,fecha_emision,id_alumno) values ('NO APROBADO','gvbhggbgasdd','2022-4-20',1);
insert into informe_final (estado, doc_informe_final,fecha_emision,id_alumno) values ('APROBADO','gvbhggbgasdd','2022-4-20',9);


insert into acta_reunion(doc_acta_reunion,fecha_emision,fecha_finppp,fecha_inicioppp,horario,notificacionta,respuesta_estudiante,id_alumno)values('doc1','2022-6-2','2022-8-2','2022-6-14','9HOO - 18H00','baba','esta aprobado',2);
