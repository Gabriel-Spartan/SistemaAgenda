INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU) VALUES 
('1805071469', 'Gabriel', 'Llerena', 'ZxywGVU7GX!'),
('1104892653', 'Carlos', 'Perez', '12345678'),
('1206789450', 'Andrea', 'Ramirez', 'andrea2024'),
('0912345678', 'Lucia', 'Lopez', 'luciaClave'),
('1703456789', 'Marcos', 'Santana', 'passMarcos'),
('1909876543', 'Fernanda', 'Quispe', 'clave2025'),
('1604567890', 'Eduardo', 'Vargas', 'mypass123'),
('1506789123', 'Daniela', 'Morales', 'danielaPass'),
('1403459876', 'Juan', 'Castillo', 'juan2023'),
('1307894561', 'Tatiana', 'Ruiz', 'ruizSecure'),
('1805674321', 'Victor', 'Chavez', 'victor@clave'),
('1212457890', 'Rosa', 'Espinoza', 'rosita123'),
('1198745632', 'Luis', 'Cordero', '123Luis!'),
('0987654321', 'Camila', 'Torres', 'camila456'),
('0923456789', 'Jorge', 'Naranjo', 'naranjo2024'),
('0887654321', 'Valentina', 'Rojas', 'vRojas001'),
('0776543210', 'Sebastian', 'Martinez', 'seba!2024'),
('0665432109', 'Melanie', 'Gomez', 'melaClave'),
('0554321098', 'David', 'Alvarez', 'alvarezPass'),
('0443210987', 'Karina', 'Ortega', 'karinaPwd');

INSERT INTO EVENTOS (ID_USU_PER, FEC_EVE, HOR_EVE, TIT_EVE, DES_EVE) VALUES 
('1805071469', STR_TO_DATE('15/05/2025', '%d/%m/%Y'), '10:00:00', 'Reunión Académica', 'Revisión de avance de proyecto final'),
('1104892653', STR_TO_DATE('15/05/2025', '%d/%m/%Y'), '14:00:00', 'Consulta Médica', 'Control mensual en clínica central'),
('1206789450', STR_TO_DATE('16/05/2025', '%d/%m/%Y'), '09:30:00', 'Clase Inglés', 'Lección 12: Verbos en pasado'),
('0912345678', STR_TO_DATE('17/05/2025', '%d/%m/%Y'), '11:00:00', 'Charla Motivacional', 'Charla en el salón 3'),
('1703456789', STR_TO_DATE('17/05/2025', '%d/%m/%Y'), '15:00:00', 'Taller de Software', 'Práctica con Git y GitHub'),
('1909876543', STR_TO_DATE('18/05/2025', '%d/%m/%Y'), '08:00:00', 'Cita Banco', 'Renovación de tarjeta'),
('1604567890', STR_TO_DATE('18/05/2025', '%d/%m/%Y'), '13:00:00', 'Almuerzo Familiar', 'Casa de los abuelos'),
('1506789123', STR_TO_DATE('19/05/2025', '%d/%m/%Y'), '16:00:00', 'Entrega de Informe', 'Informe mensual de resultados'),
('1403459876', STR_TO_DATE('19/05/2025', '%d/%m/%Y'), '10:30:00', 'Examen de Matemáticas', 'Bloque B, aula 6'),
('1307894561', STR_TO_DATE('20/05/2025', '%d/%m/%Y'), '09:00:00', 'Curso de Fotografía', 'Sesión práctica en exteriores'),
('1805071469', STR_TO_DATE('21/05/2025', '%d/%m/%Y'), '17:00:00', 'Ensayo de Banda', 'Auditorio principal'),
('1212457890', STR_TO_DATE('22/05/2025', '%d/%m/%Y'), '07:30:00', 'Viaje a Loja', 'Terminal terrestre central'),
('1198745632', STR_TO_DATE('22/05/2025', '%d/%m/%Y'), '18:00:00', 'Webinar', 'Conferencia virtual sobre IA'),
('0987654321', STR_TO_DATE('23/05/2025', '%d/%m/%Y'), '12:00:00', 'Almuerzo con Jefe', 'Restaurante El Tronco'),
('0923456789', STR_TO_DATE('23/05/2025', '%d/%m/%Y'), '08:30:00', 'Entrega de Tesis', 'Secretaría académica'),
('0887654321', STR_TO_DATE('24/05/2025', '%d/%m/%Y'), '10:15:00', 'Capacitación', 'Manejo de herramientas colaborativas'),
('0776543210', STR_TO_DATE('25/05/2025', '%d/%m/%Y'), '11:45:00', 'Asesoría Legal', 'Firma de abogado jurídico'),
('0665432109', STR_TO_DATE('25/05/2025', '%d/%m/%Y'), '16:00:00', 'Partido Amistoso', 'Cancha municipal'),
('0554321098', STR_TO_DATE('26/05/2025', '%d/%m/%Y'), '14:30:00', 'Clase de Música', 'Clases de piano'),
('0443210987', STR_TO_DATE('26/05/2025', '%d/%m/%Y'), '09:30:00', 'Prueba Técnica', 'Entrevista con empresa de software');

/*nuevos datos:*/
INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU)
VALUES ('1801478692', 'Paola', 'Martinez', 'Pao123');

INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU)
VALUES ('1807897654', 'Mario', 'Lopez', 'Ma123');

INSERT INTO USUARIOS (ID_USU, NOM_USU, APE_USU, CON_USU)
VALUES ('1787654654', 'Monica', 'Pérez', 'M01');


INSERT INTO EVENTOS (ID_USU_PER, FEC_EVE, HOR_EVE, TIT_EVE, DES_EVE) VALUES
('1801478692', '2025-05-27', '09:00:00', 'Presentación de Proyecto', 'Entrega final del proyecto de computación visual en el aula magna.'),
('1801478692', '2025-05-28', '14:30:00', 'Reunión de Seguimiento', 'Revisión de avances y asignación de nuevas tareas con el equipo de desarrollo.');
INSERT INTO EVENTOS (ID_USU_PER, FEC_EVE, HOR_EVE, TIT_EVE, DES_EVE) VALUES
('1801478692', '2025-05-29', '08:00:00', 'Desayuno con Coordinador', 'Reunión informal para revisar lineamientos del nuevo semestre.'),
('1801478692', '2025-05-29', '11:15:00', 'Defensa de Proyecto', 'Presentación final ante el tribunal académico en el auditorio 2.');