DROP DATABASE IF EXISTS database_420;

CREATE DATABASE database_420;

USE database_420;

SHOW TABLES;

CREATE TABLE usuario (
	id VARCHAR(255) PRIMARY KEY NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(42) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    foto VARCHAR(100) DEFAULT NULL,
    pais CHAR(2),
    dtNascimento DATE,
    genero CHAR(3),
    ativo BOOLEAN NOT NULL
);


CREATE TABLE aroma_terpeno (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(7) NOT NULL,
		CONSTRAINT chTipoAT CHECK (tipo IN ("Aroma", "Terpeno")),
    nome VARCHAR(42) NOT NULL UNIQUE,
    caracteristica VARCHAR(255) NOT NULL
);
## show create table aroma_terpeno;


CREATE TABLE efeito (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo CHAR(9),
		CONSTRAINT tipoE CHECK (tipo IN ("Benefício", "Malefício")),
    nome VARCHAR(255) NOT NULL,
    causa VARCHAR(255) NOT NULL
);

DELIMITER // 
CREATE TRIGGER before_insert_tipoEfeito
BEFORE INSERT ON efeito
FOR EACH ROW
BEGIN
	IF NEW.tipo = "BENEFICIO" THEN
		SET NEW.tipo = "Benefício";
	ELSEIF NEW.tipo = "MALEFICIO" THEN
		SET NEW.tipo = "Malefício";
    END IF;
END //
DELIMITER ;


CREATE TABLE duende (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	fkUsuario VARCHAR(255) UNIQUE,
		  CONSTRAINT fkUsuarioD FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
    numeroNacionalId VARCHAR(20) NOT NULL,
    dtIntegracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE planta (
	id VARCHAR(420) PRiMARY KEY NOT NULL,
  	nome VARCHAR(420) NOT NULL,
    nomePop_1 VARCHAR(420) UNIQUE,
    nomePop_2 VARCHAR(420) UNIQUE,
    genetica VARCHAR(9) NOT NULL,
		CONSTRAINT ckGeneticaPlanta CHECK (genetica IN ("Sativa", "Indica", "Ruderalis", "Híbrido")),
    porcentagemTHC FLOAT NOT NULL,
    porcentagemCDB FLOAT NOT NULL,
    fkAroma_terpeno INT,
		CONSTRAINT fkAroma_terpenoP FOREIGN KEY (fkAroma_terpeno) REFERENCES aroma_terpeno(id),
	fkEfeito INT,
		CONSTRAINT fkEfeitoCP FOREIGN KEY (fkEfeito) REFERENCES efeito(id),
    responsavel BIGINT NOT NULL,
		CONSTRAINT fkResponsavelP FOREIGN KEY (responsavel) REFERENCES duende(id),
    paisOrigem CHAR(2) NOT NULL,
    alturaEmCM FLOAT NOT NULL,
    gramaPorMetroQuadrado FLOAT NOT NULL,
    tempoFloracao INTEGER NOT NULL
);


CREATE TABLE favorito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fkUsuario VARCHAR(255) NOT NULL,
		CONSTRAINT fkUsuarioF FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420) NOT NULL,
		CONSTRAINT fkPlantaF FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    dataRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DELIMITER $$

CREATE TRIGGER before_insert_favorito
BEFORE INSERT ON favorito
FOR EACH ROW
BEGIN
	IF NEW.dataRegistro IS NULL THEN
		SET NEW.dataRegistro = CURRENT_TIMESTAMP;
	END IF;
END;

$$
DELIMITER ;


CREATE TABLE comentario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    fkUsuario VARCHAR(255),
		CONSTRAINT fkUsuarioC FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaC FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE solicitacao (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    solicitante VARCHAR(255) NOT NULL,
		CONSTRAINT fkSolicitanteSol FOREIGN KEY (solicitante) REFERENCES usuario(id),
    tipo VARCHAR(8) NOT NULL,
		CONSTRAINT ckTipo CHECK (tipo IN ("Foto", "Duende")),
	motivo VARCHAR(255),
    fotoUsuario VARCHAR(100),
    dtArmazenamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(12) NOT NULL,
		CONSTRAINT ckStatusSol CHECK (status IN ("Pendente", "Finalizado"))
);

DELIMITER $$

CREATE TRIGGER before_insert_solicitacao
BEFORE INSERT ON solicitacao
FOR EACH ROW
BEGIN
  IF NEW.dtArmazenamento IS NULL THEN
    SET NEW.dtArmazenamento = CURRENT_TIMESTAMP;
  END IF;
END;

$$
DELIMITER ;


SELECT DATA_TYPE, CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'solicitacao'
AND COLUMN_NAME = 'fotoUsuario';

CREATE TABLE imagemPlanta (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fkPlanta VARCHAR(420) NOT NULL,
		CONSTRAINT fkPlantaIP FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    caminho VARCHAR(100) NOT NULL,
    dtArmazenamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


## Por enquanto não fazer esse
CREATE TABLE relacaoUsuarioPlanta (
	id INT AUTO_INCREMENT PRIMARY KEY,
	fkUsuario VARCHAR(255),
		CONSTRAINT fkUsuarioRUP FOREIGN KEY (fkUsuario) REFERENCES usuario(id),
	fkPlanta VARCHAR(420),
		CONSTRAINT fkPlantaRUP FOREIGN KEY (fkPlanta) REFERENCES planta(id),
    tipoFloracao VARCHAR(14) NOT NULL,
		CONSTRAINT ckTipoFloracao CHECK (tipoFloracao IN ("Fotoperiódica", "Automática")),
	genero VARCHAR(12),
		CONSTRAINT ckGeneroP CHECK (genero IN ("Fêmea", "Macho", "Hermafrodita"))
);

select user from mysql.user;


#DELIMITER $$

#CREATE FUNCTION defCaptura()
	#RETURNS TIPO
    #DETERMINISTIC
    #BEGIN 
	#	DECLARE variavel TIPO;
        
     #   SET variavel = para
	