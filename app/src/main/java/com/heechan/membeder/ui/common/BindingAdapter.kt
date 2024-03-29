package com.heechan.membeder.ui.common

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.heechan.membeder.R
import com.heechan.membeder.model.data.schedule.Schedule
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.ui.SingletonObject
import com.heechan.membeder.ui.common.scheduleList.ScheduleListAdapter
import com.heechan.membeder.ui.main.HomeNoTeamFragment
import com.heechan.membeder.ui.main.HomeTeamFragment
import com.heechan.membeder.ui.main.MainActivity
import com.heechan.membeder.ui.team.caleander.TodoDeleteListAdapter
import com.heechan.membeder.ui.team.caleander.TodoEditListAdapter
import com.heechan.membeder.util.State
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, isVisible : Boolean) {
    view.visibility = if(isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bindRadioButtonValue")
fun bindRadioButtonValue(rb : RadioButton, value : Int) {
    rb.isChecked = rb.id == value
}

@BindingAdapter("bindTeamScheduleList")
fun bindTeamScheduleList(view: RecyclerView, scheduleList: List<Schedule>) {
    Log.d("bindTeamScheduleList", scheduleList.toString())
    view.apply {
        adapter = ScheduleListAdapter(scheduleList)
        adapter!!.notifyDataSetChanged()
    }
}
@BindingAdapter("bindTeamScheduleListEdit")
fun bindTeamScheduleListEdit(view: RecyclerView, scheduleList: List<Schedule>) {
    Log.d("bindTeamScheduleList", scheduleList.toString())
    view.apply {
        adapter = TodoEditListAdapter(scheduleList)
        adapter!!.notifyDataSetChanged()
    }
}
@BindingAdapter("bindTeamScheduleListDelite")
fun bindTeamScheduleListDelite(view: RecyclerView, scheduleList: List<Schedule>) {
    Log.d("bindTeamScheduleList", scheduleList.toString())
    view.apply {
        adapter = TodoDeleteListAdapter(scheduleList)
        adapter!!.notifyDataSetChanged()
    }
}

@BindingAdapter("bindSwipeOnRefresh")
fun bindSwipeOnRefresh(swiperRefresh : SwipeRefreshLayout, job : () -> Unit){
    swiperRefresh.setOnRefreshListener(job)
}

@BindingAdapter("bindSwipeRefreshing")
fun bindSwipeRefreshing(swipeRefresh: SwipeRefreshLayout, status: State?) {
    if (status != null) swipeRefresh.isRefreshing = (status == State.LOADING)
}

@BindingAdapter("bindSrcUri")
fun bindSrcUri(imageView: ImageView, imageUri : Uri?){
    if(imageUri == null)
        return

    imageView.setImageURI(imageUri)
}

@BindingAdapter("bindSrcUrl")
fun bindSrcUrl(imageView: ImageView, url : String?){
    if (url.isNullOrEmpty()) return

    Glide.with(imageView).load(url).into(imageView)
}

@BindingAdapter("bindDateFormat")
fun bindDateFormat(textView: TextView, date : String){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(date.split("T")[0], formatter)
    textView.text = "${localDate.monthValue}-${localDate.dayOfMonth}"
}

//@BindingAdapter("bindMainFragmentTeam")
//fun bindMainFragmentTeam(layout : FrameLayout, teamList : List<Team>?){
//    Log.d("bindMainFragmentTeam", "실행")
//    val fragmentManager = (layout.context as MainActivity).supportFragmentManager
//
//    val tran = fragmentManager.beginTransaction()
//    val fragment = if (teamList.isNullOrEmpty()) {
//        HomeNoTeamFragment()
//    } else {
//        HomeTeamFragment()
//    }
//
//    tran.replace(R.id.fl_main, fragment)
//    tran.commit()
//}
