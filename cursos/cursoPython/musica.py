from flask import Flask, render_template, request, redirect, session, flash

class Musica:
    def __init__(self, nome, cantor, genero):
        self.nome = nome
        self.cantor = cantor
        self.genero = genero


musica01 = Musica('Pais e Filho', 'Legião Urbana', 'MPB')
lista = [musica01]

app = Flask(__name__)

app.secret_key = 'apredendodoiniciocomdaniel'

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/autenticar', methods=['POST',])
def autenticar():
    usuario = request.form['user']
    senha = request.form['password']

    if usuario == 'admin':
        session['usuario_logado'] = usuario
        flash("Usuário logado com sucesso!")
        return redirect('/')
    else:
        flash("Usuário ou senha inválidos!")
        return redirect('/login')

@app.route('/sair')
def sair():
    session['usuario_logado'] = None

    return redirect('/')

@app.route('/cadastrar')
def cadastrarMusicas():
    return render_template('cadastrar_musicas.html', title = "Cadastrar Música")

@app.route('/adicionar', methods = ['POST',])
def adicionarMusica():
    nome = request.form['musicName']
    autor = request.form['author']
    genero = request.form['genre']

    novaMusica = Musica(nome, autor, genero)

    lista.append(novaMusica)

    return redirect('/')

@app.route('/')
def listarMusicas():
    return render_template('lista_musicas.html', title = "Musicas Cadastradas", musicas = lista)

app.run(debug=True)
