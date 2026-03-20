-- ============================================================
-- 基础数据脚本 - 角色、资源、用户初始数据
-- 执行顺序：2. 02-init-basic-data.sql
-- ============================================================

USE exam;

-- 插入管理员用户
INSERT INTO `user` (`id`, `password`, `name`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 1, NOW(), NOW(), 1);

-- 插入默认角色
INSERT INTO `role` (`id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '系统管理员', 'SYS_ADMIN', 1, NOW(), NOW(), 1),
('2', '公司管理员', 'COMPANY_ADMIN', 1, NOW(), NOW(), 1),
('3', '老师', 'TEACHER', 1, NOW(), NOW(), 1),
('4', '学生', 'STUDENT', 1, NOW(), NOW(), 1);

-- 插入用户角色关联
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `created_time`, `updated_time`, `version`) VALUES
('1', '1', '1', NOW(), NOW(), 1);

-- 插入默认资源（所有接口即权限）
INSERT INTO resource (id, name, code, url, parent_id, level, status, created_by, created_time, updated_by, updated_time, version) VALUES
-- 认证模块（parent_id=0）
('1', '认证管理', 'AUTH_MANAGE', '/auth', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('2', '登录', 'LOGIN', '/auth/login', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('3', '获取用户信息', 'GET_INFO', '/auth/getInfo', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('4', '获取菜单', 'GET_MENU', '/auth/getMenu', '1', 2, 1, '1', NOW(), '1', NOW(), 1),
('5', '登出', 'LOGOUT', '/auth/logout', '1', 2, 1, '1', NOW(), '1', NOW(), 1),

-- 用户管理模块（parent_id=0）
('6', '用户管理', 'USER_MANAGE', '/user', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('7', '用户管理', 'USER_USER', '/user/user', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('8', '获取用户列表', 'USER_LIST', '/user/user/queryUser', '7', 3, 1, '1', NOW(), '1', NOW(), 1),
('9', '新增用户', 'USER_ADD', '/user/user/add', '7', 3, 1, '1', NOW(), '1', NOW(), 1),
('10', '修改用户', 'USER_UPDATE', '/user/user/update', '7', 3, 1, '1', NOW(), '1', NOW(), 1),
('11', '删除用户', 'USER_DELETE', '/user/user/delete', '7', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 角色管理
('12', '角色管理', 'USER_ROLE', '/user/role', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('13', '获取角色列表', 'ROLE_LIST', '/user/role/queryRole', '12', 3, 1, '1', NOW(), '1', NOW(), 1),
('14', '新增角色', 'ROLE_ADD', '/user/role/add', '12', 3, 1, '1', NOW(), '1', NOW(), 1),
('15', '修改角色', 'ROLE_UPDATE', '/user/role/update', '12', 3, 1, '1', NOW(), '1', NOW(), 1),
('16', '删除角色', 'ROLE_DELETE', '/user/role/delete', '12', 3, 1, '1', NOW(), '1', NOW(), 1),
('17', '分配角色权限', 'ROLE_ASSIGN_PERM', '/user/role/assignPermission', '12', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 资源管理
('18', '资源管理', 'USER_RESOURCE', '/user/resource', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('19', '获取资源列表', 'RESOURCE_LIST', '/user/resource/queryResource', '18', 3, 1, '1', NOW(), '1', NOW(), 1),
('20', '新增资源', 'RESOURCE_ADD', '/user/resource/add', '18', 3, 1, '1', NOW(), '1', NOW(), 1),
('21', '修改资源', 'RESOURCE_UPDATE', '/user/resource/update', '18', 3, 1, '1', NOW(), '1', NOW(), 1),
('22', '删除资源', 'RESOURCE_DELETE', '/user/resource/delete', '18', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 公司管理
('23', '公司管理', 'USER_COMPANY', '/user/company', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('24', '获取公司列表', 'COMPANY_LIST', '/user/company/queryCompany', '23', 3, 1, '1', NOW(), '1', NOW(), 1),
('25', '新增公司', 'COMPANY_ADD', '/user/company/add', '23', 3, 1, '1', NOW(), '1', NOW(), 1),
('26', '修改公司', 'COMPANY_UPDATE', '/user/company/update', '23', 3, 1, '1', NOW(), '1', NOW(), 1),
('27', '删除公司', 'COMPANY_DELETE', '/user/company/delete', '23', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 部门管理
('28', '部门管理', 'USER_DEPARTMENT', '/user/department', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('29', '获取部门列表', 'DEPARTMENT_LIST', '/user/department/queryDepartment', '28', 3, 1, '1', NOW(), '1', NOW(), 1),
('30', '新增部门', 'DEPARTMENT_ADD', '/user/department/add', '28', 3, 1, '1', NOW(), '1', NOW(), 1),
('31', '修改部门', 'DEPARTMENT_UPDATE', '/user/department/update', '28', 3, 1, '1', NOW(), '1', NOW(), 1),
('32', '删除部门', 'DEPARTMENT_DELETE', '/user/department/delete', '28', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 职位管理
('33', '职位管理', 'USER_POSITION', '/user/position', '6', 2, 1, '1', NOW(), '1', NOW(), 1),
('34', '获取职位列表', 'POSITION_LIST', '/user/position/queryPosition', '33', 3, 1, '1', NOW(), '1', NOW(), 1),
('35', '新增职位', 'POSITION_ADD', '/user/position/add', '33', 3, 1, '1', NOW(), '1', NOW(), 1),
('36', '修改职位', 'POSITION_UPDATE', '/user/position/update', '33', 3, 1, '1', NOW(), '1', NOW(), 1),
('37', '删除职位', 'POSITION_DELETE', '/user/position/delete', '33', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 基础信息模块
('38', '基础信息管理', 'BASEINFO_MANAGE', '/baseinfo', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('39', '题目管理', 'BASEINFO_SUBJECT', '/baseinfo/subject', '38', 2, 1, '1', NOW(), '1', NOW(), 1),
('40', '获取题目列表', 'SUBJECT_LIST', '/baseinfo/subject/query', '39', 3, 1, '1', NOW(), '1', NOW(), 1),
('41', '新增题目', 'SUBJECT_ADD', '/baseinfo/subject/save', '39', 3, 1, '1', NOW(), '1', NOW(), 1),
('42', '修改题目', 'SUBJECT_UPDATE', '/baseinfo/subject/update', '39', 3, 1, '1', NOW(), '1', NOW(), 1),
('43', '删除题目', 'SUBJECT_DELETE', '/baseinfo/subject/delete', '39', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 试卷模块
('44', '试卷管理', 'PAPER_MANAGE', '/paper', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('45', '试卷维护', 'PAPER_MAINTAIN', '/paper/maintain', '44', 2, 1, '1', NOW(), '1', NOW(), 1),
('46', '获取试卷列表', 'PAPER_LIST', '/paper/maintain/queryPaper', '45', 3, 1, '1', NOW(), '1', NOW(), 1),
('47', '新增试卷', 'PAPER_ADD', '/paper/maintain/add', '45', 3, 1, '1', NOW(), '1', NOW(), 1),
('48', '修改试卷', 'PAPER_UPDATE', '/paper/maintain/update', '45', 3, 1, '1', NOW(), '1', NOW(), 1),
('49', '删除试卷', 'PAPER_DELETE', '/paper/maintain/delete', '45', 3, 1, '1', NOW(), '1', NOW(), 1),

-- 考试模块
('50', '考试管理', 'EXAM_MANAGE', '/exam', '0', 1, 1, '1', NOW(), '1', NOW(), 1),
('51', '考试发布记录', 'EXAM_PUBLISH_RECORD', '/exam/examPublishRecord', '50', 2, 1, '1', NOW(), '1', NOW(), 1),
('52', '获取考试列表', 'EXAM_LIST', '/exam/examPublishRecord/queryExamPublishRecord', '51', 3, 1, '1', NOW(), '1', NOW(), 1),
('53', '发布考试', 'EXAM_PUBLISH', '/exam/examPublishRecord/publish', '51', 3, 1, '1', NOW(), '1', NOW(), 1),
('54', '考试记录', 'EXAM_RECORD', '/exam/examRecord', '50', 2, 1, '1', NOW(), '1', NOW(), 1),
('55', '获取考试记录', 'EXAM_RECORD_QUERY', '/exam/examRecord/queryExamRecord', '54', 3, 1, '1', NOW(), '1', NOW(), 1),
('56', '批改试卷', 'EXAM_GRADE', '/exam/grade/grade', '50', 3, 1, '1', NOW(), '1', NOW(), 1);

-- 插入角色资源关联数据 - 系统管理员：所有权限
INSERT INTO role_resource (id, role_id, resource_id, created_time, updated_time, version) VALUES
('1', '1', '1', NOW(), NOW(), 1),
('2', '1', '2', NOW(), NOW(), 1),
('3', '1', '3', NOW(), NOW(), 1),
('4', '1', '4', NOW(), NOW(), 1),
('5', '1', '5', NOW(), NOW(), 1),
('6', '1', '6', NOW(), NOW(), 1),
('7', '1', '7', NOW(), NOW(), 1),
('8', '1', '8', NOW(), NOW(), 1),
('9', '1', '9', NOW(), NOW(), 1),
('10', '1', '10', NOW(), NOW(), 1),
('11', '1', '11', NOW(), NOW(), 1),
('12', '1', '12', NOW(), NOW(), 1),
('13', '1', '13', NOW(), NOW(), 1),
('14', '1', '14', NOW(), NOW(), 1),
('15', '1', '15', NOW(), NOW(), 1),
('16', '1', '16', NOW(), NOW(), 1),
('17', '1', '17', NOW(), NOW(), 1),
('18', '1', '18', NOW(), NOW(), 1),
('19', '1', '19', NOW(), NOW(), 1),
('20', '1', '20', NOW(), NOW(), 1),
('21', '1', '21', NOW(), NOW(), 1),
('22', '1', '22', NOW(), NOW(), 1),
('23', '1', '23', NOW(), NOW(), 1),
('24', '1', '24', NOW(), NOW(), 1),
('25', '1', '25', NOW(), NOW(), 1),
('26', '1', '26', NOW(), NOW(), 1),
('27', '1', '27', NOW(), NOW(), 1),
('28', '1', '28', NOW(), NOW(), 1),
('29', '1', '29', NOW(), NOW(), 1),
('30', '1', '30', NOW(), NOW(), 1),
('31', '1', '31', NOW(), NOW(), 1),
('32', '1', '32', NOW(), NOW(), 1),
('33', '1', '33', NOW(), NOW(), 1),
('34', '1', '34', NOW(), NOW(), 1),
('35', '1', '35', NOW(), NOW(), 1),
('36', '1', '36', NOW(), NOW(), 1),
('37', '1', '37', NOW(), NOW(), 1),
('38', '1', '38', NOW(), NOW(), 1),
('39', '1', '39', NOW(), NOW(), 1),
('40', '1', '40', NOW(), NOW(), 1),
('41', '1', '41', NOW(), NOW(), 1),
('42', '1', '42', NOW(), NOW(), 1),
('43', '1', '43', NOW(), NOW(), 1),
('44', '1', '44', NOW(), NOW(), 1),
('45', '1', '45', NOW(), NOW(), 1),
('46', '1', '46', NOW(), NOW(), 1),
('47', '1', '47', NOW(), NOW(), 1),
('48', '1', '48', NOW(), NOW(), 1),
('49', '1', '49', NOW(), NOW(), 1),
('50', '1', '50', NOW(), NOW(), 1),
('51', '1', '51', NOW(), NOW(), 1),
('52', '1', '52', NOW(), NOW(), 1),
('53', '1', '53', NOW(), NOW(), 1),
('54', '1', '54', NOW(), NOW(), 1),
('55', '1', '55', NOW(), NOW(), 1),
('56', '1', '56', NOW(), NOW(), 1);

-- 插入默认公司
INSERT INTO `company` (`id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '默认公司', 'DEFAULT', 1, NOW(), NOW(), 1);

-- 插入默认部门
INSERT INTO `department` (`id`, `company_id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '1', '默认部门', 'DEFAULT', 1, NOW(), NOW(), 1);

-- 插入默认职位
INSERT INTO `position` (`id`, `company_id`, `name`, `code`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', '1', '默认职位', 'DEFAULT', 1, NOW(), NOW(), 1);

-- 更新管理员用户的公司、部门、职位信息
UPDATE `user` SET `company_id` = '1', `department_id` = '1', `position_id` = '1' WHERE `id` = '1';

-- 插入默认系统参数
INSERT INTO `system_param` (`id`, `param_key`, `param_value`, `description`, `status`, `created_time`, `updated_time`, `version`) VALUES
('1', 'system_name', '考试系统', '系统名称', 1, NOW(), NOW(), 1),
('2', 'system_version', '1.0.0', '系统版本', 1, NOW(), NOW(), 1);

COMMIT;
