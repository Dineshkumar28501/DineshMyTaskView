package com.sgs.mytaskview.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "loginDatabase")
data class LoginModel (
    val username : String,
    val password : String,
    val mobile : String,
    val first : String,
    val last : String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    ): Parcelable