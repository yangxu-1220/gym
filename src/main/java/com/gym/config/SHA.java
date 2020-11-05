package com.gym.config;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {
	public static final String KEY_SHA = "SHA"; 
	  public String getResult(String inputStr)
	  {
	    BigInteger sha =null;
//	    System.out.println(inputStr);
	    byte[] inputData = inputStr.getBytes(); 
	    try {
	       MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA); 
	       messageDigest.update(inputData);
	       sha = new BigInteger(messageDigest.digest()); 
	    } catch (Exception e) {}
	    return sha.toString();
	  }
}
