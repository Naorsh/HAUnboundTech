package com.unboundtech.homeassignment.service;

import java.security.KeyPair;
import java.util.Base64;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.unboundtech.homeassignment.datatype.request.SignDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;
import com.unboundtech.homeassignment.datatype.response.BaseResponse;
import com.unboundtech.homeassignment.datatype.response.GenerateKeyRes;
import com.unboundtech.homeassignment.datatype.response.GetKeysListRes;
import com.unboundtech.homeassignment.datatype.response.SignDataRes;
import com.unboundtech.homeassignment.datatype.response.VerifyDataRes;
import com.unboundtech.homeassignment.service.interfaces.IKeyManager;
import com.unboundtech.homeassignment.service.interfaces.IMainService;

@Service
public class MainService implements IMainService {
	
	@Autowired
	private IKeyManager keyManager;
	
	private static Integer i;
	private static HashMap<Integer,KeyPair> rsaKeyPair;
	
	static {
		i=1;
		rsaKeyPair = new HashMap<Integer, KeyPair>();
	}
	
	private ResponseEntity<?> createBadInputRes(){
		BaseResponse response = new BaseResponse();
		response.setStatus("Failed");
		response.setMessage("Key does not exist");
		return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> generateKeyPair(){
		GenerateKeyRes response = new GenerateKeyRes();
		KeyPair pair = keyManager.keyGenerator();
		response.setKeyId(i);
		rsaKeyPair.put(i++, pair);
		response.setStatus("OK");
		response.setMessage("key generated successfully");
		return new ResponseEntity<GenerateKeyRes>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteKey(Integer key) {
		if(keyIsExist(key)){
			BaseResponse response = new BaseResponse();
			rsaKeyPair.remove(key);
			response.setStatus("OK");
			response.setMessage("key deleted successfully");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		}
		return createBadInputRes();
	}
	
	private Boolean keyIsExist(Integer key) {
		if(!rsaKeyPair.containsKey(key)) {
			return false;
		}
		return true;
	}
	
	@Override
	public ResponseEntity<?> signData(SignDataReq request) {
		if(keyIsExist(request.getKeyId())){
			SignDataRes response = new SignDataRes();
			response.setSignature(keyManager.sign(rsaKeyPair.get(request.getKeyId()), Base64.getDecoder().decode(request.getData())));
			response.setStatus("OK");
			return new ResponseEntity<SignDataRes>(response,HttpStatus.OK);
		}
		return createBadInputRes();
	}

	@Override
	public ResponseEntity<?> verifyData(VerifyDataReq request) {
		if(keyIsExist(request.getKeyId())){
			VerifyDataRes response = new VerifyDataRes();
			response.setVerified(keyManager.verify(rsaKeyPair.get(request.getKeyId()), Base64.getDecoder().decode(request.getData()), Base64.getDecoder().decode(request.getSignature())));
			response.setStatus("OK");
			return new ResponseEntity<VerifyDataRes>(response,HttpStatus.OK);
		}
		return createBadInputRes();
	}
	
	@Override
	public ResponseEntity<?> getAllKeys() {
		GetKeysListRes response = new GetKeysListRes();
		if(rsaKeyPair.size()==0){
			return new ResponseEntity<GetKeysListRes>(response, HttpStatus.NO_CONTENT);
		}
		response.setKeys(rsaKeyPair.keySet());
		response.setStatus("OK");
		return new ResponseEntity<GetKeysListRes>(response, HttpStatus.OK);
	}

	

}
