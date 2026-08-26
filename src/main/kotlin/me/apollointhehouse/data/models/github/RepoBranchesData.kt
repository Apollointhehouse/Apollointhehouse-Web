package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class RepoBranchesData(val repository: RepositoryDetail)