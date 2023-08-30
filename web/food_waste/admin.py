from flask import *
from database import *
admin=Blueprint("admin",__name__)
@admin.route('/home',methods=['post','get'])
def home():
	
	return render_template("admin/home.html")

@admin.route('/viewvol',methods=['post','get'])
def viewvol():
	data={}
	q="select *,concat(first_name,' ',last_name)as Name from volunteers inner join login using(login_id)"
	res=select(q)
	data['viewvol']=res
	return render_template("admin/viewvolunteer.html",data=data)

@admin.route('/accept',methods=['post','get'])
def accept():
	id=request.args['id']
	q="update login  set usertype='volunteer' where usertype='nill' and login_id='%s'"%(id)
	print(q)
	update(q)
	return redirect(url_for('admin.viewvol'))
	return render_template("admin/viewvolunteer.html")

@admin.route('/reject',methods=['post','get'])
def reject():
	id=request.args['id']
	q="update login  set usertype='reject' where usertype='nill' and login_id='%s'"%(id)
	print(q)
	update(q)
	return redirect(url_for('admin.viewvol'))
	return render_template("admin/viewvolunteer.html")


@admin.route('/managehotel',methods=['post','get'])
def managehotel():
	data={}
	q="select * from hotels inner join login using(login_id)"
	res=select(q)
	data['viewhotel']=res
	return render_template("admin/hotel.html",data=data)

@admin.route('/hotelaccept',methods=['post','get'])
def hotelaccept():
	data={}
	id=request.args['id']
	q="update login  set usertype='hotel' where usertype='pending' and login_id='%s'"%(id)
	print(q)
	update(q)
	return redirect(url_for('admin.managehotel'))
	return render_template("admin/hotel.html",data=data)

@admin.route('/managecharity',methods=['post','get'])
def managecharity():
	data={}
	q="select * from charity inner join login using(login_id)"
	res=select(q)
	data['viewcharity']=res
	return render_template("admin/charity.html",data=data)

@admin.route('/charityaccept',methods=['post','get'])
def charityaccept():
	data={}
	id=request.args['id']
	q="update login  set usertype='charity' where usertype='pending' and login_id='%s'"%(id)
	print(q)
	update(q)
	return redirect(url_for('admin.managecharity'))
	return render_template("admin/charity.html",data=data)


@admin.route('/viewref',methods=['post','get'])
def viewref():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from refrigerators where ref_id='%s'"%(id)
		delete(q)

		return redirect(url_for('admin.viewref'))
	if action=="update":
		q="select * from refrigerators where ref_id='%s'"%(id)
		res=select(q)
		data['updateref']=res
	if 'update' in request.form:

		status=request.form['status']
		q="update refrigerators set food_status='%s' where ref_id='%s'"%(status,id)
		update(q)
		return redirect(url_for('admin.viewref'))
	if 'submit' in request.form:
		lat=request.form['lat']
		lon=request.form['long']
		# status=request.form['status']
		q="insert into refrigerators values(null,'%s','%s','available')"%(lat,lon)
		insert(q)
	q="select * from refrigerators"
	res=select(q)
	data['viewref']=res
	return render_template("admin/refigerator.html",data=data)

@admin.route('/viewreq',methods=['post','get'])
def viewreq():
	data={}
	q="select * from food_request inner join charity using(charity_id)"
	res=select(q)
	data['viewreq']=res
	return render_template("admin/charityfoodstatus.html",data=data)


@admin.route('/viewfeed',methods=['post','get'])
def viewfeed():
	data={}
	q="SELECT * FROM feedback INNER JOIN hotels ON sender_id=hotel_id WHERE sender_type='hotel' UNION SELECT * FROM feedback INNER JOIN charity ON sender_id=charity_id WHERE sender_type='charity'"
	res=select(q)
	data['viewfeed']=res
	return render_template("admin/viewfeedback.html",data=data)



@admin.route('/viewfood',methods=['post','get'])
def viewfood():
	data={}
	q="SELECT * FROM `food_availability`"
	res=select(q)
	data['viewfood']=res
	return render_template("admin/viewfoodavailability.html",data=data)


@admin.route('/viewavail',methods=['post','get'])
def viewavail():
	data={}
	q="SELECT *,concat(first_name,' ',last_name)as Name FROM `food_distribution` INNER JOIN `volunteers` USING(`volunteer_id`) INNER JOIN `charity` USING(`charity_id`)"
	res=select(q)
	data['viewavail']=res
	return render_template("admin/viewvolunteerdistribution.html",data=data)

@admin.route('/regviewvol',methods=['post','get'])
def regviewvol():
	data={}
	q="select *,concat(first_name,' ',last_name)as Name from volunteers inner join login using(login_id) where usertype='volunteer'"
	res=select(q)
	data['viewvol']=res
	return render_template("admin/registeredviewvolunteer.html",data=data)
