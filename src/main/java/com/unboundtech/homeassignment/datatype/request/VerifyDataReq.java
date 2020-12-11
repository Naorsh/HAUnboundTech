package com.unboundtech.homeassignment.datatype.request;

import lombok.Data;

@Data
public class VerifyDataReq {
	private Integer keyId;
	private String data;
	private String signature;
}
