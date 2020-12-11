package com.unboundtech.homeassignment.service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.unboundtech.homeassignment.datatype.request.StoreDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;
import com.unboundtech.homeassignment.datatype.response.BaseResponse;
import com.unboundtech.homeassignment.datatype.response.GenerateKeyRes;
import com.unboundtech.homeassignment.datatype.response.GetKeysListRes;
import com.unboundtech.homeassignment.datatype.response.StoreDataRes;
import com.unboundtech.homeassignment.datatype.response.VerifyDataRes;
import com.unboundtech.homeassignment.service.interfaces.IKeyManager;
import com.unboundtech.homeassignment.service.interfaces.IMainService;

@Service
public class MainService implements IMainService {
	
	@Autowired
	private IKeyManager keyManager;
	
	private static Integer i;
	private static HashMap<Integer,KeyPair> rsaKeyPair;
	private static HashMap<KeyPair,String> rsaDataPair;
	private static HashMap<KeyPair,String> rsaSignaturePair;
	
	static {
		i=1;
		rsaKeyPair = new HashMap<Integer, KeyPair>();
		rsaDataPair = new HashMap<KeyPair, String>();
		rsaSignaturePair = new HashMap<KeyPair, String>();
	}

	@Override
	public GenerateKeyRes generateKeyPair() throws NoSuchAlgorithmException {
		GenerateKeyRes response = new GenerateKeyRes();
		KeyPair pair = keyManager.keyGenerator();
		response.setKeyId(i);
		rsaKeyPair.put(i++, pair);
		return response;
	}

	@Override
	public ResponseEntity<BaseResponse> deleteKey(Integer key) {
		BaseResponse response = new BaseResponse();
		if(keyIsExist(key)){
			KeyPair keyPair = rsaKeyPair.get(key);
			rsaSignaturePair.remove(keyPair);
			rsaDataPair.remove(keyPair);
			rsaKeyPair.remove(key);
			response.setStatus("OK");
			response.setMessage("key deleted successfully");
			return new ResponseEntity<BaseResponse>(response ,HttpStatus.OK);
		}
		response.setStatus("Failed");
		response.setMessage("Key does not exist");
		return new ResponseEntity<BaseResponse>(response ,HttpStatus.BAD_REQUEST);
	}
	
	private Boolean keyIsExist(Integer key) {
		if(!rsaKeyPair.containsKey(key)) {
			return false;
		}
		return true;
	}
	
	@Override
	public StoreDataRes storeData(StoreDataReq request) {
		keyManager.sign(rsaKeyPair.get(request.getKeyId()), request.getData());
		return null;
	}

	@Override
	public VerifyDataRes verifyData(VerifyDataReq request) {
		keyManager.verify(rsaKeyPair.get(request.getKeyId()), request.getData(), request.getSignature());
		return null;
	}
	
	@Override
	public GetKeysListRes getAllKeys() {
		GetKeysListRes response = new GetKeysListRes();
		response.setKeys(rsaKeyPair.keySet());
		return response;
	}

	

}
