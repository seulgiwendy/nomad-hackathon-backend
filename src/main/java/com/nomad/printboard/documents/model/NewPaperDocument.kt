package com.nomad.printboard.documents.model

data class NewPaperDocument(
        val title: String = "",
        val duedate: String = "",
        val urgent: Boolean = false,
        val filesrc: String = ""
)