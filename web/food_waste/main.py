from flask import Flask
from database import *
from public import public
from admin import admin
#from api import api
from hotel import hotel
from charity import charity
app=Flask(__name__)
app.secret_key="abcdef"
app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
#phone(demjson not installed properly)
#app.register_blueprint(api,url_prefix='/api')
app.register_blueprint(hotel,url_prefix='/hotel')
app.register_blueprint(charity,url_prefix='/charity')
app.run(debug=True,port=5007)
