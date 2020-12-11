package com.unboundtech.homeassignment.datatype.request;

import lombok.Data;

@Data
public class StoreDataReq {
	private Integer keyId;
	private byte[] Data;
}
