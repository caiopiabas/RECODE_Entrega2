/* Lógico: */

CREATE TABLE Usuarios (
    id INTEGER PRIMARY KEY,
    nome VARCHAR,
    email VARCHAR
);

CREATE TABLE PacotesViagem (
    id INTEGER PRIMARY KEY,
    nomeDoPacote VARCHAR,
    destino VARCHAR,
    preco DECIMAL,
    dataDePartida DATE,
    duracao INTEGER,
    descricao VARCHAR
);

CREATE TABLE Pedidos (
    dataCompra TIMESTAMP,
    id INTEGER PRIMARY KEY,
    pacotesViagemId INTEGER,
    usuariosId INTEGER
);
 
ALTER TABLE Pedidos ADD CONSTRAINT FK_Pedidos_2
    FOREIGN KEY (pacotesViagemId)
    REFERENCES PacotesViagem (id)
    ON DELETE RESTRICT;
 
ALTER TABLE Pedidos ADD CONSTRAINT FK_Pedidos_3
    FOREIGN KEY (usuariosId)
    REFERENCES Usuarios (id)
    ON DELETE RESTRICT;