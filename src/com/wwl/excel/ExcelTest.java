package com.wwl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {
	/**
	 * http://poi.apache.org/download.html下载路径  3.17了，全部都有的，有些不需要导包
	 * 需要导包 poi-3.17.jar、xmlbeans-2.6.0.jar、poi-ooxml-schemas-3.17.jar、poi-ooxml-3.17.jar、commons-collections4-4.1.jar
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//导出操作
		URL url=ExcelTest.class.getResource("a.xlsx"); 
		String s=url.getFile();
		File f = new File(s);
		
		InputStream is = new FileInputStream(f);
		// 第一步，创建一个Workbook，对应一个Excel文件
		Workbook wb = new XSSFWorkbook(is);
		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet,Shell是一个VB函数，它的作用是运行程序，语法是Shell
		Sheet sheet= wb.getSheetAt(0);
;		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		Row row = sheet.createRow(0);
		//第四步 在第一行第一列创建单元格cell
		Cell cell=row.createCell(0);
		//第五步  为单元格设置值
		cell.setCellValue("十多个");
		//第六步  创建输出流
		OutputStream out = new FileOutputStream(f);
		//第七步  通过第一步的对象把流输出
		wb.write(out);
		out.flush();
		out.close();
		is.close();
		wb.close();
		//注意 值是在bin中的a.xlsx中
		
		//获取excel内容操作
		//第一步获取到excel位置
		 InputStream is2=null;
	       Workbook wb2 = new XSSFWorkbook(is);
	     try{
	  			Sheet sheet2= wb.getSheetAt(0);
	  			//获取第二行
	  			Row row2 = sheet.getRow(1);
	  			//第二行第28的内容
	  			Cell cell2 = row.getCell(27);
	  			if(cell2 == null){
	  				//return "" ;
	  			}
	  			String cellValue = "";
	  			//DecimalFormat df = new DecimalFormat("0");
	  			switch (cell.getCellType()) {
	  			case XSSFCell.CELL_TYPE_STRING:
	  				cellValue = cell.getRichStringCellValue().getString().trim();
	  				break;
	  			case XSSFCell.CELL_TYPE_NUMERIC:
	  				//cellValue = df.format(cell.getNumericCellValue()).toString();
	  				break;
	  			case XSSFCell.CELL_TYPE_BOOLEAN:
	  				cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
	  				break;
	  			case XSSFCell.CELL_TYPE_FORMULA:
	  				cellValue = cell.getCellFormula();
	  				break;
	  			default:
	  				cellValue = "";
	  			}
	     }finally {
	    	 
	     }
		
	}

}
