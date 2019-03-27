package com.lamp.service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IStatisticAnalysisService {
    Map<String,Object> dimmingDataAnalysis(HashMap<String,Object> dataMap);
    Map<String,Object> getDimmingDataCuredChartsById(HashMap<String,Object> paramMap);

    Map<String,Object> getSavePowerCharData(HashMap<String,Object> sqlMap);

    Map<String, Object> energyAnalysisDataForList(HashMap<String,Object> paramMap);
    Map<String,Object> energyAnalysisDataForChart(HashMap<String,Object> paramMap);

    Map<String,Object> repairAnalysisDataForList(HashMap<String,Object> paramMap);
    Map<String,Object> repairAnalyAnalysisDataForChart(HashMap<String,Object> paramMap);

    Map<String,Object> routingAnalysisDataForList(HashMap<String,Object> paramMap);
    Map<String,Object> routingAnalysisDataForChart(HashMap<String,Object> paramMap);

    Map<String, Object> rateAnalysisDataForList(HashMap<String,Object> paramMap);
    Map<String,Object> rateAnalysisDataForChart(HashMap<String,Object> paramMap);

    Map<String,Object> workHourAnalysisDataForList(HashMap<String,Object> paramMap);
    Map<String,Object> workHourAnalysisDataForChart(HashMap<String,Object> paramMap);

    Map<String, Object> getLampStatusReport(HashMap<String,Object> dataMap);
    List<Map<String, Object>> exportGetLampStatusReport(HashMap<String,Object> dataMap);


}
