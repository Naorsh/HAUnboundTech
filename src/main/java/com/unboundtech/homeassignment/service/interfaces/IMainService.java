package com.unboundtech.homeassignment.service.interfaces;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;

import com.unboundtech.homeassignment.datatype.request.StoreDataReq;
import com.unboundtech.homeassignment.datatype.request.VerifyDataReq;
import com.unboundtech.homeassignment.datatype.response.BaseResponse;
import com.unboundtech.homeassignment.datatype.response.GenerateKeyRes;
import com.unboundtech.homeassignment.datatype.response.GetKeysListRes;
import com.unboundtech.homeassignment.datatype.response.StoreDataRes;
import com.unboundtech.homeassignment.datatype.response.VerifyDataRes;

public interface IMainService {
	
	public GenerateKeyRes generateKeyPair() throws NoSuchAlgorithmException;
	public ResponseEntity<BaseResponse> deleteKey(Integer key);
	public StoreDataRes storeData(StoreDataReq request);
	public VerifyDataRes verifyData(VerifyDataReq request);
	public GetKeysListRes getAllKeys();
}
