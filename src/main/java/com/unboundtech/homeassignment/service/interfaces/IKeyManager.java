package com.unboundtech.homeassignment.service.interfaces;

import java.security.KeyPair;

public interface IKeyManager {
	public KeyPair keyGenerator();
	public byte[] sign(KeyPair keyPair, byte[] data);
	public boolean verify(KeyPair keyPair, byte[] data, byte[] byteSig);
}
