package com.unboundtech.homeassignment.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unboundtech.homeassignment.datatype.request.StoreDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;
import com.unboundtech.homeassignment.datatype.response.BaseResponse;
import com.unboundtech.homeassignment.datatype.response.GenerateKeyRes;
import com.unboundtech.homeassignment.datatype.response.GetKeysListRes;
import com.unboundtech.homeassignment.datatype.response.StoreDataRes;
import com.unboundtech.homeassignment.datatype.response.VerifyDataRes;
import com.unboundtech.homeassignment.service.interfaces.IMainService;

@RequestMapping("api/unbound")
@RestController
public class MainController {
	
	@Autowired
	private IMainService mainService;
	
	@PostMapping("key/generate")
	public ResponseEntity<GenerateKeyRes> generateKeyPair() throws NoSuchAlgorithmException {
		GenerateKeyRes response = mainService.generateKeyPair();
		response.setStatus("OK");
		response.setMessage("key generated successfully");
		return new ResponseEntity<GenerateKeyRes>(response ,HttpStatus.OK);
	}
	
	@DeleteMapping("key/{key}")
	public ResponseEntity<BaseResponse> deleteKey(@PathVariable("key") Integer key) {
		return mainService.deleteKey(key);
	}
	
	@PostMapping("data/save")
	public ResponseEntity<StoreDataRes> storeData(@RequestBody StoreDataReq request) {
		StoreDataRes response = new StoreDataRes();
		response.setStatus("OK");
		return new ResponseEntity<StoreDataRes>(response ,HttpStatus.OK);
	}
	
	@PostMapping("data/verify")
	public ResponseEntity<VerifyDataRes> verifyData(@RequestBody VerifyDataReq request) {
		VerifyDataRes response = new VerifyDataRes();
		response.setStatus("OK");
		return new ResponseEntity<VerifyDataRes>(response ,HttpStatus.OK);
	}
	
	@GetMapping("keys")
	public ResponseEntity<GetKeysListRes> getAllKeys() {
		GetKeysListRes response = mainService.getAllKeys();
		response.setStatus("OK");
		return new ResponseEntity<GetKeysListRes>(response ,HttpStatus.OK);
	}
	
}
