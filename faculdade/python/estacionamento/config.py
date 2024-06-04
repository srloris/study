SECRET_KEY = 'estacionamento'

SQLALCHEMY_DATABASE_URI = '{SGBD}://{usuario}:{senha}@{servidor}/{database}'.format(
    SGBD = 'mysql+mysqlconnector',
    usuario = 'root',
    senha = 'toor',
    servidor = 'localhost',
    database = 'servidor2'
)
