from flask import render_template, request, redirect, session, flash, url_for
from app import app, db
from models import *
from datetime import datetime


@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/autenticar', methods=['POST',])
def autenticar():
    usuario = request.form['user']
    senha = request.form['password']

    existe_usuario = Usuario.query.filter_by(username = usuario).first()

    if existe_usuario:
        senha_usuario = existe_usuario.senha
    else:
        senha_usuario = None

    if existe_usuario and senha_usuario == senha:
        session['usuario_logado'] = usuario
        return redirect(url_for('listarVeiculosAlocados'))
    else:
        flash("Usuário ou senha inválidos!")
        return redirect(url_for('login'))

@app.route('/sair')
def sair():
    session['usuario_logado'] = None

    return redirect(url_for('login'))

@app.route('/')
def listarVeiculosAlocados():
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))
    
    veiculos = EntradaSaida.query.filter(EntradaSaida.hora_saida.is_(None)).order_by(EntradaSaida.id).all()

    for veiculo in veiculos:
        if veiculo.hora_entrada:
            veiculo.hora_entrada = veiculo.hora_entrada.strftime('%d/%m/%Y %H:%M')
    
    return render_template('index.html', title="Veículos Alocados", veiculos=veiculos, usuario=session.get('usuario_logado'))


@app.route('/adicionar', methods=['POST'])
def entradaVeiculo():
    placa = request.form['placa']

    entrada = EntradaSaida(placa_veiculo = placa, hora_entrada=datetime.now())
    db.session.add(entrada)
    db.session.commit()

    return redirect(url_for('listarVeiculosAlocados'))

@app.route('/saida/<int:id>')
def saida(id):
    veiculo = EntradaSaida.query.filter_by(id=id).first()
    veiculo.hora_saida = datetime.now()

    db.session.add(veiculo)
    db.session.commit()

    return redirect(url_for('listarVeiculosAlocados'))

@app.route('/perfil')
def editarPerfil():
    if session.get('usuario_logado') is None: 
       return redirect(url_for('login'))
    
    return render_template('perfil.html', title="Editar Perfil", usuario=session.get('usuario_logado'))

@app.route('/atualizar_perfil', methods=['POST',])
def atualizarPerfil():
    senha = request.form['senha']

    usuario_atual = Usuario.query.filter_by(username=session.get('usuario_logado')).first()

    usuario_atual.senha = senha

    db.session.add(usuario_atual)
    db.session.commit()

    return redirect(url_for('listarVeiculosAlocados'))

@app.route('/estacionamento')
def listarEstacionamento():
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))

    estacionamento = Estacionamento.query.filter_by(id=1).first()

    return render_template('estacionamento.html', title="Estacionamento", usuario=session.get('usuario_logado'), estacionamento=estacionamento)    

from flask import request

@app.route('/editar_estacionamento', methods=['POST',])
def editarEstacionamento():
    razaosocial = request.form['razaosocial']
    nome = request.form['nome']
    cnpj = request.form['cnpj']
    endereco = request.form['endereco']
    cep = request.form['cep']
    telefone = request.form['telefone']
    capacidadetotal = request.form['capacidadetotal']

    estacionamento = Estacionamento.query.filter_by(id=1).first()

    if estacionamento:
        estacionamento.razao_social = razaosocial
        estacionamento.nome = nome
        estacionamento.endereco = endereco
        estacionamento.cep = cep
        estacionamento.telefone = telefone
        estacionamento.capacidade_total = capacidadetotal
    else:
        estacionamento = Estacionamento(razao_social=razaosocial, nome=nome, cnpj=cnpj, endereco=endereco, cep=cep, telefone=telefone, capacidade_total=capacidadetotal)

    flash("Dados Atualizados!")

    db.session.add(estacionamento)
    db.session.commit()

    return redirect(url_for('listarEstacionamento'))

@app.route('/funcionarios')
def listarFuncionarios():
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))
    
    funcionarios = Funcionario.query.order_by(Funcionario.id).all()


    return render_template('funcionarios.html', title="Funcionários", funcionarios=funcionarios, usuario=session.get('usuario_logado'))

@app.route('/adicionarFuncionario', methods=['POST'])
def adicionarFuncionario():
    if session['usuario_logado'] is None or 'usuario_logado' not in session:
        return redirect(url_for('login'))

    funcionario = Funcionario(
        nome=request.form['nome'],
        endereco=request.form['endereco'],
        cep=request.form['cep'],
        cpf=request.form['cpf'],
        celular=request.form['celular'],
        email=request.form['email']
    )

    db.session.add(funcionario)
    db.session.commit()

    return redirect(url_for('listarFuncionarios'))

@app.route('/editarFuncionario/<int:id>', methods=['POST',])
def editarFuncionario(id):
    if session['usuario_logado'] is None or 'usuario_logado' not in session:
        return redirect(url_for('login'))

    funcionario = Funcionario.query.filter_by(id=id).first()

    funcionario.nome = request.form['nome']
    funcionario.endereco = request.form['endereco']
    funcionario.cep = request.form['cep']
    funcionario.cpf = request.form['cpf']
    funcionario.celular = request.form['celular']
    funcionario.email = request.form['email']

    db.session.commit()

    return redirect(url_for('listarFuncionarios'))

