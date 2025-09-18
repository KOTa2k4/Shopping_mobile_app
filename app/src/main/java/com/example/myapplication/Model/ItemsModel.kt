package com.example.myapplication.Model

import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable
import java.lang.invoke.TypeDescriptor

data class ItemsModel(
    var title: String="",
    var description: String="",
    var picUrl: ArrayList<String> =ArrayList(),
    var price: Double=0.0,
    var rating: Double=0.0,
    var numberInCart:Int=0,
    var showRecomend: Boolean=false,
    var categoryId: String=""
): Serializable
