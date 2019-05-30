package com.company.view;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    private ExcelExportService excelExportService=null;
    private String fileName=null;

    public ExcelView(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    public ExcelView(ExcelExportService excelExportService, String viewName) {
        this(excelExportService);
        this.setBeanName(viewName);
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        if(excelExportService==null){
            throw new RuntimeException("导出服务接口不能为null");
        }
        if(!StringUtils.isEmpty(fileName)){
            String reqCharset=request.getCharacterEncoding();
            reqCharset=reqCharset==null?"UTF-8":reqCharset;
            fileName=new String(fileName.getBytes(reqCharset),"ISO8859-1");
            response.setHeader("Context-disposition","attachment;filename="+fileName);
        }
        excelExportService.makeExcel(model,workbook);
    }
}
