from flask import *
from database import*
import uuid
import demjson
api=Blueprint('api',__name__)
@api.route('/login/',methods=['get','post'])
def login():
	data={}
	username = request.args['username']
	password = request.args['password']
	
	q="SELECT * FROM `login` WHERE `username`='%s' AND `password` ='%s'"%(username,password)
	res = select(q)
	if(len(res) > 0):
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	return  demjson.encode(data)

@api.route('/register/',methods=['get','post'])
def register():
	data={}
	fname=request.args['firstname']
	lname=request.args['lastname']
	age=request.args['age']
	gender=request.args['gender']
	phone=request.args['phone']
	email=request.args['email']
	username=request.args['user']
	password=request.args['pass']

	q="INSERT INTO login VALUES (null,'%s','%s','nill')"%(username,password)
	ids=insert(q)
	print(q)
	q="INSERT INTO `volunteers` VALUES(NULL,'%s','%s','%s','%s','%s','%s','%s')"%(ids,fname,lname,gender,age,phone,email)
	print(q) 
	insert(q)
	
	data['status']="success"
	data['method']="register"
	return demjson.encode(data)


@api.route('/volunteers_view_request/',methods=['get','post'])
def volunteers_view_request():
	data={}
	q="SELECT * FROM `food_request`INNER JOIN `charity` USING(`charity_id`)"
	res = select(q)
	if res:
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	# data['method']  = 'viewschools'
	return  demjson.encode(data)




@api.route('/volunteers_view_food/',methods=['get','post'])
def volunteers_view_food():
	data={}
	q="SELECT * FROM `food_availability`"
	res = select(q)
	if res:
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	# data['method']  = 'viewschools'
	return  demjson.encode(data)


@api.route('/volunteers_view_nearby_refrigerators/',methods=['get','post'])
def volunteers_view_nearby_refrigerators():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	# q="SELECT * FROM `refrigerators`"
	q="SELECT `ref_id`,`food_status`,latitude,longitude,(3959* ACOS(COS(RADIANS('%s')) * COS(RADIANS(latitude))* COS(RADIANS(longitude)- RADIANS('%s')) + SIN(RADIANS('%s')) * SIN(RADIANS(latitude)))) AS nearbylati FROM `refrigerators`"%(lati,longi,lati)
	print(q)
	res = select(q)
	if res:
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	# data['method']  = 'viewschools'
	return  demjson.encode(data)


@api.route('/guest_food/',methods=['get','post'])
def guest_food():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	q="INSERT INTO `food_availability` VALUES(NULL,'0','Guest','%s','%s',NOW(),'available')"%(lati,longi)
	insert(q)
	data['status']="success"
	data['method']="guest_food"

	return  demjson.encode(data)


@api.route('/guest_nearby_refrigerators/',methods=['get','post'])
def guest_nearby_refrigerators():
	data={}
	lati=request.args['lati']
	longi=request.args['longi']
	# q="SELECT * FROM `refrigerators`"
	q="SELECT `ref_id`,`food_status`,latitude,longitude,(3959* ACOS(COS(RADIANS('%s')) * COS(RADIANS(latitude))* COS(RADIANS(longitude)- RADIANS('%s')) + SIN(RADIANS('%s')) * SIN(RADIANS(latitude)))) AS nearbylati FROM `refrigerators`"%(lati,longi,lati)
	print(q)
	res = select(q)
	if res:
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	data['method']  = 'guest_nearby_refrigerators'
	return  demjson.encode(data)


	
@api.route('/guest_put_food/',methods=['get','post'])
def guest_put_food():
	data={}
	ref_id=request.args['ref_id']
	q="UPDATE `refrigerators` SET `food_status`='Handover' WHERE `ref_id`='%s'"%(ref_id)
	update(q)
	data['status']="success"
	data['method']="guest_put_food"

	return  demjson.encode(data)


@api.route('/volunteers_view_charity/',methods=['get','post'])
def volunteers_view_charity():
	data={}
	q="SELECT * FROM `food_request` INNER JOIN `charity` USING(`charity_id`) WHERE `request_status`='request'"
	res = select(q)
	if res:
		data['status']  = 'success'
		data['data'] = res
	else:
		data['status']	= 'failed'
	data['method']  = 'volunteers_view_charity'
	return  demjson.encode(data)

@api.route('/volunteers_distribute_food/',methods=['get','post'])
def volunteers_distribute_food():
	data={}
	logid=request.args['logid']
	p_id=request.args['p_id']
	p_type=request.args['p_type']
	c_id=request.args['c_id']
	q="INSERT INTO `food_distribution` VALUES(NULL,(SELECT `volunteer_id` FROM `volunteers` WHERE `login_id`='%s'),'%s','%s',NOW(),'%s',NOW())"%(logid,p_id,p_type,c_id)
	insert(q)
	q="UPDATE `food_request` SET `request_status`='handover' WHERE `charity_id`='%s' AND `request_status`='request'"%(c_id)
	update(q)
	data['status']="success"
	data['method']="volunteers_distribute_food"

	return  demjson.encode(data)

