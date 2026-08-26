package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class WorkflowRunNode(val databaseId: Long, val workflow: WorkflowNode)