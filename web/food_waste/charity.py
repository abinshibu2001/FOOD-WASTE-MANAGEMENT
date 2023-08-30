from flask import *
from database import *
charity=Blueprint("charity",__name__)
@charity.route('/home',methods=['post','get'])
def home():
	return render_template("charity/home.html")

@charity.route('/feedback',methods=['post','get'])
def feedback():
	data={}
	id=session['logid']
	if 'action' in request.args:
		action=request.args['action']
		ids=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from feedback where feedback_id='%s'"%(ids)
		delete(q)
		return(redirect(url_for('charity.feedback')))
	if 'submit' in request.form:
		feed=request.form['desc']
		q="insert into feedback values(null,(select charity_id from charity where login_id='%s'),'charity','%s',curdate())"%(id,feed)
		insert(q)
	q="SELECT * FROM feedback INNER JOIN charity ON sender_id=`charity_id` WHERE `charity_id`=(SELECT charity_id FROM charity WHERE login_id='%s') AND sender_type='charity'"%(id)
	res=select(q)
	data['viewfeed']=res
	return render_template("charity/sendfeedback.html",data=data)


@charity.route('/foodreq',methods=['post','get'])
def foodreq():
	data={}
	id=session['logid']
	if 'action' in request.args:
		ids=request.args['id']
		action =request.args['action']
	else:
		action=None
	if  action=="delete":
		q="delete from food_request where request_id='%s'"%(ids)
		delete(q)
		return(redirect(url_for('charity.foodreq')))
	if action=="update":
		q="select * from food_request where request_id='%s'"%(ids)
		res=select(q)
		data['updatequan']=res
	if 'update' in request.form:
		quan=request.form['quant']
		q="update food_request set quantity_required='%s' where request_id='%s'"%(quan,ids)
		update(q)
		return(redirect(url_for('charity.foodreq')))
	if 'submit' in request.form:
		quan=request.form['quant']
		q="insert into food_request values(null,(select charity_id from charity where login_id='%s'),now(),'%s','request')"%(id,quan)
		insert(q)
	q="SELECT * FROM food_request INNER JOIN charity USING(`charity_id`) WHERE food_request.charity_id=(SELECT charity_id FROM charity WHERE login_id='%s')"%(id)
	print(q)
	res=select(q)
	data['viewfood']=res
	return render_template("charity/sendfoodrequest.html",data=data)



@charity.route('/viewrefi',methods=['post','get'])
def viewrefi():
	data={}
	q="select * from refrigerators"
	res=select(q)
	data['viewref']=res
	return render_template("charity/viewrefrigerator.html",data=data)


@charity.route('/viewreceived',methods=['post','get'])
def viewreceived():
	data={}
	q="SELECT * FROM `food_distribution` INNER JOIN volunteers USING(volunteer_id) INNER JOIN `food_availability` ON `pick_up_id` =provider_id"
	res=select(q)
	data['viewvol']=res
	return render_template("charity/viewdistribution.html",data=data)


@charity.route('/rec',methods=['post','get'])
def rec():
	data={}
	id=request.args['id']
	q="update `food_availability` set status='received' where availability_id='%s'"%(id)
	print(q)
	update(q)
	return(redirect(url_for('charity.viewreceived')))
	return render_template("charity/viewdistribution.html",data=data)