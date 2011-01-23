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
import android.text.ClipboardManager;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import net.arik181.pasdroid.PasswordMaker;


public class Interface extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner save_password_spinner = (Spinner)findViewById(R.id.save_password);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.save_password_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        save_password_spinner.setAdapter(adapter);
        setShowButton();
        setHideButton();
    }

    public void setShowButton()
    {
    	Button showButton = (Button)findViewById(R.id.generate);

    	showButton.setOnClickListener(new View.OnClickListener() 
    	{
    		@Override
    		public void onClick(View v) 
    		{
    			TextView generatedPasswordView = (TextView)findViewById(R.id.generated_password);
    			EditText masterPasswordView = (EditText)findViewById(R.id.password);
    			EditText usedTextView = (EditText)findViewById(R.id.used_text);
    			String masterPassword = masterPasswordView.getText().toString();
    			String usedText = usedTextView.getText().toString();
    			
    	    	PasswordMaker pm = new PasswordMaker(masterPassword, usedText);
    	    	String password = pm.generatePassword();

    	    	generatedPasswordView.setTextSize(24);
    	    	generatedPasswordView.setText(password);
    		}
    	});
    }
    
    public void setCopyButton()
    {
    	Button copyButton = (Button)findViewById(R.id.copy);

    	copyButton.setOnClickListener(new View.OnClickListener() 
    	{
    		@Override
    		public void onClick(View v) 
    		{
    			EditText masterPasswordView = (EditText)findViewById(R.id.password);
    			EditText usedTextView = (EditText)findViewById(R.id.used_text);
    			String masterPassword = masterPasswordView.getText().toString();
    			String usedText = usedTextView.getText().toString();
    			
    			ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    	    	
    	    	PasswordMaker pm = new PasswordMaker(masterPassword, usedText);
    	    	String password = pm.generatePassword();
    	    	
    	    	cm.setText(password);
    		}
    	});
    }
    
    public void setHideButton()
    {
    	Button hideButton = (Button)findViewById(R.id.hide);
    	hideButton.setOnClickListener(new View.OnClickListener() 
    	{
    		@Override
    		public void onClick(View v) 
    		{
    			TextView generatedPasswordView = (TextView)findViewById(R.id.generated_password);
    			generatedPasswordView.setText("");
    		}
    	});
    }
    
    // Menu creation
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
    	return true;
    }
    
    // Menu selection
    //public boolean onOptionsItemSelected(MenuItem item)
    //{
    	//return true;
    //}
}
