package com.project.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.model.ExcelRecord;

public class ExcelReader {
	

	private static final Logger logger = LogManager.getLogger(ExcelReader.class);
	final DateTimeFormatter x = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public List<ExcelRecord> readBooksFromExcelFile(String excelFilePath) throws IOException, InvalidFormatException {
        try (InputStream inputStream = new FileInputStream(excelFilePath)) {
            return readBooksFromExcelFile(inputStream);
        }
    }
	
	public List<ExcelRecord> readBooksFromExcelFile(InputStream inputStream) throws IOException, InvalidFormatException { // nella parentesi inserisco String excelFilePath per versione tool console 

		List<ExcelRecord> fattoriRipartizione = new ArrayList<ExcelRecord>(); 
		//FileInputStream fis = new FileInputStream(excelFilePath);  // per versione tool solo console 

		XSSFWorkbook wb = new XSSFWorkbook(inputStream); // inserisco fis dentro la parentesi per la versione solo console
		XSSFSheet firstSheet = wb.getSheetAt(0);

		final int rowNum = firstSheet.getLastRowNum();

		for (int i = 1; i < rowNum+1; i++) {

			XSSFRow row = firstSheet.getRow(i);

			ExcelRecord aRow = new ExcelRecord();

			for (int j = 0; j < 101; j++) {

				XSSFCell nextCell = row.getCell(j);
				logger.info("debug" + j);

				switch (j) {
				
				case 0:
				    try {
				        int Mesexlsx = (int) nextCell.getNumericCellValue();
				        aRow.setMeseRif(Mesexlsx);
				    } catch (Exception e) {
				        logger.info("Error parsing period: " + j);
				        e.printStackTrace();
				    }
				    break;

				case 1:
					try {
				        int Annoxlsx = (int) nextCell.getNumericCellValue();
				        aRow.setAnnoRif(Annoxlsx);
				    } catch (Exception e) {
				        logger.info("Error parsing period: " + j);
				        e.printStackTrace();
				    }
				    break;

				case 2:
					aRow.setServizio(nextCell.getStringCellValue());
					break;

				case 3:
					aRow.setCodice(nextCell.getStringCellValue());
					break;

				case 4:
					try {
				        int Idxlsx = (int) nextCell.getNumericCellValue();
				        aRow.setFirstID(Idxlsx);
				    } catch (Exception e) {
				        logger.info("Error parsing period: " + j);
				        e.printStackTrace();
				    }
				    break;
				
				case 5:
					aRow.setH1_Q1(nextCell.getNumericCellValue());
					break;
				
				case 6:
					aRow.setH1_Q2(nextCell.getNumericCellValue());
					break;
				
				case 7:
					aRow.setH1_Q3(nextCell.getNumericCellValue());
					break;
				
				case 8:
					aRow.setH1_Q4(nextCell.getNumericCellValue());
					break;

				case 9:
					aRow.setH2_Q1(nextCell.getNumericCellValue());
					break;
				
				case 10:
					aRow.setH2_Q2(nextCell.getNumericCellValue());
					break;
				
				case 11:
					aRow.setH2_Q3(nextCell.getNumericCellValue());
					break;
				
				case 12:
					aRow.setH2_Q4(nextCell.getNumericCellValue());
					break;
				
				case 13:
					aRow.setH3_Q1(nextCell.getNumericCellValue());
					break;
				
				case 14:
					aRow.setH3_Q2(nextCell.getNumericCellValue());
					break;
				
				case 15:
					aRow.setH3_Q3(nextCell.getNumericCellValue());
					break;
					
				case 16:
					aRow.setH3_Q4(nextCell.getNumericCellValue());
					break;
				
				case 17:
					aRow.setH4_Q1(nextCell.getNumericCellValue());
					break;
				
				case 18:
					aRow.setH4_Q2(nextCell.getNumericCellValue());
					break;
				
				case 19:
					aRow.setH4_Q3(nextCell.getNumericCellValue());
					break;
					
				case 20:
					aRow.setH4_Q4(nextCell.getNumericCellValue());
					break;
				
				case 21:
					aRow.setH5_Q1(nextCell.getNumericCellValue());
					break;
				
				case 22:
					aRow.setH5_Q2(nextCell.getNumericCellValue());
					break;
				
				case 23:
					aRow.setH5_Q3(nextCell.getNumericCellValue());
					break;
					
				case 24:
					aRow.setH5_Q4(nextCell.getNumericCellValue());
					break;
					
				case 25:
					aRow.setH6_Q1(nextCell.getNumericCellValue());
					break;
				
				case 26:
					aRow.setH6_Q2(nextCell.getNumericCellValue());
					break;
				
				case 27:
					aRow.setH6_Q3(nextCell.getNumericCellValue());
					break;
					
				case 28:
					aRow.setH6_Q4(nextCell.getNumericCellValue());
					break;
					
				case 29:
					aRow.setH7_Q1(nextCell.getNumericCellValue());
					break;
				
				case 30:
					aRow.setH7_Q2(nextCell.getNumericCellValue());
					break;
				
				case 31:
					aRow.setH7_Q3(nextCell.getNumericCellValue());
					break;
					
				case 32:
					aRow.setH7_Q4(nextCell.getNumericCellValue());
					break;
				
				case 33:
					aRow.setH8_Q1(nextCell.getNumericCellValue());
					break;
				
				case 34:
					aRow.setH8_Q2(nextCell.getNumericCellValue());
					break;
				
				case 35:
					aRow.setH8_Q3(nextCell.getNumericCellValue());
					break;
					
				case 36:
					aRow.setH8_Q4(nextCell.getNumericCellValue());
					break;
					
				case 37:
					aRow.setH9_Q1(nextCell.getNumericCellValue());
					break;
				
				case 38:
					aRow.setH9_Q2(nextCell.getNumericCellValue());
					break;
				
				case 39:
					aRow.setH9_Q3(nextCell.getNumericCellValue());
					break;
					
				case 40:
					aRow.setH9_Q4(nextCell.getNumericCellValue());
					break;
				
				case 41:
					aRow.setH10_Q1(nextCell.getNumericCellValue());
					break;
				
				case 42:
					aRow.setH10_Q2(nextCell.getNumericCellValue());
					break;
				
				case 43:
					aRow.setH10_Q3(nextCell.getNumericCellValue());
					break;
					
				case 44:
					aRow.setH10_Q4(nextCell.getNumericCellValue());
					break;
					
				case 45:
					aRow.setH11_Q1(nextCell.getNumericCellValue());
					break;
				
				case 46:
					aRow.setH11_Q2(nextCell.getNumericCellValue());
					break;
				
				case 47:
					aRow.setH11_Q3(nextCell.getNumericCellValue());
					break;
					
				case 48:
					aRow.setH11_Q4(nextCell.getNumericCellValue());
					break;
				
				case 49:
					aRow.setH12_Q1(nextCell.getNumericCellValue());
					break;
				
				case 50:
					aRow.setH12_Q2(nextCell.getNumericCellValue());
					break;
				
				case 51:
					aRow.setH12_Q3(nextCell.getNumericCellValue());
					break;
					
				case 52:
					aRow.setH12_Q4(nextCell.getNumericCellValue());
					break;
				
				case 53:
					aRow.setH13_Q1(nextCell.getNumericCellValue());
					break;
				
				case 54:
					aRow.setH13_Q2(nextCell.getNumericCellValue());
					break;
				
				case 55:
					aRow.setH13_Q3(nextCell.getNumericCellValue());
					break;
					
				case 56:
					aRow.setH13_Q4(nextCell.getNumericCellValue());
					break;
				
				case 57:
					aRow.setH14_Q1(nextCell.getNumericCellValue());
					break;
				
				case 58:
					aRow.setH14_Q2(nextCell.getNumericCellValue());
					break;
				
				case 59:
					aRow.setH14_Q3(nextCell.getNumericCellValue());
					break;
					
				case 60:
					aRow.setH14_Q4(nextCell.getNumericCellValue());
					break;
					
				case 61:
					aRow.setH15_Q1(nextCell.getNumericCellValue());
					break;
				
				case 62:
					aRow.setH15_Q2(nextCell.getNumericCellValue());
					break;
				
				case 63:
					aRow.setH15_Q3(nextCell.getNumericCellValue());
					break;
					
				case 64:
					aRow.setH15_Q4(nextCell.getNumericCellValue());
					break;
				
				case 65:
					aRow.setH16_Q1(nextCell.getNumericCellValue());
					break;
				
				case 66:
					aRow.setH16_Q2(nextCell.getNumericCellValue());
					break;
				
				case 67:
					aRow.setH16_Q3(nextCell.getNumericCellValue());
					break;
					
				case 68:
					aRow.setH16_Q4(nextCell.getNumericCellValue());
					break;
				
				case 70:
					aRow.setH17_Q1(nextCell.getNumericCellValue());
					break;
				
				case 71:
					aRow.setH17_Q2(nextCell.getNumericCellValue());
					break;
				
				case 72:
					aRow.setH17_Q3(nextCell.getNumericCellValue());
					break;
					
				case 73:
					aRow.setH17_Q4(nextCell.getNumericCellValue());
					break;
				
				case 74:
					aRow.setH18_Q1(nextCell.getNumericCellValue());
					break;
				
				case 75:
					aRow.setH18_Q2(nextCell.getNumericCellValue());
					break;
				
				case 76:
					aRow.setH18_Q3(nextCell.getNumericCellValue());
					break;
					
				case 77:
					aRow.setH18_Q4(nextCell.getNumericCellValue());
					break;
					
				case 78:
					aRow.setH19_Q1(nextCell.getNumericCellValue());
					break;
				
				case 79:
					aRow.setH19_Q2(nextCell.getNumericCellValue());
					break;
				
				case 80:
					aRow.setH19_Q3(nextCell.getNumericCellValue());
					break;
					
				case 81:
					aRow.setH19_Q4(nextCell.getNumericCellValue());
					break;
				
				case 82:
					aRow.setH20_Q1(nextCell.getNumericCellValue());
					break;
				
				case 83:
					aRow.setH20_Q2(nextCell.getNumericCellValue());
					break;
				
				case 84:
					aRow.setH20_Q3(nextCell.getNumericCellValue());
					break;
					
				case 85:
					aRow.setH20_Q4(nextCell.getNumericCellValue());
					break;
					
				case 86:
					aRow.setH21_Q1(nextCell.getNumericCellValue());
					break;
				
				case 87:
					aRow.setH21_Q2(nextCell.getNumericCellValue());
					break;
				
				case 88:
					aRow.setH21_Q3(nextCell.getNumericCellValue());
					break;
					
				case 89:
					aRow.setH21_Q4(nextCell.getNumericCellValue());
					break;
				
				case 90:
					aRow.setH22_Q1(nextCell.getNumericCellValue());
					break;
				
				case 91:
					aRow.setH22_Q2(nextCell.getNumericCellValue());
					break;
				
				case 92:
					aRow.setH22_Q3(nextCell.getNumericCellValue());
					break;
					
				case 93:
					aRow.setH22_Q4(nextCell.getNumericCellValue());
					break;
				
				case 94:
					aRow.setH23_Q1(nextCell.getNumericCellValue());
					break;
				
				case 95:
					aRow.setH23_Q2(nextCell.getNumericCellValue());
					break;
				
				case 96:
					aRow.setH23_Q3(nextCell.getNumericCellValue());
					break;
					
				case 97:
					aRow.setH23_Q4(nextCell.getNumericCellValue());
					break;
					
				case 98:
					aRow.setH24_Q1(nextCell.getNumericCellValue());
					break;
				
				case 99:
					aRow.setH24_Q2(nextCell.getNumericCellValue());
					break;
				
				case 100:
					aRow.setH24_Q3(nextCell.getNumericCellValue());
					break;
					
				case 101:
				    if (nextCell != null) {
				        aRow.setH24_Q4(nextCell.getNumericCellValue());
				    } else {
				        logger.info("reading error");
				    }
				    break;
					
				default:
				    break;
				}
			}
			logger.info("Adding: " + aRow);
			fattoriRipartizione.add(aRow);
		}
		wb.close();
		inputStream.close(); // fis.close per versione console

		return fattoriRipartizione;
	}
	
	
}
