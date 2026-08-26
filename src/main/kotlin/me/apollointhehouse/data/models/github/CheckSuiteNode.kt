package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class CheckSuiteNode(val workflowRun: WorkflowRunNode? = null)