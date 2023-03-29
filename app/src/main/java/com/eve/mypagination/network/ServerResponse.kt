package com.eve.mypagination.network

data class ServerResponse(
    val info: Info,
    val results: List<CharacterModel>,
) {
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String = "",
        val prev: String = "",
    )
}

data class CharacterModel(
    val gender: String,
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
)
