/**
 * 
 */
package com.robobank.csp.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Karthikeyan N
 *
 */
public interface CSPService {

	void validateAndProcess(MultipartFile file);

}
