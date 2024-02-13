CREATE DATABASE planta_database;

USE planta_database;

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

CREATE TABLE solicitacao (
	id INT PRIMARY KEY NOT NULL,
    solicitante VARCHAR(255) NOT NULL,
		CONSTRAINT fkSolicitanteSol FOREIGN KEY (solicitante) REFERENCES usuario(id),
    fotoUsuario BLOB,
    dtArmazenamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(12) NOT NULL,
		CONSTRAINT ckStatusSol CHECK (status IN ("PENDENTE", "NÃO PENDENTE"))
);

CREATE TABLE aroma (
    fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaA FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    nome VARCHAR(42),
    PRIMARY KEY(fkPlanta, nome),
    caracterista VARCHAR(255) NOT NULL
);

CREATE TABLE terpeno (
    fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaT FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    nome VARCHAR(255),
    PRIMARY KEY(fkPlanta, nome),
    caracterista VARCHAR(255) NOT NULL
);

CREATE TABLE beneficio (
    fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaBen FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    nome VARCHAR(255),
    PRIMARY KEY(fkPlanta, nome),
    motivo VARCHAR(255) NOT NULL
);

CREATE TABLE maleficio (
    fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaMal FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    nome VARCHAR(255),
    PRIMARY KEY(fkPlanta, nome),
    motivo VARCHAR(255) NOT NULL
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