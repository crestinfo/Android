val requestQueue = Volley.newRequestQueue(this)
val stringRequest = object:StringRequest(Request.Method.POST, URL,
    object:Response.Listener<String>() {
    	fun onResponse(response:String) {
        	try
            {
                val jsonObject = JSONObject(response)
            }
            catch (e:JSONException) {
            	e.printStackTrace()
            }
        }
    }, object:Response.ErrorListener() {
    	fun onErrorResponse(error:VolleyError) {
        	Log.e("Error", "onErrorResponse: " + error)
        }
}) {
protected val params:Map<String, String>
@Throws(AuthFailureError::class)
  get() {
    val param = HashMap()
    param.put("user_id", "45")
    return param
  }
}
stringRequest.setRetryPolicy(DefaultRetryPolicy(0, 4, 4.0.toFloat()))
requestQueue.add(stringRequest)
