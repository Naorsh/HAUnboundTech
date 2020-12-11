package com.unboundtech.homeassignment.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unboundtech.homeassignment.datatype.request.SignDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;
import com.unboundtech.homeassignment.service.interfaces.IMainService;

@RequestMapping("api/unbound")
@RestController
public class MainController {
	
	@Autowired
	private IMainService mainService;
	
	@PostMapping("key/generate")
	public ResponseEntity<?> generateKeyPair() throws NoSuchAlgorithmException {
		return mainService.generateKeyPair();
	}
	
	@DeleteMapping("key/{key}")
	public ResponseEntity<?> deleteKey(@PathVariable("key") Integer key) {
		return mainService.deleteKey(key);
	}
	
	@PostMapping("data/sign")
	public ResponseEntity<?> signData(@RequestBody SignDataReq request) {
		return mainService.signData(request);
	}
	
	@PostMapping("data/verify")
	public ResponseEntity<?> verifyData(@RequestBody VerifyDataReq request) {
		return mainService.verifyData(request);
	}
	
	@GetMapping("keys")
	public ResponseEntity<?> getAllKeys() {
		return mainService.getAllKeys();
	}
	
}
