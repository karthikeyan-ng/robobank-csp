/**
 * 
 */
package com.robobank.csp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.robobank.csp.data.ValidationResponse;

/**
 * @author Karthikeyan N
 *
 */
public interface CSPService {

	List<ValidationResponse> validateAndProcess(MultipartFile file);

}
