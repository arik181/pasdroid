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
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Math;
import java.math.BigInteger;
import java.lang.Byte;
import java.io.*;

/**
 * @author arik181
 *
 */
public class PmHashUtils {

	/**
	* Convert a raw string to an arbitrary string encoding
	* Set trim to false for keeping leading zeros
	*/
	String rstr2any(String input, String encoding, boolean trim) 
	{
        /* Section 1 */
        char [] inputArray = input.toCharArray();
        char [] encodingArray = encoding.toCharArray();

        int sizeOfDividend = (int)Math.ceil((double)input.length()/2);
		int divisor = encoding.length();
		ArrayList remainders = new ArrayList();
        ArrayList dividend = new ArrayList(sizeOfDividend);

        int j,k;
        for (j=1;j<sizeOfDividend;++j)
        {
            k = ((inputArray[j*2] << 8) | (inputArray[j*2+1]));
            System.out.println(k);
            dividend.set(j,j);
        }

        int full_length = (int)Math.ceil(input.length() * 8 / (Math.log(encoding.length()) / (Math.log(2))));

        /* Section 2 */
        /* Begin conversion of input to remainders */
        
        if (trim)
        {
            while(!(dividend.isEmpty()))
            {
                ArrayList quotient = new ArrayList(input.length() * 2);
                int x = 0;
                int q = 0;
                int l = 0;
                for (l=0;l<dividend.size();++l)
                {
                    x = (x << 16);
                    //x = (x << 16) + dividend.get(l);
                    q = (int)Math.floor(x/divisor);
                    x -= (q * divisor);
                    if (quotient.isEmpty() || q > 0)
                        quotient.add(q);
                }
                remainders.add(x);
                dividend = quotient;
            }
        }
        else
        {
            /*
            int m = 0;
            for (m=0;m<full_length;++m)
            {
                int [] quotient = new char [input.size() * 2];
                List quotientList = asList(quotient);
                int x = 0;
                int q = 0;
                int l = 0;
                while (!(dividendList.isEmpty()))
                {
                    x = (x << 16) + dividend[l];
                    q = floor(x/divisor);
                    x -= q * divisor;
                    if (quotientList.isempty() || q > 0)
                        quotient[quotientList.size()] = q;
                    ++l;
                }
                remainders[l] = x;
                dividend = quotient;
            }
            */
        }

        /* Section 3 */
        //String output = remainders.toString();

        /*
        int n = 0;
        for(n = remainders.size()-1;n>=0;--n)
            output += encodingArray[remainders[n]];
        */
        String output = "";
		return output;
        
	}
};
