from flask import *
from database import *
public=Blueprint("public",__name__)
@public.route('/',methods=['post','get'])
def home():
	
	return render_template("public/home.html")

@public.route('/log',methods=['get','post'])
def log():
	if 'submit' in request.form:
		uname=request.form['uname']
		pwd=request.form['pwd']

		q="select * from login where username='%s' and password='%s'"%(uname,pwd)
		res=select(q)
		if res:
			session['logid']=res[0]['login_id']
			if res[0]['usertype']=='admin':
				return redirect(url_for('admin.home'))
			if res[0]['usertype']=='volunteer':
				return redirect(url_for('volunteer.home'))
			if res[0]['usertype']=='hotel':
				return redirect(url_for('hotel.hotelhome'))
			if res[0]['usertype']=='charity':
				return redirect(url_for('charity.home'))
		else:
			flash("invalid username and password")

	return render_template("public/login.html")



@public.route('/managevol',methods=['post','get'])
def managevol():
	data={}
	if 'submit' in request.form:
		fname=request.form['fname']
		lname=request.form['lname']
		gender=request.form['gender']
		age=request.form['age']
		ph=request.form['ph']
		email=request.form['email']
		uname=request.form['uname']
		pwd=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(uname,pwd)
		res=select(q)
		if res:
			flash("username and password already exit")
		else:
			q="insert into login values(null,'%s','%s','pending')"%(uname,pwd)
			id=insert(q)
			q="insert into volunteers values(null,'%s','%s','%s','%s','%s','%s','%s')"%(id,fname,lname,gender,age,ph,email)
			insert(q)
	q="select *,concat(first_name,' ',last_name)as Name from volunteers"
	res=select(q)
	data['viewvol']=res
	return render_template("public/Manage_Volunteers.html",data=data)

@public.route('/reghotel',methods=['post','get'])
def reghotel():
	data={}
	if 'submit' in request.form:
		hname=request.form['hname']
		lati=request.form['lat']
		lon=request.form['long']
		email=request.form['email']
		ph=request.form['ph']
		uname=request.form['uname']
		pwd=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(uname,pwd)
		res=select(q)
		if res:
			flash("username and password already exit")
		else:
			q="insert into login values(null,'%s','%s','pending')"%(uname,pwd)
			id=insert(q)
			q="insert into hotels values(null,'%s','%s','%s','%s','%s','%s')"%(id,hname,lati,lon,ph,email)
			insert(q)
	return render_template("public/hotelregistration.html",data=data)


@public.route('/recharity',methods=['post','get'])
def recharity():
	data={}
	if 'submit' in request.form:
		hname=request.form['hname']
		lati=request.form['lat']
		lon=request.form['long']
		email=request.form['email']
		ph=request.form['ph']
		uname=request.form['uname']
		pwd=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(uname,pwd)
		res=select(q)
		if res:
			flash("username and password already exit")
		else:
			q="insert into login values(null,'%s','%s','pending')"%(uname,pwd)
			id=insert(q)
			q="insert into charity values(null,'%s','%s','%s','%s','%s','%s')"%(id,hname,lati,lon,ph,email)
			insert(q)
	return render_template("public/recharity.html",data=data)