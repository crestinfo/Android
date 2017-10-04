<h1>Demonstration of Image Capture from Camera With Actual Quality image.</h1>


<h3>Here is the solution to get the full size image captured from camera.</h3>

<p>As the onActivityResult() metho d gives you only the thumbnail of the image you will not get the actual size image taken from the Camera.</p>
<p>To do so you need to make a file before capturing the camera and save the file to external storage and then get the image taken.</p>
<p>Some times the bitmap will not fit into your heap size to resolve the issue write these two properties in Manifest.xml file</p>

<code>android:hardwareAccelerated="false"</code>

<code>android:largeHeap="true"</code>

<p>Don't forgot to give permissions </p>

<code>"<uses-permission android:name="android.permission.CAMERA"/>"</code>

<code>"<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>"</code>

<code>"<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>"</code>

<h2>Conclusion</h2>

<p>There are many solution I had research and I had got the best solution here.Capturing image from camera and storing it to external storage is not an good aproach but it helps you to get the much better Output.Try it.</p>

<p>Passion To Code</p>