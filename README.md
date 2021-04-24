****基于Mysql、Spring Boot2、MyBatis、Redis的一个技术实验，演示项目

数据脚本**~~~~~~~~**
CREATE TABLE `tbl_users` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
`organization_id` int DEFAULT NULL COMMENT '国家表主键',
`full_name` varchar(45) DEFAULT NULL COMMENT '姓名',
`user_name` varchar(45) DEFAULT NULL COMMENT '账号',
`password` varchar(45) DEFAULT NULL COMMENT '密码',
`email` varchar(45) DEFAULT NULL COMMENT 'Email',
`logo` blob,
`comment` text,
`created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`updated_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fk_country_id_from_countries_idx` (`organization_id`),
CONSTRAINT `fk_country_id_from_countries` FOREIGN KEY (`organization_id`) REFERENCES `tbl_organizations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

ALTER TABLE `tbl_users`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`);

ALTER TABLE `boot`.`tbl_users`
ADD COLUMN `organization_id` INT NULL COMMENT '国家表主键' AFTER `id`,
ADD INDEX `fk_organization_id_from_organizations_idx` (`organization_id` ASC) VISIBLE;
;
ALTER TABLE `boot`.`tbl_users`
ADD CONSTRAINT `fk_organization_id_from_organizations`
FOREIGN KEY (`organization_id`)
REFERENCES `boot`.`tbl_organizations` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

CREATE TABLE `tbl_roles` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色主键',
`name` VARCHAR(45) NULL COMMENT '角色名',
`comment` VARCHAR(200) NULL COMMENT '备注',
`created_time` DATETIME NULL DEFAULT now() COMMENT '创建时间',
PRIMARY KEY (`id`));

ALTER TABLE `tbl_roles`
COMMENT = '角色表' ;

INSERT INTO `boot`.`tbl_users` (`organization_id`, `full_name`, `user_name`, `password`, `email`) VALUES ('1', '系统管理员', 'administrator', 'admin123', 'admin@penguin.org');


SELECT length('中')=3, char_length('中')=1, 3=4;
show variables like '%character%';
show variables like 'collation%';
show charset; -- 查看MySQL支持的字符集
show full columns from tbl_users; -- 查看表结构

show table status from boot like 'tbl_users'; -- 查看数据库中某个表的属性
**遇到的问题**
1. 如果model类没有getter方法，rest controller返回报错: Resolved [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable rep

2. 如果使用了延迟加载，需要在包含嵌套属性的类前额外添加注解：@JsonIgnoreProperties(value = {"handler"})，否则报错：
Type definition error: [simple type, class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: org.penguin.boot.model.User_$$_jvsteac_0["handler"])

3. 针对Mysql的JSon类型，实现基本的功能，避免了能够存入Json数据，能够读取Json数据，但是保存或读取非Json对象时出错的问题

设计模式
entity字面是实体的意思，一般和数据库中的表或对象相对应。
model字面上模型的意思，一般是给前端用的，包含了一些数据校验逻辑，一些数据类型转换的操作。
domain字面上是域的意思，个人认为是从DDD(Domain Driven Design: 领域驱动设计)而来