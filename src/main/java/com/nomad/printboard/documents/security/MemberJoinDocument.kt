package com.nomad.printboard.documents.security

data class MemberJoinDocument(
        val memberEmail: String = "",
        val password: String = "",
        val name: String = ""
)