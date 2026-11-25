package me.apollointhehouse.ui.html

import kotlinx.html.FlowContent

var FlowContent.fxAction: String
    get() = attributes["fx-action"] ?: ""
    set(value) {
        attributes["fx-action"] = value
    }

var FlowContent.extFxPush: Boolean
    get() = "ext-fx-push" in attributes
    set(value) {
        if (value) attributes["ext-fx-push"] = "" else attributes.remove("ext-fx-push")
    }

var FlowContent.fxSwap: String
    get() = attributes["fx-swap"] ?: ""
    set(value) {
        attributes["fx-swap"] = value
    }

var FlowContent.fxTarget: String
    get() = attributes["fx-target"] ?: ""
    set(value) {
        attributes["fx-target"] = value
    }
