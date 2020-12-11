package com.unboundtech.homeassignment.datatype.response;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetKeysListRes extends BaseResponse{
	private Set<Integer> keys;
}
