CREATE DATABASE servidor2;

USE servidor2;

CREATE TABLE Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    cep VARCHAR(20),
    cpf VARCHAR(20) UNIQUE,
    telefone VARCHAR(20),
    celular VARCHAR(20),
    email VARCHAR(100),
    tipo_cliente ENUM('Rotativo', 'Mensal'),
    observacoes TEXT
);

CREATE TABLE Veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50),
    marca VARCHAR(50),
    cor VARCHAR(30),
    ano INT,
    tipo_veiculo ENUM('Carro', 'Moto'),
    observacoes TEXT,
    cliente INT,
    FOREIGN KEY (cliente) REFERENCES Clientes(id)
);

CREATE TABLE Funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    cep VARCHAR(20),
    cpf VARCHAR(20) UNIQUE,
    rg VARCHAR(20),
    ctps VARCHAR(20),
    cargo VARCHAR(50),
    departamento VARCHAR(50),
    telefone VARCHAR(20),
    celular VARCHAR(20),
    email VARCHAR(100),
    observacoes TEXT
);

CREATE TABLE Estacionamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    razao_social VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    endereco VARCHAR(255),
    cep VARCHAR(20),
    telefone VARCHAR(20),
    capacidade_total INT NOT NULL,
    observacoes TEXT
);

CREATE TABLE EntradaSaidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa_veiculo VARCHAR(10) NOT NULL,
    hora_entrada DATETIME NOT NULL,
    hora_saida DATETIME,
    tipo_cliente ENUM('Rotativo', 'Mensal'),
    valor_cobrado DECIMAL(10, 2),
    funcionario_responsavel INT,
    observacoes TEXT,
    FOREIGN KEY (funcionario_responsavel) REFERENCES Funcionarios(id)
);

CREATE TABLE ControleVagas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    total_vagas INT,
    vagas_ocupadas INT,
    vagas_disponiveis INT,
    vagas_reservadas INT,
    observacoes TEXT
);

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nivel_acesso ENUM('Administrador', 'Gerência', 'Caixa', 'Funcionários'),
    funcionario_id INT,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionarios(id)
);

insert Usuarios (username, senha) values ("admin", "admin");


