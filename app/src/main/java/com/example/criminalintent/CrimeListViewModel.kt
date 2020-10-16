package com.example.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()
    init {
        for (i in 0 until 100) {
            val crime = Crime()
            crime.requiredPolice=0;
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            if(i==1 || i in 4..6)
            {
                crime.requiredPolice=1;
            }
            crimes += crime
        }


    }
}