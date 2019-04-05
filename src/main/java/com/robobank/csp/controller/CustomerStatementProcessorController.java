package com.robobank.csp.controller;

import static com.robobank.csp.consts.SwaggerApiUtils.IMPORT_API_DETAILS;
import static com.robobank.csp.consts.SwaggerApiUtils.REQ_METHOD_POST;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.ValidationResponse;
import com.robobank.csp.enums.FileType;
import com.robobank.csp.exception.ExceptionMessage;
import com.robobank.csp.exception.FileNotSupportedException;
import com.robobank.csp.service.CSPService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Customer Statement Processor Controller is to take an input in the form of
 * CSV or XML format. Upon supplied filed file do validation and prepare the
 * validation result.
 * 
 * @author Karthikeyan N
 *
 */
@RestController
@RequestMapping(path = CustomerStatementProcessorController.PATH)
@Api(value = CustomerStatementProcessorController.PATH)
public class CustomerStatementProcessorController {

	public static final String PATH = "/files";

	private CSPService cspService;

	public CustomerStatementProcessorController(CSPService cspService) {
		this.cspService = cspService;
	}

	/**
	 * Rabobank receives monthly deliveries of customer statement records. This
	 * information is delivered in two formats, CSV and XML. These records need to
	 * be validated.
	 * 
	 * @param file
	 * @return List of ValidationResponse
	 */
	@ApiOperation(value = IMPORT_API_DETAILS, httpMethod = REQ_METHOD_POST)
	@PostMapping(consumes = { "multipart/form-data" })
	public List<ValidationResponse> processData(@RequestParam("file") MultipartFile file) {

		if (!FileType.isCorrectFileType(file.getOriginalFilename()))
			throw new FileNotSupportedException(
					String.format(ExceptionMessage.FILE_VALIDATION_MESSAGE, file.getOriginalFilename()));

		return cspService.readAndValidate(file);

	}

}
