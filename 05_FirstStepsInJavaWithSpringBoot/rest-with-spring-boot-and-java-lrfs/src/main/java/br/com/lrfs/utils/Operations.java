package br.com.lrfs.utils;

import br.com.lrfs.exceptions.ResourceNotFoundException;

public class Operations {
	
	public static Double sum(String numberOne, String numberTwo) {
		return Utils.getDouble(numberOne.replaceAll(",",".")) + Utils.getDouble(numberTwo.replaceAll(",","."));
	}
	
	public static Double subtract(String numberOne, String numberTwo) {
		return Utils.getDouble(numberOne.replaceAll(",",".")) - Utils.getDouble(numberTwo.replaceAll(",","."));
	}
	
	public static Double divide(String numberOne, String numberTwo) {
		if(Utils.getDouble(numberTwo) == 0) {
			throw new ResourceNotFoundException("Não é possivel dividir por zero");
		}
		return Utils.getDouble(numberOne.replaceAll(",",".")) / Utils.getDouble(numberTwo.replaceAll(",","."));
	}
	
	public static Double multiply(String numberOne, String numberTwo) {
		return Utils.getDouble(numberOne.replaceAll(",",".")) * Utils.getDouble(numberTwo.replaceAll(",","."));
	}
	
	public static Double mean(String numberOne, String numberTwo) {
		return (Utils.getDouble(numberOne.replaceAll(",",".")) + Utils.getDouble(numberTwo.replaceAll(",","."))) / 2;
	}
	
	public static Double squareRoot(String numberOne) {
		return Math.sqrt(Utils.getDouble(numberOne.replaceAll(",",".")));
	}

}
