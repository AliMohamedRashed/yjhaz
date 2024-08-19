package com.ali.advancedtask.feature.home.data.model.response.trending_sellers


import com.google.gson.annotations.SerializedName

data class Information(
    @SerializedName("activity")
    val activity: Any,
    @SerializedName("driving_image")
    val drivingImage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("identity_number")
    val identityNumber: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("tax_record")
    val taxRecord: String,
    @SerializedName("vehicle_image")
    val vehicleImage: String,
    @SerializedName("vehicle_registration")
    val vehicleRegistration: String,
    @SerializedName("vehicle_tablet_image")
    val vehicleTabletImage: String
)