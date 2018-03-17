package com.nomad.printboard.documents.security

data class FormLoginDocument(
        val loginEmail: String = "",
        val password: String = ""
)