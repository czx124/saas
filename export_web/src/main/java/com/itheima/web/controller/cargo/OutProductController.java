package com.itheima.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.domain.cargo.ContractProductVO;
import com.itheima.service.cargo.ContractProductService;
import com.itheima.web.controller.BaseController;
import com.itheima.web.utils.DownloadUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/21 10:22
 */
@Controller
@RequestMapping("/cargo/contract")
public class OutProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;

    @RequestMapping("/print")
    public String toPrint(){
        return "cargo/print/contract-print";
    }

    //模板表格
    @RequestMapping("/printExcel")
    public void printExcel(String inputDate) throws IOException {
        //获取输入流加载模板文件
        InputStream in = session.getServletContext().getResourceAsStream("/make/xlsprint/tOUTPRODUCT.xlsx");
        //创建工作簿
        Workbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        //创建表标题
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(1);
        String title = inputDate.replace("-0","-").replace("-","年")+"月份出货表";
        cell.setCellValue(title);

        //获取第三行的样式
        row = sheet.getRow(2);
        CellStyle[] cellStyles = new CellStyle[8];
        for (int i=0; i<cellStyles.length; i++){
            cellStyles[i] = row.getCell(i+1).getCellStyle();
        }


        List<ContractProductVO> list = contractProductService.findByShipTime(getLoginCompanyId(), inputDate);
        int index = 2;
        if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++){
                row = sheet.createRow(index++);
                //设置内容
                ContractProductVO contractProductVO = list.get(i);

                cell = row.createCell(1);
                cell.setCellValue(contractProductVO.getCustomName());
                cell.setCellStyle(cellStyles[0]);

                cell = row.createCell(2);
                cell.setCellValue(contractProductVO.getOrderNo());
                cell.setCellStyle(cellStyles[1]);

                cell = row.createCell(3);
                cell.setCellValue(contractProductVO.getProductNo());
                cell.setCellStyle(cellStyles[2]);

                cell = row.createCell(4);
                cell.setCellValue(contractProductVO.getCNumber());
                cell.setCellStyle(cellStyles[3]);

                cell = row.createCell(5);
                cell.setCellValue(contractProductVO.getFactoryName());
                cell.setCellStyle(cellStyles[4]);

                cell = row.createCell(6);
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(contractProductVO.getDeliveryPeriod()));
                cell.setCellStyle(cellStyles[5]);

                cell = row.createCell(7);
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(contractProductVO.getShipTime()));
                cell.setCellStyle(cellStyles[6]);

                cell = row.createCell(8);
                cell.setCellValue(contractProductVO.getTradeTerms());
                cell.setCellStyle(cellStyles[7]);

            }
        }

        //下载
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        new DownloadUtil().download(outputStream,response,"出货表.xlsx");

    }


    //自定义表格
//    @RequestMapping("/printExcel")
//    public void printExcel(String inputDate) throws IOException {
//
//        //创建工作簿
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("出货表");
//        //
//        sheet.setColumnWidth(0,5*256);
//        sheet.setColumnWidth(1,25*256);
//        sheet.setColumnWidth(2,10*256);
//        sheet.setColumnWidth(3,25*256);
//        sheet.setColumnWidth(4,10*256);
//        sheet.setColumnWidth(5,15*256);
//        sheet.setColumnWidth(6,10*256);
//        sheet.setColumnWidth(7,10*256);
//        sheet.setColumnWidth(8,10*256);
//
//        //合并单元格
//        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));
//
//        //创建表标题
//        Row row = sheet.createRow(0);
//        row.setHeightInPoints(36);
//        Cell cell = row.createCell(1);
//        cell.setCellStyle(this.bigTitle(workbook));
//        String title = inputDate.replace("-0","-").replace("-","年")+"月份出货表";
//        cell.setCellValue(title);
//
//        //创建第一行数据
//        row = sheet.createRow(1);
//        row.setHeightInPoints(26);
//        String titles[] = new String[]{"客户", "订单号", "货号", "数量", "工厂",
//                "工厂交期", "船期", "贸易条款"};
//        for (int i = 0; i<titles.length; i++){
//            cell = row.createCell(i + 1);
//            cell.setCellValue(titles[i]);
//            cell.setCellStyle(this.text(workbook));
//        }
//
//        List<ContractProductVO> list = contractProductService.findByShipTime(getLoginCompanyId(), inputDate);
//        int index = 2;
//        if(list!=null && list.size()>0){
//            for (int i = 0; i < list.size(); i++){
//                row = sheet.createRow(index++);
//                //设置内容
//                ContractProductVO contractProductVO = list.get(i);
//
//                cell = row.createCell(1);
//                cell.setCellValue(contractProductVO.getCustomName());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(2);
//                cell.setCellValue(contractProductVO.getOrderNo());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(3);
//                cell.setCellValue(contractProductVO.getProductNo());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(4);
//                cell.setCellValue(contractProductVO.getCNumber());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(5);
//                cell.setCellValue(contractProductVO.getFactoryName());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(6);
//                cell.setCellValue(contractProductVO.getDeliveryPeriod());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(7);
//                cell.setCellValue(contractProductVO.getShipTime());
//                cell.setCellStyle(this.text(workbook));
//
//                cell = row.createCell(8);
//                cell.setCellValue(contractProductVO.getTradeTerms());
//                cell.setCellStyle(this.text(workbook));
//
//            }
//        }
//
//        //下载
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        new DownloadUtil().download(outputStream,response,"出货表.xlsx");
//
//    }

    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBold(true);//字体加粗
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);				//横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        return style;
    }

    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);				//横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        style.setBorderTop(BorderStyle.THIN);						//上细线
        style.setBorderBottom(BorderStyle.THIN);					//下细线
        style.setBorderLeft(BorderStyle.THIN);						//左细线
        style.setBorderRight(BorderStyle.THIN);						//右细线
        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);

        style.setFont(font);

        style.setAlignment(HorizontalAlignment.LEFT);				//横向居左
        style.setVerticalAlignment(VerticalAlignment.CENTER);		//纵向居中
        style.setBorderTop(BorderStyle.THIN);						//上细线
        style.setBorderBottom(BorderStyle.THIN);					//下细线
        style.setBorderLeft(BorderStyle.THIN);						//左细线
        style.setBorderRight(BorderStyle.THIN);						//右细线

        return style;
    }
}
