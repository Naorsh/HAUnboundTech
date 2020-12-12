package com.unboundtech.homeassignment.service;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.stereotype.Service;

import com.unboundtech.homeassignment.consts.Consts;
import com.unboundtech.homeassignment.service.interfaces.IKeyManager;

@Service
public class KeyManager implements IKeyManager{
	public KeyPair keyGenerator() {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance(Consts.RSA);
			kpg.initialize(2048); 
	        return kpg.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(Consts.ERROR_GENERATING_KEY_MESSAGE, e);
		}
	}
	
	public byte[] sign(KeyPair keyPair, byte[] data) {
		Signature sig;
		byte[] signatureBytes;
		try {
			sig = Signature.getInstance(Consts.NONE_WITH_RSA_ALGO);
			sig.initSign(keyPair.getPrivate());
	        sig.update(data);
	        signatureBytes = sig.sign();
	        return signatureBytes;
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(Consts.ERROR_SIGNING_DATA_MESSAGE, e);
		}
	}
	
	public boolean verify(KeyPair keyPair, byte[] data, byte[] byteSig) {
		Signature sig;
		try {
			sig = Signature.getInstance(Consts.NONE_WITH_RSA_ALGO);
			sig.initVerify(keyPair.getPublic());
	        sig.update(data);
	        return sig.verify(byteSig);
		} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalServerErrorException(Consts.ERROR_VERIFYING_DATA_MESSAGE, e);
		}
	}
	
}
