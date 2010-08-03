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


import java.security.MessageDigest;
import java.security.*;
import java.math.BigInteger;
import java.lang.*;
import java.lang.Integer;
import java.lang.Byte;
import java.io.*;

/**
 * @author arik181
 *
 */
public class PmMD5 implements PmHashBuilder {

    private String algorithm = "MD5";
    private String user = null;
    private String mod = null;
    private String charset = null;
    private String prefix = null;
    private String suffix = null;

    private int length = 0;
    private int leetlevel = 0;

	/**
	 * @param user
     * @param mod
	 */
    public void setOptions(String user, String mod)
    {
        this.user = user;
    }

	/**
	 * @param length
	 */
    public void setTrim(int length) // Default 8 cha
    {
        this.length = length;
    }

	/**
	 * @param charset
	 */
    public void setCharSet(String charset)
    {
        this.charset = charset;
    }

	/**
	 * @param leetlevel
	 */
    public void setLeet(int leetlevel) // Leet is off by defaul
    {
        this.leetlevel = leetlevel;
    }

	/**
	 * @param prefix
	 */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

	/**
	 * @param suffix
	 */
    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

	/**
	 * @param masterPassword
     * @param url
     * @return 
	 */
	public String getHash(String masterPassword, String url)
	{
		try 
		{
           String keystring = masterPassword + url;
		   MessageDigest md5 = MessageDigest.getInstance(this.algorithm);
           byte hash[] = md5.digest(keystring.getBytes());
	       String hashString = new BigInteger(1,hash).toString(16);

           return hashString;
		}
		catch (NoSuchAlgorithmException ex)
		{
			System.out.println(ex);
            return "No Such Algorithm";
		}
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println(this.algorithm);
            return "Exception";
        }
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		String password;
		PmMD5 hb = new PmMD5();
		password = hb.getHash("abc","abc");
		System.out.println(password);

        System.exit(0);
	}
}
