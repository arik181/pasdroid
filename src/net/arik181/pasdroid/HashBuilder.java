/*
 * pasdroid - An open source implementation of Password Maker for the
 * Android platform, written in Java
 * 
 * Copyright (C) 2010 Devin Quirozoliver
 * http://arik181.github.com/pasdroid
 * http://github.com/arik181/pasdroid
 * http://passwordmaker.org/
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 *  
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Written by Devin Quirozoliver <arik181@hotmail.com>
 * 
 * Original Command Line Client Written by Miquel Burns 
 * <miquelfire@gmail.com> and Eric H. Jung
*/

package net.arik181.pasdroid;

import java.security.MessageDigest;
import java.security.*;
import java.math.BigInteger;

/**
 * @author arik181
 *
 */
public class HashBuilder {

	public HashBuilder()
	{
		
	}
	
	public String GeneratePassword(
	        String algorithm,
	        String masterPassword,
	        String url,
	        String user,
	        String mod,
	        Boolean leet,
	        int leetlevel,
	        int length,
	        String charset,
	        String prefix,
	        String suffix,
	        Boolean trim,
	        Boolean sha256_bug 
	        )
	{
		return null;
	}

	public String getHash(String masterPassword, String url)
	{
		try 
		{
		   MessageDigest md5 = MessageDigest.getInstance("MD5");
	       md5.reset();
	       String keystring = masterPassword + url;
	       md5.update(keystring.getBytes(), 0, keystring.length()); 
	       
	       String hash = new BigInteger(1,md5.digest()).toString(16);
	       return hash.toString();
		}
		catch (NoSuchAlgorithmException ex)
		{
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password;
		HashBuilder hb = new HashBuilder();
		password = hb.getHash("abc","abc");
		System.out.println(password);
	}
}
