package net.arik181.pasdroid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class PasswordMaker {

	String hashAlgorithm = new String("md5");
	String key = new String("");
	String data = new String("");
	String whereToUseL33t = new String("n");
	int l33tLevel = 0;
	int passwordLength = 8;
	String charset = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_-+={}|[]\\:\";\'<>?,./");
	String prefix = new String();
	String suffix = new String();
	boolean trim = true;
	boolean sha256_bug = true;
	byte [] databytes = null;
	int divisor = 0;
	int full_length = 0;

	public PasswordMaker()
	{
		
	}
	public PasswordMaker(String newkey, String newdata)
	{
		key = newkey;
		data = newdata;
	}
	public PasswordMaker(String algorithm, String newkey, String newdata)
	{
		hashAlgorithm = algorithm;
		key = newkey;
		data = newdata;
	}
	public void setHashAlgorithm(String algorithm)
	{
		hashAlgorithm = algorithm;
	}
	public void setKey(String newkey)
	{
		key = newkey;
	}
	public void setData(String newdata)
	{
		data = newdata;
	}
	public void setWhereToUseL33t(String where)
	{
		whereToUseL33t = where;
	}
	public void setL33tLevel(int newlevel)
	{
		l33tLevel = newlevel;
	}
	public void setPasswordLength(int newlength)
	{
		passwordLength = newlength;
	}
	public void selectCharSet(int setindex)
	{
		// TODO NYI select from an array of char sets
	}
	public void setPrefix(String newprefix)
	{
		prefix = newprefix;
	}
	public void setSuffix(String newsuffix)
	{
		suffix = newsuffix;
	}
	public void setTrim(boolean newtrim)
	{
		trim = newtrim;
	}
	public String generatePassword()
	{
		// Never *ever, ever* allow the charset's length<2 else
		// the hash algorithms will run indefinitely
		if (charset.length() < 2)
		    System.exit(0);

		String password = new String();
		int count = 0;
		String tkey = new String(key);// Copy of the key so we don't interfere with it.
		int linenum = 0;
		
		while ((password.length() < passwordLength) && (count < 1000)) {
			
		    
		    key = (count++ > 0) ? tkey + "\n" + count : tkey;
		    
		    try 
			{
		    	MessageDigest md5 = MessageDigest.getInstance("MD5");
		    	String keystring = key;
		    	String datastring = data;
		    	md5.update(keystring.getBytes());
		    	md5.update(datastring.getBytes());
		    	byte[] hash = md5.digest();
		    	databytes = hash;
		    	data = hash.toString();
			}
			catch (NoSuchAlgorithmException ex)
			{
				System.out.println(ex);
				System.exit(0);
			}
	         
		    ++linenum;
		  
		    divisor = charset.length();
		    ++linenum;
		    ArrayList<Integer> remainders = new ArrayList<Integer>(0);
	    	ArrayList<Integer> dividend = new ArrayList<Integer>(0);
		    	
	    	Double m = Math.ceil((double)(databytes.length/ 2));
	    	Integer l = m.intValue();
	    	int j=0;
		    for (j=0;j<l;++j)
		    	dividend.add(0);

		    int i=0;
		    for(i=0; i<dividend.size(); ++i)
		        dividend.set(i, (0xff00 & (databytes[i * 2] << 8)) | ( 0x00ff & databytes[(i * 2) + 1]));

		    full_length = (int)Math.ceil((float)databytes.length * 8 / (Math.log(charset.length()) / Math.log(2)));

		    if (trim) {
		        while(dividend.size() > 0) {
		        	
		        	ArrayList<Integer> quotient = new ArrayList<Integer>(0);
		            Integer x = new Integer(0);
		            Integer q = new Integer(0);
		            for(i=0; i < dividend.size(); ++i) {
		            	x = (x << 16) + dividend.get(i);
		            	q = x / divisor;
		            	x -= q * divisor;
		            	if((quotient.size() > 0) || (q > 0))
		                    quotient.add(q);
		            }
		            remainders.add(x);
		            dividend = quotient;
		        }
		        
		    } else {
		    	int k=0;
		        for(k=0; k<full_length; ++k) {
		        	ArrayList<Integer> quotient = new ArrayList<Integer>(0);
		            Integer x = new Integer(0);
		            Integer q = new Integer(0);
		            for(i=0; i<dividend.size(); ++i) {
		                x = (x << 16) + dividend.get(i);
		                q = x / divisor;
		                x -= q * divisor;
		                if((quotient.size() > 0) || (q > 0))
		                    quotient.add(q);
		            }
		            remainders.add(k, x);
		            dividend = quotient;
		        }
		    }

		    password = "";
		    for(i=(remainders.size())-1;i>= 0;i--)
		        password += charset.charAt(remainders.get(i));
		     
		    if (password.length() == 0) break; 
		    
		}
		return password.substring(0,passwordLength);
	}
	
	public static void main(String[] args)
	{
		PasswordMaker pm = new PasswordMaker("abc", "abc");
		System.out.println(pm.generatePassword());
	}
}

