package com.example.criminalintent

import java.text.SimpleDateFormat
import java.util.*

data class Crime(val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false,
                 var requiredPolice:Int=0)

{


}