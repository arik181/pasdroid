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

import java.lang.*;
import java.lang.Integer;
import java.math.BigInteger;
import java.lang.Byte;
import java.io.*;

/**
 * @author arik181
 *
 */
public class PmHashUtils {

	/** This will be removed */
	String test(String input)
	{
		return input;
	}

	/**
	* Convert a raw string to an arbitrary string encoding
	* Set trim to false for keeping leading zeros
	*/
	String rstr2any(String input, String encoding, int trim) 
	{
		int divisor = strlen(encoding);
		String remainders = new String();
		
		/* Convert to an array of 16-bit big-endian values, 
		 * forming the dividend */
		// pad this
		//dividend = array_pad(array(), ceil(strlen(input) / 2), 0);
		int ceiling = new int();
		ceiling = ceil(strlen(input)/2);

		String dividend = new String();

		int n = new int();
		for(n=0; n<ceiling; ++n)
		{
			dividend += '0';	
		}

		int m = new int();
		String inp = input; // Because Miquel is a lazy twit and didn't want to do a search and replace
		for(m = 0; m < count(dividend); ++m) 
		{
			dividend[m] = (ord(inp[m * 2]) << 8) | ord(inp[m * 2 + 1]);
		}
		
		full_length = ceil((float)strlen(input) * 8 / (log(strlen(encoding)) / log(2)));
		/**
		* Repeatedly perform a long division. The binary array 
		* forms the dividend, the length of the encoding is the 
		* divisor. Once computed, the quotient forms the dividend
		* for the next step. We stop when the dividend is zero.
		* All remainders are stored for later use.
		*/
		int i = new int();
		int j = new int();
		if (trim) {
			String quotient = new String();
			while(count(dividend) > 0) {
				x = 0;
				for(i = 0; i < count(dividend); i++) {
					x = (x << 16) + dividend[i];
					q = floor(x / divisor);
					x -= q * divisor;
					if(count(quotient) > 0 || q > 0)
						quotient[count(quotient)] = q;
				}
				remainders[count(remainders)] = x;
				dividend = quotient;
			}
		} else 
		{
			for(j = 0; j < full_length; j++) {
				quotient = Array();
				x = 0;
				for(i = 0; i < count(dividend); i++) {
					x = (x << 16) + dividend[i];
					q = floor(x / divisor);
					x -= q * divisor;
					if(count(quotient) > 0 || q > 0)
						quotient[count(quotient)] = q;
				}
				remainders[j] = x;
				dividend = quotient;
			}
		}
		
		/** Convert the remainders to the output string */
		String output = "";
		int o = new int();
		for(o = count(remainders) - 1; o >= 0; o--)
			output += encoding[remainders[o]];
		
		return output;
	}
};
