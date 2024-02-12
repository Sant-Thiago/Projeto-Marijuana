CREATE DATABASE planta_database;

USE planta_database;

CREATE TABLE usuario (
	  id VARCHAR(255) PRIMARY KEY NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(20) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    pais CHAR(2) NOT NULL,
    idade VARCHAR(45),
    sexo CHAR(3)
);

ALTER TABLE usuario CHANGE país pais CHAR(2) NOT NULL;
ALTER TABLE usuario MODIFY sexo VARCHAR(42);
ALTER TABLE usuario CHANGE sexo genero VARCHAR(42);
ALTER TABLE usuario CHANGE idade dtNascimento VARCHAR(42);
ALTER TABLE usuario MODIFY senha VARCHAR(42) NOT NULL;
ALTER TABLE usuario DROP genero;
ALTER TABLE usuario DROP dt_nasc;
ALTER TABLE usuario ADD status VARCHAR(10) NOT NULL;
ALTER TABLE usuario ADD CONSTRAINT ckStatus CHECK (status IN("ATIVO", "DESATIVADO"));
ALTER TABLE usuario MODIFY pais CHAR(2) NOT NULL;

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
    porcetagemCDB VARCHAR(4) NOT NULL,
    cor VARCHAR(42) NOT NULL,
    aroma VARCHAR(42),
    terpenoDominante VARCHAR(100),
    terpenosSecundarios VARCHAR(100),
    beneficios VARCHAR(420) NOT NULL,
    maleficios VARCHAR(210) NOT NULL,
    ajudaMedica VARCHAR(420) NOT NULL,
    paisOrigem VARCHAR(420) NOT NULL,
    alturaEmCM VARCHAR(5) NOT NULL,
    gramaPorMetroQuadrado VARCHAR(8) NOT NULL,
    tempoFloracao VARCHAR(10) NOT NULL
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