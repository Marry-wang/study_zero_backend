/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 16/11/2023 17:05:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (2, 'study-zero-system.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    com.demo: debug\n', 'bfc3a199853077e63be36738e7827138', '2023-11-15 09:59:55', '2023-11-16 08:54:06', 'nacos', '127.0.0.1', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (21, 'study-zero-gateway.yml', 'DEFAULT_GROUP', 'spring:\r\n  cloud:\r\n    gateway:\r\n      # 全局的跨域处理\r\n      globalcors:\r\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\r\n        add-to-simple-url-handler-mapping: true\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOriginPatterns: \"*\"\r\n            #allowedOrigins: \"*\"\r\n            # 允许哪些网站的跨域请求\r\n            #  - \"http://localhost:8089\"\r\n            allowedMethods:\r\n              # 允许的跨域ajax的请求方式\r\n              - \"GET\"\r\n              - \"POST\"\r\n              - \"DELETE\"\r\n              - \"PUT\"\r\n              - \"OPTIONS\"\r\n              # 允许在请求中携带的头信息\r\n            allowedHeaders: \"*\"\r\n            # 是否允许携带cookie\r\n            allowCredentials: true\r\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\r\n            maxAge: 360000\r\n      routes:\r\n#        id唯一\r\n        - id: study-zero-user\r\n#          uri  匹配后要访问的地址\r\n          uri: http://127.0.0.1:10001\r\n#          需要匹配的规则\r\n          predicates:\r\n            - Path=/user/**\r\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\r\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-system\r\n          uri: http://127.0.0.1:10002\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-pay\r\n          uri: http://127.0.0.1:1003\r\n          predicates:\r\n            - Path=/pay/**\r\n          filters:\r\n            - StripPrefix=1', 'f3d5de8edce330222a0252dba69fd422', '2023-11-16 09:03:16', '2023-11-16 09:03:16', NULL, '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(0) UNSIGNED NOT NULL,
  `nid` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'service-provider.yml', 'DEFAULT_GROUP', '', 'name: 11', '351b642b8e9794802f01bcb736d68966', '2023-11-15 17:58:52', '2023-11-15 09:58:52', NULL, '127.0.0.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10002\r\n\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: root\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    filters: stat\r\n    maxActive: 100\r\n    initialSize: 1\r\n    maxWait: 60000\r\n    minIdle: 1\r\n    timeBetweenEvictionRunsMillis: 60000\r\n    minEvictableIdleTimeMillis: 300000\r\n    validationQuery: select \'x\'\r\n    testWhileIdle: true\r\n    testOnBorrow: false\r\n    testOnReturn: false\r\n    poolPreparedStatements: true\r\n    maxOpenPreparedStatements: 20\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    timeout: 300\r\n    jedis:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 8\r\n        #连接池中的最大空闲连接\r\n        max-idle: 500\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n    lettuce:\r\n      shutdown-timeout: 0\r\n\r\n\r\n# mybatis-plus相关配置\r\nmybatis-plus:\r\n  global-config:\r\n    db-config:\r\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\r\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\r\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\r\n  # mybatis-plus相关配置\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\nconfig:\r\n  jwt:\r\n    # 加密密钥\r\n    secret: qwer\r\n    # token有效时长\r\n    expire: 300\r\n    #header 名称\r\n    header: accessToken\r\nlogging:\r\n  level:\r\n    com.demo: debug\r\n', 'd6499d35a0c527d98964a45d60a29311', '2023-11-15 17:59:55', '2023-11-15 09:59:55', NULL, '127.0.0.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (2, 3, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10002\r\n\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: root\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    filters: stat\r\n    maxActive: 100\r\n    initialSize: 1\r\n    maxWait: 60000\r\n    minIdle: 1\r\n    timeBetweenEvictionRunsMillis: 60000\r\n    minEvictableIdleTimeMillis: 300000\r\n    validationQuery: select \'x\'\r\n    testWhileIdle: true\r\n    testOnBorrow: false\r\n    testOnReturn: false\r\n    poolPreparedStatements: true\r\n    maxOpenPreparedStatements: 20\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    timeout: 300\r\n    jedis:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 8\r\n        #连接池中的最大空闲连接\r\n        max-idle: 500\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n    lettuce:\r\n      shutdown-timeout: 0\r\n\r\n\r\n# mybatis-plus相关配置\r\nmybatis-plus:\r\n  global-config:\r\n    db-config:\r\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\r\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\r\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\r\n  # mybatis-plus相关配置\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\nconfig:\r\n  jwt:\r\n    # 加密密钥\r\n    secret: qwer\r\n    # token有效时长\r\n    expire: 300\r\n    #header 名称\r\n    header: accessToken\r\nlogging:\r\n  level:\r\n    com.demo: debug\r\n', 'd6499d35a0c527d98964a45d60a29311', '2023-11-16 13:39:51', '2023-11-16 05:39:51', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 4, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10002\r\n\r\nspring:\r\n  application:\r\n    name: study-zero-system\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: root\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    filters: stat\r\n    maxActive: 100\r\n    initialSize: 1\r\n    maxWait: 60000\r\n    minIdle: 1\r\n    timeBetweenEvictionRunsMillis: 60000\r\n    minEvictableIdleTimeMillis: 300000\r\n    validationQuery: select \'x\'\r\n    testWhileIdle: true\r\n    testOnBorrow: false\r\n    testOnReturn: false\r\n    poolPreparedStatements: true\r\n    maxOpenPreparedStatements: 20\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    timeout: 300\r\n    jedis:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 8\r\n        #连接池中的最大空闲连接\r\n        max-idle: 500\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n    lettuce:\r\n      shutdown-timeout: 0\r\n\r\n\r\n# mybatis-plus相关配置\r\nmybatis-plus:\r\n  global-config:\r\n    db-config:\r\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\r\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\r\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\r\n  # mybatis-plus相关配置\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\nconfig:\r\n  jwt:\r\n    # 加密密钥\r\n    secret: qwer\r\n    # token有效时长\r\n    expire: 300\r\n    #header 名称\r\n    header: accessToken\r\nlogging:\r\n  level:\r\n    com.demo: debug\r\n', '297fe0f939e1fad25bce0b87a7d067b5', '2023-11-16 13:41:30', '2023-11-16 05:41:31', NULL, '127.0.0.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (4, 5, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10002\r\n\r\nspring:\r\n  application:\r\n    name: study-zero-system\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: root\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    filters: stat\r\n    maxActive: 100\r\n    initialSize: 1\r\n    maxWait: 60000\r\n    minIdle: 1\r\n    timeBetweenEvictionRunsMillis: 60000\r\n    minEvictableIdleTimeMillis: 300000\r\n    validationQuery: select \'x\'\r\n    testWhileIdle: true\r\n    testOnBorrow: false\r\n    testOnReturn: false\r\n    poolPreparedStatements: true\r\n    maxOpenPreparedStatements: 20\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    timeout: 300\r\n    jedis:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 8\r\n        #连接池中的最大空闲连接\r\n        max-idle: 500\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n    lettuce:\r\n      shutdown-timeout: 0\r\n\r\n\r\n# mybatis-plus相关配置\r\nmybatis-plus:\r\n  global-config:\r\n    db-config:\r\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\r\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\r\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\r\n  # mybatis-plus相关配置\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\nconfig:\r\n  jwt:\r\n    # 加密密钥\r\n    secret: qwer\r\n    # token有效时长\r\n    expire: 300\r\n    #header 名称\r\n    header: accessToken\r\nlogging:\r\n  level:\r\n    com.demo: debug\r\n', '297fe0f939e1fad25bce0b87a7d067b5', '2023-11-16 13:46:29', '2023-11-16 05:46:29', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 6, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 10002\n\nspring:\n  application:\n    name: study-zero-system\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    com.demo: debug\n', '3118398c087ae21aba12141b9a27f1f5', '2023-11-16 13:57:16', '2023-11-16 05:57:17', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 7, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    com.demo: info\n', '7f902c755711bc9efc5ca138562b23f6', '2023-11-16 13:59:14', '2023-11-16 05:59:14', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 8, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    root: info\n', '1cf1caf8352f3636c0da147aafe8daaf', '2023-11-16 14:01:46', '2023-11-16 06:01:46', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (2, 9, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 10002\n\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    com.demo: debug\n', 'c117efe460693ae442cebb648e550183', '2023-11-16 14:06:31', '2023-11-16 06:06:32', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (2, 10, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'server:\n  port: 10002\n\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    root: debug\n', 'aa49a6079752ead7de10e536476103d1', '2023-11-16 14:08:49', '2023-11-16 06:08:50', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 11, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    root: debug\n', '8e50222c85fdce2e2bbe97a8973982a0', '2023-11-16 14:09:09', '2023-11-16 06:09:09', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 12, 'service-provider.yml', 'DEFAULT_GROUP', '', 'name: 11', '351b642b8e9794802f01bcb736d68966', '2023-11-16 14:13:09', '2023-11-16 06:13:10', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 13, 'service-provider.yml', 'DEFAULT_GROUP', '', 'logging:\n  level:\n    root: debug', 'e147cc72eadf19299deaa6a56de083da', '2023-11-16 14:14:50', '2023-11-16 06:14:51', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (1, 14, 'service-provider.yml', 'DEFAULT_GROUP', '', '# logging:\n#   level:\n#     root: debug', '74984f1008e4d558c406db1353940926', '2023-11-16 14:15:35', '2023-11-16 06:15:35', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 15, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'logging:\n  level:\n    root: debug\n', '5463eb1202cb4bd6cfeb9a21d7116f43', '2023-11-16 14:28:39', '2023-11-16 06:28:40', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (2, 16, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'logging:\n  level:\n    root: debug\n', '5463eb1202cb4bd6cfeb9a21d7116f43', '2023-11-16 16:53:20', '2023-11-16 08:53:20', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (2, 17, 'study-zero-system.yml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    root: debug\n', '472b092f1a137e75bffff3a61127ea80', '2023-11-16 16:54:06', '2023-11-16 08:54:06', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 18, 'study-zero-system.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/zero?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    filters: stat\n    maxActive: 100\n    initialSize: 1\n    maxWait: 60000\n    minIdle: 1\n    timeBetweenEvictionRunsMillis: 60000\n    minEvictableIdleTimeMillis: 300000\n    validationQuery: select \'x\'\n    testWhileIdle: true\n    testOnBorrow: false\n    testOnReturn: false\n    poolPreparedStatements: true\n    maxOpenPreparedStatements: 20\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    timeout: 300\n    jedis:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 8\n        #连接池中的最大空闲连接\n        max-idle: 500\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1\n        #连接池中的最小空闲连接\n        min-idle: 0\n    lettuce:\n      shutdown-timeout: 0\n\n\n# mybatis-plus相关配置\nmybatis-plus:\n  global-config:\n    db-config:\n      logic-delete-field: deleFlag # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)\n      logic-delete-value: 1 # 逻辑已删除值(默认为 1)\n      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)\n  # mybatis-plus相关配置\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\nconfig:\n  jwt:\n    # 加密密钥\n    secret: qwer\n    # token有效时长\n    expire: 300\n    #header 名称\n    header: accessToken\nlogging:\n  level:\n    root: debug\n', '472b092f1a137e75bffff3a61127ea80', '2023-11-16 16:56:04', '2023-11-16 08:56:05', NULL, '127.0.0.1', 'D', '', '');
INSERT INTO `his_config_info` VALUES (1, 19, 'service-provider.yml', 'DEFAULT_GROUP', '', 'logging:\n  level:\n    root: debug', 'e147cc72eadf19299deaa6a56de083da', '2023-11-16 16:56:08', '2023-11-16 08:56:09', NULL, '127.0.0.1', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 20, 'study_zero_gateway.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\n\r\nspring:\r\n  application:\r\n    name: study-zero-gateway\r\n  cloud:\r\n    gateway:\r\n      # 全局的跨域处理\r\n      globalcors:\r\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\r\n        add-to-simple-url-handler-mapping: true\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOriginPatterns: \"*\"\r\n            #allowedOrigins: \"*\"\r\n            # 允许哪些网站的跨域请求\r\n            #  - \"http://localhost:8089\"\r\n            allowedMethods:\r\n              # 允许的跨域ajax的请求方式\r\n              - \"GET\"\r\n              - \"POST\"\r\n              - \"DELETE\"\r\n              - \"PUT\"\r\n              - \"OPTIONS\"\r\n              # 允许在请求中携带的头信息\r\n            allowedHeaders: \"*\"\r\n            # 是否允许携带cookie\r\n            allowCredentials: true\r\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\r\n            maxAge: 360000\r\n      routes:\r\n#        id唯一\r\n        - id: study-zero-user\r\n#          uri  匹配后要访问的地址\r\n          uri: http://127.0.0.1:10001\r\n#          需要匹配的规则\r\n          predicates:\r\n            - Path=/user/**\r\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\r\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-system\r\n          uri: http://127.0.0.1:10002\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-pay\r\n          uri: http://127.0.0.1:1003\r\n          predicates:\r\n            - Path=/pay/**\r\n          filters:\r\n            - StripPrefix=1', '2a428f733c41c75cb2a2dcb93c2de948', '2023-11-16 16:56:47', '2023-11-16 08:56:48', NULL, '127.0.0.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (18, 21, 'study_zero_gateway.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\n\r\nspring:\r\n  application:\r\n    name: study-zero-gateway\r\n  cloud:\r\n    gateway:\r\n      # 全局的跨域处理\r\n      globalcors:\r\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\r\n        add-to-simple-url-handler-mapping: true\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOriginPatterns: \"*\"\r\n            #allowedOrigins: \"*\"\r\n            # 允许哪些网站的跨域请求\r\n            #  - \"http://localhost:8089\"\r\n            allowedMethods:\r\n              # 允许的跨域ajax的请求方式\r\n              - \"GET\"\r\n              - \"POST\"\r\n              - \"DELETE\"\r\n              - \"PUT\"\r\n              - \"OPTIONS\"\r\n              # 允许在请求中携带的头信息\r\n            allowedHeaders: \"*\"\r\n            # 是否允许携带cookie\r\n            allowCredentials: true\r\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\r\n            maxAge: 360000\r\n      routes:\r\n#        id唯一\r\n        - id: study-zero-user\r\n#          uri  匹配后要访问的地址\r\n          uri: http://127.0.0.1:10001\r\n#          需要匹配的规则\r\n          predicates:\r\n            - Path=/user/**\r\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\r\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-system\r\n          uri: http://127.0.0.1:10002\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-pay\r\n          uri: http://127.0.0.1:1003\r\n          predicates:\r\n            - Path=/pay/**\r\n          filters:\r\n            - StripPrefix=1', '2a428f733c41c75cb2a2dcb93c2de948', '2023-11-16 16:57:50', '2023-11-16 08:57:51', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (18, 22, 'study_zero_gateway.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      # 全局的跨域处理\n      globalcors:\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\n        add-to-simple-url-handler-mapping: true\n        corsConfigurations:\n          \'[/**]\':\n            allowedOriginPatterns: \"*\"\n            #allowedOrigins: \"*\"\n            # 允许哪些网站的跨域请求\n            #  - \"http://localhost:8089\"\n            allowedMethods:\n              # 允许的跨域ajax的请求方式\n              - \"GET\"\n              - \"POST\"\n              - \"DELETE\"\n              - \"PUT\"\n              - \"OPTIONS\"\n              # 允许在请求中携带的头信息\n            allowedHeaders: \"*\"\n            # 是否允许携带cookie\n            allowCredentials: true\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\n            maxAge: 360000\n      routes:\n#        id唯一\n        - id: study-zero-user\n#          uri  匹配后要访问的地址\n          uri: http://127.0.0.1:10001\n#          需要匹配的规则\n          predicates:\n            - Path=/user/**\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\n          filters:\n            - StripPrefix=1\n        - id: study-zero-system\n          uri: http://127.0.0.1:10002\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        - id: study-zero-pay\n          uri: http://127.0.0.1:1003\n          predicates:\n            - Path=/pay/**\n          filters:\n            - StripPrefix=1', 'c18db1076ad899a66c5765bc3c79f014', '2023-11-16 17:00:01', '2023-11-16 09:00:01', 'nacos', '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 23, 'study-zero-gateway.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    gateway:\r\n      # 全局的跨域处理\r\n      globalcors:\r\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\r\n        add-to-simple-url-handler-mapping: true\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOriginPatterns: \"*\"\r\n            #allowedOrigins: \"*\"\r\n            # 允许哪些网站的跨域请求\r\n            #  - \"http://localhost:8089\"\r\n            allowedMethods:\r\n              # 允许的跨域ajax的请求方式\r\n              - \"GET\"\r\n              - \"POST\"\r\n              - \"DELETE\"\r\n              - \"PUT\"\r\n              - \"OPTIONS\"\r\n              # 允许在请求中携带的头信息\r\n            allowedHeaders: \"*\"\r\n            # 是否允许携带cookie\r\n            allowCredentials: true\r\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\r\n            maxAge: 360000\r\n      routes:\r\n#        id唯一\r\n        - id: study-zero-user\r\n#          uri  匹配后要访问的地址\r\n          uri: http://127.0.0.1:10001\r\n#          需要匹配的规则\r\n          predicates:\r\n            - Path=/user/**\r\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\r\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-system\r\n          uri: http://127.0.0.1:10002\r\n          predicates:\r\n            - Path=/system/**\r\n          filters:\r\n            - StripPrefix=1\r\n        - id: study-zero-pay\r\n          uri: http://127.0.0.1:1003\r\n          predicates:\r\n            - Path=/pay/**\r\n          filters:\r\n            - StripPrefix=1', 'f3d5de8edce330222a0252dba69fd422', '2023-11-16 17:03:16', '2023-11-16 09:03:16', NULL, '127.0.0.1', 'I', '', '');
INSERT INTO `his_config_info` VALUES (18, 24, 'study_zero_gateway.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      # 全局的跨域处理\n      globalcors:\n        # 解决options请求被拦截问题，Cors解决方案，客户端询问服务端，这次询问需要放行\n        add-to-simple-url-handler-mapping: true\n        corsConfigurations:\n          \'[/**]\':\n            allowedOriginPatterns: \"*\"\n            #allowedOrigins: \"*\"\n            # 允许哪些网站的跨域请求\n            #  - \"http://localhost:8089\"\n            allowedMethods:\n              # 允许的跨域ajax的请求方式\n              - \"GET\"\n              - \"POST\"\n              - \"DELETE\"\n              - \"PUT\"\n              - \"OPTIONS\"\n              # 允许在请求中携带的头信息\n            allowedHeaders: \"*\"\n            # 是否允许携带cookie\n            allowCredentials: true\n            # 这次跨域检测的有效期，在有效期内容不需要每次Ajax请求都询问服务端\n            maxAge: 360000\n      routes:\n#        id唯一\n        - id: study-zero-user\n#          uri  匹配后要访问的地址\n          uri: http://127.0.0.1:10001\n#          需要匹配的规则\n          predicates:\n            - Path=/user/**\n#          https://blog.csdn.net/qq_61603262/article/details/127384412   翻译文\n#          过滤器数组，在请求传递过程中，去除请求路径中的1级路径\n          filters:\n            - StripPrefix=1\n        - id: study-zero-system\n          uri: lb://study-zero-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        - id: study-zero-pay\n          uri: http://127.0.0.1:1003\n          predicates:\n            - Path=/pay/**\n          filters:\n            - StripPrefix=1', 'f1b87ccdf4ef976ae057c89ba1d3fae4', '2023-11-16 17:03:20', '2023-11-16 09:03:21', NULL, '127.0.0.1', 'D', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
