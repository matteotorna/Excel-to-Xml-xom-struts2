package com.project.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Util {

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		    return dateToConvert.toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDate();
	
	}

	
}
