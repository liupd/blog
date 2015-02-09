package org.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat  df =new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
	
	/**
	 * date转字符串
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date){
		return df.format(date);
	}
}
