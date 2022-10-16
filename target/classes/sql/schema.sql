/**
用户表
 */
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE 'c_user'(
    'id' INTEGER PRIMARY KEY AUTOINCREMENT, --主键
    'name' VARCHAR (20), --姓名
    'phone' INTEGER (20),  --手机号
    'addr' VARCHAR (50), --地址
    'gender' VARCHAR (10), --性别
    'intro' VARCHAR (50), --简介
    'profile' VARCHAR (50), --头像
    'email' VARCHAR (50), --邮箱
    'username' VARCHAR (20), --用户名
    'password' VARCHAR (20)  --密码
);
/**
用户好友表
 */
DROP TABLE IF EXISTS `c_friend`;
CREATE TABLE 'c_friend'(
    'id' INTEGER PRIMARY KEY AUTOINCREMENT, --主键
    'userId' VARCHAR (20) NULL DEFAULT NULL, --用户id
    'friendId' VARCHAR (20) NULL DEFAULT NULL --接朋友id
);
/**
群聊表（包含用户）
 */
DROP TABLE IF EXISTS `c_group`;
CREATE TABLE 'c_group'(
    'id' INTEGER PRIMARY KEY AUTOINCREMENT, --主键
    'groupName' VARCHAR (20) NULL DEFAULT NULL,--群聊名称
    'groupOwner' INTEGER NULL DEFAULT NULL,--群聊拥有者
    'members' TEXT,
    'intro' VARCHAR (20) NULL DEFAULT NULL,--群聊名称
    'profile' VARCHAR (20) NULL DEFAULT NULL--群聊名称
);
/**
用户群聊表
 */
-- DROP TABLE IF EXISTS `c_user_group`;
-- CREATE TABLE 'c_user_group'(
--     'id' INTEGER PRIMARY KEY AUTOINCREMENT, --主键
--     'userId' VARCHAR (20) NULL DEFAULT NULL, --用户id
--     'groupId' VARCHAR (20) NULL DEFAULT NULL --群聊id
-- );
/**
消息缓存表
 */
DROP TABLE IF EXISTS `offline_msg`;
CREATE TABLE 'offline_msg'(
    'id' INTEGER PRIMARY KEY AUTOINCREMENT, --主键
    'fromId' VARCHAR (20) NULL DEFAULT NULL, --发送者id
    'toId' VARCHAR (20) NULL DEFAULT NULL, --接收者id
    'msg' VARCHAR (255) NULL DEFAULT NULL, --消息
    'time' DATE, --时间
    'type' INTEGER NULL DEFAULT NULL, --type
    'groupId' INTEGER, --groupId
    'profile' VARCHAR (20) --接收者id
    );

