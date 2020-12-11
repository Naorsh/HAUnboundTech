package com.unboundtech.homeassignment.service;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.stereotype.Service;

import com.unboundtech.homeassignment.service.interfaces.IKeyManager;

@Service
public class KeyManager implements IKeyManager{
	public KeyPair keyGenerator() {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048); 
	        return kpg.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalServerErrorException("error generating key pair", e);
		}
	}
	
	public byte[] sign(KeyPair keyPair, byte[] data) {
		Signature sig;
		byte[] signatureBytes;
		try {
			sig = Signature.getInstance("NONEwithRSA");
			sig.initSign(keyPair.getPrivate());
	        sig.update(data);
	        signatureBytes = sig.sign();
	        System.out.println("Signature:" + Base64.getEncoder().encode(signatureBytes));//new BASE64Encoder().encode(signatureBytes));
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
			throw new InternalServerErrorException("error signing data", e);
		}
        return signatureBytes;
	}
	
	public boolean verify(KeyPair keyPair, byte[] data, byte[] byteSig) {
		Signature sig;
		try {
			sig = Signature.getInstance("NONEwithRSA");
			sig.initVerify(keyPair.getPublic());
	        sig.update(data);
	        return sig.verify(byteSig);
		} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new InternalServerErrorException("error verifying data", e);
		}
	}
	
}
