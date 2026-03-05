-- 初始化试卷数据
USE exam;

-- 插入试卷表数据
INSERT INTO paper (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version, 
    name, difficulty, start_time, end_time, total_score, score, publish_times, template, status) 
VALUES 
(3001, 1, 1, 1, NOW(), 1, NOW(), 1, '政治理论试卷', 1, NULL, NULL, 100, 0, 0, 0, 1),
(3002, 1, 1, 1, NOW(), 1, NOW(), 1, '商务管理试卷', 2, NULL, NULL, 100, 0, 0, 0, 1),
(3003, 1, 1, 1, NOW(), 1, NOW(), 1, '项目管理试卷', 2, NULL, NULL, 100, 0, 0, 0, 1),
(3004, 1, 1, 1, NOW(), 1, NOW(), 1, '综合知识试卷', 2, NULL, NULL, 100, 0, 0, 0, 1),
(3005, 1, 1, 1, NOW(), 1, NOW(), 1, '基础理论试卷', 1, NULL, NULL, 100, 0, 0, 0, 0);

-- 插入试卷题目关联表数据
-- 政治理论试卷包含的题目
INSERT INTO paper_subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version,
    paper_id, subject_id, subject_type_id, score, status) 
VALUES
(4001, 1, 1, 1, NOW(), 1, NOW(), 1, 3001, 1001, 1, 10, 1),
(4002, 1, 1, 1, NOW(), 1, NOW(), 1, 3001, 1002, 1, 10, 1),
(4003, 1, 1, 1, NOW(), 1, NOW(), 1, 3001, 1004, 2, 15, 1),
(4004, 1, 1, 1, NOW(), 1, NOW(), 1, 3001, 1006, 3, 5, 1),
(4005, 1, 1, 1, NOW(), 1, NOW(), 1, 3001, 1008, 4, 20, 1);

-- 商务管理试卷包含的题目
INSERT INTO paper_subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version,
    paper_id, subject_id, subject_type_id, score, status) 
VALUES
(4006, 1, 1, 1, NOW(), 1, NOW(), 1, 3002, 1002, 1, 10, 1),
(4007, 1, 1, 1, NOW(), 1, NOW(), 1, 3002, 1003, 1, 10, 1),
(4008, 1, 1, 1, NOW(), 1, NOW(), 1, 3002, 1005, 2, 15, 1),
(4009, 1, 1, 1, NOW(), 1, NOW(), 1, 3002, 1007, 3, 5, 1),
(4010, 1, 1, 1, NOW(), 1, NOW(), 1, 3002, 1009, 4, 20, 1);

-- 项目管理试卷包含的题目
INSERT INTO paper_subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version,
    paper_id, subject_id, subject_type_id, score, status) 
VALUES
(4011, 1, 1, 1, NOW(), 1, NOW(), 1, 3003, 1003, 1, 10, 1),
(4012, 1, 1, 1, NOW(), 1, NOW(), 1, 3003, 1004, 2, 15, 1),
(4013, 1, 1, 1, NOW(), 1, NOW(), 1, 3003, 1007, 3, 5, 1),
(4014, 1, 1, 1, NOW(), 1, NOW(), 1, 3003, 1010, 4, 30, 1);

-- 综合知识试卷包含的题目
INSERT INTO paper_subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version,
    paper_id, subject_id, subject_type_id, score, status) 
VALUES
(4015, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1001, 1, 10, 1),
(4016, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1002, 1, 10, 1),
(4017, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1003, 1, 10, 1),
(4018, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1004, 2, 15, 1),
(4019, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1006, 3, 5, 1),
(4020, 1, 1, 1, NOW(), 1, NOW(), 1, 3004, 1009, 4, 20, 1);

-- 基础理论试卷包含的题目
INSERT INTO paper_subject (id, org_id, company_id, created_by, created_time, updated_by, updated_time, version,
    paper_id, subject_id, subject_type_id, score, status) 
VALUES
(4021, 1, 1, 1, NOW(), 1, NOW(), 1, 3005, 1001, 1, 10, 1),
(4022, 1, 1, 1, NOW(), 1, NOW(), 1, 3005, 1002, 1, 10, 1),
(4023, 1, 1, 1, NOW(), 1, NOW(), 1, 3005, 1006, 3, 5, 1),
(4024, 1, 1, 1, NOW(), 1, NOW(), 1, 3005, 1008, 4, 30, 1);

SELECT '试卷数据初始化完成' AS result;
