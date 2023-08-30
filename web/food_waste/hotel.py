from flask import *
from database import *
hotel=Blueprint("hotel",__name__)
@hotel.route('/hotelhome',methods=['post','get'])
def hotelhome():
	
	return render_template("hotel/home.html")


@hotel.route('/addavailablefood',methods=['post','get'])
def addavailablefood():
	data={}
	id=session['logid']
	if 'action' in request.args:
		action=request.args['action']
		ids=request.args['id']
	else:
		action=None
	if  action=="delete":
		q="delete from food_availability where availability_id='%s'"%(ids)
		delete(q)
		return(redirect(url_for('hotel.addavailablefood')))
	if 'submit' in request.form:
		lat=request.form['lat']
		lon=request.form['long']
		q="insert into food_availability value(null,(select hotel_id from hotels where login_id='%s'),'hotel','%s','%s',curdate(),'available')"%(id,lat,lon)
		insert(q)
	q="select * from food_availability where provider_id=(select hotel_id from hotels where login_id='%s')"%(id)
	res=select(q)
	data['viewfood']=res
	return render_template("hotel/addfooddetaqils.html",data=data)


@hotel.route('/feedback',methods=['post','get'])
def feedback():
	data={}
	id=session['logid']
	if 'action' in request.args:
		ids=request.args['id']
		action =request.args['action']
	else:
		action=None
	if  action=="delete":
		q="delete from feedback where feedback_id='%s'"%(ids)
		delete(q)
		return(redirect(url_for('hotel.feedback')))
	if 'submit' in request.form:
		feed=request.form['desc']
		q="insert into feedback values(null,(select hotel_id from hotels where login_id='%s'),'hotel','%s',curdate())"%(id,feed)
		insert(q)
	q="SELECT * FROM `feedback` INNER JOIN hotels ON sender_id =hotel_id AND sender_type='hotel' AND hotel_id=(SELECT hotel_id FROM hotels WHERE login_id='%s')"%(id)
	print(q)
	res=select(q)
	data['viewfeed']=res
	return render_template("hotel/addfeedback.html",data=data)

@hotel.route('/viewrefi',methods=['post','get'])
def viewrefi():
	data={}
	q="SELECT * FROM `refrigerators` WHERE `food_status`='available'"
	res=select(q)
	data['viewref']=res
	return render_template("hotel/viewrefigerator.html",data=data)


@hotel.route('/available',methods=['post','get'])
def available():
	data={}
	id=request.args['id']
	q="UPDATE `refrigerators` SET `food_status`='available' WHERE `ref_id`='%s'"%(id)
	print(q)
	update(q)
	return(redirect(url_for('hotel.viewrefi')))
	return render_template("hotel/viewrefrigerator.html",data=data)

@hotel.route('/unavailable',methods=['post','get'])
def unavailable():
	data={}
	id=request.args['id']
	q="update refrigerators set food_status='unavailable' where ref_id='%s'"%(id)
	update(q)
	return(redirect(url_for('hotel.viewrefi')))
	return render_template("hotel/viewrefrigerator.html",data=data)





@hotel.route('/handoverfood',methods=['post','get'])
def handoverfood():
	data={}
	q="SELECT * FROM `food_distribution` INNER JOIN volunteers USING(volunteer_id) INNER JOIN `food_availability` ON `pick_up_id` =provider_id"
	res=select(q)
	data['viewvol']=res
	return render_template("hotel/handover.html",data=data)

@hotel.route('/Handover',methods=['post','get'])
def Handover():
	data={}
	id=request.args['id']
	q="update `food_availability` set status='handover' where availability_id='%s'"%(id)
	print(q)
	update(q)
	return(redirect(url_for('hotel.handoverfood')))
	return render_template("hotel/handover.html",data=data)