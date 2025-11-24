package com.briup.product_source.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    Map<String, List<Object>> countNum();

    Map<String, List<Object>> countSales();

    Map<String, Long> countDisease();

    Map<String, Integer> countWeight();
}
