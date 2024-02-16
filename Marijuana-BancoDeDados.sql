CREATE DATABASE planta_database;

USE planta_database;

SHOW TABLES;

CREATE TABLE usuario (
	id VARCHAR(255) PRIMARY KEY NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(42) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    foto BLOB,
    pais CHAR(2),
    dtNascimento VARCHAR(42),
    genero VARCHAR(42),
    status VARCHAR(10) NOT NULL,
		CONSTRAINT ckStatus CHECK (status IN("ATIVO", "DESATIVADO"))
);

DROP DATABASE planta_database;
DESC usuario;

CREATE TABLE planta (
	id VARCHAR(420) PRiMARY KEY NOT NULL,
	nome VARCHAR(420) NOT NULL,
    nomePop_1 VARCHAR(420) UNIQUE,
    nomePop_2 VARCHAR(420) UNIQUE,
    genetica VARCHAR(9) NOT NULL,
		CONSTRAINT ckGeneticaP CHECK (genetica IN ("Sativa", "Indica", "Ruderalis", "Híbrido")),
    porcentagemTHC VARCHAR(4) NOT NULL,
    porcentagemCDB VARCHAR(4) NOT NULL,
    paisOrigem VARCHAR(420) NOT NULL,
    alturaEmCM VARCHAR(11) NOT NULL,
    gramaPorMetroQuadrado VARCHAR(8) NOT NULL,
    tempoFloracao VARCHAR(10) NOT NULL
);

CREATE TABLE duende (
	fkUsuario VARCHAR(255) PRIMARY KEY,
		CONSTRAINT fkUsuarioD FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
    numeroNascionalId VARCHAR(20) NOT NULL,
    dtIntegracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE favorito (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fkUsuario VARCHAR(255),
		CONSTRAINT fkUsuarioF FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaF FOREIGN KEY (fkPlanta) REFERENCES planta(id),
	data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comentario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    fkUsuario VARCHAR(255),
		CONSTRAINT fkUsuarioC FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaC FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE solicitacao (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    solicitante VARCHAR(255) NOT NULL,
		CONSTRAINT fkSolicitanteSol FOREIGN KEY (solicitante) REFERENCES usuario(id),
    tipo VARCHAR(6) NOT NULL,
		CONSTRAINT ckTipo CHECK (tipo IN ("FOTO", "DUENDE")),
	motivo VARCHAR(255),
    fotoUsuario BLOB,
    dtArmazenamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(12) NOT NULL,
		CONSTRAINT ckStatusSol CHECK (status IN ("PENDENTE", "NÃO PENDENTE"))
);

SELECT DATA_TYPE, CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'solicitacao'
AND COLUMN_NAME = 'fotoUsuario';

CREATE TABLE aroma_terpeno (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(7) NOT NULL,
		CONSTRAINT chTipoAT CHECK (tipo IN ("AROMA", "TERPENO")),
    nome VARCHAR(42) NOT NULL,
    caracterista VARCHAR(255) NOT NULL
);

CREATE TABLE efeito (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo CHAR(8),
		CONSTRAINT tipoE CHECK (tipo IN ("Benefício", "Malefício")),
    nome VARCHAR(255) NOT NULL,
    motivo VARCHAR(255) NOT NULL
);

CREATE TABLE caracteristicaPlanta (
	id INT PRIMARY KEY AUTO_INCREMENT,
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaCP FOREIGN KEY (fkPlanta) REFERENCES planta(id),
	fkAroma_terpeno INT,
		CONSTRAINT fkAroma_terpenoCP FOREIGN KEY (fkAroma_terpeno) REFERENCES aroma_terpeno(id),
	fkEfeito INT,
		CONSTRAINT fkEfeitoCP FOREIGN KEY (fkEfeito) REFERENCES efeito(id),
    responsavel VARCHAR(255),
		CONSTRAINT fkResponsavelRATP FOREIGN KEY (responsavel) REFERENCES duende(fkUsuario)
);

CREATE TABLE imagemPlanta (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fkPlanta VARCHAR(420) NOT NULL,
		CONSTRAINT fkPlantaIP FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    imagem BLOB NOT NULL,
    dtArmazenamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE relacaoUsuarioPlanta (
	fkUsuario VARCHAR(255),
		CONSTRAINT fkUsuarioRUP FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaRUP FOREIGN KEY (fkPlanta) REFERENCES planta(id),
	PRIMARY KEY (fkUsuario, fkPlanta),
    tipoFloracao VARCHAR(14) NOT NULL,
		CONSTRAINT ckTipoFloracao CHECK (tipoFloracao IN ("Fotoperiódica", "Automática")),
	genero VARCHAR(12),
		CONSTRAINT ckGeneroP CHECK (genero IN ("Fêmea", "Macho", "Hermafrodita"))
);