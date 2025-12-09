-- Initialize Process Stages based on the WTP specifications

-- 1. WTP (Water Treatment Plant) – OEM: Inovar
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('Incoming Raw Water', 'Inovar', 'Initial water intake monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chlorination', 'Inovar', 'Water chlorination process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pressure Sand Filter', 'Inovar', 'Sand filtration for turbidity removal', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carbon Filter', 'Inovar', 'Carbon filtration for pH and chlorine adjustment', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('RO Membrane', 'Inovar', 'Reverse osmosis membrane filtration', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('UV Disinfection', 'Alfa Technology', 'UV lamp disinfection process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. Inputs – OEM: Goma
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('Sugar Addition', 'Goma', 'Sugar addition process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Raw Syrup Tank', 'Goma', 'Raw syrup preparation and mixing', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Simple Syrup Tank', 'Goma', 'Simple syrup temperature and brix monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pulp Addition', 'Goma', 'Pulp addition process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('RO Water Input', 'Goma', 'RO water input monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Unit Addition', 'Goma', 'Additional units water temperature monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 3. Beverage Preparation – OEM: Goma
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('Blending Tank 1', 'Goma', 'First blending tank monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Blending Tank 2', 'Goma', 'Second blending tank monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Homogenizer', 'Goma', 'Homogenization pressure monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ready Beverage Tank', 'Goma', 'Final beverage tank totalizer', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pasteurization', 'Goma', 'Pasteurization exit temperature', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('SCADA System', 'Siemens', 'Siemens WinCC SCADA monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PLC System', 'Siemens', 'Siemens S7 1200 PLC monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. FG Preparation – OEM: Yudong, China
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('Filling Process', 'Yudong', 'Filling process monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Capping Process', 'Yudong', 'Capping starwheel counter', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Post Pasteurization', 'Yudong', 'Tunnel temperature monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cooling Process', 'Yudong', 'Cooling tunnel temperature', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Air Drying', 'Yudong', 'Air drying process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 5. Case Preparation
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('Packing Process', 'Technocrate/CP', 'Carton packing and weight monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Secondary Coding', 'Control Print', 'Secondary coding process', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('WH Storage', 'TBD', 'Warehouse storage monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 6. Others
INSERT INTO process_stages (stage_name, oem, description, is_active, created_at, updated_at) VALUES 
('ASM System', 'NCA', 'ASM temperature monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('PLC Control', 'TBD', 'PLC version and contact time monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CIP System', 'Goma', 'Clean-in-place system', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('SCADA Control', 'TBD', 'SCADA version monitoring', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Sample Sensor Readings for demonstration
-- Note: In a real system, these would be populated by actual sensor data

-- WTP Process Sample Data
INSERT INTO sensor_readings (process_stage_id, parameter_name, sensor_value, unit, timestamp, threshold_min, threshold_max, sensor_id, equipment_name) VALUES
(1, 'Chlorine', 2.5, 'ppm', CURRENT_TIMESTAMP, 1.0, 5.0, 'CL001', 'Incoming Water Monitor'),
(3, 'Turbidity', 0.8, 'NTU', CURRENT_TIMESTAMP, 0.0, 1.0, 'TB001', 'Sand Filter Monitor'),
(4, 'pH', 7.2, 'pH', CURRENT_TIMESTAMP, 6.5, 8.5, 'PH001', 'Carbon Filter pH Meter'),
(4, 'Chlorine', 1.2, 'ppm', CURRENT_TIMESTAMP, 0.5, 2.0, 'CL002', 'Carbon Filter Chlorine Meter'),
(5, 'TDS', 150, 'ppm', CURRENT_TIMESTAMP, 100, 200, 'TDS001', 'RO Membrane TDS Meter'),
(5, 'pH', 6.8, 'pH', CURRENT_TIMESTAMP, 6.0, 7.5, 'PH002', 'RO Membrane pH Meter'),
(5, 'Recovery Rate', 75.5, '%', CURRENT_TIMESTAMP, 70.0, 85.0, 'RR001', 'RO Recovery Monitor'),
(6, 'UV Lamp Intensity', 98.2, '%', CURRENT_TIMESTAMP, 85.0, 100.0, 'UV001', 'UV Disinfection Lamp');

-- Beverage Preparation Sample Data
INSERT INTO sensor_readings (process_stage_id, parameter_name, sensor_value, unit, timestamp, threshold_min, threshold_max, sensor_id, equipment_name) VALUES
(13, 'BRIX', 12.5, '°Bx', CURRENT_TIMESTAMP, 10.0, 15.0, 'BX001', 'Blending Tank 1 Brix Meter'),
(13, 'pH', 3.2, 'pH', CURRENT_TIMESTAMP, 3.0, 4.0, 'PH003', 'Blending Tank 1 pH Meter'),
(13, 'Acidity', 0.45, '%', CURRENT_TIMESTAMP, 0.3, 0.6, 'AC001', 'Blending Tank 1 Acidity Meter'),
(13, 'Temperature', 15.5, '°C', CURRENT_TIMESTAMP, 10.0, 20.0, 'TE001', 'Blending Tank 1 Temp Sensor'),
(14, 'BRIX', 12.8, '°Bx', CURRENT_TIMESTAMP, 10.0, 15.0, 'BX002', 'Blending Tank 2 Brix Meter'),
(14, 'pH', 3.1, 'pH', CURRENT_TIMESTAMP, 3.0, 4.0, 'PH004', 'Blending Tank 2 pH Meter'),
(15, 'Homogenization Pressure', 250, 'bar', CURRENT_TIMESTAMP, 200, 300, 'PR001', 'Homogenizer Digital Meter'),
(17, 'Exit Temperature', 85.5, '°C', CURRENT_TIMESTAMP, 80.0, 90.0, 'TE002', 'Pasteurization Exit Temp'),
(18, 'Cooling Temperature', 4.2, '°C', CURRENT_TIMESTAMP, 2.0, 6.0, 'TE003', 'SCADA Cooling Monitor'),
(19, 'Flow Rate', 500, 'L/h', CURRENT_TIMESTAMP, 400, 600, 'FR001', 'PLC Flow Rate Meter');
