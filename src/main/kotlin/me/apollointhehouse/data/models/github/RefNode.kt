package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class RefNode(val name: String, val target: CommitTarget? = null)