package br.com.lrfs.utils;

import br.com.lrfs.exceptions.ResourceNotFoundException;

public class Utils {
	
	public static boolean isNumeric(String numberOne) {
		// TODO Auto-generated method stub
		try
		{
			Double.parseDouble(numberOne);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static Double getDouble(String number) {
		String strNumber = number.replaceAll(",",".");
		if(!isNumeric(strNumber)) {
			throw new ResourceNotFoundException("Número inválido");
		}
		return Double.parseDouble(strNumber);
	}
}
