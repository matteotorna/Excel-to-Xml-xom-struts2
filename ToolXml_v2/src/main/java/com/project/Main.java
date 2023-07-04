package com.project;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.converter.XmlWriter;
import com.project.model.ExcelRecord;
import com.project.util.ExcelReader;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws Exception {

		logger.info("Start!");
		String excelFilePath = "C://Users//Tornaboni-Dk//Documents//Fattori_di_Ripartizione.xlsx";

		logger.info("Reading xlsx file..");
		ExcelReader reader = new ExcelReader();
		List<ExcelRecord> records = reader.readBooksFromExcelFile(excelFilePath); 
				
		
		logger.info("Writing xml file..");       
		XmlWriter writer = new XmlWriter();
		writer.writeXml(records, "C://Users//Tornaboni-Dk//Documents//output.xml");
		logger.info(records);

	}
	}


