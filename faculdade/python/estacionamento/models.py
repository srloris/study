from datetime import datetime
from app import db

class Cliente(db.Model):
    __tablename__ = 'clientes'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nome = db.Column(db.String(100), nullable=False)
    endereco = db.Column(db.String(255))
    cep = db.Column(db.String(20))
    cpf = db.Column(db.String(20), unique=True)
    celular = db.Column(db.String(20))
    email = db.Column(db.String(100))

    def __repr__(self):
        return f'<Cliente {self.nome}>'

class Veiculo(db.Model):
    __tablename__ = 'veiculos'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    placa = db.Column(db.String(10), unique=True, nullable=False)
    modelo = db.Column(db.String(50))
    marca = db.Column(db.String(50))
    cor = db.Column(db.String(30))
    ano = db.Column(db.Integer)

    def __repr__(self):
        return f'<Veiculo {self.placa}>'

class Funcionario(db.Model):
    __tablename__ = 'funcionarios'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nome = db.Column(db.String(100), nullable=False)
    endereco = db.Column(db.String(255))
    cep = db.Column(db.String(20))
    cpf = db.Column(db.String(20), unique=True)
    celular = db.Column(db.String(20))
    email = db.Column(db.String(100))

    def __repr__(self):
        return f'<Funcionario {self.nome}>'

class Estacionamento(db.Model):
    __tablename__ = 'estacionamento'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    razao_social = db.Column(db.String(100), nullable=False)
    nome = db.Column(db.String(100), nullable=False)
    cnpj = db.Column(db.String(20), unique=True, nullable=False)
    endereco = db.Column(db.String(255))
    cep = db.Column(db.String(20))
    telefone = db.Column(db.String(20))
    capacidade_total = db.Column(db.Integer, nullable=False)

    def __repr__(self):
        return f'<Estacionamento {self.nome}>'

class EntradaSaida(db.Model):
    __tablename__ = 'entradasaidas'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    placa_veiculo = db.Column(db.String(10), nullable=False)
    hora_entrada = db.Column(db.DateTime, default=datetime.utcnow, nullable=False)
    hora_saida = db.Column(db.DateTime)
    valor_cobrado = db.Column(db.Float)
    funcionario_responsavel = db.Column(db.Integer, db.ForeignKey('funcionarios.id'))

    funcionario = db.relationship('Funcionario', backref='entradas_saidas')

    def __repr__(self):
        return f'<EntradaSaida {self.placa_veiculo}>'

class ControleVagas(db.Model):
    __tablename__ = 'controlevagas'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    total_vagas = db.Column(db.Integer)
    vagas_ocupadas = db.Column(db.Integer)
    vagas_disponiveis = db.Column(db.Integer)

    def __repr__(self):
        return f'<ControleVagas {self.total_vagas}>'

class Usuario(db.Model):
    __tablename__ = 'usuarios'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    username = db.Column(db.String(50), unique=True, nullable=False)
    senha = db.Column(db.String(255), nullable=False)
    funcionario_id = db.Column(db.Integer, db.ForeignKey('funcionarios.id'))

    funcionario = db.relationship('Funcionario', backref='usuarios')

    def __repr__(self):
        return f'<Usuario {self.username}>'
