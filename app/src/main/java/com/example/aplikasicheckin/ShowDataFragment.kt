package com.example.aplikasicheckin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aplikasicheckin.api.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_show_data.view.*


/**
 * A simple [Fragment] subclass.
 */
class ShowDataFragment : Fragment() {
    var networkHelper: NetworkHelper? = null
    var adapter: DataAdapter? = null
    var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_show_data, container, false)
        mView?.data_list?.layoutManager = LinearLayoutManager(activity)
        mView?.data_list?.itemAnimator = DefaultItemAnimator()
        adapter = DataAdapter()
        mView?.data_list?.adapter = adapter
        networkHelper = NetworkHelper()
        getAllData()
        return mView
    }

    private fun getAllData() {
        networkHelper?.getRestAPI()?.getAllLokasi()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        {
                            datas ->
                            mView?.show_data_progressbar?.visibility = View.GONE
                            adapter?.datas?.addAll(datas.data!!)
                            adapter?.notifyDataSetChanged()
                        },
                        {
                            e -> Toast.makeText(activity, "Gagal menampilkan data", Toast.LENGTH_SHORT).show()
                            Log.e("Failed to get data", e?.message)
                        }
                )
    }

}