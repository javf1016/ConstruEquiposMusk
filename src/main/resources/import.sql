INSERT INTO clientes (nombre, direccion) VALUES ('Nombre del Cliente', 'Dirección del Cliente');

INSERT INTO contratos (cliente_cliente_id, fecha_inicio, fecha_fin, contrato_activo, incluye_transporte) VALUES (1, '2023-01-01', '2023-12-31', true, false);

INSERT INTO equipos (nombre, descripcion, disponible, en_reparacion, dado_de_baja) VALUES ('Nombre del Equipo', 'Descripción del Equipo', true, false, false);
INSERT INTO equipos (nombre, descripcion, disponible, en_reparacion, dado_de_baja) VALUES ('Nombre del Equipo2', 'Descripción del Equipo2', true, false, false);

INSERT INTO registrosentrega (contrato_contrato_id, equipo_equipo_id, incluido_en_contrato, incluye_transporte, costo_reparacion) VALUES (1, 1, true, false, 0.0);