package com.scarry.makakumbha.firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class AuthClass {

    private val auth = FirebaseAuth.getInstance()
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private val interval: Long = 10000 // 10seconds
    private val fastestInterval: Long = 100 // 100 milliseconds
    private lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private val requestPermissionCode = 999
    private lateinit var database:FirebaseDatabase
    // Function to handle user login
    fun initialize(context:Context,activity: Activity)
    {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        mLocationRequest = LocationRequest.create()
        checkForPermission(context, activity)
        startLocationUpdates(context)
    }

    fun loginUser(email: String, password: String, callback: (FirebaseUser?, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    callback(user, null) // Login successful, pass user data to callback
                } else {
                    callback(null, task.exception?.message) // Login failed, pass error message to callback
                }
            }
    }

    // Function to handle user registration
    fun registerUser(email: String, password: String, callback: (FirebaseUser?, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    callback(user, null) // Registration successful, pass user data to callback
                } else {
                    callback(null, task.exception?.message) // Registration failed, pass error message to callback
                }
            }
    }
    fun sendtoDatabase(context: Context,activity: Activity,First:String,Last:String) {
        initialize(context, activity)
    }
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation
            locationResult.lastLocation?.let { locationChanged(it) }
            latitude = locationResult.lastLocation!!.latitude
            longitude = locationResult.lastLocation!!.longitude
        }
    }

    private fun startLocationUpdates(context: Context) {
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = interval
        mLocationRequest.fastestInterval = fastestInterval

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()
        val settingsClient = LocationServices.getSettingsClient(context)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()!!)
    }

    private fun checkForPermission(context: Context,activity: Activity) {
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            return
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                requestPermissionCode)
            return
        }
    }
    fun locationChanged(location: Location) {
        mLastLocation = location
        longitude = mLastLocation.longitude
        latitude = mLastLocation.latitude

            database =
                FirebaseDatabase.getInstance("https://makakumbha-default-rtdb.asia-southeast1.firebasedatabase.app/")
            val usermap = HashMap<String, String>()
//                usermap["location"] = binding.LocationText.text.toString()
            usermap["latitude"] = ((latitude * 100000 % 10)).toInt().toString()
//                -25.441093
            usermap["longitude"] = ((longitude * 100000 % 10)).toInt().toString()
//                -81.8164835
            var dbref: DatabaseReference =
                database.reference.child("users").child("0").child("location")
            dbref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    dbref.setValue(usermap)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}