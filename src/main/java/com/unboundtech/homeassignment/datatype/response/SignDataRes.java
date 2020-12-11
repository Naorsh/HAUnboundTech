package com.unboundtech.homeassignment.datatype.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SignDataRes extends BaseResponse{
	private byte[] Signature;
}
