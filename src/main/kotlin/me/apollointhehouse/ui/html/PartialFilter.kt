package me.apollointhehouse.ui.html

import kotlinx.html.*
class PartialFilter(val downstream: TagConsumer<String>) : TagConsumer<String> by downstream {
    private var isInsideMain = false
    private var isMainTag = false

    override fun onTagStart(tag: Tag) {
        if (tag.tagName == "main") {
            isMainTag = true
            isInsideMain = true
            return
        }
        isMainTag = false
        if (isInsideMain) {
            downstream.onTagStart(tag)
        }
    }

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        if (isMainTag) return
        if (isInsideMain) {
            downstream.onTagAttributeChange(tag, attribute, value)
        }
    }

    override fun onTagEnd(tag: Tag) {
        if (tag.tagName == "main") {
            isInsideMain = false
            isMainTag = false
            return
        }
        if (isInsideMain) {
            downstream.onTagEnd(tag)
        }
    }

    override fun onTagContent(content: CharSequence) {
        if (isInsideMain) {
            downstream.onTagContent(content)
        }
    }

    companion object {
        fun TagConsumer<String>.filterPartials(): TagConsumer<String> =
            PartialFilter(this)
    }
}