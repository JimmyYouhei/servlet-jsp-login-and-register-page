package com.jimmyyouhei.assignment3.reusable;

public interface StringCommand {
	
	public static String convertStringArrayToText (String[] stringArrayToConvert){
		
		StringBuilder builder = new StringBuilder();
		
		for(String aStringFromArray : stringArrayToConvert) {
			
			builder.append(aStringFromArray);
			builder.append(" , ");
		}
		
		// delete the last " , "
		builder.setLength(builder.length()-3);
		
		return builder.toString();
		
	}

}
