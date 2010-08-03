class PasswordMaker_HashUtils 
{
	/*
	* Convert a raw string to an arbitrary string encoding
	* Set trim to false for keeping leading zeros
	*/
	String rstr2any(String input, String encoding, int trim) 
	{
		int divisor = strlen(encoding);
		String remainders = new String();
		
		/* Convert to an array of 16-bit big-endian values, forming the dividend */
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
		/*
		* Repeatedly perform a long division. The binary array forms the dividend,
		* the length of the encoding is the divisor. Once computed, the quotient
		* forms the dividend for the next step. We stop when the dividend is zero.
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
		
		/* Convert the remainders to the output string */
		String output = "";
		int o = new int();
		for(o = count(remainders) - 1; o >= 0; o--)
			output += encoding[remainders[o]];
		
		return output;
	}
};
