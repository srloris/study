from flask import render_template, request, redirect, session, flash, url_for
from musica import app, db
from models import Musica, Usuario, Genero

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/autenticar', methods=['POST',])
def autenticar():
    usuario = request.form['user']
    senha = request.form['password']

    existe_usuario = Usuario.query.filter_by(user = usuario).first()

    if existe_usuario:
        senha_usuario = existe_usuario.password
    else:
        senha_usuario = None

    if existe_usuario and senha_usuario == senha:
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

@app.route('/adicionar', methods=['POST'])
def adicionarMusica():
    nome = request.form['musicName']
    autor = request.form['author']
    genero_nome = request.form['genre']

    musica = Musica.query.filter_by(name=nome).first()

    if musica:
        flash("Musica já cadastrada!")
    else:
        genero = Genero.query.filter_by(name=genero_nome).first()

        if not genero:
            genero = Genero(name=genero_nome)
            db.session.add(genero)
            db.session.commit()

        nova_musica = Musica(name=nome, author=autor, genre_id=genero.id)
        db.session.add(nova_musica)
        db.session.commit()

        flash(f"Música '{nome}' adicionada com sucesso!")

    return redirect(url_for('listarMusicas'))

@app.route('/editar/<int:id>')
def editar(id):
    if session['usuario_logado'] == None or 'usuario_logado' not in session:
        return redirect(url_for('login'))
    
    musica = Musica.query.filter_by(id=id).first()
    genero = Genero.query.filter_by(id=musica.genre_id).first()

    return render_template('editar_musicas.html', title='Editar Música', musica=musica, genero=genero)

@app.route('/excluir/<int:id>')
def excluir(id):
    if session['usuario_logado'] == None or 'usuario_logado' not in session:
        return redirect(url_for('login'))
    
    musica = Musica.query.filter_by(id=id).delete()
    db.session.commit()

    flash("Musica Excluída com sucesso")
    return redirect(url_for('listarMusicas'))

@app.route('/atualizar', methods=['POST',])
def atualizar():
    id = request.form['id']
    musica = Musica.query.filter_by(id=id).first()
    genero_nome = request.form['genre']
    genero = Genero.query.filter_by(name=genero_nome).first()
    musica.name = request.form['musicName']
    musica.author = request.form['author']
    musica.genre_id = genero.id

    db.session.add(musica)
    db.session.commit()

    return redirect(url_for('listarMusicas'))

@app.route('/')
def listarMusicas():
    if session['usuario_logado'] == None or 'usuario_logado' not in session:
        return redirect(url_for('login'))
    
    musicas = Musica.query.order_by(Musica.id)

    return render_template('lista_musicas.html', title = "Musicas Cadastradas", musicas = musicas)
