package com.heechan.membeder.model.data.schedule

import com.google.gson.annotations.SerializedName
import com.heechan.membeder.model.data.auth.User

data class ScheduleAddReq(
    val name : String,      // 일정 이름
    val description : String,   // 일정 설명
    val complete : Boolean, // 일정 완료 여부
    @SerializedName("deadline")
    val deadLine : String,  // 일정 마감일
)