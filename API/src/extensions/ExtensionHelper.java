package extensions;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ExtensionHelper {
	public static LocalDate dateToLocalDate(Date date){
		LocalDate current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return current;
	} 
}
