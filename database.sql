create
database ems_thymeleaf;

use
ems_thymeleaf;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`
    INT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键',
    `username`
    VARCHAR
(
    40
) DEFAULT NULL COMMENT '用户名',
    `realname` VARCHAR
(
    100
) DEFAULT NULL COMMENT '真实姓名',
    `password` VARCHAR
(
    40
) DEFAULT NULL COMMENT '密码',
    `gender` TINYINT
(
    1
) DEFAULT '0' COMMENT '性别',
    PRIMARY KEY
(
    `runoob_id`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

CREATE TABLE IF NOT EXISTS `employee`
(
    `id`
    INT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT
    COMMENT
    '主键',
    `name`
    VARCHAR
(
    60
) DEFAULT NULL COMMENT '员工姓名',
    `salary` double
(
    10,
    2
) DEFAULT NULL COMMENT '员工工资',
    `birthday` DATETIME DEFAULT NULL COMMENT '员工生日',
    `photo` VARCHAR
(
    200
) DEFAULT NULL COMMENT '头像路径',
    PRIMARY KEY
(
    `runoob_id`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息';