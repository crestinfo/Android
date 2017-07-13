<h2>Reading from OTP from SMS in Android.</h2>
<p>In this example, we will focus on reading the sms automatically when we need it, and responding when we receive the message.</4>

<h2>Execution</h2>

<h4>No Need Of Extra Libraries</h4> 
<p>Nowadays, Applications usually use SMS texts for authentication purpose, like OTP , where a message is sent by the service-provider and it is automatically read by the application, and can verify it. This flow helps user to save a lot of app switching from application to messenger app and then entering the authentication text to app again.<p>
         
<h4>How Does It Works</h4>
<p>We will need a Single Broadcast Receiver for our Purpose, a listener to connect our receiver and activity and an Activity or Fragment according to your app for responding to the callback when the message is received.After that you get the OTP in your Activity or In Fragment and you can now verify it or save any where else as you wanted to make your app conveniance.</p>

<h4>Output:</h4> 
<p><code>It will revice the SMS which is been sent by the provider and with the help of Reciever the OTP is Shown in Text as well in Toast.</code></p>

<img src="/GetAutoOTP/screen_main.jpg" width="200" height="400" />

<p>If you need more help <a href="http://www.crestinfotech.com/contact-us/" target="_blank">contact us</a> 
or email us on <a href="mailto:raxit4u2@gmail.com">raxit4u2@gmail.com</a></p>
