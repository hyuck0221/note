package com.hshim.note.database.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringSetConverter : AttributeConverter<MutableSet<String>, String> {
    private val delimiter = ","

    override fun convertToDatabaseColumn(attribute: MutableSet<String>?): String? =
        attribute?.joinToString(delimiter)

    override fun convertToEntityAttribute(dbData: String?): MutableSet<String> =
        dbData?.split(delimiter)?.map { it.trim() }?.toMutableSet() ?: mutableSetOf()
}