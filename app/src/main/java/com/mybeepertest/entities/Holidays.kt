package com.mybeepertest.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "holidays") // by default same as class name
class Holidays {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var canBeCarryforWorded: Boolean? = null
    var date: Date? = null
}