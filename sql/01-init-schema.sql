-- ============================================================
-- 考试系统数据库初始化脚本
-- 执行顺序：1. init.sql
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS exam DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE exam;

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
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
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
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
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
  `difficulty` VARCHAR(50) DEFAULT NULL COMMENT '难度',
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
  `subject_name` VARCHAR(500) DEFAULT NULL COMMENT '题目名称',
  `category_id` VARCHAR(50) DEFAULT NULL COMMENT '题目类别ID',
  `subject_type_id` VARCHAR(50) DEFAULT NULL COMMENT '题目类型ID',
  `difficulty` BIGINT DEFAULT NULL COMMENT '难度',
  `score` DOUBLE DEFAULT NULL COMMENT '分值',
  `order_index` VARCHAR(50) DEFAULT NULL COMMENT '顺序',
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
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
  `field1` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field2` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `field3` VARCHAR(200) DEFAULT NULL COMMENT '预留',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
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
  `my_answer` VARCHAR(500) DEFAULT NULL COMMENT '我的答案',
  `standard_answer` VARCHAR(500) DEFAULT NULL COMMENT '标准答案',
  `score` DOUBLE DEFAULT NULL COMMENT '得分',
  `evaluate` VARCHAR(500) DEFAULT NULL COMMENT '评价',
  `created_by` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
  `updated_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `version` BIGINT DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_exam_record_id` (`exam_record_id`),
  KEY `idx_paper_subject_id` (`paper_subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答案记录表';

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

COMMIT;
