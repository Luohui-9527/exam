package exam.demo.modulecommon.constant;

/**
 * API 服务名称和路径常量
 * @author luohui
 * @since 2020-04-24
 */
public class ApiConstant {
    // ==================== 服务名称 ====================
    public static final String SERVICE_NAME = "exam";
    public static final String SERVICE_NAME_PAPER = "paper";
    public static final String SERVICE_NAME_BASE_INFO = "baseinfo";
    public static final String SERVICE_NAME_USER = "user";
    public static final String SERVICE_NAME_EXAM = "exam";

    // ==================== 服务路径前缀 ====================
    public static final String SERVICE_VALUE_PAPER = "/paper";
    public static final String SERVICE_VALUE_BASE_INFO = "/baseinfo";
    public static final String SERVICE_VALUE_USER = "/user";
    public static final String SERVICE_VALUE_EXAM = "/exam";

    // ==================== 通用接口 ====================
    public static final String CHECK_EDITABLE = "/checkEditable";

    // ==================== Paper 模块 ====================
    public static final String PAPER_INFO_PUBLISH_PAPER = "/info/publish/paper";
    public static final String PAPER_INFO_LIST_PAPER = "/info/list/paper";
    public static final String PAPER_INFO_FUZZY_SEARCH = "/info/fuzzy/search";
    public static final String PAPER_INFO_QUERY_DETAIL = "/info/query/detail";
    public static final String PAPER_INFO_QUERY_PUBLISHED_TIME = "/info/query/published/time";
    public static final String PAPER_INFO_QUERY_PAPER_NAME = "/info/query/paper/name";
    public static final String PAPER_INFO_QUERY_PAPER_SCORE = "/info/query/paper/score";

    // ==================== BaseInfo 模块 ====================
    public static final String GET_SUBJECT_AND_ANSWER = "/get/subject/and/answer";
    public static final String GET_SUBJECT_CUSTOMIZED = "/get/subject/customized";
    public static final String GET_SUBJECT_BY_ID = "/get/subject/by/id";
    public static final String GET_BASE_DATAS = "/get/base/datas";
    public static final String GET_BASE_DATA = "/get/base/data";
    public static final String LIST_CATEGORY = "/list/category";
    public static final String LIST_SUBJECT_TYPE = "/list/subject/type";
    public static final String GET_DIC_VAL = "/get/dictionary/val";
    public static final String GET_CATEGORY_VAL = "/get/category/val";
    public static final String GET_SUBJECT_TYPE = "/get/subject/type";

    // ==================== User 模块 ====================
    public static final String GET_USER_NAME = "/get/user/name";
    public static final String GET_COMPANY_NAME = "/get/company/name";
    public static final String GET_USER_NAME_BY_ID = "/get/company/name/by/id";
    public static final String GET_SCORING_OFFICER = "/get/scoring/officer";
    public static final String GET_ID_BY_NAME = "/get/id/by/name";
}
