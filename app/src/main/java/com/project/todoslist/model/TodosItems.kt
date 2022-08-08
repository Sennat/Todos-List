package com.project.todoslist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodosItems(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable
