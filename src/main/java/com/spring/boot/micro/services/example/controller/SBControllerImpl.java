package com.spring.boot.micro.services.example.controller;

import static com.spring.boot.micro.services.example.controller.validator.ApiFilterValidator.checkFinalValidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.micro.services.example.commons.Constants;
import com.spring.boot.micro.services.example.controller.validator.ApiFilterValidator;
import com.spring.boot.micro.services.example.dto.SBFilterBean;
import com.spring.boot.micro.services.example.exception.SBException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/services/sample")
@Api("Sample Spring Boot API")
public class SBControllerImpl extends BaseController implements SBController{

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@ApiOperation(position=1, value = "Spring Boot Sample Micro Services Example", 
	      	      notes=  "Example : A,B,C")
	@RequestMapping(method=RequestMethod.GET,produces={Constants.CONTENT_JSON} )
	public ResponseEntity<List<?>> getData(
	        @RequestParam(value="region",required=false) String region,
	        @RequestParam(value="code",required=false) String code)
	        throws SBException {
		log.info("In  SB Controller");

		ApiFilterValidator regionValidator= new ApiFilterValidator(region);
		ApiFilterValidator sbCodeValidator= new ApiFilterValidator(code);
		
		regionValidator.checkLength(4);
		sbCodeValidator.checkLength(2);
		checkFinalValidation(regionValidator,sbCodeValidator);
		
		SBFilterBean sbDTO= new SBFilterBean();
		sbDTO.setRegion(region);
		sbDTO.setCode(code);
		
		return new ResponseEntity<List<?>>(sbService.getData(Constants.SB_API, sbDTO),HttpStatus.OK);

	}
		
}
