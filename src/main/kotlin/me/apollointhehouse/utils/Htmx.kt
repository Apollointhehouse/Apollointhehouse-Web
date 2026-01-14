package me.apollointhehouse.utils

import kotlinx.html.FlowContent

var FlowContent.hxBoost: Boolean
    get() = attributes["hx-boost"].toBoolean()
    set(value) {
        attributes["hx-boost"] = value.toString()
    }

var FlowContent.hxGet: String
    get() = attributes["hx-get"] ?: ""
    set(value) {
        attributes["hx-get"] = value
    }

var FlowContent.hxSwap: String
    get() = attributes["hx-swap"] ?: ""
    set(value) {
        attributes["hx-swap"] = value
    }

var FlowContent.hxTarget: String
    get() = attributes["hx-target"] ?: ""
    set(value) {
        attributes["hx-target"] = value
    }

var FlowContent.hxTrigger: String
    get() = attributes["hx-trigger"] ?: ""
    set(value) {
        attributes["hx-trigger"] = value
    }
