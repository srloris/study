from flask import Flask, render_template, request, redirect, session, flash, url_for
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.secret_key = 'apredendodoiniciocomdaniel'

app.config['SQLALCHEMY_DATABASE_URI'] = '{SGBD}://{usuario}:{senha}@{servidor}/{database}'.format(
    SGBD = 'mysql+mysqlconnector',
    usuario = 'root',
    senha = 'toor',
    servidor = 'localhost',
    database = 'sitescape'
)

db = SQLAlchemy(app)

class Musica(db.Model):
    __tablename__ = 'ss_musics'
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    name = db.Column(db.String(100), nullable = False)
    author = db.Column(db.String(100), nullable = False)
    genre_id = db.Column(db.Integer, nullable = False)

    def __repr__(self):
        return '<Name %r>' %self.name

class Usuario(db.Model):
    __tablename__ = 'ss_users'
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    name = db.Column(db.String(100), nullable = False)
    user = db.Column(db.String(100), nullable = False, unique = True)
    password = db.Column(db.String(100), nullable = False)

    def __repr__(self):
        return '<Name %r>' %self.name

class Genero(db.Model):
    __tablename__ = 'ss_genres'
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    name = db.Column(db.String(100), nullable = False, unique = True)

    def __repr__(self):
        return '<Name %r>' %self.name

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
    
    musicas = Musica.query.order_by(Musica.id)

    for musica in musicas:
        genero = Genero.query.get(musica.genre_id)
        musica.genre_name = genero.name

    return render_template('lista_musicas.html', title = "Musicas Cadastradas", musicas = musicas)

app.run(debug=True)
