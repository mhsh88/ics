package constants.assessments;

import com.payAm.core.constant.BaseConstants;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:07
 */
public interface DatabaseQuestionConstants extends BaseConstants {

    String ENTITY = "databaseQuestion";

    String METRIC = "metric";
    String METRIC_PRIORITY = "metricPriority";
    String METRIC_WEIGHT = "metricWeight";
    String QUESTION_TEXT = "questionText";
    String ERROR_QUESTION_TEXT_MAX_LENGTH = "error.databaseQuestion.questionText.maxLength";
    String STANDARD = "standard";
    String SUB_METRIC = "subMetric";
    String ERROR_SUB_METRIC_MAX_LENGTH = "error.databaseQuestion.subMetric.maxLength";
    String SUB_METRIC_PRIORITY = "subMetricPriority";
    String SUB_METRIC_WEIGHT = "subMetricWeight";

}