****基于Mysql、Spring Boot2、MyBatis、Redis的一个技术实验，演示项目

数据脚本**~~~~~~~~**
CREATE TABLE `tbl_users` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
`full_name` VARCHAR(45) NULL COMMENT '姓名',
`user_name` VARCHAR(45) NULL COMMENT '账号',
`password` VARCHAR(45) NULL COMMENT '密码',
`email` VARCHAR(45) NULL COMMENT 'Email',
`created_time` DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
PRIMARY KEY (`id`, `created_time`))
COMMENT = '用户表';

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

**遇到的问题**
1. 如果model类没有getter方法，rest controller返回报错: Resolved [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable rep

2. 如果使用了延迟加载，需要在包含嵌套属性的类前额外添加注解：@JsonIgnoreProperties(value = {"handler"})，否则报错：
Type definition error: [simple type, class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: org.penguin.boot.model.User_$$_jvsteac_0["handler"])

3. 针对Mysql的JSon类型，实现基本的功能，避免了能够存入Json数据，能够读取Json数据，但是保存或读取非Json对象时出错的问题
