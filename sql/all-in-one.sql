-- ============================================================
-- 考试系统数据库初始化脚本 - 一键执行版
-- 使用方法：mysql -u用户名 -p exam < all-in-one.sql
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS exam DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE exam;

-- ============================================================
-- 第一部分：创建表结构（所有ID字段使用VARCHAR(50)）
-- ============================================================

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(50) NOT NULL COMMENT '用户ID',
  `position_id` VARCHAR(50) DEFAULT NULL COMMENT '职位ID',
  `department_id` VARCHAR(50) DEFAULT NULL COMMENT '部门ID',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '公司id',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '工号',
  `password` VARCHAR(100) DEFAULT NULL COMMENT '密码',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '姓名',
  `profile_picture` VARCHAR(200) DEFAULT NULL COMMENT '头像',
  `sex` BIGINT DEFAULT NULL COMMENT '性别',
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `tel` VARCHAR(20) DEFAULT NULL COMMENT '电话',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `other` VARCHAR(200) DEFAULT NULL COMMENT '其他联系',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `status` BIGINT DEFAULT NULL COMMENT '状态位',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_department_id` (`department_id`),
  KEY `idx_position_id` (`position_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE IF NOT EXISTS `role` (
  `id` VARCHAR(50) NOT NULL COMMENT '权限id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '公司id',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '组织机构id',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '角色名',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '角色代码',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `status` BIGINT DEFAULT NULL COMMENT '状态位',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 资源表
CREATE TABLE IF NOT EXISTS `resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '资源ID',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '节点名称',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '编号',
  `order_index` VARCHAR(50) DEFAULT NULL COMMENT '顺序号',
  `parent_id` VARCHAR(50) DEFAULT NULL COMMENT '父亲节点',
  `url` VARCHAR(200) DEFAULT NULL COMMENT 'URL',
  `open_img` VARCHAR(200) DEFAULT NULL COMMENT '打开图标',
  `close_img` VARCHAR(200) DEFAULT NULL COMMENT '关闭图标',
  `resource_type` BIGINT DEFAULT NULL COMMENT '资源类型',
  `leaf` BIGINT DEFAULT NULL COMMENT '叶子节点',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `status` BIGINT DEFAULT NULL COMMENT '状态位',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_resource_type` (`resource_type`),
  KEY `idx_leaf` (`leaf`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` VARCHAR(50) NOT NULL COMMENT '用户权限关联id',
  `user_id` VARCHAR(50) DEFAULT NULL COMMENT '用户id',
  `role_id` VARCHAR(50) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- 角色资源关联表
CREATE TABLE IF NOT EXISTS `role_resource` (
  `id` VARCHAR(50) NOT NULL COMMENT '关联id',
  `role_id` VARCHAR(50) DEFAULT NULL COMMENT '权限id',
  `resource_id` VARCHAR(50) DEFAULT NULL COMMENT '资源id',
  `type` BIGINT DEFAULT NULL COMMENT '是否半选',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源关联表';

-- 公司表
CREATE TABLE IF NOT EXISTS `company` (
  `id` VARCHAR(50) NOT NULL COMMENT '公司ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '组织机构ID',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '公司名',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '公司编号',
  `mnemonic_code` VARCHAR(50) DEFAULT NULL COMMENT '助记码',
  `master` VARCHAR(50) DEFAULT NULL COMMENT '法人',
  `tax` VARCHAR(50) DEFAULT NULL COMMENT '税号',
  `fax` VARCHAR(50) DEFAULT NULL COMMENT '传真',
  `tel` VARCHAR(50) DEFAULT NULL COMMENT '电话',
  `address` VARCHAR(200) DEFAULT NULL COMMENT '地址',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `website` VARCHAR(100) DEFAULT NULL COMMENT '网址',
  `status` BIGINT DEFAULT NULL COMMENT '状态位',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司表';

-- 部门表
CREATE TABLE IF NOT EXISTS `department` (
  `id` VARCHAR(50) NOT NULL COMMENT '部门ID',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '公司ID',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '部门名称',
  `mnemonic_code` VARCHAR(50) DEFAULT NULL COMMENT '助记码',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '部门编号',
  `level` VARCHAR(50) DEFAULT NULL COMMENT '部门级别',
  `parent_id` VARCHAR(50) DEFAULT NULL COMMENT '上级部门',
  `master` VARCHAR(50) DEFAULT NULL COMMENT '负责人',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '部门描述',
  `status` BIGINT DEFAULT NULL COMMENT '状态',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 职位表
CREATE TABLE IF NOT EXISTS `position` (
  `id` VARCHAR(50) NOT NULL COMMENT '职位ID',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '公司ID',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '职位名称',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '职位编号',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `status` BIGINT DEFAULT NULL COMMENT '状态位',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职位表';

-- 题目表
CREATE TABLE IF NOT EXISTS `subject` (
  `id` VARCHAR(50) NOT NULL COMMENT '题目ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `subject_type_id` VARCHAR(50) DEFAULT NULL COMMENT '题目类型id',
  `category_id` VARCHAR(50) DEFAULT NULL COMMENT '题目类别id',
  `name` VARCHAR(500) DEFAULT NULL COMMENT '题目',
  `difficulty` BIGINT DEFAULT NULL COMMENT '难度',
  `status` TINYINT DEFAULT NULL COMMENT '状态位',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_subject_type_id` (`subject_type_id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目表';

-- 题目答案表
CREATE TABLE IF NOT EXISTS `subject_answer` (
  `id` VARCHAR(50) NOT NULL COMMENT '答案ID',
  `subject_id` VARCHAR(50) DEFAULT NULL COMMENT '题目ID',
  `answer` VARCHAR(500) DEFAULT NULL COMMENT '答案',
  `correct` TINYINT DEFAULT NULL COMMENT '是否正确',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`),
  KEY `idx_subject_id` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目答案表';

-- 题目类型表
CREATE TABLE IF NOT EXISTS `subject_type` (
  `id` VARCHAR(50) NOT NULL COMMENT '题目类型ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '类型名称',
  `attribute` VARCHAR(50) DEFAULT NULL COMMENT '属性',
  `status` TINYINT DEFAULT NULL COMMENT '状态位',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目类型表';

-- 题目类别表
CREATE TABLE IF NOT EXISTS `category` (
  `id` VARCHAR(50) NOT NULL COMMENT '类别ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '类别名称',
  `parent_id` VARCHAR(50) DEFAULT NULL COMMENT '父类别ID',
  `status` TINYINT DEFAULT NULL COMMENT '状态位',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='题目类别表';

-- 数据字典表
CREATE TABLE IF NOT EXISTS `dictionary` (
  `id` VARCHAR(50) NOT NULL COMMENT '字典ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `category` VARCHAR(100) DEFAULT NULL COMMENT '分类',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '名称',
  `value` VARCHAR(200) DEFAULT NULL COMMENT '值',
  `status` TINYINT DEFAULT NULL COMMENT '状态位',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_category` (`category`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典表';

-- 试卷表
CREATE TABLE IF NOT EXISTS `paper` (
  `id` VARCHAR(50) NOT NULL COMMENT '试卷ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '试卷名',
  `paper_type` VARCHAR(50) DEFAULT NULL COMMENT '试卷类型',
  `difficulty` BIGINT DEFAULT NULL COMMENT '难度',
  `comb_exam_time` DATETIME DEFAULT NULL COMMENT '组卷日期',
  `paper_creator` VARCHAR(50) DEFAULT NULL COMMENT '组卷人',
  `comb_examer` VARCHAR(50) DEFAULT NULL COMMENT '组卷人',
  `score` DOUBLE DEFAULT NULL COMMENT '卷面分数',
  `description` VARCHAR(500) DEFAULT NULL COMMENT '卷子描述',
  `template` TINYINT DEFAULT NULL COMMENT '是否为模板',
  `download_times` INT DEFAULT NULL COMMENT '下载次数',
  `publish_times` INT DEFAULT NULL COMMENT '发布次数',
  `status` TINYINT DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试卷表';

-- 试卷题目表
CREATE TABLE IF NOT EXISTS `paper_subject` (
  `id` VARCHAR(50) NOT NULL COMMENT '试卷题目ID',
  `paper_id` VARCHAR(50) DEFAULT NULL COMMENT '试卷ID',
  `subject_id` VARCHAR(50) DEFAULT NULL COMMENT '题目ID',
  `score` DOUBLE DEFAULT NULL COMMENT '分值',
  `order_index` VARCHAR(50) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`),
  KEY `idx_paper_id` (`paper_id`),
  KEY `idx_subject_id` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试卷题目表';

-- 试卷题目答案表
CREATE TABLE IF NOT EXISTS `paper_subject_answer` (
  `id` VARCHAR(50) NOT NULL COMMENT '试卷题目答案ID',
  `paper_subject_id` VARCHAR(50) DEFAULT NULL COMMENT '试卷题目ID',
  `answer` VARCHAR(500) DEFAULT NULL COMMENT '答案',
  `score` DOUBLE DEFAULT NULL COMMENT '得分',
  PRIMARY KEY (`id`),
  KEY `idx_paper_subject_id` (`paper_subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='试卷题目答案表';

-- 考试记录表
CREATE TABLE IF NOT EXISTS `exam_record` (
  `id` VARCHAR(50) NOT NULL COMMENT '考试记录ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `paper_id` VARCHAR(50) DEFAULT NULL COMMENT '试卷ID',
  `user_id` VARCHAR(50) DEFAULT NULL COMMENT '用户ID',
  `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
  `score` DOUBLE DEFAULT NULL COMMENT '得分',
  `status` TINYINT DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_paper_id` (`paper_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='考试记录表';

-- 答案记录表
CREATE TABLE IF NOT EXISTS `answer_record` (
  `id` VARCHAR(50) NOT NULL COMMENT '答案记录ID',
  `exam_record_id` VARCHAR(50) DEFAULT NULL COMMENT '考试记录ID',
  `paper_subject_id` VARCHAR(50) DEFAULT NULL COMMENT '试卷题目ID',
  `answer` VARCHAR(500) DEFAULT NULL COMMENT '答案',
  `score` DOUBLE DEFAULT NULL COMMENT '得分',
  PRIMARY KEY (`id`),
  KEY `idx_exam_record_id` (`exam_record_id`),
  KEY `idx_paper_subject_id` (`paper_subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答案记录表';

-- 用户在线信息表
CREATE TABLE IF NOT EXISTS `user_online_info` (
  `id` BIGINT NOT NULL COMMENT '在线ID',
  `user_id` BIGINT DEFAULT NULL COMMENT '用户ID',
  `code` VARCHAR(50) DEFAULT NULL COMMENT '工号',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
  `ip` VARCHAR(50) DEFAULT NULL COMMENT 'IP',
  `online_time` DATETIME DEFAULT NULL COMMENT '上线时间',
  `offline_time` DATETIME DEFAULT NULL COMMENT '下线时间',
  `stop_time` BIGINT DEFAULT NULL COMMENT '在线时长',
  `status` TINYINT DEFAULT NULL COMMENT '状态位',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户在线信息表';

-- 系统参数表
CREATE TABLE IF NOT EXISTS `system_param` (
  `id` VARCHAR(50) NOT NULL COMMENT '参数ID',
  `org_id` VARCHAR(50) DEFAULT NULL COMMENT '机构id',
  `company_id` VARCHAR(50) DEFAULT NULL COMMENT '机构下公司id',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建者',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建日期',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改者',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改日期',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  `param_key` VARCHAR(100) DEFAULT NULL COMMENT '参数键',
  `param_value` VARCHAR(500) DEFAULT NULL COMMENT '参数值',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '参数描述',
  `status` TINYINT DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_param_key` (`param_key`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数表';

-- ============================================================
-- 第二部分：插入基础数据（角色、资源、用户）
-- ============================================================

-- 插入管理员用户
INSERT INTO `user` (`id`, `password`, `name`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 1, NOW(), NOW(), NOW());

-- 插入默认角色
INSERT INTO `role` (`id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '系统管理员', 'SYS_ADMIN', 1, NOW(), NOW(), NOW()),
('2', '公司管理员', 'COMPANY_ADMIN', 1, NOW(), NOW(), NOW()),
('3', '老师', 'TEACHER', 1, NOW(), NOW(), NOW()),
('4', '学生', 'STUDENT', 1, NOW(), NOW(), NOW());

-- 插入用户角色关联
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
('1', '1', '1');

-- 插入默认资源
INSERT INTO resource (id, name, code, url, parent_id, level, status, created_by, created_time, updated_by, updated_time, version) VALUES
('1', '认证管理', 'AUTH_MANAGE', '/auth', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('2', '登录', 'LOGIN', '/auth/login', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('3', '获取用户信息', 'GET_INFO', '/auth/getInfo', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('4', '获取菜单', 'GET_MENU', '/auth/getMenu', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('5', '登出', 'LOGOUT', '/auth/logout', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('6', '用户管理', 'USER_MANAGE', '/user', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('38', '基础信息管理', 'BASEINFO_MANAGE', '/baseinfo', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('39', '题目管理', 'BASEINFO_SUBJECT', '/baseinfo/subject', '38', 2, 1, '1', NOW(), '1', NOW(), 1),
('44', '试卷管理', 'PAPER_MANAGE', '/paper', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('50', '考试管理', 'EXAM_MANAGE', '/exam', '0', 1, 1, '1', NOW(), '1', NOW(), 1);

-- 插入默认公司
INSERT INTO `company` (`id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '默认公司', 'DEFAULT', 1, NOW(), NOW(), NOW());

-- 插入默认部门
INSERT INTO `department` (`id`, `company_id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '1', '默认部门', 'DEFAULT', 1, NOW(), NOW(), NOW());

-- 插入默认职位
INSERT INTO `position` (`id`, `company_id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '1', '默认职位', 'DEFAULT', 1, NOW(), NOW(), NOW());

-- 更新管理员用户的公司、部门、职位信息
UPDATE `user` SET `company_id` = '1', `department_id` = '1', `position_id` = '1' WHERE `id` = '1';

-- 插入默认系统参数
INSERT INTO `system_param` (`id`, `param_key`, `param_value`, `description`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', 'system_name', '考试系统', '系统名称', 1, NOW(), NOW(), NOW()),
('2', 'system_version', '1.0.0', '系统版本', 1, NOW(), NOW(), NOW());

-- ============================================================
-- 第三部分：插入业务数据（题目）
-- ============================================================

-- 插入题目类型数据
INSERT INTO subject_type (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, name, attribute, status) VALUES
('1', '1', '1', '1', NOW(), '1', NOW(), 1, '单选题', 'SINGLE', 1),
('2', '1', '1', '1', NOW(), '1', NOW(), 1, '多选题', 'MULTIPLE', 1),
('3', '1', '1', '1', NOW(), '1', NOW(), 1, '判断题', 'JUDGE', 1),
('4', '1', '1', '1', NOW(), '1', NOW(), 1, '简答题', 'ESSAY', 1);

-- 插入题目分类数据
INSERT INTO category (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, name, parent_id, status) VALUES
('1', '1', '1', '1', NOW(), '1', NOW(), 1, '政治', '0', 1),
('2', '1', '1', '1', NOW(), '1', NOW(), 1, '商务', '0', 1),
('3', '1', '1', '1', NOW(), '1', NOW(), 1, '项目管理', '0', 1),
('4', '1', '1', '1', NOW(), '1', NOW(), 1, '地理', '0', 1),
('5', '1', '1', '1', NOW(), '1', NOW(), 1, '生物', '0', 1),
('6', '1', '1', '1', NOW(), '1', NOW(), 1, '采购', '0', 1);

-- 插入难度字典数据
INSERT INTO dictionary (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, category, name, value, status) VALUES
('1', '1', '1', '1', NOW(), '1', NOW(), 1, '难度', '简单', '1', 1),
('2', '1', '1', '1', NOW(), '1', NOW(), 1, '难度', '中等', '2', 1),
('3', '1', '1', '1', NOW(), '1', NOW(), 1, '难度', '困难', '3', 1);

-- 插入题目数据
INSERT INTO subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, subject_type_id, category_id, name, difficulty, status) VALUES
('1001', '1', '1', '1', NOW(), '1', NOW(), 1, '1', '1', '中国特色社会主义进入（ ），这是党的十九大作出的一个重大政治判断', 1, 1),
('1002', '1', '1', '1', NOW(), '1', NOW(), 1, '1', '2', '谈判的当事人包括( )两类人员', 1, 1),
('1003', '1', '1', '1', NOW(), '1', NOW(), 1, '1', '6', '采购项目评审中，下列哪项不属于评审专家应具备的基本条件？', 2, 1),
('1004', '1', '1', '1', NOW(), '1', NOW(), 1, '1', '3', '项目管理的五大过程组不包括以下哪项？', 1, 1),
('1005', '1', '1', '1', NOW(), '1', NOW(), 1, '1', '4', '世界上最大的洲是？', 1, 1),
('1006', '1', '1', '1', NOW(), '1', NOW(), 1, '2', '3', '下列哪些属于项目管理的基本过程组？', 2, 1),
('1007', '1', '1', '1', NOW(), '1', NOW(), 1, '2', '2', '商务谈判的基本原则包括哪些？', 2, 1),
('1008', '1', '1', '1', NOW(), '1', NOW(), 1, '2', '6', '政府采购的主要方式包括哪些？', 2, 1),
('1009', '1', '1', '1', NOW(), '1', NOW(), 1, '3', '4', '判断对错：地球是圆的', 1, 1),
('1010', '1', '1', '1', NOW(), '1', NOW(), 1, '3', '6', '判断对错：采购评审专家可以参与与自己有利害关系的项目评审', 1, 1),
('1011', '1', '1', '1', NOW(), '1', NOW(), 1, '3', '3', '判断对错：项目范围管理包括确保项目做且只做所需的全部工作', 2, 1),
('1012', '1', '1', '1', NOW(), '1', NOW(), 1, '3', '5', '判断对错：植物进行光合作用需要光能', 1, 1),
('1013', '1', '1', '1', NOW(), '1', NOW(), 1, '4', '5', '请简述光合作用的过程', 2, 1),
('1014', '1', '1', '1', NOW(), '1', NOW(), 1, '4', '2', '请简述商务谈判的基本流程', 2, 1),
('1015', '1', '1', '1', NOW(), '1', NOW(), 1, '4', '3', '请简述项目管理的核心要素', 3, 1),
('1016', '1', '1', '1', NOW(), '1', NOW(), 1, '4', '1', '请简述中国特色社会主义进入新时代的重大意义', 3, 1);

-- 插入题目答案数据
INSERT INTO subject_answer (id, subject_id, answer, correct) VALUES
('2001', '1001', 'A.新进展', 0),
('2002', '1001', 'B.新时代', 1),
('2003', '1001', 'C.新征程', 0),
('2004', '1001', 'D.新变革', 0),
('2005', '1002', 'A.台上（一线）和台下', 1),
('2006', '1002', 'B.业务员和老板', 0),
('2007', '1002', 'C.委托人和受托人', 0),
('2008', '1003', 'A. 具有相关专业知识和经验', 0),
('2009', '1003', 'B. 具有良好的职业道德和公正性', 0),
('2010', '1003', 'C. 具有较强的沟通协调能力', 0),
('2011', '1003', 'D. 具有采购从业资格证书', 1),
('2012', '1004', 'A. 启动过程组', 0),
('2013', '1004', 'B. 规划过程组', 0),
('2014', '1004', 'C. 执行过程组', 0),
('2015', '1004', 'D. 监控过程组', 0),
('2016', '1004', 'E. 收尾过程组', 0),
('2017', '1005', 'A. 亚洲', 1),
('2018', '1005', 'B. 非洲', 0),
('2019', '1005', 'C. 欧洲', 0),
('2020', '1005', 'D. 美洲', 0),
('2021', '1006', 'A. 启动过程组', 1),
('2022', '1006', 'B. 规划过程组', 1),
('2023', '1006', 'C. 执行过程组', 1),
('2024', '1006', 'D. 监控过程组', 1),
('2025', '1006', 'E. 收尾过程组', 1),
('2026', '1007', 'A. 平等互利原则', 1),
('2027', '1007', 'B. 求同存异原则', 1),
('2028', '1007', 'C. 诚实守信原则', 1),
('2029', '1007', 'D. 公平竞争原则', 1),
('2030', '1007', 'E. 妥协让步原则', 0),
('2031', '1008', 'A. 公开招标', 1),
('2032', '1008', 'B. 邀请招标', 1),
('2033', '1008', 'C. 竞争性谈判', 1),
('2034', '1008', 'D. 单一来源采购', 1),
('2035', '1008', 'E. 询价采购', 1),
('2036', '1009', '正确', 1),
('2037', '1010', '错误', 1),
('2038', '1011', '正确', 1),
('2039', '1012', '正确', 1);

-- ============================================================
-- 第四部分：插入业务数据（试卷）
-- ============================================================

-- 插入试卷数据
INSERT INTO paper (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, name, difficulty, score, template, status) VALUES
('3001', '1', '1', '1', NOW(), '1', NOW(), 1, '政治理论试卷', 1, 100, 0, 1),
('3002', '1', '1', '1', NOW(), '1', NOW(), 1, '商务管理试卷', 2, 100, 0, 1),
('3003', '1', '1', '1', NOW(), '1', NOW(), 1, '项目管理试卷', 2, 100, 0, 1),
('3004', '1', '1', '1', NOW(), '1', NOW(), 1, '综合知识试卷', 2, 100, 0, 1),
('3005', '1', '1', '1', NOW(), '1', NOW(), 1, '基础理论试卷', 1, 100, 0, 0);

-- 插入试卷题目关联数据
INSERT INTO paper_subject (id, paper_id, subject_id, score) VALUES
('4001', '3001', '1001', 10),
('4002', '3001', '1002', 10),
('4003', '3001', '1004', 15),
('4004', '3001', '1006', 5),
('4005', '3001', '1008', 20),
('4006', '3002', '1002', 10),
('4007', '3002', '1003', 10),
('4008', '3002', '1005', 15),
('4009', '3002', '1007', 5),
('4010', '3002', '1009', 20),
('4011', '3003', '1003', 10),
('4012', '3003', '1004', 15),
('4013', '3003', '1007', 5),
('4014', '3003', '1010', 30),
('4015', '3004', '1001', 10),
('4016', '3004', '1002', 10),
('4017', '3004', '1003', 10),
('4018', '3004', '1004', 15),
('4019', '3004', '1006', 5),
('4020', '3004', '1009', 20),
('4021', '3005', '1001', 10),
('4022', '3005', '1002', 10),
('4023', '3005', '1006', 5),
('4024', '3005', '1008', 30);

COMMIT;

SELECT '数据库初始化完成！' AS result;
