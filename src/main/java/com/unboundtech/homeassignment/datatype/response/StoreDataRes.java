package com.unboundtech.homeassignment.datatype.response;

import java.security.Signature;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StoreDataRes extends BaseResponse{
	private Signature Signature;
}
