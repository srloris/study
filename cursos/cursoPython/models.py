from musica import db

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
