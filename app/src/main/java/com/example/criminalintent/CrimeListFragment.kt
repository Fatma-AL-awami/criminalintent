package com.example.criminalintent

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.system.Os.bind
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.Instant

class CrimeListFragment : Fragment() {

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = null
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }

    companion object {

        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.crime_list_fragment, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return view

    }

    private fun updateUI() {
        val crimes = crimeListViewModel.crimes
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter

    }

    //Activity1

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var crime: Crime
        val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(crime: Crime) {

            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.setText(SimpleDateFormat("EEEE, MMM, dd, yyyy").format(this.crime.date)).toString()
           // dateTextView.text = this.crime.date.toString()
            // solvedImageView.visibility = if (crime.isSolved) {
            //   View.VISIBLE
            //  } else {
            //     View.GONE
            //  }
        }


        override fun onClick(v: View) {

            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    //tActivity2
    private inner class CrimeHolder_danger(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var crime: Crime
        val titleTextView_danger: TextView = itemView.findViewById(R.id.crime_title_danger)
        val dateTextView_danger: TextView = itemView.findViewById(R.id.crime_date_danger)
        val button: Button = itemView.findViewById(R.id.button)

        fun bind(crime: Crime) {

            this.crime = crime
            titleTextView_danger.text = this.crime.title
            dateTextView_danger.setText(SimpleDateFormat("EEEE, MMM, dd, yyyy").format(this.crime.date)).toString()
           // dateTextView_danger.text = this.crime.date.toString()


        }
    }


   // Adapter
    private inner class CrimeAdapter(var crimes: List<Crime>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

       //const varible
        val nodangerCrime = 0
        val dangerCrime = 1

        override fun getItemCount() = crimes.size

        override fun getItemViewType(position: Int): Int {
            var typeValue = if (crimes[position].requiredPolice == 1)
                return dangerCrime
            else
                return nodangerCrime
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewHolder: RecyclerView.ViewHolder = when (viewType) {
                nodangerCrime -> CrimeHolder(layoutInflater.inflate(R.layout.list_item_crime, parent, false))

                else -> CrimeHolder_danger(layoutInflater.inflate(R.layout.danger_crime, parent, false))

            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val crime = crimes[position]
            if (crime.requiredPolice == 1) {
                (holder as CrimeHolder_danger).bind(crime)

            } else {

                (holder as CrimeHolder).bind(crime)
            }


        }


    }}

