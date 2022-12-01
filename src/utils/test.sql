SELECT * FROM bill;
DESC dining_table;
show processlist; #显示当前连接数
show variables like "max_connections"; #显示最大连接数
SELECT * FROM dining_table;

UPDATE dining_table SET state = '空';
UPDATE dining_table SET orderName = '';
UPDATE dining_table SET orderTel = '';

drop table bill;
create table bill (
	id int primary key auto_increment, #自增主键
	billId varchar(50) not null default '',#账单号可以按照自己规则生成 UUID
	menuId int not null default 0,#菜品的编号, 也可以使用外键
	nums SMALLINT not null default 0,#份数
	money double not null default 0, #金额
	diningTableId int not null default 0, #餐桌
	billDate datetime not null ,#订单日期
	state varchar(50) not null default '' # 状态 '未结账' , '已经结账', '挂单'
)charset=utf8;