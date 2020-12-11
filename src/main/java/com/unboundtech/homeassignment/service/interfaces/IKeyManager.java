package com.unboundtech.homeassignment.service.interfaces;

import java.security.KeyPair;
import java.security.Signature;

public interface IKeyManager {
	public KeyPair keyGenerator();
	public Signature sign(KeyPair keyPair, byte[] data);
	public boolean verify(KeyPair keyPair, byte[] data, Signature sig);

}
