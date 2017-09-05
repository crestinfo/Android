<h1>Android Service Exapmle</h1>


<h4>The demo code helps you how to run service in android and how to stop it when App goes in background or when app will be closed !</h4>

<h3>You need to stop the service in onPuase() method of base activity if you want the service to stop when app goes background and onResume() method when app again come forground</h3>


<h3>Code To Start Service</h3>

<p><code>public Intent intentService;</code></p>
<p><code>intentService = new Intent(this, ForegroundService.class)</code></p>
<p><code>startService(intentService)</code></p>

<h3>Code To Stop Service</h3>

<p><code>stopService(intentService)</code></p>



<p>If you need more help <a href="http://www.crestinfotech.com/contact-us/" target="_blank">contact us</a> 
or email us on <a href="mailto:raxit4u2@gmail.com">raxit4u2@gmail.com</a></p>


