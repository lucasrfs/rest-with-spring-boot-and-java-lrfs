package br.com.lrfs;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrfs.exceptions.UnsupportedMathOperationException;

@RestController


public class MathController {
	
	private static final String template = "Hello, %s!";
	public final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		String strNumberOne = numberOne.replaceAll(",",".");
		String strNumberTwo = numberTwo.replaceAll(",",".");
		
		if(!isNumeric(strNumberOne) || !isNumeric(strNumberTwo))
		{
			throw new UnsupportedMathOperationException("Número inválido");
		}
		else
		{
			return Double.parseDouble(strNumberOne) + Double.parseDouble(strNumberTwo);
		}
		
	}

	private boolean isNumeric(String numberOne) {
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

}
