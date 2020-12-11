package com.unboundtech.homeassignment.datatype.request;

import java.security.Signature;

import lombok.Data;

@Data
public class VerifyDataReq {
	private Integer keyId;
	private byte[] Data;
	private Signature Signature;
}
