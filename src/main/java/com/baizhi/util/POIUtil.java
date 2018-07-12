package com.baizhi.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.baizhi.entity.Shop;
import com.baizhi.entity.Theme;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.baizhi.entity.Goods;

public class POIUtil {
	/**
	 * 取得一个表对象
	 * @param 
	 * @return
	 */
	public static Workbook getGoodsXls(String title, List<HashMap<String, Object>> list) throws Exception{
			//创建一个表格
			Workbook workbook = new HSSFWorkbook();
			//创建一个工作簿
			Sheet sheet = workbook.createSheet(title);
			Field[] field = Goods.class.getDeclaredFields();
			List<String> goodsFields = CommonUtil.getGoodsFields();
			Row createRow = sheet.createRow(0);
			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, goodsFields.size());
			sheet.addMergedRegion(cra);
			Cell firstCell = createRow.createCell(0);
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			firstCell.setCellStyle(style);
			firstCell.setCellValue(title);
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 14);
			style.setFont(font);
			Row createRow2 = sheet.createRow(1);
			for (int i=0; i< goodsFields.size(); i++) {
				Cell contentCell = createRow2.createCell(i);
				contentCell.setCellValue(goodsFields.get(i));
			}
			for (int i=0; i< list.size(); i++) {
				Row contentRow = sheet.createRow(i + 2);
				for(int j=0; j<goodsFields.size(); j++){
					Cell contentCell = contentRow.createCell(j);
					if(j==0){
						contentCell.setCellValue(i+1);
						continue;
					}
					String methodName = "get" + field[j].getName().substring(0, 1).toUpperCase() + field[j].getName().substring(1);
					System.out.println(methodName);
					Method getMethod = Goods.class.getDeclaredMethod(methodName, new Class[]{});
					if("getSeller".equals(methodName)){
						getMethod = Shop.class.getDeclaredMethod("getName", new Class[]{});
						Shop shop = (Shop) list.get(i).get("shop");
						if(shop==null){
							shop = new Shop();
						}
						Object invoke = getMethod.invoke(shop, new Object[]{});
						if(invoke==null){
							invoke="";
						}
						contentCell.setCellValue(invoke.toString());
						System.out.println(invoke.toString());
					}else if("getThemeId".equals(methodName)){
						getMethod = Theme.class.getDeclaredMethod("getName", new Class[]{});
						Theme theme = (Theme) list.get(i).get("theme");
						if(theme==null){
							theme = new Theme();
						}
						Object invoke = getMethod.invoke(theme, new Object[]{});
						if(invoke==null){
							invoke="";
						}
						contentCell.setCellValue(invoke.toString());
						System.out.println(invoke.toString());
					}else{
						Object invoke = getMethod.invoke((Goods)list.get(i).get("goods"), new Object[]{});
						if(invoke==null){
							invoke="";
						}
						System.out.println(invoke.toString());
						contentCell.setCellValue(invoke.toString());
					}
				}
			}
			return workbook;
	}

	public static Workbook getUserXls(String title, List<User> list){
		try{
			//创建一个文件
	//		File file = new File(fileName);
			//创建一个表格
			Workbook workbook = new HSSFWorkbook();
			
			//创建一个工作簿
			Sheet sheet = workbook.createSheet(title);
			Field[] field = User.class.getDeclaredFields();
			List<String> userFields = CommonUtil.getUserFields();
			
			Row createRow = sheet.createRow(0);
			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, userFields.size());
			sheet.addMergedRegion(cra);
			Cell firstCell = createRow.createCell(0);
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			firstCell.setCellStyle(style);
			firstCell.setCellValue(title);
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 14);
			style.setFont(font);
			Row createRow2 = sheet.createRow(1);
			for (int i=0; i< userFields.size(); i++) {
				Cell contentCell = createRow2.createCell(i);
				contentCell.setCellValue(userFields.get(i));
			}
			for (int i=0; i< list.size(); i++) {
				Row contentRow = sheet.createRow(i + 2);
				for(int j=0; j<userFields.size(); j++){
					Cell contentCell = contentRow.createCell(j);
					if(j==0){
						contentCell.setCellValue(i+1);
						continue;
					}
					String methodName = "get" + field[j].getName().substring(0, 1).toUpperCase() + field[j].getName().substring(1);
					Method getMethod = User.class.getDeclaredMethod(methodName, new Class[]{});
					Object invoke = getMethod.invoke(list.get(i), new Object[]{});
					if(invoke==null){
						invoke="";
					}
					contentCell.setCellValue(invoke.toString());
				}
			}
			return workbook;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	//参数可以多个输出流
	public static void download(String name,Workbook workbook){
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(name);
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void download(Workbook workbook, OutputStream os){
		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
