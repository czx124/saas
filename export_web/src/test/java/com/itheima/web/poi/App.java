package com.itheima.web.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jackson
 * @date 2020/7/20 19:57
 */
public class App {
    @Test
    public void write() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("test");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("HelloWorld");
        workbook.write(new FileOutputStream("d:\\1.xls"));
        workbook.close();
    }

    @Test
    public void read() throws IOException {
        Workbook workbook = new HSSFWorkbook(new FileInputStream("d:\\1.xls"));
        Sheet test = workbook.getSheet("test");
        Row row = test.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println("获取第一行第一个单元格的值："+cell.getStringCellValue());
        System.out.println("获取有内容的总行数："+test.getPhysicalNumberOfRows());
        System.out.println("获取指定行的有内容的总列数："+row.getPhysicalNumberOfCells());
    }
}
