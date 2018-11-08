drop table if exists sys_user;

create table sys_user (
  id bigint auto_increment COMMENT '编号',
  organization_id bigint COMMENT '组织机构编号',
  username varchar(100) COMMENT '用户名',
  password varchar(100) COMMENT '密码',
  salt varchar(100) COMMENT '盐值',
  role_ids varchar(100) COMMENT '角色列表',
  group_ids varchar(100) COMMENT '组列表',
  locked bool default false COMMENT '是否锁定',
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB COMMENT '用户表';