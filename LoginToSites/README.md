<h2>Social Media Integration in Android.</h2>
<p>The Application demo is demonstating to integrate social media with your application.</4>

<h2>Execution</h2>

<h4>Gradle Library</h4> 
<p>As we are working on Facebook,Linkedin and twitter we need following dependancies.<p>

<h4>Facebook</h4> 
<p><code>compile 'com.facebook.android:facebook-android-sdk:4.15.0</code></p>
<p><code>you need to register the app in facebook developers.</code></p>
<p><code>generate SHA hash.</code></p>

<h4>Code For SHA hash</h4>
<p><code> 
		try {
        	    PackageInfo info = getPackageManager().getPackageInfo(
            	        "your package",
                	    PackageManager.GET_SIGNATURES);
            	for (android.content.pm.Signature signature : info.signatures) {
                	MessageDigest md = MessageDigest.getInstance("SHA");
                	md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage(), e);
        }
</code></p>


<p><code>put it in the SHA section in the Facebook App settings and you are ready to test it.</code></p>



<h4>Twitter</h4>
<p><code>compile('com.twitter.sdk.android:twitter:2.3.2@aar') {
        transitive = true;
    }</code>
</p>
<p><code>For twitter you have to register the app in twitter developer, you will get the  "TWITTER_KEY" "TWITTER_SECRET", user this in you code.</code></p>


<h4>Linkedin</h4>
<p><code>for linked in you need to download the sdk from "https://developer.linkedin.com/docs/android-sdk-auth".</code></p>
<p><code>compile project(':linkedin-sdk')</code></p> 
<p><code>you will found the sdk integrated with your by selecting the project section of your application.</code></p> 
            
<h3>How Does It Works</h3>
<p>Here is an example demonstrating the use of Social Media Integration. There ae three controls that make you login to  facebook,linkedin and twitter.</p>




<p>If you need more help <a href="http://www.crestinfotech.com/contact-us/" target="_blank">contact us</a> 
or email us on <a href="mailto:raxit4u2@gmail.com">raxit4u2@gmail.com</a></p>
