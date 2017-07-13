<h2>Image Uploading To Local Server in Android Using Retrofit.</h2>
<p>In this example, we will focus on uploading image to server using Retrofit Library.</4>

<h2>Demonstration</h2>

<h4>Very Quick Response Rather Volley.</h4> 
<p>Retrofit is denitely the better alternative to volley in terms of ease of use, performance, extensibility and other things. It is a type-­safe REST client for Android built by Square. Using this tool android developer can make all network stuff much more easier.<p>
         

<h4>Some Steps to Your Code.</h4> 


<h4>Step 1 : Open build.gradle and add Retrofit, Gson dependencies.</h4> 
<p><code>compile 'com.google.code.gson:gson:2.6.2'</code><p>
<p><code>compile 'com.squareup.retrofit2:retrofit:2.0.2'</code><p>
<p><code>compile 'com.squareup.retrofit2:converter-gson:2.0.2'</code><p>

         
<h4>Step 2 : Since we are working with network operations we need to add INTERNET permissions in AndroidManifest.xml file</h4>




<pre>
    <div class="container">
        <div class="block two first">
			<p><code>"<uses-permission android:name="android.permission.INTERNET"/>"</code></p>
            </div>
        </div>
    </div>
</pre>



<p>And As well we are getting the image from the internal/external storage of our device you need to add the following Permission.</p>




<pre>
    <div class="container">
        <div class="block two first">
			<p><code>"<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>"</code></p>
            </div>
        </div>
    </div>
</pre>



<h4>Step 3 :  Define the Endpoints In ApiInterface</h4>
<p>Each endpoint specifies an annotation of the HTTP method (GET, POST, etc.) and the parameters of this method can also have special annotations (@Query, @Path, @Body etc.).</p>

<p>Take a look to other annotations:</p>

<p><code>@Path – variable substitution for the API endpoint. For example user id will be swapped for{id} in the URL endpoint.</code></p>
<p><code>@Query – specifies the query key name with the value of the annotated parameter.</code></p>
<p><code>@Body – payload for the POST call</code></p>
<p><code>@Header – specifies the header with the value of the annotated parameter</code></p>



<h4>Output:</h4> 
<p><code>You will get the image in the Server Folder where ever you had specified in the server.</code></p>

<h4>Conclusion:</h4> 
<p>To sum it up, Retrofit is one of the best tool for working with network requests. I have considered most common cases which can be helpful in your projects. Happy coding!</p>


<p>If you need more help <a href="http://www.crestinfotech.com/contact-us/" target="_blank">contact us</a> 
or email us on <a href="mailto:raxit4u2@gmail.com">raxit4u2@gmail.com</a></p>
