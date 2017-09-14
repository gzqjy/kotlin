package tk.danielgong.criminalintent

import java.util.*

/**
 * Created by gongzhq on 2017/9/14.
 */

object CrimeLab {
    var mCrimes = mutableListOf<Crime>()
    init {
        for (i in 0..100) {
            val crime = Crime()
            crime.mTitle = "Crime #$i"
            crime.mSolved = (i % 2 == 0)
            mCrimes.add(crime)
        }
    }
    fun getCrime(id: UUID): Crime? {
        for (crime in mCrimes) {
            if (crime.mId.equals(id)) {
                return crime
            }
        }
        return null
    }
}