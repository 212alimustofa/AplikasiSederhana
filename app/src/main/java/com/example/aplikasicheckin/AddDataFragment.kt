package com.example.aplikasicheckin


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aplikasicheckin.api.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_data.*
import kotlinx.android.synthetic.main.fragment_add_data.view.*


/**
 * A simple [Fragment] subclass.
 */
class AddDataFragment : Fragment(), LocationListener {
    var mView: View? = null
    var locationManager: LocationManager? = null
    private var permission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    var lat: String? = ""
    var lon: String? = ""
    var networkHelper: NetworkHelper? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_add_data, container, false)
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        networkHelper = NetworkHelper()

        if(isHaveStorageAndCameraPermission()){
            try {
                locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, 22)
                Handler().postDelayed({
                    try {
                        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
                    }
                    catch (e: Exception){
                        e.printStackTrace()
                    }
                }, 2000)
            }
        }

        mView?.bt_tambah_data?.setOnClickListener({
            mView?.add_data_progress_bar?.visibility = View.VISIBLE
            mView?.content?.visibility = View.GONE
            addData(et_nama_lokasi.text.toString(), et_keterangan_lokasi.text.toString(), et_kontributor.text.toString(), lat, lon)
        })
        return mView
    }

    private fun isHaveStorageAndCameraPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val fineLocation = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            val coarseLocation = activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            return !(fineLocation != PackageManager.PERMISSION_GRANTED || coarseLocation != PackageManager.PERMISSION_GRANTED)
        }
        else {
            return true
        }

    }

    private fun addData(nama: String, keterangan: String, kontributor: String, lat: String?, lon: String?) {
        networkHelper?.getRestAPI()?.addDataLokasi("simpan", nama, keterangan, lat, lon, kontributor)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        {
                            _ -> mView?.add_data_progress_bar?.visibility = View.GONE
                            Toast.makeText(activity, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        },
                        {
                            e ->
                            mView?.add_data_progress_bar?.visibility = View.GONE
                            Toast.makeText(activity, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                        }
                )
    }

    override fun onLocationChanged(location: Location?) {
        lat = location?.latitude.toString()
        lon = location?.longitude.toString()
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {
        Toast.makeText( activity, "Gps Enabled", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderDisabled(p0: String?) {
        Toast.makeText( activity, "Gps Disables", Toast.LENGTH_SHORT).show()
    }
}
