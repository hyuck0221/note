package com.hshim.note.security.model

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security")
class SecurityProperties {
    lateinit var cors: List<String>
}