from flask import Flask, render_template, request, redirect, session, flash, url_for

class Musica:
    def __init__(self, nome, cantor, genero):
        self.nome = nome
        self.cantor = cantor
        self.genero = genero


musica01 = Musica('Pais e Filho', 'Legião Urbana', 'MPB')
lista = [musica01]

class Usuario:
    def __init__(self, nome, login, senha):
        self.nome = nome
        self.login = login
        self.senha = senha

usuario01 = Usuario('Lorenzo', 'lnunez', 'lnunez')

usuarios = {
    usuario01.login : usuario01
}

app = Flask(__name__)

app.secret_key = 'apredendodoiniciocomdaniel'

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/autenticar', methods=['POST',])
def autenticar():
    usuario = request.form['user']
    senha = request.form['password']

    if usuario in usuarios and usuarios[usuario].senha == senha:
        session['usuario_logado'] = usuario
        flash(f"Usuário {usuario} logado com sucesso!")
        return redirect(url_for('listarMusicas'))
    else:
        flash("Usuário ou senha inválidos!")
        return redirect(url_for('login'))

@app.route('/sair')
def sair():
    session['usuario_logado'] = None

    return redirect(url_for('login'))

@app.route('/cadastrar')
def cadastrarMusicas():

    if session['usuario_logado'] == None or 'usuario_logado' not in session:
        return redirect(url_for('login'))
    return render_template('cadastrar_musicas.html', title = "Cadastrar Música")

@app.route('/adicionar', methods = ['POST',])
def adicionarMusica():
    nome = request.form['musicName']
    autor = request.form['author']
    genero = request.form['genre']

    novaMusica = Musica(nome, autor, genero)

    lista.append(novaMusica)

    return redirect(url_for('listarMusicas'))

@app.route('/')
def listarMusicas():
    if session['usuario_logado'] == None or 'usuario_logado' not in session:
        return redirect(url_for('login'))

    return render_template('lista_musicas.html', title = "Musicas Cadastradas", musicas = lista)

app.run(debug=True)
