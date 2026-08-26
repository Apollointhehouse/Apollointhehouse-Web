package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class CommitTarget(val oid: String, val checkSuites: CheckSuiteConnection? = null)