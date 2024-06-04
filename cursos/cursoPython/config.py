SECRET_KEY = 'apredendodoiniciocomdaniel'

SQLALCHEMY_DATABASE_URI = '{SGBD}://{usuario}:{senha}@{servidor}/{database}'.format(
    SGBD = 'mysql+mysqlconnector',
    usuario = 'root',
    senha = 'toor',
    servidor = 'localhost',
    database = 'sitescape2'
)
