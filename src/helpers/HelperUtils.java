package helpers;

public class HelperUtils {
	
	public static String printSuccess(String data) {
		return "<div class=\"alert alert-success\">" + data + "</div>";
	}
	
	public static String printError(String data) {
		return "<div class=\"alert alert-danger\">" + data + "</div>";
	}
	
	public static String printInfo(String data) {
		return "<div class=\"alert alert-info\">" + data + "</div>";
	}
}
