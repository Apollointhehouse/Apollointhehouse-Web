package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class CheckSuiteConnection(val nodes: List<CheckSuiteNode>)