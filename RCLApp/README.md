<h2>Delete and Add Log in Android.</h2>
<p>The Example is based on How to Delete and Add Log into Android Call log history.</4>

<h2>Demonstration</h2>

<p>You may have used the Log history for getting the call you have recently deal with.To make it more easier you can delete and add new log in history without any call.<p>
         

<h4>Some Steps to Your Code.</h4> 


<h4>Step 1 : Need to Check the permission.</h4> 
<p><code>uses-permission android:name="android.permission.READ_CONTACTS"</code><p>
<p><code>uses-permission android:name="android.permission.WRITE_CONTACTS"</code><p>
<p><code>uses-permission android:name="android.permission.WRITE_CALL_LOG"</code><p>


<h4>Step 3 :  Method to Delete the log</h4>
<p><code>contentResolver.delete(UriCalls, CallLog.Calls.NUMBER + "=?", new String[]{strNum});</code></p>

<h4>Step 3 :  Method to Add the log</h4>
<p><code> resolver.insert(CallLog.Calls.CONTENT_URI, values);</code></p>

<h4>Screenshots:</h4>

<img src="/RCLApp/src.png" width="200" height="400" />       <img src="/RCLApp/src2.png" width="200" height="400" /> 

<h4>Output:</h4> 
<p><code>ADD : You will get the new Number added to your call log.</code></p>
<p><code>DELETE : You will get the Number is deleted from call log history.</code></p>

<h4>Conclusion:</h4> 
<p>To sum it up,It is for to make operation dymanically to remove call history for specific number and adding new number to history. Happy coding!</p>


<p>If you need more help <a href="http://www.crestinfotech.com/contact-us/" target="_blank">contact us</a> 
or email us on <a href="mailto:raxit4u2@gmail.com">raxit4u2@gmail.com</a></p>
