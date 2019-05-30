package com.company.view;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

public interface ExcelExportService {

     void makeExcel(Map<String,Object> model, Workbook workbook);
}
