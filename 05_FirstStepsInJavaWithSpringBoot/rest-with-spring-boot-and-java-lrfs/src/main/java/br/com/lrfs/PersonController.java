package br.com.lrfs;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrfs.exceptions.UnsupportedMathOperationException;
import br.com.lrfs.utils.Operations;
import br.com.lrfs.utils.Utils;

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
		
		return Operations.sum(numberOne, numberTwo);
				
	}
	
	@RequestMapping(value = "/subtract/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double subtract(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		return Operations.subtract(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double divide(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		return Operations.divide(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double multiply(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		return Operations.multiply(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double mean(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			) throws Exception{
		
		return Operations.mean(numberOne, numberTwo);
		
	}
	
	@RequestMapping(value = "/squareRoot/{numberOne}",
			method = RequestMethod.GET)
	public Double squareRoot(
			@PathVariable(value="numberOne") String numberOne
			) throws Exception{
		
		return Operations.squareRoot(numberOne);
		
	}

	

}
