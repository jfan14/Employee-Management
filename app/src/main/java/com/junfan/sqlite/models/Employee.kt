package com.junfan.sqlite.models

import java.io.Serializable

data class Employee(
    var id: Int,
    var name: String,
    var email: String
): Serializable

