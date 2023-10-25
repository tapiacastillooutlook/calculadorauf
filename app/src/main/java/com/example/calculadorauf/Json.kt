package com.example.calculadorauf

data class Json(
    val autor: String,
    val codigo: String,
    val nombre: String,
    val serie: List<Serie>,
    val unidad_medida: String,
    val version: String
)