@app.route('/excluirFuncionario/<int:id>')
def excluirFuncionario(id):
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))
    
    funcionario = Funcionario.query.filter_by(id=id).delete()
    db.session.commit()

    flash("Funcinário excluído com sucesso")

    return redirect(url_for('listarFuncionarios'))

@app.route('/usuarios')
def listarUsuarios():
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))
    
    usuarios = Usuario.query.order_by(Usuario.id).all()

    return render_template('usuarios.html', title="Usuários", usuarios=usuarios, usuario=session.get('usuario_logado'))


@app.route('/adicionarUsuario', methods=['POST'])
def adicionarUsuario():
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    usuario = Usuario(
        username=request.form['username'],
        senha=request.form['senha'],
        funcionario_id=request.form['funcionario']
    )

    funcionario = Funcionario.query.filter_by(nome=usuario.funcionario_id).first()

    if funcionario:
        usuario.funcionario_id = funcionario.id
    else:
        pass

    db.session.add(usuario)
    db.session.commit()

    return redirect(url_for('listarUsuarios'))

@app.route('/editarUsuario/<int:id>', methods=['POST'])
def editarUsuario(id):
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    usuario = Usuario.query.filter_by(id=id).first()
    
    if usuario:
        usuario.username = request.form['username']
        usuario.senha = request.form['senha']
        
        funcionario_nome = request.form['funcionario']
        funcionario = Funcionario.query.filter_by(nome=funcionario_nome).first()
        
        if funcionario:
            usuario.funcionario_id = funcionario.id
        else:
            pass

        db.session.commit()

    return redirect(url_for('listarUsuarios'))

@app.route('/excluirUsuario/<int:id>')
def excluirUsuario(id):
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    usuario = Usuario.query.filter_by(id=id).first()
    
    if usuario:
        db.session.delete(usuario)
        db.session.commit()
        flash("Usuário excluído com sucesso")

    return redirect(url_for('listarUsuarios'))

@app.route('/clientes')
def listarClientes():
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))
    
    clientes = Cliente.query.order_by(Cliente.id).all()

    return render_template('clientes.html', title="Clientes", clientes=clientes, usuario=session.get('usuario_logado'))

@app.route('/adicionarCliente', methods=['POST'])
def adicionarCliente():
    if session['usuario_logado'] is None or 'usuario_logado' not in session:
        return redirect(url_for('login'))

    cliente = Cliente(
        nome=request.form['nome'],
        endereco=request.form['endereco'],
        cep=request.form['cep'],
        cpf=request.form['cpf'],
        celular=request.form['celular'],
        email=request.form['email']
    )

    db.session.add(cliente)
    db.session.commit()

    return redirect(url_for('listarClientes'))

@app.route('/editarCliente/<int:id>', methods=['POST'])
def editarCliente(id):
    if session['usuario_logado'] is None or 'usuario_logado' not in session:
        return redirect(url_for('login'))

    cliente = Cliente.query.filter_by(id=id).first()

    cliente.nome = request.form['nome']
    cliente.endereco = request.form['endereco']
    cliente.cep = request.form['cep']
    cliente.cpf = request.form['cpf']
    cliente.celular = request.form['celular']
    cliente.email = request.form['email']

    db.session.commit()

    return redirect(url_for('listarClientes'))

@app.route('/excluirCliente/<int:id>')
def excluirCliente(id):
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))
    
    cliente = Cliente.query.filter_by(id=id).delete()
    db.session.commit()

    flash("Cliente excluído com sucesso")

    return redirect(url_for('listarClientes'))

@app.route('/veiculos')
def listarVeiculos():
    if session.get('usuario_logado') is None:
       return redirect(url_for('login'))
    
    veiculos = Veiculo.query.order_by(Veiculo.id).all()

    return render_template('veiculos.html', title="Veículos", veiculos=veiculos, usuario=session.get('usuario_logado'))

@app.route('/adicionarVeiculo', methods=['POST'])
def adicionarVeiculo():
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    veiculo = Veiculo(
        placa=request.form['placa'],
        modelo=request.form['modelo'],
        marca=request.form['marca'],
        cor=request.form['cor'],
        ano=request.form['ano'],
    )

    db.session.add(veiculo)
    db.session.commit()

    return redirect(url_for('listarVeiculos'))

@app.route('/editarVeiculo/<int:id>', methods=['POST'])
def editarVeiculo(id):
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    veiculo = Veiculo.query.filter_by(id=id).first()
    
    if veiculo:
        veiculo.placa = request.form['placa']
        veiculo.modelo = request.form['modelo']
        veiculo.marca = request.form['marca']
        veiculo.cor = request.form['cor']
        veiculo.ano = request.form['ano']

    db.session.commit()

    return redirect(url_for('listarVeiculos'))

@app.route('/excluirVeiculo/<int:id>')
def excluirVeiculo(id):
    if session.get('usuario_logado') is None:
        return redirect(url_for('login'))

    veiculo = Veiculo.query.filter_by(id=id).first()
    
    if veiculo:
        db.session.delete(veiculo)
        db.session.commit()
        flash("Veículo excluído com sucesso")

    return redirect(url_for('listarVeiculos'))
