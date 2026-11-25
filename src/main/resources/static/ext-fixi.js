function applyTitle(html) {
    const tpl = document.createElement("template")
    tpl.innerHTML = html
    const t = tpl.content.querySelector("title")
    if (!t) return html
    document.title = t.textContent
    t.remove()
    return tpl.innerHTML
}

document.addEventListener("fx:after", (evt) => {
    const elt = evt.target
    if (!elt.hasAttribute?.("ext-fx-push")) return
    const cfg = evt.detail.cfg

    cfg.text = applyTitle(cfg.text)

    const v = elt.getAttribute("ext-fx-push")
    const url = (v && v !== "true") ? v : elt.getAttribute("href")
    history.pushState({
        fixi: true,
        fragment: cfg.response.url,
        target: elt.getAttribute("fx-target"),
    }, "", url)
})

window.addEventListener("popstate", async (evt) => {
    const s = evt.state
    if (s?.fixi) {
        const target = document.querySelector(s.target)
        const resp = await fetch(s.fragment, { headers: { "FX-Request": "true" } })
        target.innerHTML = applyTitle(await resp.text())
    } else {
        location.reload()
    }
})