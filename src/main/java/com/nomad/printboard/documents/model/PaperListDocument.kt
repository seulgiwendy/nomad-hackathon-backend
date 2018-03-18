package com.nomad.printboard.documents.model

data class PaperListDocument(
        val title: String = "",
        val duedate: String = "",
        val filesrc: String = "",
        val urgent: Boolean,
        val registeredTime: String = ""
)