package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class RepoBranchesResponse(val data: RepoBranchesData)