from flask import *
from database import *
volunteer=Blueprint("volunteer",__name__)
@volunteer.route('/home',methods=['post','get'])
def home():
	return render_template("volunteer/home.html")


@volunteer.route('/viewreq',methods=['post','get'])
def viewreq():
	data={}
	q="SELECT * FROM `food_request` INNER JOIN charity USING(charity_id)"
	res=select(q)
	data['viewreq']=res
	return render_template("volunteer/viewfoodrequest.html",data=data)

@volunteer.route('/viewref',methods=['post','get'])
def viewref():
	data={}
	q="SELECT * FROM `refrigerators`"
	res=select(q)
	data['viewref']=res
	return render_template("volunteer/checkrefrigerator.html",data=data)


@volunteer.route('/viewfood',methods=['post','get'])
def viewfood():
	data={}
	q="SELECT * FROM `food_availability`"
	res=select(q)
	data['viewfood']=res
	return render_template("volunteer/viewfoodavailability.html",data=data)



@volunteer.route('/pickupfood',methods=['post','get'])
def pickupfood():
	data={}
	id=session['logid']
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from food_distribution where distribution_id='%s'"%(id)
		delete(q)
		return redirect(url_for('volunteer.pickupfood'))
	if 'submit' in request.form:
		hotel=request.form['pname']
		date=request.form['datetime']
		charity=request.form['cname']
		time=request.form['time']
		q="insert into food_distribution values(null,'%s','%s','hotel','%s','%s','%s')"%(id,hotel,date,charity,time)
		insert(q)
	q="SELECT * FROM `food_distribution` INNER JOIN `volunteers` USING(`volunteer_id`) INNER JOIN `charity` USING(`charity_id`) INNER JOIN hotels ON `pick_up_id`=`hotel_id` where volunteer_id=(select volunteer_id from volunteers where login_id='%s')"%(id)
	print(q)
	res=select(q)
	data['viewpick']=res
	return render_template("volunteer/pickupfood.html",data=data)