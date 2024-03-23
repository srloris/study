from flask import Flask, render_template, request

# a varial abaixo é a variavel
# que roda a aplicacao (todos
#os objetos de projeto depende dela)
#pip install flask

class Produto:
    def __init__(self, nome_prod, marca_prod, preco_prod):
            self.nome = nome_prod 
            self.marca = marca_prod
            self.preco = preco_prod

    # a linha abaixo instancia um novo produto 
prod01 = Produto("Camisa", "NIKE", "300,00")
prod02 = Produto("Blusa", "Lacoste", "255,00")
prod03 = Produto("Calça", "Oakley", "200,00")
    
    
produtos_cadastrados = [prod01, prod02, prod03]

app = Flask(__name__)

@app.route('/inicio')
def ola():
    return'<h1> Iniciando flask</h1>'


@app.route('/lista')
def lista():
    
    return render_template('lista.html', descricao = "Aqui estão seus produtos cadastrados", lista_prod = produtos_cadastrados)



@app.route('/cadastrar')
def cadastrar_produto():
     return render_template('cadastrar.html')

@app.route('/adicionar', methods=['POST',])
def adicionar_produto():
     nome_prod = request.form['txtNome']
     marca_prod = request.form['txtMarca']
     preco_prod = request.form['txtPreco']

     novo_produto= Produto(nome_prod,marca_prod,preco_prod)

     produtos_cadastrados.append(novo_produto)

     return render_template("lista.html", descricao = 'Novo produto cadastrado', lista_prod = produtos_cadastrados)

app.run()