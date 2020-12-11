package com.unboundtech.homeassignment.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.unboundtech.homeassignment.datatype.request.SignDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;

public interface IMainService {
	
	public ResponseEntity<?> generateKeyPair();
	public ResponseEntity<?> deleteKey(Integer key);
	public ResponseEntity<?> signData(SignDataReq request);
	public ResponseEntity<?> verifyData(VerifyDataReq request);
	public ResponseEntity<?> getAllKeys();
}
