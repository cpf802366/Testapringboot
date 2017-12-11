package com.cpf.boottest.util.commen;

import java.util.UUID;

public class CreateNewKey {

	 
	public static synchronized String createId() {

		return  UUID.randomUUID().toString();
	}



}