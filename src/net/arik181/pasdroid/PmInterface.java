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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.arik181.pasdroid.HashBuilder;


public class Interface extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setGenerateButton();
    }

    public void setGenerateButton()
    {
    	Button generateButton = (Button)findViewById(R.id.generate);

    	generateButton.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			TextView generatedPasswordView = (TextView)findViewById(R.id.generated_password);
    			EditText masterPasswordView = (EditText)findViewById(R.id.password);
    			EditText usedTextView = (EditText)findViewById(R.id.used_text);
    			
    	    	HashBuilder hashGenerator = new HashBuilder();
    	    	String masterPassword = masterPasswordView.getText().toString();
    	    	String usedText = usedTextView.getText().toString();
    	    	String password = "MD5: " + hashGenerator.getHash(masterPassword, usedText);
    			generatedPasswordView.setText(password);
    		}
    	});
    }
}